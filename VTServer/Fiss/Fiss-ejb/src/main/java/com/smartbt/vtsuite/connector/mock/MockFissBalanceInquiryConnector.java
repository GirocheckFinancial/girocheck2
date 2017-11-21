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
package com.smartbt.vtsuite.connector.mock;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.vtsuite.requestBuilder.RequestBuilder;
import com.smartbt.vtsuite.connector.Connector;
import com.smartbt.vtsuite.util.FissParam;
import com.smartbt.vtsuite.ws.balanceInquiry.CBAcctInqMtvnSvcReq;
import java.util.HashMap;
import java.util.Map;

public class MockFissBalanceInquiryConnector implements Connector {

    private static MockFissBalanceInquiryConnector INSTANCE;

    public static synchronized MockFissBalanceInquiryConnector get() {
        if (INSTANCE == null) {
            INSTANCE = new MockFissBalanceInquiryConnector();
        }
        return INSTANCE;
    }

    @Override
    public Map<FissParam, Object> callWS(Map<ParameterName, Object> params) {
        CBAcctInqMtvnSvcReq request = RequestBuilder.buildBalanceInquiryRequest(params);
        Map<FissParam, Object> responseMap = new HashMap<>();
        responseMap.put(FissParam.SUCCESS, true);

        Double amount = (Double) params.get(ParameterName.AMMOUNT);

        int resultCode = 7;

        if (amount == 20D) {
            resultCode = 1;
        } else {
            if (amount == 30D) {
                resultCode = 2;
            }
        }

        responseMap.put(FissParam.RESULT_CODE, resultCode);

        responseMap.put(FissParam.RESULT_MESSAGE, "Fiss Success Message");

        Map<ParameterName, String> fissData = new HashMap<>();
        fissData.put(ParameterName.CARD_NUMBER, "2222222222222222");
        fissData.put(ParameterName.FIRST_NAME, "John");
        fissData.put(ParameterName.LAST_NAME, "Smith");
        fissData.put(ParameterName.STREET, "123 Peachtree Street"); 
        fissData.put(ParameterName.CITY, "Atlanta");
        fissData.put(ParameterName.STATE, "GA");
        fissData.put(ParameterName.COUNTRY, "US");
        fissData.put(ParameterName.ZIPCODE, "30313");
        fissData.put(ParameterName.TELEPHONE, "1112223333");
        fissData.put(ParameterName.SSN, "900700001");
        fissData.put(ParameterName.BORNDATE, "21171125");

        responseMap.put(FissParam.FISS_PERSONAL_INFO_DATA, fissData);
        responseMap.put(FissParam.BALANCE, "100");

        if (amount == 50D) {
            responseMap.put(FissParam.RESULT_CODE, "1");
            fissData.put(ParameterName.SSN, "900700005");
        }

        return responseMap;
    }

}
