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
import com.smartbt.vtsuite.ws.history.pending.CBPndTxnInqMtvnSvcReq;
import java.util.Map;

public class MockFissTransactionHistoryPendingConnector implements Connector {
 
    private static MockFissTransactionHistoryPendingConnector INSTANCE;

    public static synchronized MockFissTransactionHistoryPendingConnector get() {
        if (INSTANCE == null) {
            INSTANCE = new MockFissTransactionHistoryPendingConnector();
        }
        return INSTANCE;
    } 

    public void callWS(Map<ParameterName, Object> params) {
        CBPndTxnInqMtvnSvcReq request = RequestBuilder.buildTransactionHistoryPendingRequest(params);
 
    }

}
