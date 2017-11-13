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
import com.smartbt.vtsuite.ws.setProductId.CBProdIDMaintMtvnSvcReq;
import com.smartbt.vtsuite.ws.setProductId.CBProdIDMaintMtvnSvcRes;
import com.smartbt.vtsuite.ws.setProductId.MtvnCWCBProdIDMaintWSV1;
import com.smartbt.vtsuite.ws.setProductId.MtvnCWCBProdIDMaintWSV1Interface;
import java.util.Map;
import javax.xml.ws.BindingProvider;

public class MockFissSetProductIdConnector implements Connector {
 
    private static MockFissSetProductIdConnector INSTANCE;

    public static synchronized MockFissSetProductIdConnector get() {
        if (INSTANCE == null) {
            INSTANCE = new MockFissSetProductIdConnector();
        }
        return INSTANCE;
    }
 
    public void callWS(Map<ParameterName, Object> params){
        CBProdIDMaintMtvnSvcReq request = RequestBuilder.buildSetProductIdRequest(params); 

        int a = 3;
    }

}
