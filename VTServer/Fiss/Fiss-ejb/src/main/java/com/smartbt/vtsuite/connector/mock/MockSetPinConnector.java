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
import com.smartbt.vtsuite.ws.setPin.CBPinOffsetChgMtvnSvcReq;
import java.util.HashMap;
import java.util.Map;

public class MockSetPinConnector extends Connector {

    private static MockSetPinConnector INSTANCE;

    public static synchronized MockSetPinConnector get() {
        if (INSTANCE == null) {
            INSTANCE = new MockSetPinConnector();
        }
        return INSTANCE;
    }

    public Map<FissParam, Object> callWS(Map<ParameterName, Object> params) {
        CBPinOffsetChgMtvnSvcReq request = RequestBuilder.buildSetPinRequest(params);

        
        Map<FissParam, Object> responseMap = new HashMap<>();
        responseMap.put(FissParam.SUCCESS, true); 
        responseMap.put(FissParam.RESULT_MESSAGE, "Fiss Success Message");
 
        return responseMap;
    }

}
