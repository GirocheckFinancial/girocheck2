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
package com.smartbt.vtsuite.connector.prod;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.vtsuite.requestBuilder.RequestBuilder;
import com.smartbt.vtsuite.connector.Connector;
import com.smartbt.vtsuite.ws.history.hold.CBHoldListInqMtvnSvcReq;
import com.smartbt.vtsuite.ws.history.hold.CBHoldListInqMtvnSvcRes;
import com.smartbt.vtsuite.ws.history.hold.MtvnCWCBHoldListInqWSV2;
import com.smartbt.vtsuite.ws.history.hold.MtvnCWCBHoldListInqWSV2Interface;
import java.util.Map;
import javax.xml.ws.BindingProvider;

public class FissTransactionHistoryHoldConnector implements Connector {

    private MtvnCWCBHoldListInqWSV2Interface port;
    private MtvnCWCBHoldListInqWSV2 service;

    private static FissTransactionHistoryHoldConnector INSTANCE;

    public static synchronized FissTransactionHistoryHoldConnector get() {
        if (INSTANCE == null) {
            INSTANCE = new FissTransactionHistoryHoldConnector();
        }
        return INSTANCE;
    }

    public FissTransactionHistoryHoldConnector() {
        String url = "";
        try {
            service = new MtvnCWCBHoldListInqWSV2();
            port = service.getMtvnCWCBHoldListInqWSV2Port();

            url = "https://xmlgateway.metavante.org/ConnectwareWS/CBHoldListInqWSV2";
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, ">[FissTransactionHistoryHoldConnector] URL: " + url, null);

            BindingProvider bindingProvider = (BindingProvider) port;
            bindingProvider.getRequestContext().put(
                    BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                    url);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void callWS(Map<ParameterName, Object> params) {
        CBHoldListInqMtvnSvcReq request = RequestBuilder.buildTransactionHistoryHoldRequest(params);

        CBHoldListInqMtvnSvcRes response = port.cbHoldListInq(request);

        int a = 3;
    }

}
