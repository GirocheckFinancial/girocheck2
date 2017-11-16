/*
 ** File: CoreTransactionBusinessLogic.java
 **
 ** Date Created: April 2013
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
import com.smartbt.girocheck.servercommon.jms.JMSManager;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.enums.ResultMessage;
import com.smartbt.girocheck.servercommon.manager.BalanceInquiryLogManager;
import com.smartbt.girocheck.servercommon.manager.CreditCardManager;
import com.smartbt.girocheck.servercommon.manager.HostTxManager;
import com.smartbt.girocheck.servercommon.model.BalanceInquiryLog;
import com.smartbt.girocheck.servercommon.model.CreditCard;
import com.smartbt.girocheck.servercommon.model.Transaction;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil; 
import com.smartbt.vtsuite.vtcommon.nomenclators.NomHost;
import java.util.Date;
import java.util.Properties; 
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

@TransactionManagement(value = TransactionManagementType.BEAN)
public class BalanceInquiryBusinessLogic extends CoreAbstractTransactionBusinessLogic {

    private JMSManager jmsManager = JMSManager.get();

    public BalanceInquiryBusinessLogic() {
        super();
    }

    @Override
    public void process(DirexTransactionRequest direxTransactionRequest, Transaction transaction) throws Exception {

        DirexTransactionResponse direxTransactionResponse = null;
        BalanceInquiryLog log = new BalanceInquiryLog();

        try {
            String cardNumber = (String) direxTransactionRequest.getTransactionData().get(ParameterName.CARD_NUMBER);

            if (cardNumber != null && cardNumber.length() >= 4) {
                log.setLast4cc(cardNumber.substring(cardNumber.length() - 4));
            }

            try {
                HibernateUtil.beginTransaction();

                CreditCard card = CreditCardManager.get().getCardByNumber(cardNumber);

                if (card != null && card.getClient() != null) {
                    if (card.getClient().getBlackListAll() != null && card.getClient().getBlackListAll() == true) {
                         HibernateUtil.commitTransaction();
                        
                        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreCardToBankBL::] Client " + card.getClient().getFirstName() + " is in the black list for All Transactions.", null);
                        transaction.setResultMessage("Client is in Black List.");
                        transaction.setResultCode(ResultCode.CLIENT_IN_BLACKLIST.getCode());
                        transaction.setTransactionFinished(true);
                        throw new TransactionException(transaction, ResultCode.CLIENT_IN_BLACKLIST, "Girocheck Decline-Please call customer service");
                    }
                } else {
                    System.out.println("[BalanceInquiryBusinessLogic] card.getClient() = null");
                }

                log.setData_sc1(card);

                HibernateUtil.commitTransaction();
            } catch (Exception e) {
                e.printStackTrace();

                HibernateUtil.rollbackTransaction();
            } 
            
            NomHost hostName = (NomHost) direxTransactionRequest.getTransactionData().get(ParameterName.HOSTNAME);

            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[BalanceInquiryBusinessLogic] Send message generic host:: " + hostName, null);

            HostTxManager hostManager = hostManagers.get(hostName);
 
            direxTransactionResponse = hostManager.processTransaction(direxTransactionRequest);

            direxTransactionResponse.getTransactionData().put(ParameterName.PRINTLOGO, "01");
         
        } catch (SingleException se) {
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[BalanceInquiryBusinessLogic] Error ", se.getMessage());

        } catch (Exception e) {
            e.printStackTrace();
            direxTransactionResponse = DirexTransactionResponse.forException(ResultCode.CORE_ERROR, e);

            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[BalanceInquiryBusinessLogic] Error ", e.getMessage());
        }

        jmsManager.send(direxTransactionResponse, jmsManager.getCoreOutQueue(), direxTransactionRequest.getCorrelation());

        log.setDateTime(new Date());
        log.setResultCode(direxTransactionResponse.getResultCode().getCode());
        log.setResultMessage(direxTransactionResponse.getResultMessage());
        log.setErrorCode(direxTransactionResponse.getErrorCode());

        String balanceStr = (String) direxTransactionResponse.getTransactionData().get(ParameterName.BALANCE);

        if (balanceStr != null) {
            Double balance = null;
            try {
                balance = Double.parseDouble(balanceStr);
            } catch (Exception e) {
                System.out.println("Exception trying to parse balance " + balance);
            }
            log.setAmount(balance);
        }

        try {
            HibernateUtil.beginTransaction();

            BalanceInquiryLogManager.get().save(log);

            HibernateUtil.commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();

            HibernateUtil.rollbackTransaction();
        }

    }

}

class SingleException extends Exception {
}
