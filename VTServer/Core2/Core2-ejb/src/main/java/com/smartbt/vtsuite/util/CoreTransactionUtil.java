/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.util;

import com.smartbt.girocheck.servercommon.dao.MobileClientDao;
import com.smartbt.girocheck.servercommon.display.mobile.MobileClientDisplay;
import com.smartbt.girocheck.servercommon.utils.SMSUtils;
import com.smartbt.girocheck.servercommon.enums.EmailName;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.enums.ResultMessage;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.jms.JMSManager;
import com.smartbt.girocheck.servercommon.log.LogUtil;
import com.smartbt.girocheck.servercommon.manager.ClientManager;
import com.smartbt.girocheck.servercommon.manager.CreditCardManager;
import com.smartbt.girocheck.servercommon.manager.EmailManager;
import com.smartbt.girocheck.servercommon.manager.IdeologyResultManager;
import com.smartbt.girocheck.servercommon.manager.TerminalManager;
import com.smartbt.girocheck.servercommon.manager.TransactionManager;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.messageFormat.IdType;
import com.smartbt.girocheck.servercommon.model.Client;
import com.smartbt.girocheck.servercommon.model.CreditCard;
import com.smartbt.girocheck.servercommon.model.Email;
import com.smartbt.girocheck.servercommon.model.SubTransaction;
import com.smartbt.girocheck.servercommon.model.Terminal;
import com.smartbt.girocheck.servercommon.model.Transaction;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.girocheck.servercommon.utils.pushNotification.PushNotificationManager;
import com.smartbt.vtsuite.manager.AbstractCommonBusinessLogic;
import com.smartbt.vtsuite.util.email.GoogleMail;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomHost;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.jms.Queue;

/**
 *
 * @author Roberto Rodriguez :: <roberto.rodriguez@smartbt.com>
 */
public class CoreTransactionUtil {

    public static void subTransactionFailed(Transaction transaction, DirexTransactionResponse response, Queue queue, String correlationId) throws Exception {
        if (queue != null) {
            response.setTransaction(null);
            System.out.println("CoreTransactionUtil :: subTransactionFailed  send(" + queue.getQueueName() + ", " + correlationId);
            JMSManager.get().send(response, queue, correlationId);
        } else {
            System.out.println("CoreTransactionUtil :: subTransactionFailed  queue = null.");
        }

        String transactionType = (response != null && response.getTransactionType() != null) ? response.getTransactionType().toString() : "Unknown";

        LogUtil.logAndStore("CoreBL", "SubTransactionFailed  -> " + transactionType + "   " + response != null ? response.getResultMessage() : "");

        boolean sendCardRestoreBecauseCardPersonalizationFailed = false;
        boolean alreadyContainsFailedSubTransaction = false;
        if (transaction != null && transaction.getSub_Transaction() != null) {
            for (SubTransaction subTransaction : transaction.getSub_Transaction()) {
                //It should contain personalization and it should be success.
                if (subTransaction.getType() == TransactionType.TECNICARD_CARD_PERSONALIZATION.getCode()
                        && subTransaction.getResultCode() == ResultCode.SUCCESS.getCode()) {
                    sendCardRestoreBecauseCardPersonalizationFailed = true;
                }

                if (subTransaction.getResultCode() != ResultCode.SUCCESS.getCode()) {
                    alreadyContainsFailedSubTransaction = true;
                }
            }
        }

        if (response != null
                && !(response.getTransactionType() == TransactionType.GENERIC_CARD_VALIDATION && alreadyContainsFailedSubTransaction) //Avoid a second sub transaction register in this case
                && response.getTransactionType() != null
                && response.getTransactionType() != TransactionType.TRANSACTION_TYPE
                && !transaction.containSubTransaction(response.getTransactionType())) { // si entra aki es pk alguna sub_transaccion especifica fallo.
            SubTransaction subTransaction = new SubTransaction();
            subTransaction.setType(response.getTransactionType().getCode());
            subTransaction.setResultCode(response.getResultCode().getCode());
            subTransaction.setResultMessage(response.getResultMessage());
            subTransaction.setErrorCode(response.getErrorCode());
            NomHost host = response.getTransactionType().getHost();
            subTransaction.setHost(host == null ? 0 : host.getId());
            //subTransaction.setTransaction( transaction );
            transaction.addSubTransaction(subTransaction);
        }

        transaction.setResultCode(response.getResultCode().getCode());
        String msg = (response.getResultMessage() != null && response.getResultMessage().length() > 254) ? response.getResultMessage().substring(0, 254) : response.getResultMessage();
        transaction.setResultMessage(msg);

        if (sendCardRestoreBecauseCardPersonalizationFailed && transaction.getData_sc1() != null) {
            System.out.println("Sending Tecnicard Restore because Personalization failed.");
            Map data = new HashMap();
            data.put(ParameterName.CARD_NUMBER, transaction.getData_sc1().getCardNumber());
            data.put(ParameterName.REQUEST_ID, transaction.getRequestId());

            DirexTransactionRequest request = new DirexTransactionRequest();
            request.setTransactionData(data);
            request.setTransactionType(TransactionType.TECNICARD_RESTORE_CARD);
            request.setCorrelation(transaction.getRequestId());
            try {
                AbstractCommonBusinessLogic.callHost(request, NomHost.TECNICARD, AbstractCommonBusinessLogic.GENERIC_VALIDATION_WAIT_TIME, transaction);
            } catch (TransactionalException transactionalException) {
                System.out.println("[CoreTransactionUtil].subTransactionFailed -> Caught TransactionalException");
                if (transaction.getData_sc1() != null) {
                    try {
                        HibernateUtil.beginTransaction();
                        CreditCardManager.get().delete(transaction.getData_sc1());//(If personalization fails, Card needs to be removed from Data Base)
                        HibernateUtil.commitTransaction();
                    } catch (Exception e) {
                        HibernateUtil.rollbackTransaction();
                    }
                    transaction.setData_sc1(null);
                }
                //Two reasons for doing this:
                //1-If personalization fails, Card needs to be removed from Data Base
                //2-In the recursive call, this will avoid calling Texnicard's Restore Card Again
                subTransactionFailed(transaction, transactionalException.getResponse(), null, null);
                return;
            }

        }

        persistTransaction(transaction);

    }

    //For the case there is not Response
    public static void subTransactionFailed(Transaction transaction, Queue queue, String correlationId, TransactionType transactionType, String exceptionMessage, ResultCode resultCode) throws Exception {
        if (resultCode == null) {
            resultCode = ResultCode.FAILED;
        }

        DirexTransactionResponse response = DirexTransactionResponse.forException(resultCode, exceptionMessage);

        response.setTransactionType(transactionType);

        subTransactionFailed(transaction, response, queue, correlationId);
    }

    public static void persistTransaction(Transaction transaction) throws Exception {

        List<EmailName> emailsToSend = new ArrayList<>();
        Map<String, String> emailValuesMap = new HashMap<>();

        TerminalManager terminalManager = TerminalManager.get();
        TransactionManager transactionManager = TransactionManager.get();
        ClientManager clientManager = ClientManager.get();
        transaction.setTransactionFinished(true);
        printTransaction(transaction);

        boolean sendCardPersonalizeSMS = false;
        String smsMessage = null;
        Client client = null;

        try {
            HibernateUtil.beginTransaction();

            int idTerminal = transaction.getTerminal().getId();
            Terminal persistentTerminal = terminalManager.findById(idTerminal);
            transaction.setTerminal(persistentTerminal);
            // transaction.getClient().getTransaction().add( transaction );

            client = transaction.getClient();
            if (transaction.getClient() != null) {

                //if is Check or Cash
                if (transaction.getOperation() != null
                        && (transaction.getOperation().equals("01") || transaction.getOperation().equals("02"))) {

                    //if SUCCESS
                    if (transaction.getResultCode() == ResultCode.SUCCESS.getCode()) {
                        System.out.println("[CoreTransactionUtil] Successfull Check or Cash clientId = " + client.getId());
                        CreditCard card = transaction.getData_sc1();

                        Integer successfulLoads = client.getSuccessfulLoads();
                        if (successfulLoads == null) {
                            successfulLoads = 0;
                        }

                        client.setSuccessfulLoads(successfulLoads + 1);

                        if (client.getSuccessfulLoads() == 2) {

                            emailsToSend.add(EmailName.TWO_SUCCESSFUL_LOADS_TO_TECNICARD);

                            emailValuesMap.put("user_name", client.getFirstName());
                            emailValuesMap.put("user_lastname", client.getLastName());
                            emailValuesMap.put("masked_card", card.getMaskCardNumber());
                        }

                        transaction.setData_sc1(card);

                        if (transaction.getSub_Transaction() != null) {
                            for (SubTransaction subTransaction : transaction.getSub_Transaction()) {
                                if ((subTransaction.getType() == TransactionType.TECNICARD_CARD_PERSONALIZATION.getCode() || subTransaction.getType() == TransactionType.CARD_PERSONALIZATION.getCode())
                                        && subTransaction.getResultCode() == ResultCode.SUCCESS.getCode()) {

                                    if (persistentTerminal.getMerchant() != null) {
                                        Integer inventory = persistentTerminal.getMerchant().getInventory();
                                        Integer threshold = persistentTerminal.getMerchant().getThreshold();

                                        if (inventory != null && threshold != null) {
                                            if (persistentTerminal.getMerchant().getInventory() == 1) {
                                                emailsToSend.add(EmailName.ALERT_INVENTORY_REACH_ZERO);
                                            }

                                            if (persistentTerminal.getMerchant().getInventory() > 0) {
                                                persistentTerminal.getMerchant().setInventory(inventory - 1);
                                            }

                                            if (persistentTerminal.getMerchant().getInventory() == threshold) {
                                                emailsToSend.add(EmailName.ALERT_INVENTORY_REACH_THRESHOLD);
                                                emailValuesMap.put("_merchant", persistentTerminal.getMerchant().getLegalName());
                                                emailValuesMap.put("_threshold", persistentTerminal.getMerchant().getThreshold() + "");
                                            }
                                        }

                                    } else {
                                        System.out.println("[CoreTransactionUtil] Merchant is NULL");
                                    }
                                    sendCardPersonalizeSMS = true;
                                }
                            }
                        }
                    }
                }

                clientManager.saveOrUpdate(client);
            }

            CreditCard cardToRemove = null;

            if (transaction.getResultCode() != 0
                    && transaction.getData_sc1() != null
                    && CreditCardManager.get().canDeleteCard(transaction.getData_sc1().getId(), transaction.getId())) {
                cardToRemove = transaction.getData_sc1();
                transaction.setData_sc1(null);
            }

            transactionManager.saveOrUpdate(transaction);

            if (cardToRemove != null) {
                CreditCardManager.get().delete(cardToRemove);
            }

            if(transaction.getIdeologyResultId() != null && transaction.getIdeologyResultId() != 0) {
                try{
                    IdeologyResultManager.get().setDisposition(transaction.getIdeologyResultId(), transaction.getResultCode() == ResultCode.SUCCESS.getCode());
                }catch(Exception e){
                    System.out.println("[CoreTransactionUtil] Failed to set IdeologyResult Disposition");
                    e.printStackTrace(); 
                }
            }

            System.out.println("**************  TRANSACTION SAVED SUCCESSFULY **************");

            try {
                if (!emailsToSend.isEmpty()) {

                    Email email;
                    for (EmailName emailName : emailsToSend) {
                        System.out.println("--------------  SENDING " + emailName + " EMAIL --------------");
                        email = EmailManager.get().getByName(emailName);
                        email.setValues(emailValuesMap);
                        GoogleMail.get().sendEmail(email);
                    }

                }
            } catch (Exception emailEx) {
                emailEx.printStackTrace();
            }

            String balanceAfterLoad = transaction.getBalanceAfterLoad();

            if (sendCardPersonalizeSMS) {
                if (balanceAfterLoad != null) {
                    smsMessage = "Thanks for activating your VoltCash card, available balance is $" + balanceAfterLoad + " TEXT STOP to STOP";
                }
            } else {
                if (transaction.getTransactionType() == TransactionType.CARD_RELOAD.getCode()
                        || transaction.getTransactionType() == TransactionType.CARD_RELOAD_WITH_DATA.getCode()) {
                    if (balanceAfterLoad != null) {
                        smsMessage = "Your VoltCash card was just loaded, available balance is $" + balanceAfterLoad;
                    }

                }

            }

            //---- SENDING PUSH NOTIFICATION ------------
            if (transaction.getResultCode() == ResultCode.SUCCESS.getCode()
                    && (transaction.getTransactionType() == TransactionType.CARD_RELOAD.getCode()
                    || transaction.getTransactionType() == TransactionType.NEW_CARD_LOAD.getCode()
                    || transaction.getTransactionType() == TransactionType.CARD_RELOAD_WITH_DATA.getCode())) {

                try {
                    MobileClientDisplay mobileClient = MobileClientDao.get().getMobileClientByClient(client.getId());

                    if (mobileClient != null
                            && mobileClient.getAllowNotifications() != null
                            && mobileClient.getAllowNotifications()
                            && mobileClient.getPushToken() != null) {

                        PushNotificationManager.sendCardLoadMessage(mobileClient.getDeviceType(), mobileClient.getPushToken(), mobileClient.getLang(), transaction.getAmmount());

                    } else {
                        System.out.println("PN could not send the msg");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            HibernateUtil.commitTransaction();
        } catch (Exception e) {
            HibernateUtil.rollbackTransaction();
            LogUtil.logAndStore("Exception in persistTransaction", e.getMessage());
            throw e;
        }

        String sendSMSProperty = System.getProperty("SEND_SMS");
        Boolean sendSMS = sendSMSProperty != null && sendSMSProperty.equalsIgnoreCase("true");

        if (smsMessage != null && sendSMS && client != null && client.getTelephone() != null && !client.getExcludeSms()) {

            System.out.println("--------------  SENDING SMS MESSAGE TO: 1" + client.getTelephone() + " --------------");

            System.out.println("text: " + smsMessage);
            try {
                SMSUtils.sendSMS("1" + client.getTelephone(), smsMessage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void printTransaction(Transaction transaction) {

        System.out.println("");

        System.out.println("--****************  SAVING TRANSACTION *****************--");
        System.out.println("type :: " + TransactionType.get(transaction.getTransactionType()));
        System.out.println("requestId :: " + transaction.getRequestId());
        System.out.println("date :: " + transaction.getDateTime());
        System.out.println("operation :: " + transaction.getOperation());
        System.out.println("ResultCode :: " + transaction.getResultCode());
        System.out.println("ResultMessage :: " + transaction.getResultMessage());

        System.out.println("");
        System.out.println("");
        System.out.println("--****************  sub-transactions  *****************--");
        System.out.println("");
        System.out.println("");

        List<SubTransaction> subTransactions = new ArrayList(transaction.getSub_Transaction());
        Collections.sort(subTransactions);

        for (SubTransaction subTransaction : subTransactions) {
            System.out.println("________________  " + subTransaction.getOrder() + " :: " + TransactionType.get(subTransaction.getType()) + " __________________");
            System.out.println("                ResultCode :: " + subTransaction.getResultCode());
            System.out.println("                ResultMessage :: " + subTransaction.getResultMessage());
            System.out.println("                Errorcode :: " + subTransaction.getErrorCode());
            System.out.println("");
        }
    }

//This method determines if the given ID is an ITIN, 
//-If it is an ITIN, it returns 100 otherwise it is SSN and the method returns 2
/*
     1.	it is an ITIN if it starts with 9
     and has a range of 70 - 88 in the fourth and fifth digit 

     or if it is included in one of the following ranges:

     •	900-70-0000 through 999-88-9999.
     •	900-90-0000 through 999-92-9999.
     •	900-94-0000 through 999-99-9999.

     */
    public static IdType getIdTypeFromId(String ssn) {
        IdType t;
        if (ssn == null || ssn.length() != 9) {
            return IdType.OTHERS;
        }

        for (int i = 0; i < ssn.length(); i++) {
            if (!Character.isDigit(ssn.charAt(i))) {
                return IdType.OTHERS;
            }
        }

        return isITIN(ssn) ? IdType.OTHERS : IdType.SSN;
    }

    private static boolean isITIN(String ssn) {
        return condition1(ssn) || condition2(ssn);
    }

    private static boolean condition1(String ssn) {
        Integer sub = Integer.parseInt(ssn.substring(3, 5));
        return ssn.charAt(0) == '9' && sub >= 70 && sub <= 88;
    }

    private static boolean condition2(String ssn) {
        Long sub = Long.parseLong(ssn);
        Boolean b1 = (sub >= 900_70_0000L && sub <= 999_88_9999L);
        Boolean b2 = (sub >= 900_90_0000L && sub <= 999_92_9999L);
        Boolean b3 = (sub >= 900_94_0000L && sub <= 999_99_9999L);

        return b1 || b2 || b3;
    }

//    public static void printMap(DirexTransactionRequest request) {
//        Map map = request.getTransactionData();
//
//        Iterator it = map.keySet().iterator();
//        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[NewCoreComplexTransactionBusinessLogic] Printing map", null);
//        while (it.hasNext()) {
//            Object key = it.next();
//            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[NewCoreComplexTransactionBusinessLogic] " + key + " -> " + map.get(key), null);
//        }
//    }
    public static void addSuccessfulSubTransaction(Transaction transaction, TransactionType transactionType) {
        SubTransaction subTransaction = new SubTransaction();
        subTransaction.setType(transactionType.getCode());
        subTransaction.setResultCode(ResultCode.SUCCESS.getCode());
        subTransaction.setResultMessage(ResultMessage.SUCCESS.getMessage());
        transaction.addSubTransaction(subTransaction);
    }

}
