/*
 ** File: CoreTransactionManager.java
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

import com.smartbt.girocheck.servercommon.enums.EnumApplicationParameter;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.enums.ResultMessage;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.jms.JMSManager;
import com.smartbt.girocheck.servercommon.manager.ApplicationParameterManager;
import com.smartbt.girocheck.servercommon.manager.ClientManager;
import com.smartbt.girocheck.servercommon.manager.CreditCardManager;
import com.smartbt.girocheck.servercommon.manager.TerminalManager;
import com.smartbt.girocheck.servercommon.manager.TransactionManager;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.messageFormat.IdType;
import com.smartbt.girocheck.servercommon.model.Address;
import com.smartbt.girocheck.servercommon.model.Client;
import com.smartbt.girocheck.servercommon.model.CreditCard;
import com.smartbt.girocheck.servercommon.model.Merchant;
import com.smartbt.girocheck.servercommon.model.PersonalIdentification;
import com.smartbt.girocheck.servercommon.model.State;
import com.smartbt.girocheck.servercommon.model.Terminal;
import com.smartbt.girocheck.servercommon.model.Transaction;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.girocheck.servercommon.utils.DirexException;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomHost;
import java.sql.Blob;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * The Core Manager Class
 */
public class CoreTransactionManager {

    private static CoreAbstractTransactionBusinessLogic businessLogic;
    public static List SINGLE_TRANSACTION_LIST;
    public static List COMPLEX_TRANSACTION_LIST;
    public static List LOCAL_TRANSACTION_LIST;
    public static List CARD_TO_BANK_BL_LIST;

    private static ApplicationParameterManager applicationParameterManager = new ApplicationParameterManager();
    private static Map<EnumApplicationParameter, Double> amountAplicationParameters;

    //TODO move this to System Properties
    public static final String ID_SCAN_AUTH_KEY = "48fa49a3-8ca4-4fc5-9a60-93271739969d";

    static {
        SINGLE_TRANSACTION_LIST = Arrays.asList(TransactionType.CHECK_AUTH, TransactionType.BALANCE_INQUIRY);
        COMPLEX_TRANSACTION_LIST = Arrays.asList(TransactionType.NEW_CARD_LOAD, TransactionType.CARD_RELOAD, TransactionType.CARD_RELOAD_WITH_DATA);
        LOCAL_TRANSACTION_LIST = Arrays.asList(TransactionType.CHECK_AUTH_LOCATION_CONFIG);
        CARD_TO_BANK_BL_LIST = Arrays.asList(TransactionType.CARD_TO_BANK);
    }

    /**
     * The default constructor
     */
    public CoreTransactionManager() {
    }

    /**
     * Process Direx Transaction Request.
     *
     * @param direxTransactionRequest The transaction request object
     * @throws Exception
     */
    public void processTransaction(DirexTransactionRequest direxTransactionRequest) throws Exception {
        TransactionType transactionType = direxTransactionRequest.getTransactionType();
        Map txData = direxTransactionRequest.getTransactionData();

        try {
            if (txData != null && txData.containsKey(TransactionType.TRANSACTION_TYPE)) {

                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, ">[Core2TransactionManager] Processing: " + transactionType, null);

                NomHost genericHost = getGenericHost(direxTransactionRequest);
                txData.put(ParameterName.HOSTNAME, genericHost);

                switch (transactionType) {
                    case BALANCE_INQUIRY:
                        businessLogic = new BalanceInquiryBusinessLogic();
                        break;
                    case CARD_RELOAD:
                    case CARD_RELOAD_WITH_DATA:
                    case NEW_CARD_LOAD:
                        String operation = (String) txData.get(ParameterName.OPERATION);

                        if (operation.contains("02")) {
                            businessLogic = new CashBusinessLogic();
                        } else {
                            businessLogic = new CheckBusinessLogic();
                        }
                        break;
                    case CHECK_AUTH_LOCATION_CONFIG:
                        businessLogic = new CoreLocalTransactionBusinessLogic();
                        break;
                    case CARD_TO_BANK:
                        businessLogic = new CoreCardToBankBusinessLogic();
                        break;
                    default:
                        String msg = "Transaction " + transactionType + " not supported.";
                        DirexTransactionResponse response = DirexTransactionResponse.forException(ResultCode.CORE_ERROR, ResultMessage.FAILED, msg, "");
                        JMSManager.get().send(response, JMSManager.get().getCoreOutQueue(), direxTransactionRequest.getCorrelation());
                        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[Core2TransactionManager] Exception: " + msg, null);
                        return;
                }

                Transaction transaction = null;

                if (transactionType != TransactionType.BALANCE_INQUIRY) {

                    transaction = createTransaction(direxTransactionRequest, transactionType);

                    if (transaction.getResultCode() != null) {
                        if (transaction.getResultCode() == 3) {
                            DirexTransactionResponse response = DirexTransactionResponse.forException(ResultCode.CARD_RELOAD_DATA_CANCELED, ResultMessage.CARD_RELOAD_DATA_CANCELED);
                            JMSManager.get().send(response, JMSManager.get().getCoreOutQueue(), direxTransactionRequest.getCorrelation());
                            return;
                        }
                        if (transaction.getResultCode() == 900) {
                            DirexTransactionResponse response = DirexTransactionResponse.forException(ResultCode.CARD_UNAUTHORIZED_BY_MIDDLEWARE, ResultMessage.CARD_UNAUTHORIZED_BY_MIDDLEWARE);
                            JMSManager.get().send(response, JMSManager.get().getCoreOutQueue(), direxTransactionRequest.getCorrelation());
                            return;
                        }
                    }
                }

                businessLogic.process(direxTransactionRequest, transaction);

            } else {
                DirexTransactionResponse response = DirexTransactionResponse.forException(ResultCode.CORE_ERROR, ResultMessage.CORE_RECEIVED_NULL);
                JMSManager.get().send(response, JMSManager.get().getCoreOutQueue(), direxTransactionRequest.getCorrelation());

            }
        } catch (LoggingValidationException lve) {
            DirexTransactionResponse response = DirexTransactionResponse.forException(ResultCode.CORE_ERROR, ResultMessage.FAILED, lve.getResultCode() + lve.getMessage(), "");
            JMSManager.get().send(response, JMSManager.get().getCoreOutQueue(), direxTransactionRequest.getCorrelation());
        } catch (CreditCardException ccException) {
            DirexTransactionResponse response = DirexTransactionResponse.forException(ResultCode.CORE_ERROR, ccException.getMessage());
            JMSManager.get().send(response, JMSManager.get().getCoreOutQueue(), direxTransactionRequest.getCorrelation());
        } catch (AmountException amountException) {
            DirexTransactionResponse response = DirexTransactionResponse.forException(ResultCode.CORE_ERROR, amountException.getMessage());
            JMSManager.get().send(response, JMSManager.get().getCoreOutQueue(), direxTransactionRequest.getCorrelation());
        } catch (Exception ex) {
            DirexTransactionResponse response = DirexTransactionResponse.forException(ResultCode.CORE_ERROR, ex);
            JMSManager.get().send(response, JMSManager.get().getCoreOutQueue(), direxTransactionRequest.getCorrelation());
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[Core2TransactionManager] Exception processing transaction. ", ex.getMessage());
            ex.printStackTrace();
        }
    }

    private Transaction createTransaction(DirexTransactionRequest direxTransactionRequest, TransactionType transactionType) throws Exception {
        TerminalManager terminalManager = new TerminalManager();
        TransactionManager transactionManager = new TransactionManager();
        ClientManager clientManager = new ClientManager();
        CreditCardManager creditCardManager = new CreditCardManager();
        Transaction transaction = new Transaction();
        Map txData = direxTransactionRequest.getTransactionData();
        try {
            HibernateUtil.beginTransaction();

            //------------------------------
            if (txData.get(ParameterName.HOSTNAME) == null) {
                transaction.setResultCode(900);
                return transaction;
            }

            String requestId = direxTransactionRequest.getRequestId();
            TransactionType originalTransactionType = direxTransactionRequest.getTransactionType();

            transaction.setRequestId(requestId);
            transaction.setTransactionType(originalTransactionType.getCode());
            Date currentDate = new Date();
            transaction.setDateTime(currentDate);

            String terminalId = (String) txData.get(ParameterName.TERMINAL_ID);

            Client client = null;

            //--------------------------------------   
            /*
        
             new card reload stuff
        
             */
            if (transactionType == TransactionType.CARD_RELOAD_WITH_DATA) {
                System.out.println("[Core2TransactionManager] transactionType == TransactionType.CARD_RELOAD_WITH_DATA");
                String cardNumberCR = (String) txData.get(ParameterName.CARD_NUMBER);

                client = creditCardManager.getClient(cardNumberCR);

                if (client == null || client.getData_SD() == null || client.getData_SD().isEmpty()) {
                    System.out.println("[Core2TransactionManager] Card not exist. Sending Code 3");

                    transaction.setResultCode(3);
                    return transaction;
                } else {
                    System.out.println("[Core2TransactionManager] CARD_RELOAD_WITH_DATA -> Card exist");
                }

                Address address = new Address();
                State state = new State();
                try {
                    address = client.getAddress();
                    state = address.getState();
                } catch (Exception e) {
                    System.out.println("[Core2TransactionManager] createTransaction() ... address or state null.");
                    e.printStackTrace();
                }

                PersonalIdentification identification = clientManager.getIdentificationByClientId(client.getId());

                byte[] idFront = new byte[0];// identification.getIdFrontAsByteArray();
                byte[] idBack = new byte[0];

                if (identification.getIdFront() != null) {
                    Blob idFrontBlob = identification.getIdFront();
                    int idFrontLength = (int) idFrontBlob.length();

                    if (idFrontLength > 1) {
                        idFront = idFrontBlob.getBytes(1, idFrontLength);
                    }

                }

                if (identification.getIdBack() != null) {
                    Blob idBackBlob = identification.getIdBack();
                    idBack = idBackBlob.getBytes(1, (int) idBackBlob.length());
                }

                if (idBack == null || idBack.length == 0) {
                    idBack = idFront;
                }

                txData.put(ParameterName.IDBACK, idBack);
                txData.put(ParameterName.IDFRONT, idFront);

                String phone = client.getTelephone() != null ? client.getTelephone() : "3055551212";

                txData.put(ParameterName.PHONE, phone);
                txData.put(ParameterName.TELEPHONE, phone);

                txData.put(ParameterName.SSN, client.getSsn());
                txData.put(ParameterName.IDTYPE, IdType.getIdType(identification.getIdType()));
                txData.put(ParameterName.ID, identification.getIdentification());
                txData.put(ParameterName.EXPIRATION_DATE_AS_DATE, identification.getExpirationDate());

                txData.put(ParameterName.BORNDATE_AS_DATE, client.getBornDate());
                txData.put(ParameterName.FIRST_NAME, client.getFirstName());
                txData.put(ParameterName.LAST_NAME, client.getLastName());

                System.out.println("Putting LAST_NAME = " + client.getLastName());

                txData.put(ParameterName.ADDRESS, address.getAddress());
                txData.put(ParameterName.CITY, address.getCity());
                txData.put(ParameterName.STATE, state.getCode());

                txData.put(ParameterName.STATE_ABBREVIATION, state.getAbbreviation());
                txData.put(ParameterName.ZIPCODE, address.getZipcode());

            }

            Terminal terminal = terminalManager.findBySerialNumber(terminalId);
            Merchant merchant = terminal.getMerchant();
            
            transaction.setMerchant(merchant);

            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[Core2TransactionManager] (terminalId) :: " + terminalId, null);
            if (terminal == null) {
                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[Core2TransactionManager] Terminal with id : " + terminalId + " doesn't exist. ", null);
                throw new LoggingValidationException(ResultCode.TERMINAL_ID_NOT_EXIST, " Terminal with id : " + terminalId + " doesn't exist. ", transaction);
            } else {
                String user = (String) txData.get(ParameterName.USER);
                String password = (String) txData.get(ParameterName.PASSWORD);
                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[Core2TransactionManager] createTransaction(...) (user/passw) :: (" + user + "/" + password + "). Originals (" + terminal.getGirocheckUser() + "/" + terminal.getGirocheckPassword() + ")", null);
                if (!terminal.getGirocheckUser().equals(user) || !terminal.getGirocheckPassword().equals(password)) {
                    throw new LoggingValidationException(ResultCode.LOGIN_FAILED, "Login Failed. For this terminal is (" + terminal.getGirocheckUser() + "/" + terminal.getGirocheckPassword() + "). Received(" + user + "/" + password + ")", transaction);
                }
            }

            txData.put(ParameterName.IDMERCHANT, merchant.getId()); 
            txData.put(ParameterName.MERCHANT_NAME, merchant.getLegalName());
            txData.put(ParameterName.TERMINAL_ID_ISTREAM, merchant.getIdIstreamTecnicardCheck());
            txData.put(ParameterName.MERCHANT_CITY_STATE, merchant.getCityState());
           
            if (txData.containsKey(ParameterName.AMOUNT)) {
                double ammount = (Double) txData.get(ParameterName.AMOUNT);

                transaction.setAmmount(ammount);

                String operation = (String) txData.get(ParameterName.OPERATION);

                if (amountAplicationParameters == null) {
                    amountAplicationParameters = applicationParameterManager.getAmountAplicationParameters();
                }

                Double activationFeeConfig = amountAplicationParameters.get(EnumApplicationParameter.ACTIVATION_FEE);
                txData.put(ParameterName.ACTIVATION_FEE_CONFIG, activationFeeConfig);

                validateAmount(ammount, operation, transaction, amountAplicationParameters);
            }

            if (txData.containsKey(ParameterName.SSN)) {
                String ssn = (String) txData.get(ParameterName.SSN);

                byte[] addressForm = null;

                if (transactionType == TransactionType.NEW_CARD_LOAD
                        && txData.containsKey(ParameterName.ADDRESS_CORRECT)
                        && (txData.get(ParameterName.ADDRESS_CORRECT) != null)
                        && (((String) txData.get(ParameterName.ADDRESS_CORRECT)).contains("n") || ((String) txData.get(ParameterName.ADDRESS_CORRECT)).contains("N"))
                        && txData.containsKey(ParameterName.ADDRESS_FORM) && txData.get(ParameterName.ADDRESS_FORM) != null) {
                    addressForm = (byte[]) txData.get(ParameterName.ADDRESS_FORM);
                }
                System.out.println("[Core2TransactionManager] client = clientManager.createOrGet( ssn, addressForm );");
                client = clientManager.createOrGet(ssn, addressForm);

                if (client.getEmail() != null && !client.getEmail().isEmpty()) {
                    txData.put(ParameterName.EMAIL, client.getEmail());
                }
            }

            if (client != null && client.hasITIN()) {
                /*
                 * ITIN value 100
                 */
                txData.put(ParameterName.IDTYPE, IdType.OTHERS);
            } else {
                txData.put(ParameterName.IDTYPE, IdType.SSN);
            }
  
            txData.put(ParameterName.CERTEGY_LOCATION_ID, merchant.getIdIstreamFuzeCash()); //TODO create new parameter for this
 

            transaction.setTerminal(terminal);
            transaction.setClient(client);

            if (client != null) {
                txData.put(ParameterName.CLIENT_ID, client.getId());
            }
            //Black List 
            if (client != null) {
                System.out.println("client.getBlackListAll() = " + client.getBlackListAll());

                if (client.getBlackListAll() != null && client.getBlackListAll() == true) {
                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreCardToBankBL::] Client " + client.getFirstName() + " is in the black list for All Transactions.", null);
                 
                    transaction.setResultCode(ResultCode.CLIENT_IN_BLACKLIST.getCode());
                    transaction.setTransactionFinished(true);
                    throw new TransactionException(transaction, ResultCode.CLIENT_IN_BLACKLIST, "Girocheck Decline-Please call customer service");
                }
            }

            if (txData.containsKey(ParameterName.PHONE)
                    && txData.get(ParameterName.PHONE) != null
                    && !((String) txData.get(ParameterName.PHONE)).isEmpty()
                    && ((String) txData.get(ParameterName.PHONE)).length() >= 3) {
                String cell_area_code = (String) txData.get(ParameterName.PHONE);
                txData.put(ParameterName.CELL_PHONE_AREA, cell_area_code.substring(0, 3));

                String cell_phone = (String) txData.get(ParameterName.PHONE);
                txData.put(ParameterName.CELL_PHONE, cell_phone.substring(3));

            }
            if (!txData.containsKey(ParameterName.ACCOUNT_NUMBER)) {
                String account = terminalManager.getAccountFromMerchantByTerminalSerialNumber(terminal.getSerialNumber());
                transaction.setAccount(account);
                txData.put(ParameterName.ACCOUNT_NUMBER, account);
            } else {
                transaction.setAccount((String) txData.get(ParameterName.ACCOUNT_NUMBER));
            }

            // ---------------------  CREDIT CARD LOGIC -------
            if (transactionType != TransactionType.CARD_TO_BANK
                    && transactionType != TransactionType.CHECK_AUTH_LOCATION_CONFIG) {
                if (txData.containsKey(ParameterName.CARD_NUMBER)) {
                    String cardNumber = (String) txData.get(ParameterName.CARD_NUMBER);

                    if (cardNumber != null && !cardNumber.isEmpty() && cardNumber.length() >= 12) {
                        System.out.println("[Core2TransactionManager] cardNumberCR == *******" + cardNumber.substring(12));

                        CreditCard creditCard = null;
                        try {
                            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[Core2TransactionManager] Obtaining Card for transactionType = " + transactionType.toString(), null);

                            switch (transactionType) {
                                case NEW_CARD_LOAD:
                                case CARD_RELOAD:
                                case CARD_RELOAD_WITH_DATA:
                                    creditCard = creditCardManager.createOrGet(cardNumber, client, merchant);
                                    break;
                                case BALANCE_INQUIRY:
                                    creditCard = creditCardManager.getCardByNumber(cardNumber);
                                    if (creditCard != null) {
                                        transaction.setClient(creditCard.getClient());
                                    }
                            }
                        } catch (Exception e) {
                            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[Core2TransactionManager] createTransaction(...) Error in creditCardManager.createOrGet(...) ", null);
                            e.printStackTrace();
                            throw new CreditCardException(ResultCode.CORE_ERROR, " CreditCard obtainment operation problem. ", transaction);
                        }

                        if (creditCard != null) {
                            transaction.setData_sc1(creditCard);
                        } else {
                            throw new CreditCardException(ResultCode.CREDIT_CARD_NOT_EXIST, "Card does not exist. ", transaction);
                        }
                    } else {
                        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[Core2TransactionManager] createTransaction(...) CreditCard value from the Terminal is NULL. ", null);
                        throw new CreditCardException(ResultCode.CREDIT_CARD_NOT_EXIST, "CreditCard Value from Terminal : Is Null. ", transaction);
                    }

                }
            }
            // -----------------------------------------------------------------
 
            if (txData.containsKey(ParameterName.OPERATION)) {
                String operation = (String) txData.get(ParameterName.OPERATION);
                transaction.setOperation(operation);
                if (operation.contains("01")) {// check 
                    txData.put(ParameterName.TERMINAL_ID_ISTREAM, merchant.getIdIstreamTecnicardCheck());
 
                    txData.put(ParameterName.TERMINAL_ID_TECNICARD, merchant.getIdTecnicardCheck());
                } else {  // if operation == 02 cash 
                    txData.put(ParameterName.TERMINAL_ID_ISTREAM, merchant.getIdIstreamTecnicardCash());
                    txData.put(ParameterName.TERMINAL_ID_TECNICARD, merchant.getIdTecnicardCash());
                }
            }

            transaction.setTransactionFinished(false);

            transactionManager.saveOrUpdate(transaction);

            txData.put(ParameterName.REQUEST_ID, transaction.getId());

//            HibernateUtil.commitTransaction();
        } catch (AmountException amountException) {
            amountException.printStackTrace();
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[Core2TransactionManager] createTransaction(...) amountException " + amountException.getMessage(), null);
            transactionManager.saveOrUpdate(amountException.getTransaction());
            throw amountException;
        } catch (LoggingValidationException loggingValidationException) {
            loggingValidationException.printStackTrace();
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[Core2TransactionManager] createTransaction(...) LoggingValidationException " + loggingValidationException.getMessage(), null);
            transactionManager.saveOrUpdate(loggingValidationException.getTransaction());
            throw loggingValidationException;
        } catch (CreditCardException cardException) {
            cardException.printStackTrace();
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[Core2TransactionManager] createTransaction(...) CreditCardException " + cardException.getMessage(), null);
            transactionManager.saveOrUpdate(cardException.getTransaction());
            throw cardException;
        } catch (TransactionException transactionalException) {
            transactionalException.printStackTrace();
            transactionManager.saveOrUpdate(transactionalException.getTransaction());
            throw transactionalException;
        } catch (Exception e) {
            e.printStackTrace();
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[Core2TransactionManager] createTransaction(...) Exception. ", e.getMessage());
            throw e;
        } finally {
            try {
                HibernateUtil.commitTransaction();
            } catch (Exception e) {
                HibernateUtil.rollbackTransaction();
            }

        }

        return transaction;
    }

    public NomHost getGenericHost(DirexTransactionRequest request) throws Exception {

        NomHost host = null;

        Map transactionData = request.getTransactionData();

        String cardNumber = (String) transactionData.get(ParameterName.CARD_NUMBER);

        host = UtilOperations.getHostByCard(cardNumber);

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[Core2TransactionManager] Finding Host result: " + host, null);
        return host;
    }

    public void validateAmount(Double amount, String operation, Transaction transaction, Map<EnumApplicationParameter, Double> amountParameters) throws AmountException {
        Double minCheck = amountParameters.get(EnumApplicationParameter.AMOUNT_MIN_CHECK);
        Double maxCheck = amountParameters.get(EnumApplicationParameter.AMOUNT_MAX_CHECK);
        Double minCash = amountParameters.get(EnumApplicationParameter.AMOUNT_MIN_CASH);
        Double maxCash = amountParameters.get(EnumApplicationParameter.AMOUNT_MAX_CASH);

        boolean isValid;
        if (operation != null && operation.contains("01")) {// check
            isValid = amount >= minCheck && amount <= maxCheck;
        } else {
            isValid = amount >= minCash && amount <= maxCash;
        }
        if (!isValid) {
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[Core2TransactionManager] Exception => INVALID AMOUNT", null);

            throw new AmountException("Amount " + amount + " is out of the allowed range.", transaction);
        }
    }

}

class TransactionException extends DirexException {

    private Transaction transaction;

    public TransactionException(Transaction transaction, ResultCode resultCode, String resultMessage) {
        super(resultCode, resultMessage);
        this.transaction = transaction;
    }

    public TransactionException(ResultCode resultCode, String message, Transaction transaction) {
        super(resultCode, message);
        this.transaction = transaction;
        this.transaction.setResultCode(resultCode.getCode());
        this.transaction.setResultMessage(message);
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

}

class LoggingValidationException extends TransactionException {

    public LoggingValidationException(ResultCode resultCode, String message, Transaction transaction) {
        super(resultCode, message, transaction);
    }
}

class CreditCardException extends TransactionException {

    public CreditCardException(ResultCode resultCode, String message, Transaction transaction) {
        super(resultCode, message, transaction);
    }
}

class AmountException extends TransactionException {

    public AmountException(String message, Transaction transaction) {
        super(ResultCode.INVALID_AMOUNT, message, transaction);
    }
}
