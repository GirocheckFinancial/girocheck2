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

import com.smartbt.girocheck.common.VTSuiteMessages;
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import java.util.HashMap;
import java.util.Map;

public class MockIdeologyBusinessLogic {
    public static final String FISS_URL = System.getProperty("FISS_URL");
    
    private static MockIdeologyBusinessLogic INSTANCE;
  

    public static synchronized MockIdeologyBusinessLogic get() {
        if (INSTANCE == null) {
            INSTANCE = new MockIdeologyBusinessLogic();
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
                responseMap = (transactionData);
                break;
            case TRANSACTION_HISTORY:
                responseMap = balanceInquiryCardValidation(transactionData);
                break;
            case CARD_PERSONALIZATION:
                responseMap = transactionHistory(transactionData);
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
        }
        
        
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[FissBusinessLogic] Finish " + transactionType, null);

        direxTransactionResponse.setResultCode(ResultCode.SUCCESS);
        direxTransactionResponse.setResultMessage(VTSuiteMessages.SUCCESS);
        
        if(responseMap != null){
            direxTransactionResponse.getTransactionData().putAll(responseMap);
        }

        return direxTransactionResponse;
    }

    public Map balanceInquiryCardValidation(Map params) {
        System.out.println("[FissBusinessLogic] balanceInquiryCardValidation");
        Map result = new HashMap();
        
        return result;
    }

    public Map transactionHistory(Map params) {
        System.out.println("[FissBusinessLogic] transactionHistory");
        Map result = new HashMap();
        
        return result;
    }

    public Map cardPersonalization(Map params) {
        System.out.println("[FissBusinessLogic] cardPersonalization");
        Map result = new HashMap();
        
        return result;
    }

    public Map cardActivtion(Map params) {
        System.out.println("[FissBusinessLogic] cardActivtion");
        Map result = new HashMap();
        
        return result;
    }

    public Map cardLoad(Map params) {
        System.out.println("[FissBusinessLogic] cardLoad");
        Map result = new HashMap();
        
        return result;
    }

    public Map cardCashing(Map params) {
        System.out.println("[FissBusinessLogic] cardCashing");
        Map result = new HashMap();
        
        return result;
    }

    
}
