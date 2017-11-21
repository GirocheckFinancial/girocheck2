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

import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
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
 
        return direxTransactionResponse;
    } 
    
}
