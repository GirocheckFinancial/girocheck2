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
import com.smartbt.vtsuite.ws.history.details.CBHistTxnDtlInqMtvnSvcReq;
import com.smartbt.vtsuite.ws.history.details.CBHistTxnDtlInqMtvnSvcRes;
import com.smartbt.vtsuite.ws.history.details.MtvnCWCBHistTxnDtlInqWSV6;
import com.smartbt.vtsuite.ws.history.details.MtvnCWCBHistTxnDtlInqWSV6Interface;
import java.util.Map;
import javax.xml.ws.BindingProvider;

public class FissTransactionHistoryDetailsConnector extends Connector {

    private MtvnCWCBHistTxnDtlInqWSV6Interface port;
    private MtvnCWCBHistTxnDtlInqWSV6 service;

    private static FissTransactionHistoryDetailsConnector INSTANCE;

    public static synchronized FissTransactionHistoryDetailsConnector get() {
        if (INSTANCE == null) {
            INSTANCE = new FissTransactionHistoryDetailsConnector();
        }
        return INSTANCE;
    }

    public FissTransactionHistoryDetailsConnector() {
        String url = "";
        try {
            service = new MtvnCWCBHistTxnDtlInqWSV6();
            port = service.getMtvnCWCBHistTxnDtlInqWSV6Port();

            url = "https://xmlgateway.metavante.org/ConnectwareWS/CBHistTxnDtlInqWSV6";
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, ">[FissTransactionHistoryDetailsConnector] URL: " + url, null);

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
        CBHistTxnDtlInqMtvnSvcReq request = RequestBuilder.buildTransactionHistoryDetailsRequest(params);
        CBHistTxnDtlInqMtvnSvcRes response = port.cbHistTxnDtlInq(request);

        return null;
    }

}
