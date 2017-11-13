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
import com.smartbt.vtsuite.ws.setPin.CBPinOffsetChgMtvnSvcReq;
import com.smartbt.vtsuite.ws.setPin.CBPinOffsetChgMtvnSvcRes;
import com.smartbt.vtsuite.ws.setPin.MtvnCWCBPinOffsetChgWSV1;
import com.smartbt.vtsuite.ws.setPin.MtvnCWCBPinOffsetChgWSV1Interface;
import java.util.Map;
import javax.xml.ws.BindingProvider;

public class MockSetPinConnector implements Connector {
 
    private static MockSetPinConnector INSTANCE;

    public static synchronized MockSetPinConnector get() {
        if (INSTANCE == null) {
            INSTANCE = new MockSetPinConnector();
        }
        return INSTANCE;
    }
 

    public void callWS(Map<ParameterName, Object> params){
        CBPinOffsetChgMtvnSvcReq request = RequestBuilder.buildSetPinRequest(params);
 
        int a = 3;
    }

}
