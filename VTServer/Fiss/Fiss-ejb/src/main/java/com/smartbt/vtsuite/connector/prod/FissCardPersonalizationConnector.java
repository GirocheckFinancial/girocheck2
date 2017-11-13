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
import com.smartbt.vtsuite.ws.cardPersonalization.CBNmeAddrChgMtvnSvcReq;
import com.smartbt.vtsuite.ws.cardPersonalization.CBNmeAddrChgMtvnSvcRes;
import com.smartbt.vtsuite.ws.cardPersonalization.MtvnCWCBNmeAddrChgWSV13;
import com.smartbt.vtsuite.ws.cardPersonalization.MtvnCWCBNmeAddrChgWSV13Interface;
import java.util.Map;
import javax.xml.ws.BindingProvider;

public class FissCardPersonalizationConnector implements Connector {

    private MtvnCWCBNmeAddrChgWSV13Interface port;
    private MtvnCWCBNmeAddrChgWSV13 service;

    private static FissCardPersonalizationConnector INSTANCE;

    public static synchronized FissCardPersonalizationConnector get() {
        if (INSTANCE == null) {
            INSTANCE = new FissCardPersonalizationConnector();
        }
        return INSTANCE;
    }

    public FissCardPersonalizationConnector() {
        String url = "";
        try {
            service = new MtvnCWCBNmeAddrChgWSV13();
            port = service.getMtvnCWCBNmeAddrChgWSV13Port();

            url = "https://xmlgateway.metavante.org/ConnectwareWS/CBNmeAddrChgWSV13";
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, ">[FissCardPersonalizationConnector] URL: " + url, null);

            BindingProvider bindingProvider = (BindingProvider) port;
            bindingProvider.getRequestContext().put(
                    BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                    url);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void callWS(Map<ParameterName, Object> params){
        CBNmeAddrChgMtvnSvcReq request = RequestBuilder.buildCardPersonalizationRequest(params);

        CBNmeAddrChgMtvnSvcRes response = port.cbNmeAddrChg(request);

        int a = 3;
    }

}
