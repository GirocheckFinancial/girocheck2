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

import com.google.gson.Gson;
import com.smartbt.entity.NewBranchRequest;
import com.smartbt.entity.ComplianceResponse;
import com.smartbt.entity.PostTransactionRequest;
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.cxf.jaxrs.client.WebClient;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

public class ComplianceBusinessLogic {
    public static final  String COMPLIANCE_URL = System.getProperty("COMPLIANCE_URL");//"12345";
    
    private static WebClient CLIENT;
    private static ComplianceBusinessLogic INSTANCE;

    public static synchronized ComplianceBusinessLogic get() {
        if (INSTANCE == null) {
            INSTANCE = new ComplianceBusinessLogic();
        }
        return INSTANCE;
    }

    public ComplianceBusinessLogic() {
        List<Object> providers = new ArrayList<Object>();
        providers.add(new JacksonJsonProvider());

        String url = COMPLIANCE_URL;
        
        if(url == null || url.isEmpty()){
            url = "http://10.10.11.153:2000/RestComplex.svc/";
        }
        System.out.println("ComplianceBusinessLogic :: url = " + url);
        
        CLIENT = WebClient.create(url, providers).accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON);

    }
  
    public DirexTransactionResponse process(DirexTransactionRequest request) throws Exception {
        Map transactionData = request.getTransactionData();
        DirexTransactionResponse direxTransactionResponse = new DirexTransactionResponse();

        TransactionType transactionType = request.getTransactionType();

        Response response = null;

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[ComplianceBusinessLogic] proccessing :::: " + transactionType, null);

        switch (transactionType) {
            case COMPLIANCE_NEW_BRANCH:
                response = newBranch(transactionData);
                break;
            case COMPLIANCE_POST_TRANSACTION:
                response = postTransaction(transactionData);
                break;
        }
        
        String complianceResponseStr = response.readEntity(String.class); 
        
        System.out.println("ComplianceBusinessLogic -> complianceResponseStr = " + complianceResponseStr);
         
        Gson gson = new Gson();
        ComplianceResponse complianceResponse= gson.fromJson(complianceResponseStr, ComplianceResponse.class);

        
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[ComplianceBusinessLogic] Finish " + transactionType, null);

        if (complianceResponse != null && complianceResponse.getStatus() != null 
                && complianceResponse.getStatus().equals("100")) {
            System.out.println("------ Compliance Response ------");
            System.out.println("Status = " + complianceResponse.getStatus());
            System.out.println("Message = " + complianceResponse.getMessage());
            System.out.println("");
        
            direxTransactionResponse.setResultCode(ResultCode.SUCCESS);
            direxTransactionResponse.setResultMessage(complianceResponse.getMessage());
        } else {
            direxTransactionResponse.setApproved(false);
            direxTransactionResponse.setResultCode(ResultCode.COMPLIANCE_HOST_ERROR);
            if (complianceResponse != null) {
                direxTransactionResponse.setResultMessage(complianceResponse.getMessage());
                direxTransactionResponse.setErrorCode(complianceResponse.getStatus());
            }else{
                direxTransactionResponse.setResultMessage("Compliance return null.");
                direxTransactionResponse.setErrorCode(ResultCode.COMPLIANCE_HOST_ERROR.getCode() + "");
            }
        }

        return direxTransactionResponse;
    }

    public Response newBranch(Map map) { 
        System.out.println("ComplianceBusinessLogic.newBranch()");
        CLIENT.replacePath("newBranch"); 
        NewBranchRequest request = new NewBranchRequest(map);
        return CLIENT.post(request); 
    }

    private Response postTransaction(Map map) {
        System.out.println("ComplianceBusinessLogic.postTransaction()");
        CLIENT.replacePath("postTransaction"); 
        PostTransactionRequest request = new PostTransactionRequest(map);
        Response response = CLIENT.post(request);
         
        return response;
    }
    
    
}
