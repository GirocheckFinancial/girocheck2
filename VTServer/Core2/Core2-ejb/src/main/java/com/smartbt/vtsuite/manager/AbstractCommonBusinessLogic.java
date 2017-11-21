
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
import org.glassfish.grizzly.http.HttpServerFilter;

@TransactionManagement(value = TransactionManagementType.BEAN)
public abstract class AbstractCommonBusinessLogic extends CoreAbstractTransactionBusinessLogic {

    protected static final long TERMINAL_CONFIRMATION_WAIT_TIME = 180000;//3min 
    protected static final long PERSONAL_INFO_WAIT_TIME = 420000;

    protected static CountryManager countryManager = CountryManager.get();
    protected static StateManager stateManager = StateManager.get();
    protected PersonalIdentificationManager personalIdentificationManager = PersonalIdentificationManager.get();

    public static DirexTransactionResponse callHost(DirexTransactionRequest request, NomHost host, Transaction transaction) throws JMSException, Exception {
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CORE2] Calling host:: " + host, null);

        DirexTransactionResponse response = hostManagers.get(host).processTransaction(request);

        transaction.addSubTransactionList(response.getTransaction().getSub_Transaction());

        //important (make response data available fro subsequent request)
        Map data = request.getTransactionData();
        data.putAll(response.getTransactionData()); 
        response.setTransactionData(data);

        if (!response.wasApproved()) {
            response.setTransactionType(request.getTransactionType());
            throw new TransactionalException(response);
        }

        return response;
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

        if (transactionType == TransactionType.TERMINAL_CONFIRMATION) {
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

        callHost(certegyRequest, NomHost.CERTEGY, transaction);
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
            response = callHost(request, NomHost.COMPLIANCE, transaction);
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
            response = callHost(request, NomHost.IDEOLOGY, transaction);
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
