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
import com.smartbt.vtsuite.ws.history.pending.CBPndTxnInqMtvnSvcReq;
import com.smartbt.vtsuite.ws.history.pending.CBPndTxnInqMtvnSvcRes;
import com.smartbt.vtsuite.ws.history.pending.MtvnCWCBPndTxnInqWSV7;
import com.smartbt.vtsuite.ws.history.pending.MtvnCWCBPndTxnInqWSV7Interface;
import java.util.Map;
import javax.xml.ws.BindingProvider;

public class FissTransactionHistoryPendingConnector extends Connector {

    private MtvnCWCBPndTxnInqWSV7Interface port;
    private MtvnCWCBPndTxnInqWSV7 service;

    private static FissTransactionHistoryPendingConnector INSTANCE;

    public static synchronized FissTransactionHistoryPendingConnector get() {
        if (INSTANCE == null) {
            INSTANCE = new FissTransactionHistoryPendingConnector();
        }
        return INSTANCE;
    }

    public FissTransactionHistoryPendingConnector() {
        String url = "";
        try {
            service = new MtvnCWCBPndTxnInqWSV7();
            port = service.getMtvnCWCBPndTxnInqWSV7Port();

            url = "https://xmlgateway.metavante.org/ConnectwareWS/CBPndTxnInqWSV7";
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, ">[FissTransactionHistoryPendingConnector] URL: " + url, null);

            BindingProvider bindingProvider = (BindingProvider) port;
            bindingProvider.getRequestContext().put(
                    BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                    url);

            addLogger(bindingProvider);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public   Map<FissParam, Object>  callWS(Map<ParameterName, Object> params) {
        CBPndTxnInqMtvnSvcReq request = RequestBuilder.buildTransactionHistoryPendingRequest(params);

        CBPndTxnInqMtvnSvcRes response = port.cbPndTxnInq(request);

        return null;
    }

}
