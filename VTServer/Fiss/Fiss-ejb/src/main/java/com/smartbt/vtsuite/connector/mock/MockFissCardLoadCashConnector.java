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
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.vtsuite.requestBuilder.RequestBuilder;
import com.smartbt.vtsuite.connector.Connector;
import com.smartbt.vtsuite.util.FissParam;
import com.smartbt.vtsuite.ws.cardCashing.CBPrpdAdjMtvnSvcReq;
import com.smartbt.vtsuite.ws.cardCashing.CBPrpdAdjMtvnSvcRes;
import com.smartbt.vtsuite.ws.cardCashing.CBPrpdAdjResData;
import com.smartbt.vtsuite.ws.cardCashing.MtvnCWCBPrpdAdjWSV5;
import com.smartbt.vtsuite.ws.cardCashing.MtvnCWCBPrpdAdjWSV5Interface;
import java.util.HashMap;
import java.util.Map;
import javax.xml.ws.BindingProvider;

public class MockFissCardLoadCashConnector extends Connector {

    private static MockFissCardLoadCashConnector INSTANCE;

    public static synchronized MockFissCardLoadCashConnector get() {
        if (INSTANCE == null) {
            INSTANCE = new MockFissCardLoadCashConnector();
        }
        return INSTANCE;
    }

    public Map<FissParam, Object> callWS(Map<ParameterName, Object> params) {
        CBPrpdAdjMtvnSvcReq request = RequestBuilder.buildCardLoadCashRequest(params);

        Map<FissParam, Object> responseMap = new HashMap<>();

        responseMap.put(FissParam.SUCCESS, true);
        responseMap.put(FissParam.RESULT_MESSAGE, "Success");

        return responseMap;
    }

}
