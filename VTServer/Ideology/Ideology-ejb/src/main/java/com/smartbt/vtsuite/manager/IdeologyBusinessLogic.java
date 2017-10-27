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
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import java.util.HashMap;
import java.util.Map;

public class IdeologyBusinessLogic {
    public static final String IDEOLOGY_URL = System.getProperty("IDEOLOGY_URL");
    
    private static IdeologyBusinessLogic INSTANCE;
  

    public static synchronized IdeologyBusinessLogic get() {
        if (INSTANCE == null) {
            INSTANCE = new IdeologyBusinessLogic();
        }
        return INSTANCE;
    }

    public IdeologyBusinessLogic() { 
         
    }

    public DirexTransactionResponse process(DirexTransactionRequest request) throws Exception {
        Map transactionData = request.getTransactionData();
        DirexTransactionResponse direxTransactionResponse = new DirexTransactionResponse();

        TransactionType transactionType = request.getTransactionType();

        Map responseMap = null;

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[IdeologyBusinessLogic] proccessing:: " + transactionType, null);

        switch (transactionType) {
            case IDEOLOGY_VERYFY_CLIENT:
                responseMap = verifyClient(transactionData);
                 
                break;
        }
        
        
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[IdeologyBusinessLogic] Finish " + transactionType, null);

        direxTransactionResponse.setResultCode(ResultCode.SUCCESS);
        direxTransactionResponse.setResultMessage(VTSuiteMessages.SUCCESS);
        
        if(responseMap != null){
            direxTransactionResponse.getTransactionData().putAll(responseMap);
        }

        return direxTransactionResponse;
    }

    public Map verifyClient(Map params) {
        System.out.println("[IdeologyBusinessLogic] verifyClient");
        Map result = new HashMap();
        
        return result;
    }
    
}
