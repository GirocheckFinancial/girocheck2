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
import com.smartbt.vtsuite.ws.cardLoad.CBPrpdLdUnldMtvnSvcReq;
import com.smartbt.vtsuite.ws.cardLoad.CBPrpdLdUnldMtvnSvcRes;
import com.smartbt.vtsuite.ws.cardLoad.MtvnCWCBPrpdLdUnldWSV3;
import com.smartbt.vtsuite.ws.cardLoad.MtvnCWCBPrpdLdUnldWSV3Interface;
import java.util.Map;
import javax.xml.ws.BindingProvider;

public class FissCardLoadConnector implements Connector {

    private MtvnCWCBPrpdLdUnldWSV3Interface port;
    private MtvnCWCBPrpdLdUnldWSV3 service;

    private static FissCardLoadConnector INSTANCE;

    public static synchronized FissCardLoadConnector get() {
        if (INSTANCE == null) {
            INSTANCE = new FissCardLoadConnector();
        }
        return INSTANCE;
    }

    public FissCardLoadConnector() {
        String url = "";
        try {
            service = new MtvnCWCBPrpdLdUnldWSV3();
            port = service.getMtvnCWCBPrpdLdUnldWSV3Port();

            url = "https://xmlgateway.metavante.org/ConnectwareWS/CBPrpdLdUnldWSV3";
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, ">[FissCardLoadConnector] URL: " + url, null);

            BindingProvider bindingProvider = (BindingProvider) port;
            bindingProvider.getRequestContext().put(
                    BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                    url);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void callWS(Map<ParameterName, Object> params){
        CBPrpdLdUnldMtvnSvcReq request = RequestBuilder.buildCardLoadRequest(params);

        CBPrpdLdUnldMtvnSvcRes response = port.cbPrpdLdUnld(request);

        int a = 3;
    }

}
