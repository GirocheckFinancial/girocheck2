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

import com.smartbt.vtsuite.connector.prod.*;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.vtsuite.requestBuilder.RequestBuilder;
import com.smartbt.vtsuite.connector.Connector;
import com.smartbt.vtsuite.ws.balanceInquiry.CBAcctInqMtvnSvcReq;
import com.smartbt.vtsuite.ws.balanceInquiry.CBAcctInqMtvnSvcRes;
import com.smartbt.vtsuite.ws.balanceInquiry.MtvnCWCBAcctInqWSV15;
import com.smartbt.vtsuite.ws.balanceInquiry.MtvnCWCBAcctInqWSV15Interface;
import com.smartbt.vtsuite.ws.cardPersonalization.CBNmeAddrChgMtvnSvcReq;
import com.smartbt.vtsuite.ws.cardPersonalization.CBNmeAddrChgMtvnSvcRes;
import com.smartbt.vtsuite.ws.cardPersonalization.MtvnCWCBNmeAddrChgWSV13;
import com.smartbt.vtsuite.ws.cardPersonalization.MtvnCWCBNmeAddrChgWSV13Interface;
import java.util.Map;
import javax.xml.ws.BindingProvider;

public class MockFissCardActivationConnector implements Connector {

    private static MockFissCardActivationConnector INSTANCE;

    public static synchronized MockFissCardActivationConnector get() {
        if (INSTANCE == null) {
            INSTANCE = new MockFissCardActivationConnector();
        }
        return INSTANCE;
    }

    public void callWS(Map<ParameterName, Object> params) {
        RequestBuilder.buildCardActivationRequest(params);

        int a = 3;
    }

}
