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
import com.smartbt.vtsuite.ws.history.general.CBHistTxnInqMtvnSvcReq;
import com.smartbt.vtsuite.ws.history.general.CBHistTxnInqMtvnSvcRes;
import com.smartbt.vtsuite.ws.history.general.MtvnCWCBHistTxnInqWSV7;
import com.smartbt.vtsuite.ws.history.general.MtvnCWCBHistTxnInqWSV7Interface;
import com.smartbt.vtsuite.ws.history.hold.CBHoldListInqMtvnSvcReq;
import com.smartbt.vtsuite.ws.history.hold.CBHoldListInqMtvnSvcRes;
import java.util.Map;
import javax.xml.ws.BindingProvider;

public class FissTransactionHistoryGeneralConnector implements Connector {

    private MtvnCWCBHistTxnInqWSV7Interface port;
    private MtvnCWCBHistTxnInqWSV7 service;

    private static FissTransactionHistoryGeneralConnector INSTANCE;

    public static synchronized FissTransactionHistoryGeneralConnector get() {
        if (INSTANCE == null) {
            INSTANCE = new FissTransactionHistoryGeneralConnector();
        }
        return INSTANCE;
    }

    public FissTransactionHistoryGeneralConnector() {
        String url = "";
        try {
            service = new MtvnCWCBHistTxnInqWSV7();
            port = service.getMtvnCWCBHistTxnInqWSV7Port();

            url = "https://xmlgateway.metavante.org/ConnectwareWS/CBHistTxnInqWSV7";
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, ">[FissTransactionHistoryGeneralConnector] URL: " + url, null);

            BindingProvider bindingProvider = (BindingProvider) port;
            bindingProvider.getRequestContext().put(
                    BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                    url);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void callWS(Map<ParameterName, Object> params) {
        CBHistTxnInqMtvnSvcReq request = RequestBuilder.buildTransactionHistoryGeneralRequest(params); 
        CBHistTxnInqMtvnSvcRes response = port.cbHistTxnInq(request);

        int a = 3;
    }

}
