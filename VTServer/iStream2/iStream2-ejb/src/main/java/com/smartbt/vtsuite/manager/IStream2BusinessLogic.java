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
package com.smartbt.vtsuite.manager;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.enums.ResultMessage;
import com.smartbt.girocheck.servercommon.utils.IMap;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.vtsuite.boundary.util.MapUtil;
import com.smartbt.vtsuite.boundary.ws.AuxField;
import com.smartbt.vtsuite.boundary.ws.Image;
import com.smartbt.vtsuite.boundary.ws.TLS;
import com.smartbt.vtsuite.boundary.ws.TLS_Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.ws.BindingProvider;

/**
 * Mpowa Business Logic Class
 */
public class IStream2BusinessLogic {

    public static final String ISTREAM_USERNAME = System.getProperty("ISTREAM_USERNAME");
    public static final String ISTREAM_PASSWORD = System.getProperty("ISTREAM_PASSWORD");
    private static IStream2BusinessLogic INSTANCE;

    public static synchronized IStream2BusinessLogic get() {
        if (INSTANCE == null) {
            INSTANCE = new IStream2BusinessLogic();
        }
        return INSTANCE;
    }
    private TLS_Service service;
    private TLS port;
//    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger( iStream2BusinessLogic.class );

    /**
     * Constructor
     */
    public IStream2BusinessLogic() {

        String url = "";
        try {

            service = new TLS_Service();
            port = service.getTLSPort();

            url = "https://www.istreamdeposit.com/TLS";

            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, ">[IStream2BusinessLogic] URL: " + url, null);

            BindingProvider bindingProvider = (BindingProvider) port;
            bindingProvider.getRequestContext().put(
                    BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                    url);

        } catch (Exception ex) {
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[IStream2BusinessLogic] Error", ex.getMessage());
        }

    }
//USer: GCTLS
//PW: jpl500
//LocationId: 4769778

    public DirexTransactionResponse process(DirexTransactionRequest request, byte[] checkBack, byte[] checkFront) throws Exception {
        System.out.println("IStream2BusinessLogic -> process");
        System.out.println("IStream2BusinessLogic -> process:: checkBack = " + checkBack);
        System.out.println("IStream2BusinessLogic -> process:: checkFront = " + checkFront);
        try {
            Map transactionData = request.getTransactionData();
            DirexTransactionResponse direxTransactionResponse = new DirexTransactionResponse();
            String userName = ISTREAM_USERNAME; //"GCTLS";
            String password = ISTREAM_PASSWORD; //"jpl500";  
            String locationIdStr = MapUtil.getStringValueFromMap(transactionData, ParameterName.TERMINAL_ID_ISTREAM, true);
            Integer locationId = Integer.parseInt(locationIdStr);
            String ammount = MapUtil.getStringValueFromMap(transactionData, ParameterName.AMOUNT, true);
            String depositName = "Deposit at " + (new Date());
            String micr = MapUtil.getStringValueFromMap(transactionData, ParameterName.MICR, true);

            if (micr != null) {
                micr = micr.replaceAll("C", "<").replaceAll("A", ":").replaceAll("D", "=");
            }

            String cutomerItemId = MapUtil.getStringValueFromMap(transactionData, ParameterName.CHECK_ID, true);

            if (checkBack == null) {
                checkBack = (byte[]) transactionData.get(ParameterName.CHECK_BACK);
                System.out.println(" byte[] checkBack = (byte[]) transactionData.get(ParameterName.CHECK_BACK);");
            }

            if (checkFront == null) {
                checkFront = (byte[]) transactionData.get(ParameterName.CHECK_FRONT);
                System.out.println(" byte[] checkBack = (byte[]) transactionData.get(ParameterName.CHECK_FRONT);");
            }

            Image tiffImage = new Image();
            tiffImage.setImgBackBinary(checkBack);
            tiffImage.setImgFrontBinary(checkFront);
            Image highQualityImage = new Image();

            highQualityImage.setImgBackBinary(checkBack);
            highQualityImage.setImgFrontBinary(checkFront);
            List<AuxField> auxFields = null;
            TransactionType transactionType = request.getTransactionType();

            IMap response = null;
            Map transactionResponseMap = new HashMap();

            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[iStream2BusinessLogic] proccessing:: " + transactionType, null);

            switch (transactionType) {
                case ISTREAM2_SEND_SINGLE_ICL:
                    String log = requestToString(userName, password, "" + locationId, ammount, depositName, micr, cutomerItemId, checkBack, checkFront, null);
                    System.out.println("----------- IStrean -> SendSingleICL -----------");
                    System.out.println(log);
                    response = (IMap) port.sendSingleICL(userName, password, locationId, ammount, depositName, micr, cutomerItemId, tiffImage, highQualityImage, auxFields);
                    break;
            }

            if (response != null) {
                transactionResponseMap = response.toMap();
            }
            direxTransactionResponse.setTransactionData(transactionResponseMap);
            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[iStream2BusinessLogic] Finish " + transactionType, null);

            return direxTransactionResponse;
        } catch (Exception e) {
            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[iStream2BusinessLogic] Host connection failed", null);
            e.printStackTrace();
            return DirexTransactionResponse.forException(ResultCode.ISTREAM2_HOST_ERROR, ResultMessage.ISTREAM_FAILED, "IStream2 failed all attempts to connect.", "0");

        }
    }

    public static String requestToString(String userName, String password, String locationId, String ammount, String depositName, String micr, String cutomerItemId, byte[] CHECK_BACK, byte[] CHECK_FRONT, String auxFields) {
        StringBuilder s = new StringBuilder();
        s.append("<SendSingleICL>").append('\n');
        s.append("    <userName>").append(userName).append("</userName>").append('\n');
        s.append("    <password>").append(password).append("</password>").append('\n');
        s.append("    <locationId>").append(locationId).append("</locationId>").append('\n');
        s.append("    <ammount>").append(ammount).append("</ammount>").append('\n');
        s.append("    <depositName>").append(depositName).append("</depositName>").append('\n');
        s.append("    <micr>").append(micr).append("</micr>").append('\n');
        s.append("    <cutomerItemId>").append(cutomerItemId).append("</cutomerItemId>").append('\n');

        s.append("    <highQualityImage>").append('\n');
        if (CHECK_BACK != null && CHECK_BACK.length != 0) {
            s.append("    <CHECK_BACK>").append("AN IMAGE").append("</CHECK_BACK>").append('\n');
        }
        if (CHECK_FRONT != null && CHECK_FRONT.length != 0) {
            s.append("    <CHECK_FRONT>").append("AN IMAGE").append("</CHECK_FRONT>").append('\n');
        }
        s.append("    </highQualityImage>").append('\n');

        s.append("    <tiffImage>").append('\n');
        if (CHECK_BACK != null && CHECK_BACK.length != 0) {
            s.append("    <CHECK_BACK>").append("AN IMAGE").append("</CHECK_BACK>").append('\n');
        }
        if (CHECK_FRONT != null && CHECK_FRONT.length != 0) {
            s.append("    <CHECK_FRONT>").append("AN IMAGE").append("</CHECK_FRONT>").append('\n');
        }
        s.append("    </tiffImage>").append('\n');
        //  s.append("    <auxFields>").append(auxFields).append("</auxFields>").append('\n');
        s.append("</SendSingleICL>").append('\n');

        return s.toString();
    }
}
