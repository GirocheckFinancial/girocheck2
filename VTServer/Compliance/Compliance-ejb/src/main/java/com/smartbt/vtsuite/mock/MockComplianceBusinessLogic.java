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
import java.util.Map;

public class MockComplianceBusinessLogic { 
    private static MockComplianceBusinessLogic INSTANCE;
 
    public static synchronized MockComplianceBusinessLogic get() {
        if (INSTANCE == null) {
            INSTANCE = new MockComplianceBusinessLogic();
        }
        return INSTANCE;
    }
 

    public DirexTransactionResponse process(DirexTransactionRequest request) throws Exception {
        Map transactionData = request.getTransactionData();
        DirexTransactionResponse direxTransactionResponse = new DirexTransactionResponse();

        TransactionType transactionType = request.getTransactionType();

        Map responseMap = null;

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[ComplianceBusinessLogic] proccessing:: " + transactionType, null);

           switch (transactionType) {
            case COMPLIANCE_NEW_BRANCH: 
                break;
            case COMPLIANCE_POST_TRANSACTION: 
                break;
        }

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[ComplianceBusinessLogic] Finish " + transactionType, null);

        direxTransactionResponse.setResultCode(ResultCode.SUCCESS);
        direxTransactionResponse.setResultMessage(VTSuiteMessages.SUCCESS);
        
        if(responseMap != null){
            direxTransactionResponse.getTransactionData().putAll(responseMap);
        }

        return direxTransactionResponse;
    }
 
}
