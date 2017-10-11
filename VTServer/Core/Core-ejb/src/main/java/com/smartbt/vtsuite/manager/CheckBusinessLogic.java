/*

 @Author Roberto Rodriguez
 robertoSoftwareEngineer@gmail.com

 */
package com.smartbt.vtsuite.manager;

import com.smartbt.girocheck.servercommon.enums.CheckStatus;
import com.smartbt.vtsuite.util.CoreTransactionUtil;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.enums.ResultMessage;
import com.smartbt.girocheck.servercommon.jms.JMSManager;
import com.smartbt.girocheck.servercommon.manager.CheckBlacklistRuleManager;
import com.smartbt.girocheck.servercommon.model.Transaction;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomHost;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Map;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.sql.rowset.serial.SerialBlob;
import static com.smartbt.vtsuite.util.CoreTransactionUtil.*;
import com.smartbt.vtsuite.util.TransactionalException; 

@TransactionManagement(value = TransactionManagementType.BEAN)
public class CheckBusinessLogic extends AbstractCommonBusinessLogic {

    @Override
    public void process(DirexTransactionRequest request, Transaction transaction) throws Exception {
        String checkId = "";
        String correlationId = request.getCorrelation();
        
        String lastName = (String)request.getTransactionData().get(ParameterName.LAST_NAME);
        
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CheckBusinessLogic] process:: lastName = " + lastName, null);
        boolean idScanSuccess = true;

        DirexTransactionResponse response;
        TransactionType originalTransaction = request.getTransactionType();
        Queue currentQueue = JMSManager.get().getCoreOutQueue();

        //This variables will control the behavior in Exceptional cases 
        boolean sendCertegyReverseRequestIfFails = false;
        boolean saveCheckIfFails = true;
        boolean sendWestechSendSingleICL = false;

        try {

            if (!originalTransaction.equals(TransactionType.CARD_RELOAD_WITH_DATA)) {

                try {
                    DirexTransactionResponse personalInfoResponse = getPersonalInfoFromIDReader(request);

                    Map driverLicenseInfo = personalInfoResponse.getTransactionData();
                    request.getTransactionData().putAll(driverLicenseInfo);

                    idScanSuccess = true;
                } catch (TransactionalException e) {
                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CheckBusinessLogic] Driver License INELEGIBLE", null);
                    idScanSuccess = false;
                }
            }

            transaction.setSingle(Boolean.FALSE);

            if (request.getTransactionData().containsKey(ParameterName.OPERATION)) {
                transaction.setOperation((String) request.getTransactionData().get(ParameterName.OPERATION));
            }

            if (request.getTransactionData().containsKey(ParameterName.MICR)) {
                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CheckBusinessLogic] Value of MICR from POS: " + request.getTransactionData().get(ParameterName.MICR), null);
            }

            //-------  go to HOST WEST TECH (checkAuth) -------
            request.setTransactionType(TransactionType.WESTECH_CHECK_PROCESS);

            response = sendMessageToHost(request, NomHost.WESTECH, WESTECH_HOST_WAIT_TIME, transaction);

            //-----  WAIT CHECK INFO MESSAGE -------------- 
            checkId = (String) response.getTransactionData().get(ParameterName.CHECK_ID);
            lastName = (String) response.getTransactionData().get(ParameterName.LAST_NAME);

            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CheckBusinessLogic] Sending to WESTECH checkId value: " + checkId, null);
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CheckBusinessLogic] Sending to WESTECH lastName =: " + lastName, null);

            DirexTransactionRequest checkInfoRequest = receiveMessageFromFront(TransactionType.CHECK_INFO, transaction, checkId, correlationId);

            //TODO
            //When version 2 be stable, we can to send the answer to Westech from the Front
            // sendResponseToIStreamFront(true, checkId);
            Map checkInfoRequestMap = checkInfoRequest.getTransactionData();

            if (checkInfoRequestMap.containsKey(ParameterName.ID) && checkInfoRequestMap.get(ParameterName.ID).equals("0")) {
                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CheckBusinessLogic] ID = 0  (Westech Declined)", null);
                throw new TransactionalException(ResultCode.WESTECH_DECLINED, TransactionType.CHECK_INFO, "Girocheck Decline-Please call customer service");
            }

            validateCheck(request, checkInfoRequestMap);

            //TODO check is this is necessay (if Westech not trim the last name)
            if (idScanSuccess && checkInfoRequestMap.containsKey(ParameterName.LAST_NAME)) {
                checkInfoRequestMap.remove(ParameterName.LAST_NAME);
            }

            request.getTransactionData().putAll(checkInfoRequestMap);

            feeCalculator(request, transaction);

            //------ PROCESS PERSONAL INFO  ------
            processPersonalInfo(transaction, request, checkInfoRequestMap);

            saveCheckIfFails = false;

            System.out.println("[CheckBusinessLogic] After processPersonalInfo STATE = " + request.getTransactionData().get(ParameterName.STATE));

            //--- SEND TO COMPLIANCE ------
            
            DirexTransactionResponse complianceResponse = sendToCompliance(request,transaction);
          
            if(complianceResponse != null){
                response = complianceResponse;
            }
            
            //-------SEND TO CERTEGY ------
            request.setTransactionType(TransactionType.CERTEGY_AUTHENTICATION);
            DirexTransactionResponse certegyResponse = null;
            try {
                certegyResponse = sendMessageToHost(request, NomHost.CERTEGY, CERTEGY_WAIT_TIME, transaction);
            } catch (Exception te) {
                te.printStackTrace();
                //TODO check here that it fails because Certegy denied the check
                // and not any other technical issue
                transaction.getCheck().setStatus(CheckStatus.DENIED.getStatus());
                throw te;
            }

            if (certegyResponse != null && certegyResponse.getTransactionData().containsKey(ParameterName.DEPOSIT_ID)) {
                String depositId = (String) certegyResponse.getTransactionData().get(ParameterName.DEPOSIT_ID);
                System.out.println("[CheckBusinessLogic] certegyApprovalNumber = " + depositId);

                transaction.setCertegyApprovalNumber(depositId);
            }
            sendCertegyReverseRequestIfFails = true;

            //----------  TECNICARD VALIDATON ------------------
            String hostName = (String) request.getTransactionData().get(ParameterName.HOSTNAME);

            request.setTransactionType(TransactionType.GENERIC_HOST_VALIDATION);
            request.getTransactionData().put(TransactionType.TRANSACTION_TYPE, originalTransaction);
            response = sendMessageToHost(request, NomHost.valueOf(hostName), GENERIC_VALIDATION_WAIT_TIME, transaction);

            String estimatedPostingTime = response.getResultMessage();

            sendAnswerToTerminal(TransactionType.get(transaction.getTransactionType()), ResultCode.SUCCESS, "", correlationId);

            //-------------- WAIT CONFIRMATION FROM TERMINAL -------------
            DirexTransactionRequest confirmationRequest;
            confirmationRequest = receiveMessageFromFront(TransactionType.TECNICARD_CONFIRMATION, transaction, checkId, correlationId);

            extractTecnicardConfirmationInformation(confirmationRequest, transaction);

            //------ CREATE TECNICARD_CONFIRMATION SUBTRANSACTION ------
            addSuccessfulSubTransaction(transaction, TransactionType.TECNICARD_CONFIRMATION);

            correlationId = confirmationRequest.getCorrelation();
            request.setCorrelation(correlationId);
            currentQueue = JMSManager.get().getCore2OutQueue();

            //-----  SEND TO GENERIC HOST -------  GENERIC_HOST_CARD_LOAD
            request.setTransactionType(TransactionType.GENERIC_HOST_CARD_LOAD);

            response = sendMessageToHost(request, NomHost.valueOf(hostName), GENERIC_CARD_LOAD_WAIT_TIME, transaction);

            if (response.wasApproved()) {
                CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CheckBusinessLogic] Sending answer to Terminal -> correlationId = " + correlationId, null);
                sendAnswerToTerminal(TransactionType.TECNICARD_CONFIRMATION, ResultCode.SUCCESS, estimatedPostingTime, correlationId);
                sendWestechSendSingleICL = true;
            }

            transaction.setResultCode(ResultCode.SUCCESS.getCode());
            transaction.setResultMessage(ResultMessage.SUCCESS.getMessage());

            if (sendWestechSendSingleICL) {
                request.setTransactionType(TransactionType.ISTREAM2_SEND_SINGLE_ICL);

                CheckStatus checkStatus = CheckStatus.COMPLETED;
                try {
                    response = sendMessageToHost(request, NomHost.ISTREAM2, ISTREAM_HOST_WAIT_TIME, transaction);
                } catch (TransactionalException e) {
                    System.out.println("ISTREAM2_SEND_SINCE_ICL was not approved. response.wasApproved() = " + response.wasApproved());
                    checkStatus = CheckStatus.HOLD;
                }

                transaction.getCheck().setStatus(checkStatus.getStatus());
            }

            CoreTransactionUtil.persistTransaction(transaction);

            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CheckBusinessLogic] Transaction finished successfully", null);

            //
            //     EXCEPTIONAL CASES
            //
        } catch (TransactionalException transactionalException) {
            System.out.println("*********** TransactionalException ************ saveCheckIfFails = " + saveCheckIfFails);
            if (saveCheckIfFails) {
                System.out.println("Filling out check...");
                fillOutCheck(request.getTransactionData(), transaction);
            }

            if (sendCertegyReverseRequestIfFails) {
                certegyReverseRequest(request, transaction);
            }

            if (transactionalException.getResponse() != null) {// this is when  !response.approved()
                System.out.println("Sending subTransactionFailed 1");
                CoreTransactionUtil.subTransactionFailed(transaction, transactionalException.getResponse(), currentQueue, correlationId);
            } else {//this is when caughting Exception 
                System.out.println("Sending subTransactionFailed 2 -> transactionalException.getTransactionType()" + transactionalException.getTransactionType());
                CoreTransactionUtil.subTransactionFailed(transaction, currentQueue, correlationId, transactionalException.getTransactionType(), transactionalException.getMessage(), transactionalException.getResultCode());
            }
        } catch (Exception e) {
            System.out.println("!!!!!!!!!!!!!!!!  UNEXPECTED  EXCEPTION  !!!!!!!!!!!!!!!!!!!");
            e.printStackTrace();
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CheckBusinessLogic] UNEXPECTED Exception ", null);

            String msg = e.getMessage();
            if (msg != null) {
                transaction.setResultMessage((msg.length() > 254) ? msg.substring(0, 254) : msg);
            } else {
                transaction.setResultMessage("Message Error was printed, Please check the server logs. ");
            }

            CoreTransactionUtil.subTransactionFailed(transaction, currentQueue, correlationId, request.getTransactionType(), e.getMessage(), ResultCode.FAILED);

        }
    }

    private DirexTransactionRequest receiveMessageFromFront(TransactionType transactionType, Transaction transaction, String checkId, String correlationId) throws Exception {
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CheckBusinessLogic] Waiting " + transactionType + " from FRONT", null);

        DirexTransactionResponse response;
        Message message = null;

        // in error case
        ResultCode errorCode = null;
        ResultMessage errorMessage = null;
        String correlation = null;
        Long waitTime = null;
        Queue queue = null;

        switch (transactionType) {
            case CHECK_INFO:
                errorCode = ResultCode.ISTREAM_FRONT_PERSONAL_INFO_NOT_RECEIVED;
                errorMessage = ResultMessage.ISTREAM_FRONT_PERSONAL_INFO_RECEIVED_AS_NULL;
                correlation = checkId;
                waitTime = PERSONAL_INFO_WAIT_TIME;
                queue = JMSManager.get().getFrontIStreamOutQueue();
                break;
//            case CERTEGY_INFO:
//                errorCode = ResultCode.ISTREAM_FRONT_CERTEGY_INFO_NOT_RECEIVED;
//                errorMessage = ResultMessage.ISTREAM_FRONT_CERTEGY_INFO_NOT_RECEIVED;
//                correlation = checkId;
//                waitTime = CERTEGY_INFO_WAIT_TIME;
//                queue = JMSManager.get().getFrontIStreamOutQueue();
//                break;
            case TECNICARD_CONFIRMATION:
                errorCode = ResultCode.TERMINAL_CONFIRMATION_TIME_EXCEED;
                errorMessage = ResultMessage.TERMINAL_CONFIRMATION_TIME_EXCEED;
                correlation = correlationId;
                waitTime = TECNICARD_CONFIRMATION_WAIT_TIME;
                queue = JMSManager.get().getCore2InQueue();
                CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CheckBusinessLogic] receiveMessageFromFront(...) queue = JMSManager.get().getCore2InQueue(); correlationId = " + correlationId, null);
                break;
        }

        try {
            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CheckBusinessLogic] receiveMessageFromFront(...) JMSManager.get().receiving(...) correlation = " + correlation, null);
            message = JMSManager.get().receive(queue, correlation, waitTime);
            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CheckBusinessLogic] Recived " + transactionType, null);
        } catch (IOException | JMSException e) {
            throw new TransactionalException(errorCode, transactionType, e);
        }
 
        if (message == null || !(message instanceof ObjectMessage)) {
            System.out.println(transactionType + " -> Message received is null.");
            throw new TransactionalException(errorCode, transactionType, "Message received is null.");
        }

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CheckBusinessLogic] Received " + transactionType + " successfully.", null);

        ObjectMessage objectMessage = (ObjectMessage) message;
        Serializable s = objectMessage.getObject();
        DirexTransactionRequest request = (DirexTransactionRequest) s;
        DirexTransactionResponse iStreamResponse = new DirexTransactionResponse();
        if (request.getTransactionType() != transactionType) {
            iStreamResponse.getTransactionData().put(ParameterName.CHECK_ID, checkId);

            iStreamResponse.setResultCode(ResultCode.CORE_ERROR);
            iStreamResponse.setResultMessage(ResultMessage.FAILED.getMessage() + " TransactionType expected " + transactionType + " and received " + request.getTransactionType());
            iStreamResponse.setApproved(false);

            JMSManager.get().send(iStreamResponse, JMSManager.get().getFrontIStreamInQueue(), checkId);
            CoreTransactionUtil.subTransactionFailed(transaction, iStreamResponse, JMSManager.get().getCoreOutQueue(), correlationId);

        }

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CheckBusinessLogic] Message received", null);
        return request;
    }

    private void sendResponseToIStreamFront(boolean success, String checkId) throws JMSException {
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CheckBusinessLogic] Send response back to IStreamFront with result :: " + (success ? "SUCCESS" : "FAILED"), null);

        DirexTransactionResponse iStreamResponse = new DirexTransactionResponse();
        iStreamResponse.getTransactionData().put(ParameterName.CHECK_ID, checkId);

        iStreamResponse.setResultCode(success ? ResultCode.SUCCESS : ResultCode.FAILED);
        iStreamResponse.setResultMessage(success ? ResultMessage.SUCCESS.getMessage() : ResultMessage.FAILED.getMessage());
        iStreamResponse.setApproved(success);

        JMSManager.get().send(iStreamResponse, JMSManager.get().getFrontIStreamInQueue(), checkId);
    }

    private void extractTecnicardConfirmationInformation(DirexTransactionRequest request, Transaction transaction) throws Exception, SQLException {
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CheckBusinessLogic] extractTecnicardConfirmationInformation(...) ", null);
        Map transactionData = request.getTransactionData();
        try {
            if (transactionData.containsKey(ParameterName.TRUNCATED_CHECK) && transactionData.get(ParameterName.TRUNCATED_CHECK) != null) {
                byte[] truncatedCheck = (byte[]) transactionData.get(ParameterName.TRUNCATED_CHECK);
                if (truncatedCheck != null && truncatedCheck.length < 65535) {
                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CheckBusinessLogic] extractTecnicardConfirmationInformation(...) truncatedCheck != null", null);
                    java.sql.Blob truncatedCheckBlob = new SerialBlob(truncatedCheck);
                    transaction.setTruncatedCheck(truncatedCheckBlob);
                } else {
                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CheckBusinessLogic] extractTecnicardConfirmationInformation(...) truncatedCheck IS null", null);
                    throw new Exception("[CheckBusinessLogic] extractTecnicardConfirmationInformation(...) truncatedCheck IS null");
                }
            } else {
                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CheckBusinessLogic] extractTecnicardConfirmationInformation(...) truncatedCheck IS null", null);
                throw new Exception("[CheckBusinessLogic] extractTecnicardConfirmationInformation(.) truncatedCheck IS null");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        }
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CheckBusinessLogic] extractTecnicardConfirmationInformation(...) DONE", null);
    }

    public void validateCheck(DirexTransactionRequest request, Map checkInfoRequestMap) throws TransactionalException, Exception {
        Double westechAmount = 0D;
        String westechAmountString = "";
        try {
            westechAmountString = (String) checkInfoRequestMap.get(ParameterName.AMMOUNT);
            westechAmount = Double.parseDouble(westechAmountString);
        } catch (Exception e) {
        }

        Double amount = (Double) request.getTransactionData().get(ParameterName.AMMOUNT);

        if (!amount.equals(westechAmount)) {
            System.out.println("Amounts doesn't match:");
            System.out.println("westechAmount = " + westechAmountString);
            System.out.println("terminalAmount = " + amount);

            throw new TransactionalException(ResultCode.TERMINAL_WRONG_AMMOUNT, TransactionType.CHECK_INFO, ResultMessage.TERMINAL_WRONG_AMMOUNT.getMessage());
        }

        checkInfoRequestMap.remove(ParameterName.AMMOUNT);

        String makerName = (String) checkInfoRequestMap.get(ParameterName.MAKER_NAME);
        String routingNumber = (String) checkInfoRequestMap.get(ParameterName.ROUTING_BANK_NUMBER);
        String accountNumber = (String) checkInfoRequestMap.get(ParameterName.ACCOUNT_NUMBER);

        Boolean validCheck = true;
        
        HibernateUtil.beginTransaction();
        System.out.println("validating Check...");
        validCheck = CheckBlacklistRuleManager.get().validateCheck(makerName, routingNumber, accountNumber);
        System.out.println("validCheck = " + validCheck);
        HibernateUtil.commitTransaction();
        
        if (!validCheck) {
            System.out.println("The check is in the black list...");
            throw new TransactionalException(ResultCode.CHECK_IN_BLACK_LIST, TransactionType.CHECK_INFO, ResultMessage.CHECK_IN_BLACK_LIST.getMessage());
        }
    }

}
