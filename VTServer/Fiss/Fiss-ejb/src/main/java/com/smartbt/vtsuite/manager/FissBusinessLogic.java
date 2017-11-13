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

import com.smartbt.girocheck.common.VTSuiteMessages;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.vtsuite.connector.ConnectorFactory;
import com.smartbt.vtsuite.util.FissUtil;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FissBusinessLogic {

    public static final String FISS_URL = System.getProperty("FISS_URL");

    private static FissBusinessLogic INSTANCE;

    public static void main(String[] args) throws Exception {
        DirexTransactionRequest request = new DirexTransactionRequest();
        request.setTransactionType(TransactionType.TRANSACTION_HISTORY);
//        request.setTransactionType(TransactionType.TRANSACTION_HISTORY);

        Map params = new HashMap();
        params.put(ParameterName.CARD_NUMBER, "1111111111111111");
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
        params.put(ParameterName.AMMOUNT, 34.56);
        params.put(ParameterName.PASSWORD, "#Aaaaaa1");
        params.put(ParameterName.PIN, "1234");

        request.setTransactionData(params);

        FissBusinessLogic.get().process(request);
    }

    public static synchronized FissBusinessLogic get() {
        if (INSTANCE == null) {
            INSTANCE = new FissBusinessLogic();
        }
        return INSTANCE;
    }

    public DirexTransactionResponse process(DirexTransactionRequest request) throws Exception {
        Map transactionData = request.getTransactionData();
        DirexTransactionResponse direxTransactionResponse = new DirexTransactionResponse();

        TransactionType transactionType = request.getTransactionType();

        Map responseMap = null;

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[FissBusinessLogic] proccessing:: " + transactionType, null);

        switch (transactionType) {
            case FISS_BALANCE_INQUIRY_CARD_VALIDATION:
                responseMap = balanceInquiryCardValidation(transactionData);
                break;
            case TRANSACTION_HISTORY:
                responseMap = transactionHistory(transactionData);
                break;
            case CARD_PERSONALIZATION:
                responseMap = cardPersonalization(transactionData);
                break;
            case CARD_ACTIVATION:
                responseMap = cardActivtion(transactionData);
                break;
            case CARD_LOAD:
                responseMap = cardLoad(transactionData);
                break;
            case CARD_CASHING:
                responseMap = cardCashing(transactionData);
                break;
            case FISS_SET_PRODUCT_ID:
                responseMap = setProductId(transactionData);
                break;
            case FISS_CHANGE_PASSWORD:
                responseMap = changePassword(transactionData);
                break;
            case FISS_SET_PIN:
                responseMap = setPin(transactionData);
                break;

        }

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[FissBusinessLogic] Finish " + transactionType, null);

        direxTransactionResponse.setResultCode(ResultCode.SUCCESS);
        direxTransactionResponse.setResultMessage(VTSuiteMessages.SUCCESS);

        if (responseMap != null) {
            direxTransactionResponse.getTransactionData().putAll(responseMap);
        }

        return direxTransactionResponse;
    }

    public Map balanceInquiryCardValidation(Map params) {
        System.out.println("[FissBusinessLogic] balanceInquiryCardValidation");
        Map result = new HashMap();
        ConnectorFactory.getBalanceInquiryConnector().callWS(params);
        return result;
    }

    public Map transactionHistory(Map params) {
        System.out.println("[FissBusinessLogic] transactionHistory");
        Map result = new HashMap();
        ConnectorFactory.getTransactionHistoryHoldConnector().callWS(params);
        ConnectorFactory.getTransactionHistoryPendingConnector().callWS(params);
        ConnectorFactory.getTransactionHistoryGeneralConnector().callWS(params);
        ConnectorFactory.getTransactionHistoryDetailsConnector().callWS(params);

        return result;
    }

    public Map cardPersonalization(Map params) {
        System.out.println("[FissBusinessLogic] cardPersonalization");
        Map result = new HashMap();

        ConnectorFactory.getCardPersonalizationConnector().callWS(params);

        return result;
    }

    public Map setProductId(Map params) {
        System.out.println("[FissBusinessLogic] setProductId");
        Map result = new HashMap();

        ConnectorFactory.getSetProductIdConnector().callWS(params);

        return result;
    }

    public Map cardActivtion(Map params) {
        System.out.println("[FissBusinessLogic] cardActivtion");
        Map result = new HashMap();

        ConnectorFactory.getCardActivationConnector().callWS(params);
        return result;
    }

    public Map cardLoad(Map params) {
        System.out.println("[FissBusinessLogic] cardLoad");
        Map result = new HashMap();

        ConnectorFactory.getCardLoadConnector().callWS(params);
        return result;
    }

    public Map cardCashing(Map params) {
        System.out.println("[FissBusinessLogic] cardCashing");
        Map result = new HashMap();

        return result;
    }

    private Map changePassword(Map transactionData) {
        Map result = new HashMap();

        ConnectorFactory.getChangePasswordConnector().callWS(transactionData);
        return result;
    }

    private Map setPin(Map transactionData) {
        Map result = new HashMap();

        ConnectorFactory.getSetPinConnector().callWS(transactionData);
        return result;
    }

}
