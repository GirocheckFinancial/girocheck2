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
import com.smartbt.girocheck.servercommon.manager.IdeologyResultManager;
import com.smartbt.girocheck.servercommon.model.IdeologyResult;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.vtsuite.entity.IdeologyResponse;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.MediaType; 
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

        IdeologyResponse response = null;

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[IdeologyBusinessLogic] proccessing:: " + transactionType, null);

        switch (transactionType) {
            case IDEOLOGY_VERYFY_CLIENT:
                response = verifyClient(transactionData);

                break;
        }

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[IdeologyBusinessLogic] Finish " + transactionType, null);

        if (response != null && response.wasSuccess()) {
            direxTransactionResponse.setResultCode(ResultCode.SUCCESS);
            direxTransactionResponse.setResultMessage(VTSuiteMessages.SUCCESS);
            direxTransactionResponse.getTransactionData().put(ParameterName.IDEOLOGY_RESULT_ID, response.getIdeologyResultId());
        } else {
            direxTransactionResponse.setResultCode(ResultCode.IDEOLOGY_HOST_UNEXPECTED_RESULT_CODE);

            String resultMessage = response != null ? response.getErrorMessage() : "Ideology response iwas null";
            direxTransactionResponse.setResultMessage(resultMessage);
        }

        return direxTransactionResponse;
    }

    public IdeologyResponse verifyClient(Map params) {
        System.out.println("[IdeologyBusinessLogic] verifyClient");

        // params = buildMockTransactionDataMap();
        MultiValueMap requestMap = buildRequestMap(params);

        System.out.println("------- IDEOLOGY REQUEST --------");
        System.out.println(requestMap.toSingleValueMap().toString());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(requestMap, headers);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(IDEOLOGY_URL, request, String.class);

        String resp = responseEntity.getBody();

        System.out.println("Calling Ideology...");

        IdeologyResponse response = IdeologyResponse.getFromXML(resp);

        System.out.println("------- IDEOLOGY RESPONSE --------");
        if (response == null) {
            System.out.println("Response is NULL");
        } else {
            System.out.println(resp);
            IdeologyResult entity = response.toEntity();
            
            entity.setMerchant(params.get(ParameterName.IDMERCHANT) + "");
           Integer idIdeologyResult = persitResult(6, entity);
           response.setIdeologyResultId(idIdeologyResult);
        }

        return response;//new HashMap();
    }

    private static MultiValueMap buildRequestMap(Map transactionData) {
        MultiValueMap<String, String> requestMap = new LinkedMultiValueMap<>();

        requestMap.add("username", IDEOLOGY_USERNAME);
        requestMap.add("password", IDEOLOGY_PASSWORD);

        setPersonalInfo(requestMap, transactionData);
        setDOBFields(requestMap, transactionData);
        setDateFields(requestMap, transactionData);
        return requestMap;
    }

    private static void setPersonalInfo(MultiValueMap<String, String> requestMap, Map transactionData) {
        requestMap.add("firstName", (String) transactionData.get(ParameterName.FIRST_NAME));
        requestMap.add("lastName", (String) transactionData.get(ParameterName.LAST_NAME));
        requestMap.add("ssn", (String) transactionData.get(ParameterName.SSN));
    }

    private static void setDateFields(MultiValueMap<String, String> requestMap, Map transactionData) {
        requestMap.add("address", (String) transactionData.get(ParameterName.ADDRESS));
        requestMap.add("city", (String) transactionData.get(ParameterName.CITY));
        requestMap.add("state", (String) transactionData.get(ParameterName.STATE_ABBREVIATION));
        requestMap.add("zip", (String) transactionData.get(ParameterName.ZIPCODE));
    }

    private static void setDOBFields(MultiValueMap<String, String> requestMap, Map transactionData) {

        if (transactionData.containsKey(ParameterName.BORNDATE_AS_DATE)) {
            Date dob = (Date) transactionData.get(ParameterName.BORNDATE_AS_DATE);
            Calendar c = Calendar.getInstance();
            c.setTime(dob);
            int day = dob.getDate();
            int month = dob.getMonth() + 1;
            int year = dob.getYear() + 1900;

            requestMap.add("dobDay", day + "");
            requestMap.add("dobMonth", month + "");
            requestMap.add("dobYear", year + "");
        } else {
            if (transactionData.containsKey(ParameterName.BORNDATE)) {
                String dob = (String) transactionData.get(ParameterName.BORNDATE);

                String[] dobParts = dob.split("-");

                if (dobParts.length > 0) {
                    requestMap.add("dobYear", dobParts[dobParts.length - 1]);
                }

                if (dobParts.length > 1) {
                    requestMap.add("dobMonth", dobParts[dobParts.length - 2]);
                }

                if (dobParts.length > 2) {
                    requestMap.add("dobDay", dobParts[dobParts.length - 3]);
                }
            }
        }

    }

    public Integer persitResult(int clientId, IdeologyResult result) {
        Integer id = 0;
        try {
            HibernateUtil.beginTransaction();

            id = IdeologyResultManager.get().save(clientId, result);

            HibernateUtil.commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            HibernateUtil.rollbackTransaction();
        }

        return id;
    }

    public static void main(String[] args) throws Exception {
        MultiValueMap map = buildRequestMap(buildMockTransactionDataMap());

        System.out.println(" map.toString()= " + map.toString());
        System.out.println("");
        System.out.println("");
        System.out.println("");
    }

    private static Map buildMockTransactionDataMap() {
        Map map = new HashMap();

        map.put(ParameterName.FIRST_NAME, "Julio");
        map.put(ParameterName.LAST_NAME, "Cesar");
        map.put(ParameterName.ADDRESS, "2323 Hidden Glen Dr");
        map.put(ParameterName.CITY, "Marieta");
        map.put(ParameterName.STATE, "GA");
        map.put(ParameterName.ZIPCODE, "30067");
        map.put(ParameterName.BORNDATE_AS_DATE, new Date());
        map.put(ParameterName.SSN, "757374453");

        return map;
    }

}
