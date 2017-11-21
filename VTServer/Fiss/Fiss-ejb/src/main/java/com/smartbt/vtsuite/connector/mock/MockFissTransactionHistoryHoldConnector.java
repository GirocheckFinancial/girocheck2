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
import com.smartbt.vtsuite.util.FissParam;
import com.smartbt.vtsuite.ws.history.hold.CBHoldListInqMtvnSvcReq;
import java.util.Map;

public class MockFissTransactionHistoryHoldConnector implements Connector {

    private static MockFissTransactionHistoryHoldConnector INSTANCE;

    public static synchronized MockFissTransactionHistoryHoldConnector get() {
        if (INSTANCE == null) {
            INSTANCE = new MockFissTransactionHistoryHoldConnector();
        }
        return INSTANCE;
    }

    @Override
    public Map<FissParam, Object> callWS(Map<ParameterName, Object> params) {
        CBHoldListInqMtvnSvcReq request = RequestBuilder.buildTransactionHistoryHoldRequest(params);
        return null;
    }

}
