/*
 ** File: MerchantController.java
 **
 ** Date Created: October 2013
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
package com.smartbt.vtsuite.controller;

import com.smartbt.girocheck.servercommon.display.MerchantDisplay;
import com.smartbt.girocheck.servercommon.display.message.BaseResponse;
import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.manager.MerchantManager;
import com.smartbt.girocheck.servercommon.manager.StateManager;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.vtsuite.manager.ComplianceBusinessLogic;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Roberto Rodriguez
 */
@Path("VTAMS")
public class MerchantController {

    @Context
    private HttpHeaders context;
    @Context
    private HttpServletResponse response;
    @Context
    private HttpServletRequest request;
    private MerchantManager manager = new MerchantManager();
//    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(MerchantController.class);

    @POST
    @Path("getMerchantsByAgrupation")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseDataList getMerchantsByAgrupation(@FormParam("idAgrupation") int idAgrupation) {
//        log.info("getMerchantsByAgrupation :: Incoming parameters : \n idAgrupation: " + idAgrupation);

        return manager.getMerchantsByAgrupation(idAgrupation);
    }

    @PUT
    @Path("saveOrUpdateMerchant")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseData saveOrUpdateMerchant(MerchantDisplay merchant) throws Exception {
         System.out.println("MerchantController -> saveOrUpdateMerchant");
        merchant.print();
        ResponseData responseData = manager.saveOrUpdateMerchant(merchant);

        MerchantDisplay merchantDisplay = (MerchantDisplay) responseData.getData();
        registerMerchantInCompliance(merchantDisplay);
        return responseData;
    }

    private void registerMerchantInCompliance(MerchantDisplay merchantDisplay) {
        Map map = new HashMap();
        map.put(ParameterName.IDMERCHANT, merchantDisplay.getId());
        map.put(ParameterName.MERCHANT_NAME, merchantDisplay.getLegalName());

        if (merchantDisplay.getPhone() != null) {
            String phone = getPhoneNumber(merchantDisplay.getPhone());
            map.put(ParameterName.PHONE, phone);
        }

        if (merchantDisplay.getAddress() != null) {
            map.put(ParameterName.ADDRESS, merchantDisplay.getAddress().getAddress());
            map.put(ParameterName.ZIPCODE, merchantDisplay.getAddress().getZip());

            if (merchantDisplay.getAddress() != null
                    && merchantDisplay.getAddress().getState() != null) {
                int idState = merchantDisplay.getAddress().getState().getId();
                String stateAbbreviation = StateManager.get().getAbbreviationById(idState);
                map.put(ParameterName.STATE_ABBREVIATION, stateAbbreviation);
            }
        }

        try {
            if (merchantDisplay.getId() == null) {
                DirexTransactionRequest request = new DirexTransactionRequest(map, TransactionType.COMPLIANCE_NEW_BRANCH, null);

                ComplianceBusinessLogic.get().process(request); 
            }

        } catch (Exception e) {
            System.out.println("MerchantController -> Exception Calling ComplianceBusinessLogic.get().newBranch(map);");
            e.printStackTrace();
        }

    }

    public String getPhoneNumber(String phone) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < phone.length(); i++) {
            char c = phone.charAt(i);
            if (c >= 48 && c <= 58) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    @POST
    @Path("getMerchantsById")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseData getMerchantsById(@FormParam("id") int id) throws Exception {
//        log.info("getMerchantsById :: Incoming parameter : \n merchant: id :: " + id);

        return manager.getMerchantsById(id);

    }

    @POST
    @Path("deleteMerchant")
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse deleteMerchant(@FormParam("id") int id) throws Exception {
//        log.info("Incoming parameter : \n idEntity: id = " + id);
        return manager.deleteMerchant(id);
    }

    @POST
    @Path("searchMerchants")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseDataList searchMerchants(@FormParam("search") String search) throws Exception {
//        log.info("searchAgrupations:: Incoming parameters : [search] :: " + search);

        return manager.searchMerchants(search);
    }

}
