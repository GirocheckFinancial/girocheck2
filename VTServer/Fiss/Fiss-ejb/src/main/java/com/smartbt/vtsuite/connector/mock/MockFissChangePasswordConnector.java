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
import com.smartbt.vtsuite.ws.changePassword.SZChgPwdMtvnSvcReq;
import java.util.Map;

public class MockFissChangePasswordConnector implements Connector {
 
    private static MockFissChangePasswordConnector INSTANCE;

    public static synchronized MockFissChangePasswordConnector get() {
        if (INSTANCE == null) {
            INSTANCE = new MockFissChangePasswordConnector();
        }
        return INSTANCE;
    } 

    public void callWS(Map<ParameterName, Object> params){
        SZChgPwdMtvnSvcReq request = RequestBuilder.buildChangePasswordRequest(params);
 
        int a = 3;
    }

}