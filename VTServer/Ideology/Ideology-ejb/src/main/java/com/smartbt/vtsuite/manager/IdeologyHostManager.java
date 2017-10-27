/*
 ** File: IdeologyHostManager.java
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
 
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.model.SubTransaction;
import com.smartbt.vtsuite.mock.MockIdeologyBusinessLogic;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomHost;

/**
 * The Host Manager class
 */
public class IdeologyHostManager {

    private static IdeologyHostManager INSTANCE;

    public static synchronized IdeologyHostManager get() {
        if (INSTANCE == null) {
            INSTANCE = new IdeologyHostManager();
        }
        return INSTANCE;
    }

    /**
     * Process Direx Transaction Request.
     *
     * @param request
     * @return DirexTransactionResponse The transaction response object
     * @throws Exception
     */
    public DirexTransactionResponse processTransaction(DirexTransactionRequest request) throws Exception {
        DirexTransactionResponse response;

        SubTransaction subTransaction = new SubTransaction();
        subTransaction.setType(request.getTransactionType().getCode());
        subTransaction.setHost(NomHost.FISS.getId());

        try {
            String prodProperty = System.getProperty("PROD");
            Boolean isProd = prodProperty != null && prodProperty.equalsIgnoreCase("true");
            System.out.println("IdeologyHostManager -> isProd = " + isProd);
            
            if (isProd) {
                response = IdeologyBusinessLogic.get().process(request);
            } else {
                response = MockIdeologyBusinessLogic.get().process(request);
            }
 
            if (response.wasApproved()) {
                subTransaction.setResultCode(response.getResultCode().getCode());
                subTransaction.setResultMessage(response.getResultMessage());
            } else {
                subTransaction.setResultCode(ResultCode.FISS_HOST_ERROR.getCode());
                subTransaction.setResultMessage(response.getResultMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            response = DirexTransactionResponse.forException(ResultCode.FISS_HOST_ERROR, e);
            subTransaction.setResultCode(ResultCode.FISS_HOST_ERROR.getCode());
            subTransaction.setResultMessage(e.getMessage());
        }

        response.getTransaction().addSubTransaction(subTransaction);

        System.out.println("[IdeologyHostManager]  response.getTransaction().getSub_Transaction().size()" + response.getTransaction().getSub_Transaction().size());
        return response;
    }

}
