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

import com.smartbt.girocheck.common.VTSuiteMessages;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.vtsuite.entity.IdeologyResponse;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.MediaType;
;
import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;



public class IdeologyBusinessLogic {

    public static String IDEOLOGY_URL = System.getProperty("IDEOLOGY_URL");
    public static String IDEOLOGY_USERNAME = System.getProperty("IDEOLOGY_USERNAME");
    public static String IDEOLOGY_PASSWORD = System.getProperty("IDEOLOGY_PASSWORD");
 
    private static HttpHeaders headers = new HttpHeaders();
    private static RestTemplate restTemplate = new RestTemplate();
    
    private static IdeologyBusinessLogic INSTANCE;

    public static synchronized IdeologyBusinessLogic get() {
        if (INSTANCE == null) {
            INSTANCE = new IdeologyBusinessLogic();
        }
        return INSTANCE;
    }

    public IdeologyBusinessLogic() {
        List<Object> providers = new ArrayList<Object>();
        providers.add(new JacksonJaxbJsonProvider());
        
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Host", "web.ideologylive.com");

        if (IDEOLOGY_URL == null || IDEOLOGY_URL.isEmpty()) {
            IDEOLOGY_URL = "https://web.idologylive.com/api/idiq.svc";
        }
        System.out.println("IdeologyBusinessLogic :: IDEOLOGY_URL = " + IDEOLOGY_URL);

        if (IDEOLOGY_USERNAME == null || IDEOLOGY_USERNAME.isEmpty()) {
            IDEOLOGY_USERNAME = "GiroCheck.API.GPR";
        }

        if (IDEOLOGY_PASSWORD == null || IDEOLOGY_PASSWORD.isEmpty()) {
            IDEOLOGY_PASSWORD = "G!roCk@CbKc2~7Qr";
        }
 
    }

    public DirexTransactionResponse process(DirexTransactionRequest request) throws Exception {
        Map transactionData = request.getTransactionData();
        DirexTransactionResponse direxTransactionResponse = new DirexTransactionResponse();

        TransactionType transactionType = request.getTransactionType();

        Map responseMap = null;

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[IdeologyBusinessLogic] proccessing:: " + transactionType, null);

        switch (transactionType) {
            case IDEOLOGY_VERYFY_CLIENT:
                responseMap = verifyClient(transactionData);

                break;
        }

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[IdeologyBusinessLogic] Finish " + transactionType, null);

        direxTransactionResponse.setResultCode(ResultCode.SUCCESS);
        direxTransactionResponse.setResultMessage(VTSuiteMessages.SUCCESS);

        if (responseMap != null) {
            direxTransactionResponse.getTransactionData().putAll(responseMap);
        }

        return direxTransactionResponse;
    }

    public Map verifyClient(Map params) {
        System.out.println("[IdeologyBusinessLogic] verifyClient");
        
        params = buildMockTransactionDataMap();
        
        MultiValueMap requestMap = buildRequestMap( params);
         
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(requestMap, headers);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(IDEOLOGY_URL, request, String.class);

        String resp = responseEntity.getBody();
        
        System.out.println("Calling Ideology...");

        IdeologyResponse response = IdeologyResponse.getFromXML(resp);

        if (response == null) {
            System.out.println("Response is NULL");
        } else {
            System.out.println(response.toString());
        } 
        
        return new HashMap(); 
    }

    private static MultiValueMap buildRequestMap(Map transactionData) {
        MultiValueMap<String, String> requestMap = new LinkedMultiValueMap<>();

        requestMap.add("username", IDEOLOGY_USERNAME);
        requestMap.add("password", IDEOLOGY_PASSWORD); 
        setpersonalInfo(requestMap, transactionData);
        setDOBFields(requestMap, transactionData);
        setDateFields(requestMap, transactionData); 
        return requestMap;
    }
    
    

    private static void setpersonalInfo(MultiValueMap<String, String> requestMap, Map transactionData) {
        requestMap.add("firstName", (String)transactionData.get(ParameterName.FIRST_NAME));
        requestMap.add("lastName", (String)transactionData.get(ParameterName.LAST_NAME));
        requestMap.add("ssn", (String)transactionData.get(ParameterName.SSN));
    }
    
    private static void setDateFields(MultiValueMap<String, String> requestMap, Map transactionData) {
        requestMap.add("address", (String)transactionData.get(ParameterName.ADDRESS));
        requestMap.add("city", (String)transactionData.get(ParameterName.CITY));
        requestMap.add("state", (String)transactionData.get(ParameterName.STATE_ABBREVIATION));
        requestMap.add("zip",  (String)transactionData.get(ParameterName.ZIPCODE));
    }
    
    private static void setDOBFields(MultiValueMap<String, String> requestMap, Map transactionData) {
        Date dob = (Date) transactionData.get(ParameterName.BORNDATE_AS_DATE);

        if (dob != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(dob);
            int day = dob.getDate();
            int month = dob.getMonth() + 1;
            int year = dob.getYear() + 1900;

            requestMap.add("dobDay", day + "");
            requestMap.add("dobMonth", month + "");
            requestMap.add("dobYear", year + "");
        }

    }

    public static void main(String[] args) throws Exception {
        System.out.println((new IdeologyBusinessLogic()).verifyClient(headers));
    }

    // @RequestMapping(value = "/ideology", method = RequestMethod.GET)
//    public static String ideology() throws Exception {
//        RestTemplate restTemplate = new RestTemplate();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        headers.add("Host", "web.ideologylive.com");
//
//        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
//        map.add("username", "GiroCheck.API.GPR");
//        map.add("password", "G!roCk@CbKc2~7Qr");
//        map.add("firstName", "Julio");
//        map.add("lastName", "Cesar");
//        map.add("address", "2323 Hidden Glen Dr");
//        map.add("city", "Marieta");
//        map.add("state", "GA");
//        map.add("zip", "30067");
//        map.add("dobMonth", "12");
//        map.add("dobDay", "6");
//        map.add("dobYear", "1983");
//        map.add("ssn", "757374453");
//
//        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
//
//        ResponseEntity<String> responseEntity = restTemplate.postForEntity("https://web.idologylive.com/api/idiq.svc", request, String.class);
//
//        String resp = responseEntity.getBody();
//
//        IdeologyResponse response = IdeologyResponse.getFromXML(resp);
//
//        if (response == null) {
//            System.out.println("Response is NULL");
//        } else {
//            System.out.println(response.toString());
//        }
//
//        return resp;
//    }
    
    private static Map buildMockTransactionDataMap(){
        Map map = new HashMap();
        
        map.put(ParameterName.FIRST_NAME, "Julio");
        map.put(ParameterName.LAST_NAME, "Cesar");
        map.put(ParameterName.ADDRESS, "2323 Hidden Glen Dr");
        map.put(ParameterName.CITY, "Marieta");
        map.put(ParameterName.STATE, "GA");
        map.put(ParameterName.ZIPCODE, "30067");
        map.put(ParameterName.BORNDATE_AS_DATE,  new Date()); 
        map.put(ParameterName.SSN, "757374453");
        
        return map;
    }

}
