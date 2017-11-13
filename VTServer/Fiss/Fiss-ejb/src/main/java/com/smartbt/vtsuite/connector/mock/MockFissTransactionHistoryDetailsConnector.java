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
package com.smartbt.vtsuite.connector.mock;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.vtsuite.requestBuilder.RequestBuilder;
import com.smartbt.vtsuite.connector.Connector;
import com.smartbt.vtsuite.ws.history.details.CBHistTxnDtlInqMtvnSvcReq;
import java.util.Map;

public class MockFissTransactionHistoryDetailsConnector implements Connector {
 
    private static MockFissTransactionHistoryDetailsConnector INSTANCE;

    public static synchronized MockFissTransactionHistoryDetailsConnector get() {
        if (INSTANCE == null) {
            INSTANCE = new MockFissTransactionHistoryDetailsConnector();
        }
        return INSTANCE;
    }
 

    public void callWS(Map<ParameterName, Object> params) {
        CBHistTxnDtlInqMtvnSvcReq request = RequestBuilder.buildTransactionHistoryDetailsRequest(params); 
      
        int a = 3;
    }

}
