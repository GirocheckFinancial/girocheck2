
/*

 @Author Roberto Rodriguez
 robertoSoftwareEngineer@gmail.com

 */
package com.smartbt.vtsuite.manager;

import com.smartbt.girocheck.servercommon.jms.JMSManager;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.enums.ResultMessage;
import com.smartbt.girocheck.servercommon.manager.CountryManager;
import com.smartbt.girocheck.servercommon.manager.HostTxManager;
import com.smartbt.girocheck.servercommon.manager.PersonalIdentificationManager;
import com.smartbt.girocheck.servercommon.manager.StateManager;
import com.smartbt.girocheck.servercommon.model.Transaction;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomHost;
import java.util.Map;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jms.JMSException;
import javax.jms.Queue;
import com.smartbt.vtsuite.util.TransactionalException;
import java.util.HashMap;

@TransactionManagement(value = TransactionManagementType.BEAN)
public abstract class AbstractCommonBusinessLogic extends CoreAbstractTransactionBusinessLogic {

//    protected int state = 0;
    // WAITING TIMES
    protected static final long TECNICARD_CONFIRMATION_WAIT_TIME = 180000;//3min
    protected static final long ISTREAM_HOST_WAIT_TIME = 30000;//30sec
    protected static final long WESTECH_HOST_WAIT_TIME = 30000;//30sec
    protected static final long COMPLIANCE_HOST_WAIT_TIME = 30000;//30sec
    protected static final long IDEOLOGY_HOST_WAIT_TIME = 30000;//30sec
    protected static final long PERSONAL_INFO_WAIT_TIME = 420000;//7min 
    protected static final long CHOICE_WAIT_TIME = 300000;//5min 
    public static final long GENERIC_VALIDATION_WAIT_TIME = 60000;//1min
    protected static final long GENERIC_CARD_LOAD_WAIT_TIME = 60000;//1min 
    protected static final long CERTEGY_WAIT_TIME = 30000;//30sec
    protected static final long CERTEGY_INFO_WAIT_TIME = 420000;//7min 

    protected static CountryManager countryManager = CountryManager.get();
    protected static StateManager stateManager = StateManager.get();
    protected PersonalIdentificationManager personalIdentificationManager = PersonalIdentificationManager.get();
 
    public static DirexTransactionResponse callHost(DirexTransactionRequest request, NomHost host, long waitTime, Transaction transaction) throws JMSException, Exception {
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CORE2] Calling host:: " + host, null);

        DirexTransactionResponse direxTransactionResponse = hostManagers.get(host).processTransaction(request);

        transaction.addSubTransactionList(direxTransactionResponse.getTransaction().getSub_Transaction());

        if (!direxTransactionResponse.wasApproved()) {
            direxTransactionResponse.setTransactionType(request.getTransactionType());
            throw new TransactionalException(direxTransactionResponse);
        }
        return direxTransactionResponse;
    }

    protected void sendAnswerToTerminal(TransactionType transactionType, ResultCode resultCode, String estimated_posting_time, String correlationId) throws JMSException {
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[AbstractCommonBusinessLogic] Send answer to TERMINAL", null);

        DirexTransactionResponse provissionalResponse = new DirexTransactionResponse();
        provissionalResponse.setResultCode(resultCode);

        if (!estimated_posting_time.isEmpty()) {
            provissionalResponse.setResultMessage(estimated_posting_time);
        } else {
            provissionalResponse.setResultMessage(ResultMessage.SUCCESS.getMessage());
        }

        Queue queue;

        if (transactionType == TransactionType.TECNICARD_CONFIRMATION) {
            provissionalResponse.getTransactionData().put(ParameterName.PRINTLOGO, "01");
            queue = JMSManager.get().getCore2OutQueue();
        } else {
            queue = JMSManager.get().getCoreOutQueue();
        }

        //provissionalResponse.getTransactionData().put("host", host);
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[AbstractCommonBusinessLogic] Send message to TERMINAL:: queue = " + queue.getQueueName() + ", correlationId = " + correlationId, null);
        JMSManager.get().send(provissionalResponse, queue, correlationId);
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[AbstractCommonBusinessLogic] Send message to TERMINAL Done.", null);
    }

    protected void certegyReverseRequest(DirexTransactionRequest request, Transaction transaction) throws Exception {

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AbstractCommonBusinessLogic]  Sending  CertegyReverseRequest", null);
        DirexTransactionRequest certegyRequest = request.clone();
        certegyRequest.setTransactionType(TransactionType.CERTEGY_REVERSE_REQUEST);

        callHost(certegyRequest, NomHost.CERTEGY, CERTEGY_WAIT_TIME, transaction);
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AbstractCommonBusinessLogic] Response from CERTEGY, subTRANSACTION FINISHED. ", null);

    }

    protected String getFromMap(Map map, ParameterName parameterName) {
        if (map.containsKey(parameterName)) {
            return (String) map.get(parameterName);
        } else {
            return null;
        }
    }

    public DirexTransactionResponse sendToCompliance(DirexTransactionRequest request, Transaction transaction) throws Exception {
        DirexTransactionResponse response = null;

        try {
            request.setTransactionType(TransactionType.COMPLIANCE_POST_TRANSACTION);
            response = callHost(request, NomHost.COMPLIANCE, COMPLIANCE_HOST_WAIT_TIME, transaction);
        } catch (Exception e) {
            String activateCompliance = System.getProperty("ACTIVATE_COMPLIANCE");
            System.out.println("AbstractCommonBusinessLogic() -> activateCompliance = " + activateCompliance);
            Boolean isComplianceActive = activateCompliance != null && activateCompliance.equalsIgnoreCase("true");

            System.out.println("[AbstractCommonBusinessLogic] Exception calling COMPLIANCE_POST_TRANSACTION");

            e.printStackTrace();

            if (isComplianceActive) {
                throw e;
            } else {//If is not active, record the subtransaction for future reference  
                if (e instanceof TransactionalException) {
                }
            }
        }

        return response;
    }

    public DirexTransactionResponse sendToIdeology(DirexTransactionRequest request, Transaction transaction) throws Exception {
        DirexTransactionResponse response = null;

        try {
            request.setTransactionType(TransactionType.IDEOLOGY_VERYFY_CLIENT);
            response = callHost(request, NomHost.IDEOLOGY, IDEOLOGY_HOST_WAIT_TIME, transaction);
        } catch (Exception e) {
            String activateIdeology = System.getProperty("ACTIVATE_IDEOLOGY");
            System.out.println("AbstractCommonBusinessLogic() -> activateIdeology = " + activateIdeology);
            Boolean isIdeologyActive = activateIdeology != null && activateIdeology.equalsIgnoreCase("true");

            System.out.println("[AbstractCommonBusinessLogic] Exception calling COMPLIANCE_POST_TRANSACTION");

            e.printStackTrace();

            if (isIdeologyActive) {
                throw e;
            } else { //If is not active dont throw exception, just log the sub transaction

            }
        }

        return response;
    }

    public void postprocess(DirexTransactionRequest direxTransactionRequest, DirexTransactionResponse direxTransactionResponse) throws Exception {
    }
}
