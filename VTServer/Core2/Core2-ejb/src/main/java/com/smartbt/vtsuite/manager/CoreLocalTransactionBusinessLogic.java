/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.manager;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.enums.ResultMessage;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.jms.JMSManager;
import com.smartbt.girocheck.servercommon.manager.FeeBucketsManager;
import com.smartbt.girocheck.servercommon.manager.TransactionManager;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.model.Transaction;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.vtsuite.util.CoreTransactionUtil;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomHost;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Roberto Rodriguez :: <roberto.rodriguez@smartbt.com>
 */
public class CoreLocalTransactionBusinessLogic extends CoreAbstractTransactionBusinessLogic {

    TransactionManager transactionManager = new TransactionManager();
    private JMSManager jmsManager = JMSManager.get();

    public CoreLocalTransactionBusinessLogic() {
        super();
    }

    @Override
    public void process(DirexTransactionRequest direxTransactionRequest, Transaction transaction) throws Exception {

        TransactionType transactionType = (TransactionType) direxTransactionRequest.getTransactionData().get(TransactionType.TRANSACTION_TYPE);
        DirexTransactionResponse response = new DirexTransactionResponse();

        switch (transactionType) {
            case CHECK_AUTH_LOCATION_CONFIG:
                response = checkAuthLocationConfig(direxTransactionRequest, transaction);
                break;
        }

        transaction.setResultCode(response.getResultCode().getCode());
        transaction.setResultMessage(response.getResultMessage());
        transaction.setTransactionFinished(true);

        JMSManager.get().send(response, JMSManager.get().getCoreOutQueue(), direxTransactionRequest.getCorrelation());

        if (transaction.getTransactionType() == TransactionType.CHECK_AUTH_LOCATION_CONFIG.getCode()) {

            if (transaction.getResultCode() == ResultCode.SUCCESS.getCode()) {
                return;
            }
        }

        HibernateUtil.beginTransaction();
        System.out.println("CheckAuthLocationConfig persisting transaction.");
        transactionManager.getTransactionDAO().save(transaction);
        HibernateUtil.commitTransaction();
    }

    public DirexTransactionResponse checkAuthLocationConfig(DirexTransactionRequest direxTransactionRequest, Transaction transaction) throws Exception {

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CoreLocalTransactionBusinessLogic] checkAuthLocationConfig(...)", null);

        DirexTransactionResponse response;
        DirexTransactionResponse auxResponse;

        response = calculateFees(direxTransactionRequest, transaction);

        if (!response.wasApproved()) {
            return response;
        }

        direxTransactionRequest.getTransactionData().putAll(response.getTransactionData());

        NomHost hostName = (NomHost) direxTransactionRequest.getTransactionData().get(ParameterName.HOSTNAME);

        Double activationFee = 0D;

        if (hostName == NomHost.TECNICARD) {
            auxResponse = tCValidation(direxTransactionRequest, transaction);

            if (!auxResponse.wasApproved()) {
                return auxResponse;
            }

            if (auxResponse.getTransactionData().containsKey(ParameterName.ACTIVATION_FEE)) {
                CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CoreLocalTransactionBusinessLogic] ACTIVATION_FEE = " + auxResponse.getTransactionData().get(ParameterName.ACTIVATION_FEE), null);
                activationFee = (Double) auxResponse.getTransactionData().get(ParameterName.ACTIVATION_FEE);
            }

            response.setResultCode(auxResponse.getResultCode());
            response.setResultMessage(auxResponse.getResultMessage());
            response.setTerminalResultMessage(auxResponse.getTerminalResultMessage());
        } else {
            response.setResultCode(ResultCode.SUCCESS);
            response.setResultMessage(ResultMessage.SUCCESS.getMessage());
            response.setTerminalResultMessage(ResultMessage.SUCCESS.getMessage());
        }

        response.getTransactionData().put(ParameterName.ACTIVATION_FEE, activationFee);

        return response;//ajustar el transaction data con los campos q se le van a enviar a la terminal.
    }

    private DirexTransactionResponse tCValidation(DirexTransactionRequest direxTransactionRequest, Transaction transaction) throws Exception {

        DirexTransactionResponse response = new DirexTransactionResponse();

        payOutAmountCalculator(direxTransactionRequest, transaction);

        try {
            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CoreLocalTransactionBusinessLogic] Calling Tecnicard", null);

            response = hostManagers.get(NomHost.TECNICARD).processTransaction(direxTransactionRequest);

        } catch (Exception e) {
            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CoreLocalTransactionBL] Exception calling Tecnicard.", null);
            response.setApproved(false);
            e.printStackTrace();
        }

        if (!response.wasApproved()) {
            response = DirexTransactionResponse.forException(direxTransactionRequest.getTransactionType(), ResultCode.CORE_ERROR, ResultMessage.TECNICARD_FAILED, "TECNICARD ERROR VALIDATING CARD.");
            response.setTransactionType(direxTransactionRequest.getTransactionType());
            CoreTransactionUtil.subTransactionFailed(transaction, response, jmsManager.getCoreOutQueue(), direxTransactionRequest.getCorrelation(), NomHost.TECNICARD);
        }
        return response;
    }

    private DirexTransactionResponse calculateFees(DirexTransactionRequest direxTransactionRequest, Transaction transaction) throws Exception {

        DirexTransactionResponse response = new DirexTransactionResponse();

        Map responseMap = new HashMap();
        try {
            HibernateUtil.beginTransaction();

            FeeBucketsManager feeBucketsManager = new FeeBucketsManager();
            responseMap = (Map) feeBucketsManager.getFees((direxTransactionRequest.getTransactionData().get(ParameterName.IDMERCHANT)) + "",
                    (direxTransactionRequest.getTransactionData().get(ParameterName.OPERATION)) + "",
                    (direxTransactionRequest.getTransactionData().get(ParameterName.AMOUNT)) + "");

            response.setTransactionData(responseMap);
            HibernateUtil.commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            HibernateUtil.rollbackTransaction();

            response = DirexTransactionResponse.forException(direxTransactionRequest.getTransactionType(), ResultCode.CORE_ERROR, ResultMessage.FAILED, "");
            response.setTransactionType(direxTransactionRequest.getTransactionType());
            CoreTransactionUtil.subTransactionFailed(transaction, response, jmsManager.getCoreOutQueue(), direxTransactionRequest.getCorrelation(), NomHost.TECNICARD);
            return response;
        }

        return response;
    }

    private void payOutAmountCalculator(DirexTransactionRequest request, Transaction transaction) {

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreLocalTransactionBusinessLogic] feeCalculator() start ... amount = " + request.getTransactionData().get(ParameterName.AMOUNT), null);
        Double amount = (Double) request.getTransactionData().get(ParameterName.AMOUNT);

        Float feeAmount = (Float) request.getTransactionData().get(ParameterName.CRDLDF);

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreLocalTransactionBusinessLogic] amount =" + amount + " ,FEE_AMOUNT applied: " + feeAmount, null);
        Double payOut = amount - feeAmount;

        request.getTransactionData().put(ParameterName.PAYOUT_AMOUNT, payOut);
        request.getTransactionData().put(ParameterName.FEE_AMOUNT, feeAmount);

        transaction.setPayoutAmmount(payOut);
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreLocalTransactionBusinessLogic] feeCalculator() done with PAYOUT_AMOUNT: " + payOut, null);
    }

    class BreakException extends Exception {
    }
}
