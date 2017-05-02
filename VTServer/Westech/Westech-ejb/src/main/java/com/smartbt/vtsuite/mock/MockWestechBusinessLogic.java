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
package com.smartbt.vtsuite.mock;

import com.smartbt.girocheck.common.VTSuiteMessages;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.vtsuite.dev.CheckService;
import com.smartbt.vtsuite.dev.CheckServiceSoap;
import static com.smartbt.vtsuite.manager.WestechBusinessLogic.WT_PASSWORD;
import static com.smartbt.vtsuite.manager.WestechBusinessLogic.WT_USERNAME;
import static com.smartbt.vtsuite.manager.WestechBusinessLogic.buildIdProofXML;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javaxt.utils.Base64;

public class MockWestechBusinessLogic { 
    private static MockWestechBusinessLogic INSTANCE;
 
    public static synchronized MockWestechBusinessLogic get() {
        if (INSTANCE == null) {
            INSTANCE = new MockWestechBusinessLogic();
        }
        return INSTANCE;
    }
 

    public DirexTransactionResponse process(DirexTransactionRequest request) throws Exception {
        Map transactionData = request.getTransactionData();
        DirexTransactionResponse direxTransactionResponse = new DirexTransactionResponse();

        TransactionType transactionType = request.getTransactionType();

        Map responseMap = null;

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[WestechBusinessLogic] proccessing:: " + transactionType, null);

        switch (transactionType) {
            case WESTECH_CHECK_PROCESS:
                responseMap = checkProcess(transactionData);
                break;
        }

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[WestechBusinessLogic] Finish " + transactionType, null);

        direxTransactionResponse.setResultCode(ResultCode.SUCCESS);
        direxTransactionResponse.setResultMessage(VTSuiteMessages.SUCCESS);
        
        if(responseMap != null){
            direxTransactionResponse.getTransactionData().putAll(responseMap);
        }

        return direxTransactionResponse;
    }

    public Map checkProcess(Map params) {
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[WestechBusinessLogic] Calling method insertTransaction", null);
        
        byte[] checkFront = (byte[])params.get(ParameterName.CHECK_FRONT);
        byte[] checkBack = (byte[])params.get(ParameterName.CHECK_BACK);
        byte[] idProof = (byte[])params.get(ParameterName.IDFRONT);
        
        String idProofXML = buildIdProofXML(params);
        byte[] idProof2dBarcode = Base64.decode(idProofXML);
        
        String log = printRequest(checkFront,checkBack,idProof, idProofXML);
        System.out.println(log);
         
        Map map = new HashMap();
        map.put(ParameterName.CHECK_ID, "1");
        return map;
    }

   
    
    public static  String printRequest(byte[] checkFront,byte[]  checkBack,byte[]  idProof, String idProof2dBarcode){
         StringBuilder sb = new StringBuilder();
        sb.append("<WestechRequest>").append('\n');
        sb.append("<Username>").append(WT_USERNAME).append("</Username>").append('\n');
        sb.append("<Password>").append(WT_PASSWORD).append("</Password>").append('\n');
        
        if(checkFront != null && checkFront.length > 0){
            sb.append("<CheckFront>").append("AN IMAGE").append("</CheckFront>").append('\n');
        }
        
        if(checkBack != null && checkBack.length > 0){
            sb.append("<checkBack>").append("AN IMAGE").append("</checkBack>").append('\n');
        }
        
        if(idProof != null && idProof.length > 0){
            sb.append("<idProof>").append("AN IMAGE").append("</idProof>").append('\n');
        }
        
        sb.append(idProof2dBarcode);
         sb.append("</WestechRequest>").append('\n');
        return sb.toString();
    }
    
//    <IdProofInformation>
//  <Firstname>Mr.Alan</Firstname>
//  <Lastname>C</Lastname>
//  <DateofBirth>06-01-1985</DateofBirth>
//  <Address>703 NW 62ND AVE. SUITE 230</Address>
//  <City>MIAMI</City>
// <State>FL</State>
// <ZipCode>33126</ZipCode>
//</IdProofInformation>
}
