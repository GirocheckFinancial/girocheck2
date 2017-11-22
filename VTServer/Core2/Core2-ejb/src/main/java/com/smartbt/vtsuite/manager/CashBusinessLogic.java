
/*

 @Author Roberto Rodriguez
 robertoSoftwareEngineer@gmail.com

 */
package com.smartbt.vtsuite.manager;

import com.smartbt.vtsuite.util.CoreTransactionUtil;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.enums.ResultMessage;
import com.smartbt.girocheck.servercommon.jms.JMSManager;
import com.smartbt.girocheck.servercommon.model.Transaction;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.vtsuite.util.TransactionalException;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomHost;
import java.io.Serializable;
import java.util.Map;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import static com.smartbt.vtsuite.util.CoreTransactionUtil.*;

@TransactionManagement(value = TransactionManagementType.BEAN)
public class CashBusinessLogic extends AbstractCommonBusinessLogic {

    @Override
    public void process(DirexTransactionRequest request, Transaction transaction) throws Exception {

        NomHost bankHost = (NomHost) request.getTransactionData().get(ParameterName.HOSTNAME);

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CashBusinessLogic] Send answer to TERMINAL", null);

        if (request.getTransactionData().containsKey(ParameterName.DLDATASCAN) || request.getTransactionData().containsKey(ParameterName.DLDATASWIPE)) {
            if (request.getTransactionData().get(ParameterName.DLDATASCAN) != null || request.getTransactionData().get(ParameterName.DLDATASWIPE) != null) {
                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CashBusinessLogic] data ParameterName.DLDATASCAN received value: " + request.getTransactionData().get(ParameterName.DLDATASCAN), null);
                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CashBusinessLogic] data ParameterName.DLDATASWIPE received value: " + request.getTransactionData().get(ParameterName.DLDATASWIPE), null);
            } else {
                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CashBusinessLogic] data from DLDATASCAN or DLDATASWIPE is null", null);
            }
        }

        DirexTransactionResponse response;
        TransactionType originalTransaction = request.getTransactionType();

        String correlationId = request.getCorrelation();
        Queue currentQueue = JMSManager.get().getCoreOutQueue();

        if (request.getTransactionData().containsKey(ParameterName.OPERATION)) {
            transaction.setOperation((String) request.getTransactionData().get(ParameterName.OPERATION));
        }

        try {

            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CashBusinessLogic] Calculating fee for cash from amount", null);

            UtilOperations.feeCalculator(request, transaction);

            Map personalInfoRequestMap;
            if (!originalTransaction.equals(TransactionType.CARD_RELOAD_WITH_DATA)) {

                CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CashBusinessLogic] Getting personal info from PersonalInfoFromIDReader()", null);
                DirexTransactionResponse personalInfoResponse = UtilOperations.getPersonalInfoFromIDReader(request);

                personalInfoRequestMap = personalInfoResponse.getTransactionData();

            } else {
                personalInfoRequestMap = request.getTransactionData();
            }

            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CashBusinessLogic] Personal info from PersonalInfoFromIDReader() done", null);

            request.getTransactionData().putAll(personalInfoRequestMap);

            //------ PROCESS PERSONAL INFO  ------
            UtilOperations.processPersonalInfo(transaction, request, personalInfoRequestMap);

            //--- SEND TO COMPLIANCE ------
            DirexTransactionResponse complianceResponse = sendToCompliance(request, transaction);

            if (complianceResponse != null) {
                response = complianceResponse;
            }

            //----------  IDEOLOGY ------------------
            if (bankHost == NomHost.FISS) {
                response = sendToIdeology(request, transaction);

                if (response.getTransactionData().containsKey(ParameterName.IDEOLOGY_RESULT_ID)) {
                    Integer ideologyResultId = (Integer) response.getTransactionData().get(ParameterName.IDEOLOGY_RESULT_ID);
                    transaction.setIdeologyResultId(ideologyResultId);
                }
            }

            //----------  CARD VALIDATON ------------------ 
            request.setTransactionType(TransactionType.CARD_VALIDATION);
            request.getTransactionData().put(TransactionType.TRANSACTION_TYPE, originalTransaction);

            response = callHost(request, bankHost, transaction);
            //important
            request.getTransactionData().putAll(response.getTransactionData());

            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CashBusinessLogic] Recived message from " + bankHost + " Validation", null);

            String estimatedPostingTime = response.getResultMessage();

            //Send response to the terminal of reload or new cardload 
            sendAnswerToTerminal(originalTransaction, response.getResultCode(), estimatedPostingTime, correlationId);

            //-------------- WAIT CONFIRMATION FROM TERMINAL -------------
            DirexTransactionRequest confirmationRequest = null;

            confirmationRequest = receiveMessageFromFront(TransactionType.TERMINAL_CONFIRMATION, correlationId, transaction);

            correlationId = confirmationRequest.getCorrelation();
            request.setCorrelation(correlationId);
            currentQueue = JMSManager.get().getCore2OutQueue();

            //------ CREATE TECNICARD_CONFIRMATION SUBTRANSACTION ------
            addSuccessfulSubTransaction(transaction, TransactionType.TERMINAL_CONFIRMATION);

            //-----  SEND TO GENERIC HOST -----------    GENERIC_HOST_CARD_LOAD
            request.setTransactionType(TransactionType.CARD_LOAD);

            response = callHost(request, bankHost, transaction);

            sendAnswerToTerminal(TransactionType.TERMINAL_CONFIRMATION, response.getResultCode(), estimatedPostingTime, correlationId);

            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CashBusinessLogic] Before consuming choiceNotifyPayment " + transaction.getSub_Transaction().size(), null);
            transaction.setResultCode(ResultCode.SUCCESS.getCode());
            transaction.setResultMessage(ResultMessage.SUCCESS.getMessage());

            CoreTransactionUtil.persistTransaction(transaction);

            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CashBusinessLogic] Transaction finished successfully. ", null);
            //
            //     EXCEPTIONAL CASES
            //
        } catch (TransactionalException transactionalException) {
            System.out.println("*********** TransactionalException ************");

            if (transactionalException.getResponse() != null) {
                // this is when  !response.approved()
                System.out.println("Sending subTransactionFailed 1");
                CoreTransactionUtil.subTransactionFailed(transaction, transactionalException.getResponse(), currentQueue, correlationId, bankHost);
            } else {
                //this is when caughting Exception
                System.out.println("Sending subTransactionFailed 2 -> transactionalException.getTransactionType()" + transactionalException.getTransactionType());
                CoreTransactionUtil.subTransactionFailed(transaction, currentQueue, correlationId, transactionalException.getTransactionType(), transactionalException.getMessage(), transactionalException.getResultCode(), bankHost);
            }

        } catch (Exception e) {
            System.out.println("!!!!!!!!!!!!!!!!  UNEXPECTED  EXCEPTION  !!!!!!!!!!!!!!!!!!!");
            e.printStackTrace();
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] UNEXPECTED Exception ", null);

            String msg = e.getMessage();
            if (msg != null) {
                transaction.setResultMessage((msg.length() > 254) ? msg.substring(0, 254) : msg);
            } else {
                transaction.setResultMessage("Message Error was printed, Please check the server logs. ");
            }

            CoreTransactionUtil.subTransactionFailed(transaction, currentQueue, correlationId, request.getTransactionType(), e.getMessage(), ResultCode.FAILED, bankHost);
        }
    }

    private DirexTransactionRequest receiveMessageFromFront(TransactionType transactionType, String correlationId, Transaction transaction) throws Exception {

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "", null);
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "", null);
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CashBusinessLogic] Waiting " + transactionType + " from FRONT. ", null);

        DirexTransactionResponse response;
        Message message = null;

        // in error case
        ResultCode errorCode = null;
        Long waitTime = null;
        Queue queue = null;

        switch (transactionType) {

            case TERMINAL_CONFIRMATION:
                errorCode = ResultCode.TERMINAL_CONFIRMATION_TIME_EXCEED;
                waitTime = TERMINAL_CONFIRMATION_WAIT_TIME;
                queue = JMSManager.get().getCore2InQueue();
                break;
        }

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CashBusinessLogic] Receiving... ", null);
        message = JMSManager.get().receive(queue, correlationId, waitTime);

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CashBusinessLogic] Received " + transactionType, null);

        if (message == null || !(message instanceof ObjectMessage)) {
            // throw new TransactionalException(ResultCode.getFromHost(host), transactionType, e);
            response = DirexTransactionResponse.forException(errorCode, ResultMessage.FAILED, transactionType + " not received. ", "");
            response.setTransactionType(transactionType);
            CoreTransactionUtil.subTransactionFailed(transaction, response, JMSManager.get().getCoreOutQueue(), correlationId, null);
            throw new Exception();
        }

        ObjectMessage objectMessage = (ObjectMessage) message;
        Serializable s = objectMessage.getObject();
        DirexTransactionRequest request = (DirexTransactionRequest) s;

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CashBusinessLogic] Message received. ", null);
        return request;
    }

}