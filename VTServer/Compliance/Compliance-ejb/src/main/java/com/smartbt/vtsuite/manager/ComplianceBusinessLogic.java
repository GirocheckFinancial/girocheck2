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
import com.smartbt.girocheck.common.VTSuiteMessages;
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

        CLIENT = WebClient.create("http://10.10.11.153:2000/RestComplex.svc/", providers).accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON);

    }

    public static void main(String[] args) {
        ComplianceBusinessLogic cbl = new ComplianceBusinessLogic();
        //cbl.CLIENT.path("testservice");
        cbl.CLIENT.replacePath("postTransaction");
        // cbl.CLIENT.path("newBranch");
        PostTransactionRequest request = new PostTransactionRequest(null);
        Response r = cbl.CLIENT.post(request);

        ComplianceResponse response = r.readEntity(ComplianceResponse.class);
        System.out.println("Status = " + response.getStatus());
        System.out.println("Message = " + response.getMessage());
    }
//
//    public static void main(String[] args) {
//        ComplianceBusinessLogic cbl = new ComplianceBusinessLogic();
//        //cbl.CLIENT.path("testservice");
//        cbl.CLIENT.replacePath("newBranch");
//        // cbl.CLIENT.path("newBranch");
//        NewBranchRequest request = new NewBranchRequest(null);
//        Response r = cbl.CLIENT.post(request);
//
//        ComplianceResponse response = r.readEntity(ComplianceResponse.class);
//        System.out.println("Status = " + response.getStatus());
//        System.out.println("Message = " + response.getMessage());
//    }

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

        System.out.println("------ Compliance Response ------");
        System.out.println("Status = " + complianceResponse.getStatus());
        System.out.println("Message = " + complianceResponse.getMessage());
        System.out.println("");
        
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[ComplianceBusinessLogic] Finish " + transactionType, null);

        if (complianceResponse != null && complianceResponse.getStatus() != null && complianceResponse.getStatus().equals("200")) {
            direxTransactionResponse.setResultCode(ResultCode.SUCCESS);
            direxTransactionResponse.setResultMessage(VTSuiteMessages.SUCCESS);
        } else {
            direxTransactionResponse.setApproved(false);
            direxTransactionResponse.setResultCode(ResultCode.COMPLIANCE_HOST_ERROR);
            if (response != null) {
                direxTransactionResponse.setResultMessage(complianceResponse.getMessage());
                direxTransactionResponse.setErrorCode(complianceResponse.getStatus());
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
