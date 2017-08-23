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
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
import org.json.JSONArray;

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
        WebClient client = WebClient.create("")
                .type(MediaType.APPLICATION_JSON); 
      // client.path("http://maps.google.com/maps/api/geocode/json?address=2322+Hidden+Glen+Dr,+Marietta,+Georgia");
       client.replacePath("http://maps.google.com/maps/api/geocode/json?address=2322+Hidden+Glen+Dr,+Marietta,+Georgia");
        // cbl.CLIENT.path("newBranch"); 
        Response r = client.get();

        String response = r.readEntity(String.class);
        
       
         
//        Gson gson = new Gson();
//        Map map = gson.fromJson(response, Map.class);
        
        JsonParser parser = new JsonParser();
         JsonObject json = (JsonObject) parser.parse(response);
         
         JsonArray results = json.getAsJsonArray("results");
         
         if(results != null && !results.isJsonNull() && results.size() > 0){
             JsonElement result =  results.get(0);
             
             JsonObject address = result.getAsJsonObject();
             
              JsonObject geometry =  address.getAsJsonObject("geometry");
             
              if(geometry != null){ 
                  JsonObject location =  geometry.getAsJsonObject("location");
             
                    if(location != null){  
                         String lat = location.get("lat").getAsString();
                         String lng = location.get("lng").getAsString();
                         
                         
                    } 
                    }
            
         }else{
             System.out.println("null");
         }  
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

        System.out.println("------ Compliance Response ------");
        System.out.println("Status = " + complianceResponse.getStatus());
        System.out.println("Message = " + complianceResponse.getMessage());
        System.out.println("");
        
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[ComplianceBusinessLogic] Finish " + transactionType, null);

        if (complianceResponse != null && complianceResponse.getStatus() != null 
                && complianceResponse.getStatus().equals("100")) {
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
