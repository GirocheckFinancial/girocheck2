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
import com.smartbt.girocheck.servercommon.manager.HostManager;
import com.smartbt.girocheck.servercommon.manager.TerminalManager;
import com.smartbt.girocheck.servercommon.manager.TransactionManager;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.messageFormat.IdType;
import com.smartbt.girocheck.servercommon.model.Address;
import com.smartbt.girocheck.servercommon.model.Client;
import com.smartbt.girocheck.servercommon.model.CreditCard;
import com.smartbt.girocheck.servercommon.model.Host;
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
    private Host cardHost;
    private static HostManager hostManager = new HostManager();
    private ApplicationParameterManager applicationParameterManager = new ApplicationParameterManager();
    private Map<EnumApplicationParameter, Double> amountAplicationParameters;

    //TODO move this to System Properties
    public static final String ID_SCAN_AUTH_KEY = "48fa49a3-8ca4-4fc5-9a60-93271739969d";

    static {
        SINGLE_TRANSACTION_LIST = Arrays.asList(TransactionType.ISTREAM_CHECK_AUTH, TransactionType.TECNICARD_BALANCE_INQUIRY, TransactionType.ORDER_EXPRESS_CONTRATACIONES);
        COMPLEX_TRANSACTION_LIST = Arrays.asList(TransactionType.NEW_CARD_LOAD, TransactionType.CARD_RELOAD, TransactionType.CARD_RELOAD_WITH_DATA);
        LOCAL_TRANSACTION_LIST = Arrays.asList(TransactionType.ISTREAM_CHECK_AUTH_LOCATION_CONFIG);
        CARD_TO_BANK_BL_LIST = Arrays.asList(TransactionType.TECNICARD_CARD_TO_BANK);
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

        try {
            if (direxTransactionRequest.getTransactionData() != null && direxTransactionRequest.getTransactionData().containsKey(TransactionType.TRANSACTION_TYPE)) {

                TransactionType transactionType = direxTransactionRequest.getTransactionType();

                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, ">[CoreTransactionManager] Processing: " + transactionType, null);

                switch (transactionType) {
                    case TECNICARD_BALANCE_INQUIRY:
                        businessLogic = new BalanceInquiryBusinessLogic();
                        break;
                    case CARD_RELOAD:
                    case CARD_RELOAD_WITH_DATA:
                    case NEW_CARD_LOAD:
                        String operation = (String) direxTransactionRequest.getTransactionData().get(ParameterName.OPERATION);

                        if (operation.contains("02")) {
                            businessLogic = new CashBusinessLogic();
                        } else {
                            businessLogic = new CheckBusinessLogic();
                        }
                        break;
                    case ISTREAM_CHECK_AUTH_LOCATION_CONFIG:
                        businessLogic = new CoreLocalTransactionBusinessLogic();
                        break;
                    case TECNICARD_CARD_TO_BANK:
                        businessLogic = new CoreCardToBankBusinessLogic();
                        break;
                    default:
                        String msg = "Transaction " + transactionType + " not supported.";
                        DirexTransactionResponse response = DirexTransactionResponse.forException(ResultCode.CORE_ERROR, ResultMessage.FAILED, msg, "");
                        JMSManager.get().send(response, JMSManager.get().getCoreOutQueue(), direxTransactionRequest.getCorrelation());
                        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreTransactionManager] Exception: " + msg, null);
                        return;
                }

                Transaction transaction = null;

                if (transactionType != TransactionType.TECNICARD_BALANCE_INQUIRY) {

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
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreTransactionManager] Exception processing transaction. ", ex.getMessage());
            ex.printStackTrace();
        }
    }

    private Transaction createTransaction(DirexTransactionRequest direxTransactionRequest, TransactionType transactionType) throws Exception {
        TerminalManager terminalManager = new TerminalManager();
        TransactionManager transactionManager = new TransactionManager();
        ClientManager clientManager = new ClientManager();
        CreditCardManager creditCardManager = new CreditCardManager();
        Transaction transaction = new Transaction();

        try {
            HibernateUtil.beginTransaction();

            cardHost = findingHost(direxTransactionRequest);

            //------------------------------
            if (cardHost == null || cardHost.getHostName() == null || cardHost.getHostName().equals(NomHost.FUZE.toString())) {
                transaction.setResultCode(900);
                return transaction;
            }

            String requestId = direxTransactionRequest.getRequestId();
            TransactionType originalTransactionType = direxTransactionRequest.getTransactionType();

            transaction.setRequestId(requestId);
            transaction.setTransactionType(originalTransactionType.getCode());
            Date currentDate = new Date();
            transaction.setDateTime(currentDate);

            String terminalId = (String) direxTransactionRequest.getTransactionData().get(ParameterName.TERMINAL_ID);

            Client client = null;

            //--------------------------------------   
            /*
        
             new card reload stuff
        
             */
            if (transactionType == TransactionType.CARD_RELOAD_WITH_DATA) {
                System.out.println("[CoreTransactionManager] transactionType == TransactionType.CARD_RELOAD_WITH_DATA");
                String cardNumberCR = (String) direxTransactionRequest.getTransactionData().get(ParameterName.CARD_NUMBER);

                client = creditCardManager.getClient(cardNumberCR);

                if (client == null || client.getData_SD() == null || client.getData_SD().isEmpty()) {
                    System.out.println("[CoreTransactionManager] Sending Code 3");
                    System.out.println("client == null -> " + (client == null));
                    if (client != null) {
                        System.out.println("client.getData_SD() == null -> " + (client.getData_SD() == null));
                        if (client.getData_SD() != null) {
                            System.out.println("client.getData_SD().isEmpty() -> " + (client.getData_SD().isEmpty()));
                        }
                    }

                    transaction.setResultCode(3);
                    return transaction;
                } else {
                    System.out.println("[CoreTransactionManager] CARD_RELOAD_WITH_DATA -> Card exist");
                }

                System.out.println("[CoreTransactionManager] CARD_RELOAD_WITH_DATA -> client.ssn = " + client.getSsn());

                Address address = new Address();
                State state = new State();
                try {
                    address = client.getAddress();
                    state = address.getState();
                } catch (Exception e) {

                    System.out.println("[CoreTransactionManager] createTransaction() ... address or state null.");
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

                direxTransactionRequest.getTransactionData().put(ParameterName.IDBACK, idBack);
                direxTransactionRequest.getTransactionData().put(ParameterName.IDFRONT, idFront);

                String phone = client.getTelephone() != null ? client.getTelephone() : "3055551212";

                direxTransactionRequest.getTransactionData().put(ParameterName.PHONE, phone);
                direxTransactionRequest.getTransactionData().put(ParameterName.TELEPHONE, phone);

                direxTransactionRequest.getTransactionData().put(ParameterName.SSN, client.getSsn());
                direxTransactionRequest.getTransactionData().put(ParameterName.IDTYPE, IdType.getIdType(identification.getIdType()));
                direxTransactionRequest.getTransactionData().put(ParameterName.ID, identification.getIdentification());
                direxTransactionRequest.getTransactionData().put(ParameterName.EXPIRATION_DATE_AS_DATE, identification.getExpirationDate());

                direxTransactionRequest.getTransactionData().put(ParameterName.BORNDATE_AS_DATE, client.getBornDate());
                direxTransactionRequest.getTransactionData().put(ParameterName.FIRST_NAME, client.getFirstName());
                direxTransactionRequest.getTransactionData().put(ParameterName.LAST_NAME, client.getLastName());
                
                System.out.println("Putting LAST_NAME = " + client.getLastName());
                
                direxTransactionRequest.getTransactionData().put(ParameterName.ADDRESS, address.getAddress());
                direxTransactionRequest.getTransactionData().put(ParameterName.CITY, address.getCity());
                direxTransactionRequest.getTransactionData().put(ParameterName.STATE, state.getCode());

                direxTransactionRequest.getTransactionData().put(ParameterName.STATE_ABBREVIATION, state.getAbbreviation());
                direxTransactionRequest.getTransactionData().put(ParameterName.ZIPCODE, address.getZipcode());

            }

            Terminal terminal = terminalManager.findBySerialNumber(terminalId);

            transaction.setMerchant(terminal.getMerchant());

            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreTransactionManager] (terminalId) :: " + terminalId, null);
            if (terminal == null) {
                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreTransactionManager] Terminal with id : " + terminalId + " doesn't exist. ", null);
                throw new LoggingValidationException(ResultCode.TERMINAL_ID_NOT_EXIST, " Terminal with id : " + terminalId + " doesn't exist. ", transaction);
            } else {
                String user = (String) direxTransactionRequest.getTransactionData().get(ParameterName.USER);
                String password = (String) direxTransactionRequest.getTransactionData().get(ParameterName.PASSWORD);
                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreTransactionManager] createTransaction(...) (user/passw) :: (" + user + "/" + password + "). Originals (" + terminal.getGirocheckUser() + "/" + terminal.getGirocheckPassword() + ")", null);
                if (!terminal.getGirocheckUser().equals(user) || !terminal.getGirocheckPassword().equals(password)) {
                    throw new LoggingValidationException(ResultCode.LOGIN_FAILED, "Login Failed. For this terminal is (" + terminal.getGirocheckUser() + "/" + terminal.getGirocheckPassword() + "). Received(" + user + "/" + password + ")", transaction);
                }
            }

            direxTransactionRequest.getTransactionData().put(ParameterName.IDMERCHANT, terminal.getMerchant().getId());

            direxTransactionRequest.getTransactionData().put(ParameterName.LOCATION_ID, terminal.getMerchant().getIdIstreamTecnicardCheck());

            if (direxTransactionRequest.getTransactionData().containsKey(ParameterName.AMMOUNT)) {
                double ammount = (Double) direxTransactionRequest.getTransactionData().get(ParameterName.AMMOUNT);

                transaction.setAmmount(ammount);

                String operation = (String) direxTransactionRequest.getTransactionData().get(ParameterName.OPERATION);

                if (amountAplicationParameters == null) {
                    amountAplicationParameters = applicationParameterManager.getAmountAplicationParameters();
                }

                Double activationFeeConfig = amountAplicationParameters.get(EnumApplicationParameter.ACTIVATION_FEE);
                direxTransactionRequest.getTransactionData().put(ParameterName.ACTIVATION_FEE_CONFIG, activationFeeConfig);

                validateAmount(ammount, operation, transaction, amountAplicationParameters);
            }

            if (direxTransactionRequest.getTransactionData().containsKey(ParameterName.SSN)) {
                String ssn = (String) direxTransactionRequest.getTransactionData().get(ParameterName.SSN);

                byte[] addressForm = null;

                if (transactionType == TransactionType.NEW_CARD_LOAD
                        && direxTransactionRequest.getTransactionData().containsKey(ParameterName.ADDRESS_CORRECT)
                        && (direxTransactionRequest.getTransactionData().get(ParameterName.ADDRESS_CORRECT) != null)
                        && (((String) direxTransactionRequest.getTransactionData().get(ParameterName.ADDRESS_CORRECT)).contains("n") || ((String) direxTransactionRequest.getTransactionData().get(ParameterName.ADDRESS_CORRECT)).contains("N"))
                        && direxTransactionRequest.getTransactionData().containsKey(ParameterName.ADDRESS_FORM) && direxTransactionRequest.getTransactionData().get(ParameterName.ADDRESS_FORM) != null) {
                    addressForm = (byte[]) direxTransactionRequest.getTransactionData().get(ParameterName.ADDRESS_FORM);
                }
                System.out.println("[CoreTransactionManager] client = clientManager.createOrGet( ssn, addressForm );");
                client = clientManager.createOrGet(ssn, addressForm);

                if (client.getEmail() != null && !client.getEmail().isEmpty()) {
                    direxTransactionRequest.getTransactionData().put(ParameterName.EMAIL, client.getEmail());
                }
            }

            if (client != null && client.hasITIN()) {
                /*
                 * ITIN value 100
                 */
                direxTransactionRequest.getTransactionData().put(ParameterName.IDTYPE, IdType.OTHERS);
            } else {
                direxTransactionRequest.getTransactionData().put(ParameterName.IDTYPE, IdType.SSN);
            }

            if (transactionType == TransactionType.NEW_CARD_LOAD || transactionType == TransactionType.CARD_RELOAD || transactionType == TransactionType.CARD_RELOAD_WITH_DATA) {
                direxTransactionRequest.getTransactionData().put(ParameterName.MERCHANT_NAME, terminal.getMerchant().getLegalName());
              //  direxTransactionRequest.getTransactionData().put(ParameterName.EMAIL, "girocheck@cardmarte.com");
            }

            direxTransactionRequest.getTransactionData().put(ParameterName.IDPOS, terminal.getMerchant().getIdPosOrderExp());
            direxTransactionRequest.getTransactionData().put(ParameterName.IDTELLER, terminal.getMerchant().getIdTellerOrderExp());
            direxTransactionRequest.getTransactionData().put(ParameterName.IDTELLERPAGO, terminal.getMerchant().getIdTellerPagoOrderExp());
            direxTransactionRequest.getTransactionData().put(ParameterName.CERTEGY_LOCATION_ID, terminal.getMerchant().getIdIstreamFuzeCash()); //TODO create new parameter for this

            if (transactionType == TransactionType.ISTREAM_CHECK_AUTH_LOCATION_CONFIG) {
                direxTransactionRequest.getTransactionData().put(ParameterName.TERMINAL_ID_ISTREAM, terminal.getMerchant().getIdIstreamTecnicardCheck());
                //todo remove this for version 2 (We eill take this from System Properties)
                direxTransactionRequest.getTransactionData().put(ParameterName.TERMINAL_USER_ISTREAM, terminal.getMerchant().getIstreamUser());
                direxTransactionRequest.getTransactionData().put(ParameterName.TERMINAL_PASSWORD_ISTREAM, terminal.getMerchant().getIstreamPassword());
            }

            transaction.setTerminal(terminal);
            transaction.setClient(client);

            //Black List 
            if (client != null) {
                System.out.println("client.getBlackListAll() = " + client.getBlackListAll());

                if (client.getBlackListAll() != null && client.getBlackListAll() == true) {
                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreCardToBankBL::] Client " + client.getFirstName() + " is in the black list for All Transactions.", null);
                    transaction.setResultMessage("Client is in Black List.");
                    transaction.setResultCode(ResultCode.CLIENT_IN_BLACKLIST.getCode());
                    transaction.setTransactionFinished(true);
                    throw new TransactionException(transaction, ResultCode.CLIENT_IN_BLACKLIST, "Girocheck Decline-Please call customer service");
                }
            }

            if (direxTransactionRequest.getTransactionData().containsKey(ParameterName.PHONE)
                    && direxTransactionRequest.getTransactionData().get(ParameterName.PHONE) != null
                    && !((String) direxTransactionRequest.getTransactionData().get(ParameterName.PHONE)).isEmpty()
                    && ((String) direxTransactionRequest.getTransactionData().get(ParameterName.PHONE)).length() >= 3) {
                String cell_area_code = (String) direxTransactionRequest.getTransactionData().get(ParameterName.PHONE);
                direxTransactionRequest.getTransactionData().put(ParameterName.CELL_PHONE_AREA, cell_area_code.substring(0, 3));

                String cell_phone = (String) direxTransactionRequest.getTransactionData().get(ParameterName.PHONE);
                direxTransactionRequest.getTransactionData().put(ParameterName.CELL_PHONE, cell_phone.substring(3));

            }
            if (!direxTransactionRequest.getTransactionData().containsKey(ParameterName.ACCOUNT_NUMBER)) {
                String account = terminalManager.getAccountFromMerchantByTerminalSerialNumber(terminal.getSerialNumber());
                transaction.setAccount(account);
                direxTransactionRequest.getTransactionData().put(ParameterName.ACCOUNT_NUMBER, account);
            } else {
                transaction.setAccount((String) direxTransactionRequest.getTransactionData().get(ParameterName.ACCOUNT_NUMBER));
            }

            // ---------------------  CREDIT CARD LOGIC -------
            if (transactionType != TransactionType.TECNICARD_CARD_TO_BANK
                    && transactionType != TransactionType.ISTREAM_CHECK_AUTH_LOCATION_CONFIG) {
                if (direxTransactionRequest.getTransactionData().containsKey(ParameterName.CARD_NUMBER)) {
                    String cardNumber = (String) direxTransactionRequest.getTransactionData().get(ParameterName.CARD_NUMBER);

                    if (cardNumber != null && !cardNumber.isEmpty() && cardNumber.length() >= 12) {
                        System.out.println("[CoreTransactionManager] cardNumberCR == *******" + cardNumber.substring(12));

                        CreditCard creditCard = null;
                        try {
                            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreTransactionManager] Obtaining Card for transactionType = " + transactionType.toString(), null);

                            switch (transactionType) {
                                case NEW_CARD_LOAD:
                                case CARD_RELOAD:
                                case CARD_RELOAD_WITH_DATA:
                                    creditCard = creditCardManager.createOrGet(cardNumber, client, terminal.getMerchant());
                                    break;
                                case TECNICARD_BALANCE_INQUIRY:
                                    creditCard = creditCardManager.getCardByNumber(cardNumber);
                                    if (creditCard != null) {
                                        transaction.setClient(creditCard.getClient());
                                    }
                            }
                        } catch (Exception e) {
                            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreTransactionManager] createTransaction(...) Error in creditCardManager.createOrGet(...) ", null);
                            e.printStackTrace();
                            throw new CreditCardException(ResultCode.CORE_ERROR, " CreditCard obtainment operation problem. ", transaction);
                        }

                        if (creditCard != null) {
                            transaction.setData_sc1(creditCard);
                        } else {
                            throw new CreditCardException(ResultCode.CREDIT_CARD_NOT_EXIST, "Card does not exist. ", transaction);
                        }
                    } else {
                        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreTransactionManager] createTransaction(...) CreditCard value from the Terminal is NULL. ", null);
                        throw new CreditCardException(ResultCode.CREDIT_CARD_NOT_EXIST, "CreditCard Value from Terminal : Is Null. ", transaction);
                    }

                }
            }
            // -----------------------------------------------------------------

            direxTransactionRequest.getTransactionData().put(ParameterName.HOSTNAME, cardHost.getHostName());
            //todo remove this for version 2 (We eill take this from System Properties)
            if (COMPLEX_TRANSACTION_LIST.contains(transactionType)) {
                direxTransactionRequest.getTransactionData().put(ParameterName.TERMINAL_USER_ISTREAM, terminal.getMerchant().getIstreamUser());
                direxTransactionRequest.getTransactionData().put(ParameterName.TERMINAL_PASSWORD_ISTREAM, terminal.getMerchant().getIstreamPassword());
            }

            if (direxTransactionRequest.getTransactionData().containsKey(ParameterName.OPERATION)) {
                String operation = (String) direxTransactionRequest.getTransactionData().get(ParameterName.OPERATION);
                transaction.setOperation(operation);
                if (operation.contains("01")) {// check 
                    direxTransactionRequest.getTransactionData().put(ParameterName.TERMINAL_ID_ISTREAM, terminal.getMerchant().getIdIstreamTecnicardCheck());

                    direxTransactionRequest.getTransactionData().put(ParameterName.IDSERVICE, "1");
                    direxTransactionRequest.getTransactionData().put(ParameterName.TERMINAL_ID_TECNICARD, terminal.getMerchant().getIdTecnicardCheck());
                } else {  // if operation == 02 cash 
                    direxTransactionRequest.getTransactionData().put(ParameterName.TERMINAL_ID_ISTREAM, terminal.getMerchant().getIdIstreamTecnicardCash());

                    direxTransactionRequest.getTransactionData().put(ParameterName.IDSERVICE, "2");
                    direxTransactionRequest.getTransactionData().put(ParameterName.TERMINAL_ID_TECNICARD, terminal.getMerchant().getIdTecnicardCash());
                }
            }

            transaction.setTransactionFinished(false);

            transactionManager.saveOrUpdate(transaction);

            direxTransactionRequest.getTransactionData().put(ParameterName.REQUEST_ID, transaction.getId());

//            HibernateUtil.commitTransaction();
        } catch (AmountException amountException) {
            amountException.printStackTrace();
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreTransactionManager] createTransaction(...) amountException " + amountException.getMessage(), null);
            transactionManager.saveOrUpdate(amountException.getTransaction());
            throw amountException;
        } catch (LoggingValidationException loggingValidationException) {
            loggingValidationException.printStackTrace();
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreTransactionManager] createTransaction(...) LoggingValidationException " + loggingValidationException.getMessage(), null);
            transactionManager.saveOrUpdate(loggingValidationException.getTransaction());
            throw loggingValidationException;
        } catch (CreditCardException cardException) {
            cardException.printStackTrace();
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreTransactionManager] createTransaction(...) CreditCardException " + cardException.getMessage(), null);
            transactionManager.saveOrUpdate(cardException.getTransaction());
            throw cardException;
        } catch (TransactionException transactionalException) {
            transactionalException.printStackTrace();
            transactionManager.saveOrUpdate(transactionalException.getTransaction());
            throw transactionalException;
        } catch (Exception e) {
            e.printStackTrace();
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreTransactionManager] createTransaction(...) Exception. ", e.getMessage());
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

    public Host findingHost(DirexTransactionRequest request) throws Exception {

        Host host = new Host();

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreTransactionManager] findingHost(DirexTransactionRequest request). ", null);

        Map transactionData = request.getTransactionData();

        if (transactionData.containsKey(ParameterName.CARD_NUMBER)) {

            String cardNumber = (String) transactionData.get(ParameterName.CARD_NUMBER);

            String binNumber = "";
            try {
                binNumber = cardNumber.substring(0, 6);
            } catch (Exception e) {
                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreTransactionManager] BAD CARD NUMBER WAS RECEIVED " + cardNumber + " and the exception was: ", e.getMessage());
            }

            if (!binNumber.isEmpty()) {
                host = hostManager.getHostByBinNumber(binNumber);
            }

        } else {
            host = hostManager.getDefaultHost();
        }

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreTransactionManager] Finding Host result: " + host.getHostName(), null);
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
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreTransactionManager] Exception => INVALID AMOUNT", null);

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
