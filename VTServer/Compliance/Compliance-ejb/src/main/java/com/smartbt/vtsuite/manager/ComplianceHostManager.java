/*
 ** File: ComplianceHostManager.java
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
import com.smartbt.girocheck.servercommon.manager.HostTxManager;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.model.SubTransaction;
import com.smartbt.vtsuite.mock.MockComplianceBusinessLogic;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomHost;

/**
 * The Host Manager class
 */
public class ComplianceHostManager implements HostTxManager{

    private static ComplianceHostManager INSTANCE;

    public static synchronized ComplianceHostManager get() {
        if (INSTANCE == null) {
            INSTANCE = new ComplianceHostManager();
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
        subTransaction.setHost(NomHost.COMPLIANCE.getId());

        try {
            String prodProperty = System.getProperty("PROD");
            Boolean isProd = true;// prodProperty != null && prodProperty.equalsIgnoreCase("true");
            System.out.println("ComplianceHostManager -> isProd = " + isProd);
            
            if (isProd) {
                response = ComplianceBusinessLogic.get().process(request);
            } else {
                response = MockComplianceBusinessLogic.get().process(request);
            }
 
            subTransaction.setResultMessage(response.getResultMessage());
            
            if (response.wasApproved()) {
                subTransaction.setResultCode(response.getResultCode().getCode()); 
            } else {
                subTransaction.setResultCode(ResultCode.COMPLIANCE_HOST_ERROR.getCode()); 
                response.setResultMessage("Girocheck Decline C--Please Call customer service.");
            }
             
            
        } catch (Exception e) {
            e.printStackTrace();
            response = DirexTransactionResponse.forException(ResultCode.COMPLIANCE_HOST_ERROR, "Girocheck Decline C--Please Call customer service.");
            subTransaction.setResultCode(ResultCode.COMPLIANCE_HOST_ERROR.getCode());
            subTransaction.setResultMessage(e.getMessage());
        }

        response.getTransaction().addSubTransaction(subTransaction);

        System.out.println("[ComplianceHostManager]  response.getTransaction().getSub_Transaction().size()" + response.getTransaction().getSub_Transaction().size());
        return response;
    }

}
