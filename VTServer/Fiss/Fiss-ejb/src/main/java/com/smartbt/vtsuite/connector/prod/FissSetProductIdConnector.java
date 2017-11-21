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
import com.smartbt.vtsuite.ws.cardLoad.CBPrpdLdUnldResData;
import com.smartbt.vtsuite.ws.setProductId.CBProdIDMaintMtvnSvcReq;
import com.smartbt.vtsuite.ws.setProductId.CBProdIDMaintMtvnSvcRes;
import com.smartbt.vtsuite.ws.setProductId.CBProdIDMaintResData;
import com.smartbt.vtsuite.ws.setProductId.MtvnCWCBProdIDMaintWSV1;
import com.smartbt.vtsuite.ws.setProductId.MtvnCWCBProdIDMaintWSV1Interface;
import java.util.HashMap;
import java.util.Map;
import javax.xml.ws.BindingProvider;

public class FissSetProductIdConnector implements Connector {

    private MtvnCWCBProdIDMaintWSV1Interface port;
    private MtvnCWCBProdIDMaintWSV1 service;

    private static FissSetProductIdConnector INSTANCE;

    public static synchronized FissSetProductIdConnector get() {
        if (INSTANCE == null) {
            INSTANCE = new FissSetProductIdConnector();
        }
        return INSTANCE;
    }

    public FissSetProductIdConnector() {
        String url = "";
        try {
            service = new MtvnCWCBProdIDMaintWSV1();
            port = service.getMtvnCWCBProdIDMaintWSV1Port();

            url = "https://xmlgateway.metavante.org/ConnectwareWS/CBProdIDMaintWSV1";
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, ">[FissSetProductIdConnector] URL: " + url, null);

            BindingProvider bindingProvider = (BindingProvider) port;
            bindingProvider.getRequestContext().put(
                    BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                    url);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public Map<FissParam, Object>  callWS(Map<ParameterName, Object> params){
        CBProdIDMaintMtvnSvcReq request = RequestBuilder.buildSetProductIdRequest(params);

        CBProdIDMaintMtvnSvcRes response = port.cbProdIDMaint(request);

           Map<FissParam, Object> responseMap = new HashMap<>();

        Boolean success = response != null && response.getSvc() != null
                && !response.getSvc().isEmpty()
                && response.getSvc().get(0).getErrCde().equals("0");

        responseMap.put(FissParam.SUCCESS, success);

        if (response.getSvc() != null && !response.getSvc().isEmpty()
                && response.getSvc().get(0).getMsgData() != null
                && response.getSvc().get(0).getMsgData().getCBProdIDMaintResData()!= null) {

            CBProdIDMaintResData data = response.getSvc().get(0).getMsgData().getCBProdIDMaintResData();

            if (data.getApplMsgLst() != null && data.getApplMsgLst().getApplMsg() != null
                    && !data.getApplMsgLst().getApplMsg().isEmpty()) {
                responseMap.put(FissParam.RESULT_MESSAGE, data.getApplMsgLst().getApplMsg().get(0).getApplMsgTxt());
            }
        }

        return responseMap;
    }

}
