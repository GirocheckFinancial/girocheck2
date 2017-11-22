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
import com.smartbt.vtsuite.ws.cardCashing.CBPrpdAdjMtvnSvcReq;
import com.smartbt.vtsuite.ws.cardCashing.CBPrpdAdjMtvnSvcRes;
import com.smartbt.vtsuite.ws.cardCashing.CBPrpdAdjResData;
import com.smartbt.vtsuite.ws.cardCashing.MtvnCWCBPrpdAdjWSV5;
import com.smartbt.vtsuite.ws.cardCashing.MtvnCWCBPrpdAdjWSV5Interface;
import java.util.HashMap;
import java.util.Map;
import javax.xml.ws.BindingProvider;

public class FissCardLoadCashConnector extends Connector {

    private MtvnCWCBPrpdAdjWSV5Interface port;
    private MtvnCWCBPrpdAdjWSV5 service;

    private static FissCardLoadCashConnector INSTANCE;

    public static synchronized FissCardLoadCashConnector get() {
        if (INSTANCE == null) {
            INSTANCE = new FissCardLoadCashConnector();
        }
        return INSTANCE;
    }

    public FissCardLoadCashConnector() {
        String url = "";
        try {
            service = new MtvnCWCBPrpdAdjWSV5();
            port = service.getMtvnCWCBPrpdAdjWSV5Port();

            url = "https://xmlgateway.metavante.org/ConnectwareWS/CBPrpdAdjWSV5";
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, ">[FissCardLoadCashConnector] URL: " + url, null);

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
        CBPrpdAdjMtvnSvcReq request = RequestBuilder.buildCardLoadCashRequest(params);

        CBPrpdAdjMtvnSvcRes response = port.cbPrpdAdj(request);

        Map<FissParam, Object> responseMap = new HashMap<>();

        Boolean success = response != null && response.getSvc() != null
                && !response.getSvc().isEmpty()
                && response.getSvc().get(0).getErrCde().equals("0");

        responseMap.put(FissParam.SUCCESS, success);

        if (response.getSvc() != null && !response.getSvc().isEmpty()
                && response.getSvc().get(0).getMsgData() != null
                && response.getSvc().get(0).getMsgData().getCBPrpdAdjResData() != null) {

            CBPrpdAdjResData data = response.getSvc().get(0).getMsgData().getCBPrpdAdjResData();

            if (data.getApplMsgLst() != null && data.getApplMsgLst().getApplMsg() != null
                    && !data.getApplMsgLst().getApplMsg().isEmpty()) {
                responseMap.put(FissParam.RESULT_MESSAGE, data.getApplMsgLst().getApplMsg().get(0).getApplMsgTxt());
            }
        }

        return responseMap;
    }

}
