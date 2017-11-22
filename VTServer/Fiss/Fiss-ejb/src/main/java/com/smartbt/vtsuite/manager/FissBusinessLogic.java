/*
 ** File: IStreamBusinessLogic.java
 **
 ** Date Created: February 2013
 **
 ** Copyright @ 2004-2013 Smart Business Technology, Inc.
 **
 ** All rights reserved. No part of this software may be 
 ** reproduced, transmitted, transcribed, stored in a retrieval 
 ** system, or translated into any language or computer language, 
 ** in any form or by any means, electronic, mechanical, magnetic, 
 ** optical, chemical, manual or otherwise, without the prior 
 ** written permission of Smart Business Technology, Inc.
 **
 */
package com.smartbt.vtsuite.manager;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.vtsuite.connector.ConnectorFactory;
import com.smartbt.vtsuite.util.FissParam;
import com.smartbt.vtsuite.util.FissPrintUtil;
import com.smartbt.vtsuite.util.FissUtil;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FissBusinessLogic {

    public static final String FISS_URL = System.getProperty("FISS_URL");

    private static FissBusinessLogic INSTANCE;

    public static void main(String[] args) throws Exception {
        DirexTransactionRequest request = new DirexTransactionRequest();
        request.setTransactionType(TransactionType.FISS_CHANGE_PASSWORD);
//        request.setTransactionType(TransactionType.TRANSACTION_HISTORY);

        Map params = new HashMap();
        params.put(ParameterName.CARD_NUMBER, "5400290040068571");
        params.put(ParameterName.FIRST_NAME, "Roberto");
        params.put(ParameterName.LAST_NAME, "Rodriguez");
        params.put(ParameterName.ADDRESS, "9842 Palmetto Club Dr");
        params.put(ParameterName.CITY, "Miami");
        params.put(ParameterName.STATE_ABBREVIATION, "FL");
        params.put(ParameterName.COUNTRY, "US");
        params.put(ParameterName.ZIPCODE, "33157");
        params.put(ParameterName.SSN, "123456789");
        params.put(ParameterName.BORNDATE_AS_DATE, new Date());
        params.put(ParameterName.TELEPHONE, "7864540209");
        params.put(ParameterName.FISS_PRODUCT_ID, FissUtil.PRODUCT_ID);
        params.put(ParameterName.AMOUNT, 34.56);
        params.put(ParameterName.PASSWORD, "#Girocheck1");
        params.put(ParameterName.PIN, "1234");

        request.setTransactionData(params);

        FissBusinessLogic.get().process(TransactionType.CARD_VALIDATION, request);
    }

    public static synchronized FissBusinessLogic get() {
        if (INSTANCE == null) {
            INSTANCE = new FissBusinessLogic();
        }
        return INSTANCE;
    }

    public Map<FissParam, Object> process(TransactionType transactionType, DirexTransactionRequest request) throws Exception {
        Map transactionData = request.getTransactionData();
        transactionData.put(ParameterName.TRANSACTION_TYPE, transactionType);

        Map<FissParam, Object> responseMap = null;

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[FissBusinessLogic] proccessing:: " + transactionType, null);

        switch (transactionType) {
            case BALANCE_INQUIRY://ok
            case CARD_VALIDATION:
                responseMap = balanceInquiryCardValidation(transactionData);
                break;
            case TRANSACTION_HISTORY://to last
                responseMap = transactionHistory(transactionData);
                break;
            case CARD_PERSONALIZATION://ok
                responseMap = cardPersonalization(transactionData);
                break;
            case CARD_ACTIVATION://ok
                responseMap = cardActivation(transactionData);
                break;
            case CARD_LOAD://ok
                responseMap = cardLoad(transactionData);
                break;
            case CARD_TO_BANK:
                responseMap = cardCashing(transactionData);
                break;
//            case FISS_SET_PRODUCT_ID://not need for now
//                responseMap = setProductId(transactionData);
//                break;
            case FISS_CHANGE_PASSWORD://need to do a job for this
                responseMap = changePassword(transactionData);
                break;
            case FISS_SET_PIN://ok
                responseMap = setPin(transactionData);
                break;
            case RESTORE_CARD://ok
                responseMap = restoreCard(transactionData);
                break;

        }

        return responseMap;
    }

    private Map balanceInquiryCardValidation(Map params) {
        System.out.println("balanceInquiryCardValidation...");
        TransactionType transactionType = (TransactionType) params.get(ParameterName.TRANSACTION_TYPE);
        Map<FissParam, Object> responseMap = ConnectorFactory.getBalanceInquiryConnector().callWS(params);

        FissPrintUtil.printResponse(transactionType + "_RESPONSE", responseMap);

        if (wasSuccess(responseMap)) {
            Map<ParameterName, Object> fissData = (Map<ParameterName, Object>) responseMap.get(FissParam.FISS_PERSONAL_INFO_DATA);
 
            if (fissData == null
                    || (responseMap.containsKey(FissParam.RESULT_CODE) && ((Integer) responseMap.get(FissParam.RESULT_CODE) == 1)
                    && !params.get(ParameterName.SSN).equals(fissData.get(ParameterName.SSN)))) {
                responseMap.put(FissParam.SUCCESS, false);
                responseMap.put(FissParam.RESULT_CODE, ResultCode.FISS_HOST_UNEXPECTED_RESULT_CODE);
                responseMap.put(FissParam.RESULT_MESSAGE, "Fiss Card Holder SSN doesn't match.");
            }
        }
        return responseMap;
    }

    private Map transactionHistory(Map params) {
        System.out.println("[FissBusinessLogic] transactionHistory");
        Map result = new HashMap();
        ConnectorFactory.getTransactionHistoryHoldConnector().callWS(params);
        ConnectorFactory.getTransactionHistoryPendingConnector().callWS(params);
        ConnectorFactory.getTransactionHistoryGeneralConnector().callWS(params);
        ConnectorFactory.getTransactionHistoryDetailsConnector().callWS(params);

        return result;
    }

    private Map cardPersonalization(Map params) {
        System.out.println("[FissBusinessLogic] cardPersonalization");
        Map<FissParam, Object> responseMap = ConnectorFactory.getCardPersonalizationConnector().callWS(params);
        FissPrintUtil.printResponse("CARD_PERSONALIZATION_RESPONSE", responseMap);
        return responseMap;
    }

    private Map restoreCard(Map params) {
        System.out.println("[FissBusinessLogic] restoreCard");

        params.put(ParameterName.STATUS_CODE, "7");
        Map<FissParam, Object> responseMap = ConnectorFactory.getCardActivationConnector().callWS(params);
        FissPrintUtil.printResponse("RESTORE_CARD_DEACTIVATION_RESPONSE", responseMap);

        if (wasSuccess(responseMap)) {
            Map<ParameterName, String> fissPersonalInfoData = (Map<ParameterName, String>) params.get(ParameterName.FISS_PERSONAL_INFO_DATA);

            params.putAll(fissPersonalInfoData);

            responseMap = ConnectorFactory.getCardPersonalizationConnector().callWS(params);
            FissPrintUtil.printResponse("RESTORE_CARD_PERSONALIZATION_RESPONSE", responseMap);
           
        }

        return responseMap;
    }

    private Map setProductId(Map params) {
        System.out.println("[FissBusinessLogic] setProductId");
        Map<FissParam, Object> responseMap = ConnectorFactory.getSetProductIdConnector().callWS(params);
        FissPrintUtil.printResponse("SET_PRODUCT_ID_RESPONSE", responseMap);
        return responseMap;
    }

    private Map cardActivation(Map params) {
        System.out.println("[FissBusinessLogic] cardActivtion");
        params.put(ParameterName.STATUS_CODE, "1");
        Map<FissParam, Object> responseMap = ConnectorFactory.getCardActivationConnector().callWS(params);
        FissPrintUtil.printResponse("CARD_ACTIVATION_RESPONSE", responseMap);
        return responseMap;
    }

    private Map cardLoad(Map params) {
        System.out.println("[FissBusinessLogic] cardLoad");

        String operation = (String) params.get(ParameterName.OPERATION);
        Boolean isCheck = operation.equals("01");

        Map<FissParam, Object> responseMap;

        if (isCheck) {
            params.put(ParameterName.REQUEST_TYPE, ParameterName.AMOUNT);
            responseMap = ConnectorFactory.getCardLoadConnector().callWS(params);
        } else {
            responseMap = ConnectorFactory.getCardLoadCashConnector().callWS(params); 
        }
        FissPrintUtil.printResponse("CARD_LOAD_RESPONSE", responseMap);

        if (wasSuccess(responseMap)) {

            params.put(ParameterName.REQUEST_TYPE, ParameterName.FEE_AMOUNT);

            responseMap = ConnectorFactory.getCardLoadConnector().callWS(params);
            FissPrintUtil.printResponse("CARD_LOAD_FEE_RESPONSE", responseMap);
        }

        return responseMap;
    }

    private Map cardCashing(Map transactionData) {
        System.out.println("[FissBusinessLogic] cardCashing");
        Map<FissParam, Object> responseMap = ConnectorFactory.getCardCashingConnector().callWS(transactionData);
        FissPrintUtil.printResponse("CARD_CASHING_RESPONSE", responseMap);
        return responseMap;
    }

    private Map changePassword(Map transactionData) {
        Map<FissParam, Object> responseMap = ConnectorFactory.getChangePasswordConnector().callWS(transactionData);
        FissPrintUtil.printResponse("CHANGE_PASSWORD_RESPONSE", responseMap);
        return responseMap;
    }

    private Map setPin(Map transactionData) {
        Map<FissParam, Object> responseMap = ConnectorFactory.getSetPinConnector().callWS(transactionData);
        FissPrintUtil.printResponse("SET_PIN_RESPONSE", responseMap);
        return responseMap;
    }

    private boolean wasSuccess(Map<FissParam, Object> responseMap) {
        return responseMap != null && responseMap.containsKey(FissParam.SUCCESS)
                && (Boolean) responseMap.get(FissParam.SUCCESS) == true;
    }
}
