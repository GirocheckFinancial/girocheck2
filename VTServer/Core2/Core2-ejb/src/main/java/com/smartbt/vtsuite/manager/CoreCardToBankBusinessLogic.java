/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.manager;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.enums.ResultMessage;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.jms.JMSManager;
import com.smartbt.girocheck.servercommon.manager.AchCardManager;
import com.smartbt.girocheck.servercommon.manager.ApplicationParameterManager;
import com.smartbt.girocheck.servercommon.manager.CreditCardManager;
import com.smartbt.girocheck.servercommon.manager.TerminalManager;
import com.smartbt.girocheck.servercommon.manager.TransactionManager;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.model.AchCard;
import com.smartbt.girocheck.servercommon.model.Client;
import com.smartbt.girocheck.servercommon.model.Merchant;
import com.smartbt.girocheck.servercommon.model.Terminal;
import com.smartbt.girocheck.servercommon.model.Transaction;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.vtsuite.util.CoreTransactionUtil;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomHost;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.sql.rowset.serial.SerialBlob;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Alejo
 */
@TransactionManagement(value = TransactionManagementType.BEAN)
public class CoreCardToBankBusinessLogic extends CoreAbstractTransactionBusinessLogic {

//    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(CoreCardToBankBusinessLogic.class);
    private Transaction transaction;

    private JMSManager jmsManager = JMSManager.get();

    private static TransactionManager transactionManager = new TransactionManager();

    private static AchCardManager achManager = new AchCardManager();

    private static CreditCardManager creditCardManager = new CreditCardManager();

    private TerminalManager terminalManager = new TerminalManager();

    private static ApplicationParameterManager parameterManager = new ApplicationParameterManager();

    public CoreCardToBankBusinessLogic() {
        super();
    }

    @Override
    public void process(DirexTransactionRequest direxTransactionRequest, Transaction transaction) throws Exception {

        ObjectMessage tmsg;

        String clientBankName = "";

        String clientRoutingBankNumber = "";

        String merchantAccountNumber = "";

        String merchantRoutingNumber = "";

        Boolean hasAch = true;

        Client client = null;
        String fullAddress = "";
        Merchant merchant = null;

        long tecnicardWaitTime = 50000;

        Properties tecnicardProps = new Properties();

        long tecnicardCardToBankConfirmationFrontWaitTime = 120000;

        DirexTransactionResponse direxTransactionResponse = new DirexTransactionResponse();

        String terminalId = (String) direxTransactionRequest.getTransactionData().get(ParameterName.TERMINAL_ID);

        String cardNumber = (String) direxTransactionRequest.getTransactionData().get(ParameterName.CARD_NUMBER);

        this.transaction = transaction;

        try {

            HibernateUtil.beginTransaction();

            hasAch = achManager.existAchCard(cardNumber);

            client = creditCardManager.getClient(cardNumber);
            if (client != null) {
                if ((client.getBlacklistCard2bank() != null && client.getBlacklistCard2bank() == true)
                        || (client.getBlackListAll() != null && client.getBlackListAll() == true)) {
                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreCardToBankBL::] Client " + client.getFirstName() + " is in the black list.", null);
                    HibernateUtil.commitTransaction();

                    direxTransactionResponse = direxTransactionResponse.forException(TransactionType.TECNICARD_CARD_TO_BANK, ResultCode.CLIENT_IN_BLACKLIST, ResultMessage.CLIENT_IN_CARD2BANK_BLACKLIST);
                    direxTransactionResponse.setTransactionType(TransactionType.get(transaction.getTransactionType()));
                    CoreTransactionUtil.subTransactionFailed(transaction, direxTransactionResponse, jmsManager.getCoreOutQueue(), direxTransactionRequest.getCorrelation());
                    JMSManager.get().send(direxTransactionResponse, JMSManager.get().getCoreOutQueue(), direxTransactionRequest.getCorrelation());
                    return;
                }

                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreCardToBankBL::] Client Name = " + client.getFirstName(), null);
                if (client.getAddress() != null) {
                    fullAddress = client.getAddress().getFullAddress();
                } else {
                    fullAddress = "";
                }

            } else {
                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreCardToBankBL::] Client was NULL", null);
            }
            Terminal terminal = terminalManager.findBySerialNumber(terminalId);
            merchant = terminal.getMerchant();

            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreCardToBankBL::] Merchant = " + (merchant == null ? "NULL" : "NOT NULL"), null);

            if (merchant != null) {
                merchantAccountNumber = merchant.getAccount();
                merchantRoutingNumber = merchant.getRoutingBankNumber();
            }
            clientBankName = parameterManager.getAplicationParameterByName("bankName").getValue();
            clientRoutingBankNumber = parameterManager.getAplicationParameterByName("routingBankNumber").getValue();

            HibernateUtil.commitTransaction();

        } catch (Exception e) {
            e.printStackTrace();
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreCardToBankBL::]>> Error getting data from DataBase method process() first WS", e.getMessage());
            HibernateUtil.rollbackTransaction();
        }

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreCardToBankBL::] hasAch = " + hasAch, null);

        if (hasAch) {
            Map map = new HashMap();
            map.put(ParameterName.EXISTACH, true);// exist ach

            direxTransactionResponse.setTransactionData(map);
            direxTransactionResponse.setResultCode(ResultCode.SUCCESS);
            direxTransactionResponse.setResultMessage(ResultMessage.SUCCESS.getMessage());

        } else {

            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreCardToBankBL::] No ACH", null);

            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CoreCardToBankBL::] Filling map with AchForm data", null);
            //Filling achForm fields.

            Map map = new HashMap();
            if (merchant != null) {
                map.put(ParameterName.MERCHANT_NAME, merchant.getLegalName());
                map.put(ParameterName.EXISTACH, false);// doesn't exist ach
                map.put(ParameterName.BANK_NAME, clientBankName);
                if (client == null) {
                    map.put(ParameterName.CUSTUMER_NAME, "");
                    map.put(ParameterName.LAST_NAME, "");
                    map.put(ParameterName.CUSTUMER_ADDRESS, "");
                } else {
                    map.put(ParameterName.CUSTUMER_NAME, client.getFirstName() + " " + client.getLastName());
                    map.put(ParameterName.CUSTUMER_ADDRESS, fullAddress);
                }

                map.put(ParameterName.ROUTING_BANK_NUMBER, clientRoutingBankNumber);
                map.put(ParameterName.ACCOUNT_NUMBER, "751" + cardNumber.substring(7));

                direxTransactionResponse.setTransactionData(map);
                direxTransactionResponse.setResultCode(ResultCode.SUCCESS);
                direxTransactionResponse.setResultMessage(ResultMessage.SUCCESS.getMessage());
            } else {
                direxTransactionResponse = direxTransactionResponse.forException(TransactionType.TECNICARD_CARD_TO_BANK, ResultCode.CORE_ERROR, ResultMessage.CARDTOBANK_CORE_MERCHANT_MISSING);
                direxTransactionResponse.setTransactionType(TransactionType.get(transaction.getTransactionType()));
                CoreTransactionUtil.subTransactionFailed(transaction, direxTransactionResponse, jmsManager.getCoreOutQueue(), direxTransactionRequest.getCorrelation());
                JMSManager.get().send(direxTransactionResponse, JMSManager.get().getCoreOutQueue(), direxTransactionRequest.getCorrelation());
                return;
            }

        }

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CoreCardToBankBL::] Sending Message to Front", null);
        JMSManager.get().send(direxTransactionResponse, JMSManager.get().getCoreOutQueue(), direxTransactionRequest.getCorrelation());

        Message tecnicard_CardToBank_Message;

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CoreCardToBankBL::] Receiving message from Front", null);
        try {

            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CoreCardToBankBL::] Waiting message from Front CardToBankConfirmation.  Correlation:: " + direxTransactionRequest.getCorrelation(), null);
            tecnicard_CardToBank_Message = jmsManager.receive(jmsManager.getCore2InQueue(), direxTransactionRequest.getCorrelation(), tecnicardCardToBankConfirmationFrontWaitTime);

            if (tecnicard_CardToBank_Message == null || !(tecnicard_CardToBank_Message instanceof ObjectMessage)) {

                CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CoreCardToBankBL::] JMSException Receiving from Front", null);
                direxTransactionResponse = DirexTransactionResponse.forException(ResultCode.CORE_RECEIVED_NULL, ResultMessage.CORE_RECEIVED_NULL);//cambiar los mensajes
                direxTransactionResponse.setTransactionType(TransactionType.get(transaction.getTransactionType()));
                CoreTransactionUtil.subTransactionFailed(transaction, direxTransactionResponse, jmsManager.getCoreOutQueue(), direxTransactionRequest.getCorrelation());
                return;
            }

        } catch (JMSException e) {

            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreCardToBankBL::] JMSException waiting message from GirochekFront", e.getMessage());
            direxTransactionResponse = DirexTransactionResponse.forException(ResultCode.CORE_RECEIVED_NULL, ResultMessage.CORE_RECEIVED_NULL);
            direxTransactionResponse.setTransactionType(TransactionType.get(transaction.getTransactionType()));
            CoreTransactionUtil.subTransactionFailed(transaction, direxTransactionResponse, jmsManager.getCoreOutQueue(), direxTransactionRequest.getCorrelation());
            return;
        }

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CoreCardToBankBL::] Message Received. From Front 2nd ws", null);
        ObjectMessage tmsg2 = (ObjectMessage) tecnicard_CardToBank_Message;

        DirexTransactionRequest request = (DirexTransactionRequest) tmsg2.getObject();

        if (!request.getTransactionData().containsKey(ParameterName.EXISTACH)
                || !request.getTransactionData().get(ParameterName.EXISTACH).toString().equalsIgnoreCase("true")) {

            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CoreCardToBankBL::] ACH doesn't exist. Saving ACH", null);
            AchCard achCard = new AchCard();
            String ach = "";
            if (request.getTransactionData().containsKey(ParameterName.ACH_FORM)) {
                ach = (String) request.getTransactionData().get(ParameterName.ACH_FORM);
            }

            if (ach != null) {
                byte[] achForm = DatatypeConverter.parseBase64Binary(ach);

                java.sql.Blob achFormBlob = new SerialBlob(achForm);

                achCard.setAchform(achFormBlob);
                achCard.setCardNumber(cardNumber);
                achCard.setMerchant(merchant);
            } else {

                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreCardToBankBL::] AchForm from the transaction IS NULL", null);
                direxTransactionResponse = DirexTransactionResponse.forException(ResultCode.CORE_ERROR, ResultMessage.CARDTOBANK_CORE_ACH_MISSING);
                direxTransactionResponse.setTransactionType(TransactionType.get(transaction.getTransactionType()));
                CoreTransactionUtil.subTransactionFailed(transaction, direxTransactionResponse, jmsManager.getCoreOutQueue(), direxTransactionRequest.getCorrelation());
                return;
            }
            try {
                HibernateUtil.beginTransaction();
                achManager.save(achCard);
                HibernateUtil.commitTransaction();
            } catch (Exception e) {
                e.printStackTrace();
                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreCardToBankBL::] Error", e.getMessage());
                HibernateUtil.rollbackTransaction();

                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "--[CoreCardToBankBL::] AchForm from the transaction IS NULL", null);
                direxTransactionResponse = DirexTransactionResponse.forException(ResultCode.CREDIT_CARD_NOT_EXIST, ResultMessage.CREDIT_CARD_NOT_EXIST);
                direxTransactionResponse.setTransactionType(TransactionType.get(transaction.getTransactionType()));
                CoreTransactionUtil.subTransactionFailed(transaction, direxTransactionResponse, jmsManager.getCore2OutQueue(), direxTransactionRequest.getCorrelation());
                return;
            }
        }

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CoreCardToBankBL::] Sending to Tecnicard", null);
        try {
            Double amount = (Double) request.getTransactionData().get(ParameterName.AMMOUNT);

            request.getTransactionData().put(ParameterName.CARD_NUMBER, cardNumber);
            request.getTransactionData().put(ParameterName.ACCOUNT_NUMBER, merchantAccountNumber);
            request.getTransactionData().put(ParameterName.ROUTING_BANK_NUMBER, merchantRoutingNumber);
            request.getTransactionData().put(ParameterName.PAYOUT_AMMOUNT, (amount));
            request.getTransactionData().put(ParameterName.REQUEST_ID, request.getTransactionData().get(ParameterName.REQUEST_ID));

            NomHost host = (NomHost) direxTransactionRequest.getTransactionData().get(ParameterName.HOSTNAME);

            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CoreCardToBankBL::] Calling generic host ::" + host, null);

            request.setTransactionType(TransactionType.TECNICARD_CARD_TO_BANK);
            
            direxTransactionResponse = hostManagers.get(host).processTransaction(request);
 
            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "1--[CoreCardToBankBL::] direxTransactionResponse.getResultCode().getCode() = " + direxTransactionResponse.getResultCode().getCode(), null);
            if (!direxTransactionResponse.getTransaction().getSub_Transaction().isEmpty()) {
                transaction.addSubTransactionList(direxTransactionResponse.getTransaction().getSub_Transaction());
            }

            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CoreCardToBankBL::] Send message back to Front", null);
            //Response sent without transaction
            direxTransactionResponse.setTransaction(null);
            direxTransactionResponse.getTransactionData().put(ParameterName.PRINTLOGO, "01");
            JMSManager.get().send(direxTransactionResponse, jmsManager.getCore2OutQueue(), direxTransactionRequest.getCorrelation());

            transaction.setClient(client);
            transaction.setAmmount(amount);
            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "2--[CoreCardToBankBL::] direxTransactionResponse.getResultCode().getCode() = " + direxTransactionResponse.getResultCode().getCode(), null);
            transaction.setResultCode(direxTransactionResponse.getResultCode().getCode());
            transaction.setResultMessage(direxTransactionResponse.getResultMessage());

            CoreTransactionUtil.persistTransaction(transaction);

//            coreLogger.logAndStore("CoreCardToBankBL", " [CoreCardToBankBL::]            Transaction finished successfuly.");
//            log.info("[CoreCardToBankBL::] Transaction finished successfully");
            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[CoreCardToBankBL::] Transaction finished successfully", null);

        } catch (TecnicardNotRespondException tnre) {
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreCardToBankBL::] TecnicardNotRespondException...", tnre.getMessage());

            direxTransactionResponse = DirexTransactionResponse.forException(ResultCode.TECNICARD_RESPONSE_TIME_EXCEEDED, ResultMessage.RESPONSE_TIME_EXCEEDED, " Tecnicard", "");
            direxTransactionResponse.setTransactionType(TransactionType.TECNICARD_CARD_TO_BANK_CONFIRMATION);
            JMSManager.get().send(direxTransactionResponse, jmsManager.getCore2OutQueue(), direxTransactionRequest.getCorrelation());
            CoreTransactionUtil.subTransactionFailed(transaction, direxTransactionResponse, jmsManager.getCore2OutQueue(), direxTransactionRequest.getCorrelation());
        } catch (Exception e) {
            e.printStackTrace();

            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreCardToBankBL::] Unhandled exception ", e.getMessage());

            direxTransactionResponse = DirexTransactionResponse.forException(ResultCode.CORE_ERROR, ResultMessage.FAILED, "Tecnicard ", e.getMessage());
            direxTransactionResponse.setTransactionType(TransactionType.TECNICARD_CARD_TO_BANK_CONFIRMATION);
            JMSManager.get().send(direxTransactionResponse, jmsManager.getCore2OutQueue(), direxTransactionRequest.getCorrelation());
            transaction.setResultCode(ResultCode.CORE_ERROR.getCode());
            String msg = e.getMessage();
            transaction.setResultMessage((msg != null && msg.length() > 254) ? msg.substring(0, 254) : msg);

            CoreTransactionUtil.persistTransaction(transaction);
        }

    }

    class TecnicardNotRespondException extends Exception {
    }
}
