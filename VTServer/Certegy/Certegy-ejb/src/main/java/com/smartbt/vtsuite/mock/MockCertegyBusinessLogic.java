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
package com.smartbt.vtsuite.mock;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.enums.ResultMessage;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger; 
import java.util.HashMap;
import java.util.Map;

/**
 * Mpowa Business Logic Class
 */
public class MockCertegyBusinessLogic {

    private static MockCertegyBusinessLogic INSTANCE;

    public static synchronized MockCertegyBusinessLogic get() {
        if (INSTANCE == null) {
            INSTANCE = new MockCertegyBusinessLogic();
        }
        return INSTANCE;
    }
 
    public DirexTransactionResponse process(DirexTransactionRequest request) throws Exception {

        Map transactionData = request.getTransactionData();
        DirexTransactionResponse direxTransactionResponse = new DirexTransactionResponse();

        TransactionType transactionType = request.getTransactionType();

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[MockCertegyBusinessLogic] proccessing:: " + transactionType, null);

        Map result = new HashMap();

        switch (transactionType) {
            case CERTEGY_AUTHENTICATION:
                result = combinedEnrollmentAuthentication(transactionData);
                break;
            case CERTEGY_REVERSE_REQUEST:
                result = reverseRequest(transactionData);
                break;
        }

        Boolean success = (Boolean) result.get(ParameterName.SUCESSFULL_PROCESSING);
        String resultCode = (String) result.get(ParameterName.RESULT_CODE);

        System.out.println("[MockCertegyBusinessLogic] :: resultCode = " + resultCode);

        if (success) {
            direxTransactionResponse = DirexTransactionResponse.forSuccess();
            direxTransactionResponse.getTransactionData().putAll(result);
            return direxTransactionResponse;
        } else {
            direxTransactionResponse = DirexTransactionResponse.forException(ResultCode.CERTEGY_DENY, ResultMessage.CERTEGY_DENY);
            direxTransactionResponse.setErrorCode(resultCode);
        }

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[MockCertegyBusinessLogic] Finish " + transactionType, null);

        return direxTransactionResponse;
    }

    public Map combinedEnrollmentAuthentication(Map params) {
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[MockCertegyBusinessLogic] Calling method insertTransaction", null);

        Map map = new HashMap();
        map.put(ParameterName.DEPOSIT_ID, "12345");
        map.put(ParameterName.RESULT_CODE, "00");
        map.put(ParameterName.SUCESSFULL_PROCESSING, true);
        return map;
    }

    public Map reverseRequest(Map params) {
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[MockCertegyBusinessLogic] Calling method cancelationRequest", null);

        Map map = new HashMap();
        map.put(ParameterName.RESULT_CODE, "13");
        map.put(ParameterName.SUCESSFULL_PROCESSING, true);
        return map;
    }

}
