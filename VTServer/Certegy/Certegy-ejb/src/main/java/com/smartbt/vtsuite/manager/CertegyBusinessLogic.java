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
import com.smartbt.girocheck.servercommon.enums.ResultMessage;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.vtsuite.boundary.PCA;
import com.smartbt.vtsuite.boundary.PCARequest;
import com.smartbt.vtsuite.boundary.PCAResponse;
import com.smartbt.vtsuite.boundary.PCAReverseRequest;
import com.smartbt.vtsuite.boundary.PCAReverseResponse;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Mpowa Business Logic Class
 */
public class CertegyBusinessLogic {

    public static final String CERTEGY_SITE_ID = "2897891071345202";
    public static final BigDecimal CERTEGY_VERSION = new BigDecimal("1.2");
    private static CertegyBusinessLogic INSTANCE;
    private Proxy proxy;
    private PCA port;

    public static synchronized CertegyBusinessLogic get() {
        if (INSTANCE == null) {
            INSTANCE = new CertegyBusinessLogic();
        }
        return INSTANCE;
    }

    public CertegyBusinessLogic() {
        proxy = new Proxy();
        port = proxy.getPort();
    }

    public DirexTransactionResponse process(DirexTransactionRequest request) throws Exception {

        Map transactionData = request.getTransactionData();
        DirexTransactionResponse direxTransactionResponse = new DirexTransactionResponse();

        TransactionType transactionType = request.getTransactionType();

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CertegyBusinessLogic] proccessing:: " + transactionType, null);

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

        System.out.println("[CertegyBusinessLogic] :: resultCode = " + resultCode);

        if (success) {
            direxTransactionResponse = DirexTransactionResponse.forSuccess();
            direxTransactionResponse.getTransactionData().putAll(result);
            return direxTransactionResponse;
        } else {
            direxTransactionResponse = DirexTransactionResponse.forException(ResultCode.CERTEGY_DENY, ResultMessage.CERTEGY_DENY);
            direxTransactionResponse.setErrorCode(resultCode);
        }

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CertegyBusinessLogic] Finish " + transactionType, null);

        return direxTransactionResponse;
    }

    public Map combinedEnrollmentAuthentication(Map params) {
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CertegyBusinessLogic] Calling method insertTransaction", null);

        PCARequest request = PCARequest.build(params);

        System.out.println(request.toString());

        PCAResponse response = port.authorize(request);

        if (response != null) {
            return response.toMap();
        } else {
            return null;
        }
    }

    public Map reverseRequest(Map params) {
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CertegyBusinessLogic] Calling method cancelationRequest", null);

        params.remove(ParameterName.CHECK_ISSUE_DATE);

        PCAReverseRequest request = PCAReverseRequest.build(params);
        System.out.println(request.toString());
        PCAReverseResponse response = port.reverse(request);

        if (response != null) {
            return response.toMap();
        } else {
            return null;
        }
    }

}
