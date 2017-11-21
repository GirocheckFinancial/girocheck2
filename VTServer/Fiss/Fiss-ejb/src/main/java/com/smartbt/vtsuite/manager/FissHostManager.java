/*
 ** File: FissHostManager.java
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
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import static com.smartbt.girocheck.servercommon.enums.TransactionType.*;
import com.smartbt.girocheck.servercommon.manager.HostTxManager;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.model.SubTransaction;
import com.smartbt.vtsuite.util.FissParam;
import static com.smartbt.vtsuite.util.FissUtil.FISS_REASON;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomHost;
import java.util.HashMap;
import java.util.Map;

/**
 * The Host Manager class
 */
public class FissHostManager implements HostTxManager {

    private static final TransactionType[] PERSONALIZATION = new TransactionType[]{
        TransactionType.CARD_PERSONALIZATION, TransactionType.FISS_SET_PIN, TransactionType.CARD_ACTIVATION};

    private static FissHostManager INSTANCE;

    public static synchronized FissHostManager get() {
        if (INSTANCE == null) {
            INSTANCE = new FissHostManager();
        }
        return INSTANCE;
    }
    //*************************************
    //-- card validation

    //----check status-------------    
    //status = 1 -> (just check SSN)
    //status = 7 ->  personalize  
    //(personalize)   -> Card Personalization
//                    -> PIN change   (just pass SSN last 4 and CC)
    //                -> Card Activation   (code 1)
    //status = others ->  ERROR
    //OK here
    //************** Card LOAD (check)  
    //Load Funds Use CBPrpdLdUnld                                    Use::  G1; 
    //Fees Use CBPrpdLdUnld   (amount goes in field unload amount    Use::  G4; 
    //************** Card LOAD (cash)   
    //Load Funds Load Funds Use CBPrpdAdj  
    //Fees Use CBPrpdLdUnld   (amount goes in field unload amount   Use::  G5; 
    //Restore card
    //Card card validation
    //If restore card, then put back the values retrieved in the card validation
    // Card Activation Use CBNegFleMaint  pass code 7
    /**
     * Process Direx Transaction Request.
     *
     * @param request
     * @return DirexTransactionResponse The transaction response object
     * @throws Exception
     */
    public DirexTransactionResponse processTransaction(DirexTransactionRequest request) throws Exception {
        DirexTransactionResponse response = new DirexTransactionResponse();
        Map<FissParam, Object> responseMap = new HashMap<>();

//        SubTransaction subTransaction = new SubTransaction();
//        subTransaction.setType(request.getTransactionType().getCode());
//        subTransaction.setHost(NomHost.FISS.getId());
        TransactionType transactionType = request.getTransactionType();

        switch (transactionType) {
            case BALANCE_INQUIRY:
            case CARD_VALIDATION:
                responseMap = processSubTransaction(transactionType, request, response);

                if (response.wasApproved()) {
                    response.getTransactionData().put(ParameterName.FISS_PERSONAL_INFO_DATA, responseMap.get(FissParam.FISS_PERSONAL_INFO_DATA));
                    response.getTransactionData().put(ParameterName.BALANCE, responseMap.get(FissParam.BALANCE));

                    if (responseMap.containsKey(FissParam.RESULT_CODE)
                            && (Integer) responseMap.get(FissParam.RESULT_CODE) == 7) {

                        for (TransactionType personalizationTransaction : PERSONALIZATION) {
                            processSubTransaction(personalizationTransaction, request, response);

                            if (!response.wasApproved()) {
                                break;
                            }
                        }
                    }
                }
                break;
            default:
                responseMap = processSubTransaction(transactionType, request, response);
                break;

        }

        if (response.wasApproved()) {
            response.setResultCode(ResultCode.SUCCESS);
            response.setResultMessage(ResultMessage.SUCCESS.getMessage());
        }

        System.out.println("[FissHostManager]  response.getTransaction().getSub_Transaction().size()" + response.getTransaction().getSub_Transaction().size());
        return response;
    }

    private Map<FissParam, Object> processSubTransaction(TransactionType transactionType,
            DirexTransactionRequest request, DirexTransactionResponse response) {

        Map<FissParam, Object> responseMap = new HashMap<>();

        SubTransaction subTransaction = new SubTransaction();
        subTransaction.setType(transactionType.getCode());
        subTransaction.setHost(NomHost.FISS.getId());

        Boolean success = true;
         
        try {
            responseMap = FissBusinessLogic.get().process(transactionType, request);

            if (responseMap.containsKey(FissParam.SUCCESS)) {
                success = (Boolean) responseMap.get(FissParam.SUCCESS);
            } else {
                success = false;
            }

            subTransaction.setResultCode( success ? 0 : -1);
            subTransaction.setResultMessage((String) responseMap.get(FissParam.RESULT_MESSAGE));

            Integer resultCode = (Integer) responseMap.get(FissParam.RESULT_CODE);
            String resultCodeStr = resultCode + (FISS_REASON.containsKey(resultCode) ? " - " + FISS_REASON.get(resultCode) : "");
            subTransaction.setErrorCode(resultCodeStr);

        } catch (Exception ex) {
            success = false;
            subTransaction.setResultCode(ResultCode.FISS_HOST_ERROR.getCode());
            subTransaction.setResultMessage(ex.getMessage());

            System.out.println("[FissHostManager] Unsexpected Exception");
            System.out.println("ex.getMessage() = " + ex.getMessage());
            ex.printStackTrace();
        }

        response.getTransaction().addSubTransaction(subTransaction);
        response.setApproved(success);

        if (!success) {
            response.getTransaction().setResultCode(subTransaction.getResultCode());
            response.getTransaction().setResultMessage(subTransaction.getResultMessage());
            response.setResultCode(ResultCode.FISS_HOST_ERROR);
            response.setResultMessage(subTransaction.getResultMessage());
        }

        return responseMap;
    }
}
