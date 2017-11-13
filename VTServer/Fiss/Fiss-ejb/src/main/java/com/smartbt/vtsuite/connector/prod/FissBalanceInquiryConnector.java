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
import com.smartbt.vtsuite.ws.balanceInquiry.CBAcctInqMtvnSvcReq;
import com.smartbt.vtsuite.ws.balanceInquiry.CBAcctInqMtvnSvcRes;
import com.smartbt.vtsuite.ws.balanceInquiry.MtvnCWCBAcctInqWSV15;
import com.smartbt.vtsuite.ws.balanceInquiry.MtvnCWCBAcctInqWSV15Interface;
import java.util.Map;
import javax.xml.ws.BindingProvider;

public class FissBalanceInquiryConnector implements Connector {

    private MtvnCWCBAcctInqWSV15Interface port;
    private MtvnCWCBAcctInqWSV15 service;

    private static FissBalanceInquiryConnector INSTANCE;

    public static synchronized FissBalanceInquiryConnector get() {
        if (INSTANCE == null) {
            INSTANCE = new FissBalanceInquiryConnector();
        }
        return INSTANCE;
    }

    public FissBalanceInquiryConnector() {
        String url = "";
        try {
            service = new MtvnCWCBAcctInqWSV15();
            port = service.getMtvnCWCBAcctInqWSV15Port();

            url = "https://xmlgateway.metavante.org/ConnectwareWS/CBAcctInqWSV15";
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, ">[FissBalanceInquiryConnector] URL: " + url, null);

            BindingProvider bindingProvider = (BindingProvider) port;
            bindingProvider.getRequestContext().put(
                    BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                    url);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void callWS(Map<ParameterName, Object> params){
        CBAcctInqMtvnSvcReq request = RequestBuilder.buildBalanceInquiryRequest(params);

        CBAcctInqMtvnSvcRes response = port.cbAcctInq(request);

        int a = 3;
    }

}
