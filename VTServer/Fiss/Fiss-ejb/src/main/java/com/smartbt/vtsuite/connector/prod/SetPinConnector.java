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
import com.smartbt.vtsuite.ws.setPin.CBPinOffsetChgMtvnSvcReq;
import com.smartbt.vtsuite.ws.setPin.CBPinOffsetChgMtvnSvcRes;
import com.smartbt.vtsuite.ws.setPin.MtvnCWCBPinOffsetChgWSV1;
import com.smartbt.vtsuite.ws.setPin.MtvnCWCBPinOffsetChgWSV1Interface;
import java.util.Map;
import javax.xml.ws.BindingProvider;

public class SetPinConnector implements Connector {

    private MtvnCWCBPinOffsetChgWSV1Interface port;
    private MtvnCWCBPinOffsetChgWSV1 service;

    private static SetPinConnector INSTANCE;

    public static synchronized SetPinConnector get() {
        if (INSTANCE == null) {
            INSTANCE = new SetPinConnector();
        }
        return INSTANCE;
    }

    public SetPinConnector() {
        String url = "";
        try {
            service = new MtvnCWCBPinOffsetChgWSV1();
            port = service.getMtvnCWCBPinOffsetChgWSV1Port();

            url = "https://xmlgateway.metavante.org/ConnectwareWS/CBPinOffsetChgWSV1";
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, ">[SetPinConnector] URL: " + url, null);

            BindingProvider bindingProvider = (BindingProvider) port;
            bindingProvider.getRequestContext().put(
                    BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                    url);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void callWS(Map<ParameterName, Object> params){
        CBPinOffsetChgMtvnSvcReq request = RequestBuilder.buildSetPinRequest(params);

        CBPinOffsetChgMtvnSvcRes response = port.cbPinOffsetChg(request );

        int a = 3;
    }

}
