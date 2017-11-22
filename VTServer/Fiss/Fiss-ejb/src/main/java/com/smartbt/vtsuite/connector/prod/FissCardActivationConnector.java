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
import com.smartbt.vtsuite.util.FissParam;
import com.smartbt.vtsuite.ws.cardActivation.CBNegFleMaintMtvnSvcReq;
import com.smartbt.vtsuite.ws.cardActivation.CBNegFleMaintMtvnSvcRes;
import com.smartbt.vtsuite.ws.cardActivation.CBNegFleMaintResData;
import com.smartbt.vtsuite.ws.cardActivation.MtvnCWCBNegFleMaintWSV1;
import com.smartbt.vtsuite.ws.cardActivation.MtvnCWCBNegFleMaintWSV1Interface;
import java.util.HashMap;
import java.util.Map;
import javax.xml.ws.BindingProvider;

public class FissCardActivationConnector extends Connector {

    private MtvnCWCBNegFleMaintWSV1Interface port;
    private MtvnCWCBNegFleMaintWSV1 service;

    private static FissCardActivationConnector INSTANCE;

    public static synchronized FissCardActivationConnector get() {
        if (INSTANCE == null) {
            INSTANCE = new FissCardActivationConnector();
        }
        return INSTANCE;
    }

    public FissCardActivationConnector() {
        String url = "";
        try {
            service = new MtvnCWCBNegFleMaintWSV1();
            port = service.getMtvnCWCBNegFleMaintWSV1Port();

            url = "https://xmlgateway.metavante.org/ConnectwareWS/CBNegFleMaintWSV1";
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, ">[FissCardPersonalizationConnector] URL: " + url, null);

            BindingProvider bindingProvider = (BindingProvider) port;
            bindingProvider.getRequestContext().put(
                    BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                    url);

            addLogger(bindingProvider);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public Map<FissParam, Object> callWS(Map<ParameterName, Object> params) {
        CBNegFleMaintMtvnSvcReq request = RequestBuilder.buildCardActivationRequest(params);

        CBNegFleMaintMtvnSvcRes response = port.cbNegFleMaint(request);

        Map<FissParam, Object> responseMap = new HashMap<>();

        Boolean success = response != null && response.getSvc() != null
                && !response.getSvc().isEmpty()
                && response.getSvc().get(0).getErrCde().equals("0");

        responseMap.put(FissParam.SUCCESS, success);

        if (response.getSvc() != null && !response.getSvc().isEmpty()
                && response.getSvc().get(0).getMsgData() != null
                && response.getSvc().get(0).getMsgData().getCBNegFleMaintResData() != null) {

            CBNegFleMaintResData data = response.getSvc().get(0).getMsgData().getCBNegFleMaintResData();

            if (data.getApplMsgLst() != null && data.getApplMsgLst().getApplMsg() != null
                    && !data.getApplMsgLst().getApplMsg().isEmpty()) {
                responseMap.put(FissParam.RESULT_MESSAGE, data.getApplMsgLst().getApplMsg().get(0).getApplMsgTxt());
            }
        }

        return responseMap;
    }

}
