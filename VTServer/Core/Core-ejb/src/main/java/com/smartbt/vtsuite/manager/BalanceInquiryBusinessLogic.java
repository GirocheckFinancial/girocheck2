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
import com.smartbt.girocheck.servercommon.model.BalanceInquiryLog;
import com.smartbt.girocheck.servercommon.model.CreditCard;
import com.smartbt.girocheck.servercommon.model.Transaction;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import java.io.Serializable;
import java.util.Date;
import java.util.Properties;
import javax.jms.Message;
import javax.jms.ObjectMessage;
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
        try {
            Properties msgProps = new Properties();

            TransactionType transactionType = direxTransactionRequest.getTransactionType();
            String host = transactionType.getHost().toString();

            msgProps.setProperty("hostName", host);

            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[BalanceInquiryBusinessLogic] Send message to " + host, null);

            long wait_time = 10000;
            jmsManager.sendWithProps(direxTransactionRequest, jmsManager.getHostInQueue(), direxTransactionRequest.getCorrelation(), msgProps);
            ObjectMessage tmsg;

            Message message = null;
            try {
                message = jmsManager.receive(jmsManager.getHostOutQueue(), direxTransactionRequest.getCorrelation(), wait_time);
            } catch (Exception e) {
                direxTransactionResponse = DirexTransactionResponse.forException(ResultCode.RESPONSE_TIME_EXCEEDED, ResultMessage.RESPONSE_TIME_EXCEEDED, " for " + transactionType.toString(), "");
                throw new SingleException();
            }
            if (message == null || !(message instanceof ObjectMessage)) {
                direxTransactionResponse = DirexTransactionResponse.forException(ResultCode.RESPONSE_TIME_EXCEEDED, ResultMessage.RESPONSE_TIME_EXCEEDED, " for " + transactionType.toString(), "");
                CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[BalanceInquiryBusinessLogic] Message null " + direxTransactionResponse.getResultMessage(), null);
                throw new SingleException();
            }

            tmsg = (ObjectMessage) message;
            Serializable s = tmsg.getObject();
            direxTransactionResponse = (DirexTransactionResponse) s;

            direxTransactionResponse.getTransactionData().put(ParameterName.PRINTLOGO, "01");
            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[BalanceInquiryBusinessLogic] Recived message from: " + host, null);

        } catch (SingleException se) {
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[BalanceInquiryBusinessLogic] Error ", se.getMessage());

        } catch (Exception e) {
            e.printStackTrace();
            direxTransactionResponse = DirexTransactionResponse.forException(ResultCode.CORE_ERROR, e);

            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[BalanceInquiryBusinessLogic] Error ", e.getMessage());
        }

        jmsManager.send(direxTransactionResponse, jmsManager.getCoreOutQueue(), direxTransactionRequest.getCorrelation());

        BalanceInquiryLog log = new BalanceInquiryLog();

        log.setDateTime(new Date());
        log.setResultCode(direxTransactionResponse.getResultCode().getCode());
        log.setResultMessage(direxTransactionResponse.getResultMessage());
        log.setErrorCode(direxTransactionResponse.getErrorCode());

        String cardNumber = (String) direxTransactionRequest.getTransactionData().get(ParameterName.CARD_NUMBER);

        if (cardNumber != null && cardNumber.length() >= 4) {
            log.setLast4cc(cardNumber.substring(cardNumber.length() - 4));
        }

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

            CreditCard card = CreditCardManager.get().getCardByNumber(cardNumber);
            log.setData_sc1(card);
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
