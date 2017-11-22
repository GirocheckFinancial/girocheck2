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
import com.smartbt.vtsuite.ws.balanceInquiry.CBAcctInqMtvnSvcReq;
import com.smartbt.vtsuite.ws.balanceInquiry.CBAcctInqMtvnSvcRes;
import com.smartbt.vtsuite.ws.balanceInquiry.CBAcctInqResData;
import com.smartbt.vtsuite.ws.balanceInquiry.MtvnCWCBAcctInqWSV15;
import com.smartbt.vtsuite.ws.balanceInquiry.MtvnCWCBAcctInqWSV15Interface;
import java.util.HashMap;
import java.util.Map;
import javax.xml.ws.BindingProvider;

public class FissBalanceInquiryConnector extends Connector {

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

            addLogger(bindingProvider);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public Map<FissParam, Object> callWS(Map<ParameterName, Object> params) {
        Map<FissParam, Object> responseMap = new HashMap<>();
        CBAcctInqMtvnSvcReq request = RequestBuilder.buildBalanceInquiryRequest(params);

        CBAcctInqMtvnSvcRes response = port.cbAcctInq(request);

        Boolean success = response != null && response.getSvc() != null
                && !response.getSvc().isEmpty()
                && response.getSvc().get(0).getErrCde().equals("0");

        if (response.getSvc() != null && !response.getSvc().isEmpty()
                && response.getSvc().get(0).getMsgData() != null
                && response.getSvc().get(0).getMsgData().getCBAcctInqResData() != null) {
            CBAcctInqResData data = response.getSvc().get(0).getMsgData().getCBAcctInqResData();

            Integer resultCode = 0;
            Boolean receivedExpectedCode = false;
            if (data.getE130050() != null && !data.getE130050().isEmpty()) {
                try {
                    resultCode = Integer.parseInt(data.getE130050());
                    receivedExpectedCode = (resultCode == 1 || resultCode == 7);
                } catch (Exception e) {
                    System.out.println("[FissBalanceInquiryConnector] Failed to parse ResultCode = " + data.getE130050());
                    e.printStackTrace();
                }
            }

            responseMap.put(FissParam.SUCCESS, success && receivedExpectedCode);
            responseMap.put(FissParam.RESULT_CODE, resultCode);

            if (data.getApplMsgLst() != null && data.getApplMsgLst().getApplMsg() != null
                    && !data.getApplMsgLst().getApplMsg().isEmpty()) {
                responseMap.put(FissParam.RESULT_MESSAGE, data.getApplMsgLst().getApplMsg().get(0).getApplMsgTxt());
            }

            if (!receivedExpectedCode && (!responseMap.containsKey(FissParam.RESULT_MESSAGE)
                    || responseMap.get(FissParam.RESULT_MESSAGE) == null
                    || ((String) responseMap.get(FissParam.RESULT_MESSAGE)).isEmpty())) {
                responseMap.put(FissParam.RESULT_MESSAGE, "Fiss returned unexpected Card Status Code: " + resultCode);
            }
             
            Map<ParameterName, String> fissData = new HashMap<>();
            fissData.put(ParameterName.CARD_NUMBER, data.getE130013());
            fissData.put(ParameterName.FIRST_NAME, data.getE130024());
            fissData.put(ParameterName.LAST_NAME, data.getE130023());
            fissData.put(ParameterName.STREET, data.getE130029());
            fissData.put(ParameterName.CITY, data.getE130031());
            fissData.put(ParameterName.STATE, data.getE130032());
            fissData.put(ParameterName.COUNTRY, data.getE130033());
            fissData.put(ParameterName.ZIPCODE, data.getE130034());
            fissData.put(ParameterName.TELEPHONE, data.getE130138());
            fissData.put(ParameterName.SSN, data.getE130145());
            fissData.put(ParameterName.BORNDATE, data.getE130147());

            responseMap.put(FissParam.FISS_PERSONAL_INFO_DATA, fissData);
            responseMap.put(FissParam.BALANCE, data.getE130491());
        }

        return responseMap;
    }

}
