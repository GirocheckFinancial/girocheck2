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

import com.smartbt.girocheck.servercommon.email.EmailUtils;
import com.smartbt.girocheck.servercommon.email.ImagePart;
import com.smartbt.vtsuite.util.CoreTransactionUtil;
import com.smartbt.girocheck.servercommon.enums.EnumCountry;
import com.smartbt.girocheck.servercommon.enums.EnumState;
import com.smartbt.girocheck.servercommon.jms.JMSManager;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.enums.ResultMessage;
import com.smartbt.girocheck.servercommon.manager.CountryManager;
import com.smartbt.girocheck.servercommon.manager.FeeBucketsManager;
import com.smartbt.girocheck.servercommon.manager.PersonalIdentificationManager;
import com.smartbt.girocheck.servercommon.manager.StateManager;
import com.smartbt.girocheck.servercommon.manager.TransactionManager;
import com.smartbt.girocheck.servercommon.messageFormat.IdType;
import com.smartbt.girocheck.servercommon.model.Address;
import com.smartbt.girocheck.servercommon.model.Check;
import com.smartbt.girocheck.servercommon.model.Country;
import com.smartbt.girocheck.servercommon.model.PersonInfo;
import com.smartbt.girocheck.servercommon.model.PersonalIdentification;
import com.smartbt.girocheck.servercommon.model.State;
import com.smartbt.girocheck.servercommon.model.SubTransaction;
import com.smartbt.girocheck.servercommon.model.Transaction;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.girocheck.servercommon.utils.IDScanner;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import static com.smartbt.vtsuite.manager.AbstractCommonBusinessLogic.stateManager;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomHost;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomState;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.sql.rowset.serial.SerialBlob;
import org.apache.xerces.impl.dv.util.Base64;

@TransactionManagement(value = TransactionManagementType.BEAN)
public class NewCoreComplexTransactionBusinessLogic extends CoreAbstractTransactionBusinessLogic {

    private JMSManager jmsManager = JMSManager.get();
    private int state = 0;
    private String checkId;
    private Transaction transaction;
    private String correlationId;
    // WAITING TIMES
    private static final long TECNICARD_CONFIRMATION_WAIT_TIME = 180000;//3min
    private static final long ISTREAM_HOST_WAIT_TIME = 30000;//30sec
    private static final long PERSONAL_INFO_WAIT_TIME = 420000;//7min 
    public static final long GENERIC_VALIDATION_WAIT_TIME = 60000;//1min
    private static final long GENERIC_CARD_LOAD_WAIT_TIME = 60000;//1min
    private static final long CERTEGY_INFO_WAIT_TIME = 420000;//7min

    private static CountryManager countryManager = new CountryManager();
    private static TransactionManager transactionManager = new TransactionManager();
    private static StateManager stateManager = new StateManager();
    private PersonalIdentificationManager personalIdentificationManager = new PersonalIdentificationManager();

    public NewCoreComplexTransactionBusinessLogic() {
        super();
    }

    @Override
    public void process(DirexTransactionRequest request, Transaction transaction) throws Exception {

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[NewCoreComplexTransactionBusinessLogic] Send answer to TERMINAL", null);
        boolean idScanSuccess = true;
        state = 1;
        DirexTransactionResponse response;
        Map responseMap;
        TransactionType originalTransaction = request.getTransactionType();

        //COMMENT FOR TESTING
        if (!originalTransaction.equals(TransactionType.CARD_RELOAD_WITH_DATA)) {

            //  Map transactioDataWITHDL;
            DirexTransactionResponse personalInfoResponse = getPersonalInfoFromIDReader(request);

            if (idScanSuccess = personalInfoResponse.wasApproved()) {
                Map driverLicenseInfo = personalInfoResponse.getTransactionData();
                request.getTransactionData().putAll(driverLicenseInfo);
            } else {
                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] Driver License ILEGIBLE", null);
            }

        }

        this.transaction = transaction;
        this.correlationId = request.getCorrelation();
        transaction.setSingle(Boolean.FALSE);

        if (request.getTransactionData().containsKey(ParameterName.OPERATION)) {
            transaction.setOperation((String) request.getTransactionData().get(ParameterName.OPERATION));
        }

        if (request.getTransactionData().containsKey(ParameterName.MICR)) {
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] Value of MICR from POS: " + request.getTransactionData().get(ParameterName.MICR), null);
        }

        try {

            //-------  go to HOST ISTREAM (checkAuth) -------
            request.setTransactionType(TransactionType.ISTREAM_CHECK_AUTH);

            try {
                response = sendMessageToHost(request, NomHost.ISTREAM.toString(), ISTREAM_HOST_WAIT_TIME, false);

            } catch (Exception e) {
//                generateNotificationEmail("There was a problem receiving generic host validation response", null);
                return;
            }

            if (!response.wasApproved()) {
                response.setTransactionType(request.getTransactionType());
                CoreTransactionUtil.subTransactionFailed(transaction, response, jmsManager.getCoreOutQueue(), correlationId);
                return;
            } else {
                transaction.addSubTransactionList(response.getTransaction().getSub_Transaction());
            }

            responseMap = response.getTransactionData();

            //-----  WAIT PERSONAL INFO MESSAGE -------------- 
            checkId = (String) responseMap.get(ParameterName.CHECK_ID);

            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] ISTREAM checkId value: " + checkId, null);

            DirexTransactionRequest personalInfoRequest;

            try {
                personalInfoRequest = receiveMessageFromFront(TransactionType.PERSONAL_INFO);
            } catch (Exception e) {
                CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[NewCoreComplexTransactionBusinessLogic] Error Receiving personal info ", null);
                e.printStackTrace();
//                generateNotificationEmail("There was a problem receiving personal info", null);
                return;
            }

            Map personalInfoRequestMap = personalInfoRequest.getTransactionData();

            //dont take the past name sent by iStream,
            //because when it is composed it will be trimed
            if (idScanSuccess && personalInfoRequestMap.containsKey(ParameterName.LAST_NAME)) {
                personalInfoRequestMap.remove(ParameterName.LAST_NAME);
            }

            request.getTransactionData().putAll(personalInfoRequestMap);

            feeCalculator(request, transaction);

            if (personalInfoRequestMap.containsKey(ParameterName.ID) && personalInfoRequestMap.get(ParameterName.ID).equals("0")) {
                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] ParameterName.ID from personal info: " + personalInfoRequestMap.get(ParameterName.ID), null);
                sendResponseToIStreamFront(false, checkId);
                response = DirexTransactionResponse.forException(ResultCode.ISTREAM_CANCELLED_TRANSACTION, ResultMessage.ISTREAM_CANCELLED_TRANSACTION);
                response.setTransactionType(TransactionType.PERSONAL_INFO);
                CoreTransactionUtil.subTransactionFailed(transaction, response, jmsManager.getCoreOutQueue(), correlationId);
                return;
            }
            //------ CREATE PERSONAL INFO SUBTRANSACTION ------
            SubTransaction personalInfoSubTransaction = new SubTransaction();
            personalInfoSubTransaction.setType(TransactionType.PERSONAL_INFO.getCode());
            personalInfoSubTransaction.setResultCode(ResultCode.SUCCESS.getCode());
            personalInfoSubTransaction.setResultMessage(ResultMessage.SUCCESS.getMessage());
            transaction.addSubTransaction(personalInfoSubTransaction);

            //--------------------------------------------------
            state = 2;
            //-------------------------
            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[NewCoreComplexTransactionBusinessLogic] Persist personal Info", null);
            //PERSIST PERSONAL INFO

            fixPersonalInfoName(request);
            fillOutClient(request.getTransactionData());
            fillOutClientAddress(request.getTransactionData());
            PersonalIdentification identification = fillOutPersonalIdentification(request.getTransactionData());
            fillOutCheck(request.getTransactionData());

            if (personalInfoRequestMap.containsKey(ParameterName.IDCOUNTRY) || personalInfoRequestMap.containsKey(ParameterName.IDSTATE)
                    || personalInfoRequestMap.containsKey(ParameterName.COUNTRY) || personalInfoRequestMap.containsKey(ParameterName.STATE)) {
                try {
                    HibernateUtil.beginTransaction();

                    request.getTransactionData().put(ParameterName.OEIDSTATE, NomState.FL.getId() + "");
                    request.getTransactionData().put(ParameterName.IDCOUNTRY, EnumCountry.EUA.getCode() + "");

                    Country country = countryManager.getByAbbreviation("USA");
                    transaction.getClient().getAddress().setCountry(country);

                    if (identification.getIdType() != null) {
                        personalIdentificationManager.removeByClientAndType(transaction.getClient().getId(), identification.getIdType(), identification.getId());
                    }

                    String stateAbbreviation = (String) personalInfoRequestMap.get(ParameterName.STATE_ABBREVIATION);
                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] processPersonalInfo -> StateAbbreviation = " + stateAbbreviation, null);

                    if (stateAbbreviation != null && !stateAbbreviation.isEmpty()
                            && (!personalInfoRequestMap.containsKey(ParameterName.STATE) //Do this just if STATE is not in the map
                            || (transaction.getClient().getAddress() != null && transaction.getClient().getAddress().getState() == null))) { //Do it when state is null in Client's address
                        State state = stateManager.getByAbbreviation(stateAbbreviation);

                        transaction.getClient().getAddress().setState(state);
                        request.getTransactionData().put(ParameterName.STATE, state.getCode() + "");
                    }

                    HibernateUtil.commitTransaction();
                } catch (Exception e) {
                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] Error 2 ", e.getMessage());
                    HibernateUtil.rollbackTransaction();
                    e.printStackTrace();
                }
            }

            identification.setClient(transaction.getClient());
            Set set = new HashSet();
            set.add(identification);
            transaction.getClient().setData_SD(set);

            // notify to IStream
            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[NewCoreComplexTransactionBusinessLogic] ORDER_EXPRESS Success", null);

            transaction.addSubTransactionList(response.getTransaction().getSub_Transaction());

            responseMap = response.getTransactionData();

            if (request.getTransactionData().containsKey(ParameterName.PHONE)) {
                String cell_area_code = (String) request.getTransactionData().get(ParameterName.PHONE);
                request.getTransactionData().put(ParameterName.CELL_PHONE_AREA, cell_area_code.substring(0, 3));

                String cell_phone = (String) request.getTransactionData().get(ParameterName.PHONE);
                request.getTransactionData().put(ParameterName.CELL_PHONE, cell_phone.substring(3));

                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] CELL AREA CODE: " + cell_phone.substring(0, 3) + " CELL NUMBER " + cell_phone.substring(3), null);
            }

            //----------  TECNICARD VALIDATON ------------------
            String hostName = (String) request.getTransactionData().get(ParameterName.HOSTNAME);

            request.setTransactionType(TransactionType.GENERIC_HOST_VALIDATION);
            request.getTransactionData().put(TransactionType.TRANSACTION_TYPE, originalTransaction);
            response = sendMessageToHost(request, hostName, GENERIC_VALIDATION_WAIT_TIME, true);

            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] Recived message from " + hostName + " Validation", null);

            transaction.addSubTransactionList(response.getTransaction().getSub_Transaction());

            if (!response.wasApproved()) {
                sendResponseToIStreamFront(false, checkId);
                CoreTransactionUtil.subTransactionFailed(transaction, response, jmsManager.getCoreOutQueue(), correlationId);
                return;
            }

            String estimatedPostingTime = response.getResultMessage();

            sendResponseToIStreamFront(true, checkId);
            DirexTransactionRequest certegyRequest;
            try {
                certegyRequest = receiveMessageFromFront(TransactionType.CERTEGY_INFO);
            } catch (Exception e) {
                CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[NewCoreComplexTransactionBusinessLogic] Error Receiving certegy ", null);

                e.printStackTrace();
//                generateNotificationEmail("There was a problem receiving certegy", null);
                return;
            }

            Map certegyInfoRequestMap = certegyRequest.getTransactionData();

            //send response to certegy
            DirexTransactionResponse certegyResponse;

            if (certegyInfoRequestMap.get(TransactionType.TRANSACTION_TYPE) == TransactionType.CERTEGY_INFO) {
                certegyResponse = DirexTransactionResponse.forSuccess();
            } else {
                certegyResponse = DirexTransactionResponse.forException(ResultCode.ISTREAM_FRONT_CERTEGY_INFO_NOT_RECEIVED, ResultMessage.ISTREAM_FRONT_CERTEGY_INFO_NOT_RECEIVED);
            }
            jmsManager.send(certegyResponse, jmsManager.getFrontIStreamInQueue(), checkId);

            request.getTransactionData().putAll(certegyInfoRequestMap);

            boolean certegySuccess = false;
            String certegyCode = "EMPTY";
            //----
            //Verify if Certegy success here  ---------
            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[NewCoreComplexTransactionBusinessLogic] Checking Certegy for success", null);
            if (certegyInfoRequestMap.containsKey(ParameterName.CERTEGY_CODE)) {
                certegyCode = (String) certegyInfoRequestMap.get(ParameterName.CERTEGY_CODE);
                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] Certegy code: [" + certegyCode + "]", null);
                if (certegyCode.trim().equalsIgnoreCase("00")) {
                    certegySuccess = true;
                }
            }
            //-----------------------------------------

            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] Certegy value: " + certegySuccess, null);
            state = 3;
            if (certegySuccess) {
                sendAnswerToTerminal(originalTransaction, ResultCode.SUCCESS, estimatedPostingTime, hostName);

                //------ CREATE CERTEGY INFO SUBTRANSACRTION ------
                SubTransaction certegyInfoSubTransaction = new SubTransaction();
                certegyInfoSubTransaction.setType(TransactionType.CERTEGY_INFO.getCode());
                certegyInfoSubTransaction.setResultCode(ResultCode.SUCCESS.getCode());
                certegyInfoSubTransaction.setErrorCode(certegyCode);
                certegyInfoSubTransaction.setResultMessage(ResultMessage.SUCCESS.getMessage());
                transaction.addSubTransaction(certegyInfoSubTransaction);
                //--------------------------------------------------

                if (certegyInfoRequestMap.containsKey(ParameterName.DEPOSIT_ID)) {
                    String depositId = (String) certegyInfoRequestMap.get(ParameterName.DEPOSIT_ID);
                    transaction.setIstream_id(depositId);
                }
            } else {
                response.setResultCode(ResultCode.CERTEGY_DENY);
                response.setResultMessage(ResultMessage.CERTEGY_DENY.getMessage());
                response.setTransactionType(TransactionType.CERTEGY_INFO);
                response.setErrorCode(certegyCode);
                CoreTransactionUtil.subTransactionFailed(transaction, response, jmsManager.getCoreOutQueue(), correlationId);
                return;
            }

            //-------------- WAIT CONFIRMATION FROM TERMINAL -------------
            DirexTransactionRequest confirmationRequest;
            try {
                confirmationRequest = receiveMessageFromFront(TransactionType.TECNICARD_CONFIRMATION);
            } catch (Exception e) {
                String action = "decline";
                sendIstreamCheckAuthSubmit(request, action);

                return;
            }

            extractTecnicardConfirmationInformation(confirmationRequest);

            //------ CREATE TECNICARD_CONFIRMATION SUBTRANSACTION ------
            SubTransaction tecnicardConfirmationSubTransaction = new SubTransaction();
            tecnicardConfirmationSubTransaction.setType(TransactionType.TECNICARD_CONFIRMATION.getCode());
            tecnicardConfirmationSubTransaction.setResultCode(ResultCode.SUCCESS.getCode());
            tecnicardConfirmationSubTransaction.setResultMessage(ResultMessage.SUCCESS.getMessage());
            transaction.addSubTransaction(tecnicardConfirmationSubTransaction);
            //--------------------------------------------------

            //-----  SEND TO GENERIC HOST -----------    GENERIC_HOST_CARD_LOAD
            request.setTransactionType(TransactionType.GENERIC_HOST_CARD_LOAD);

            try {

                if (hostName.equals(NomHost.FUZE.toString())) {
                    request.getTransactionData().put(ParameterName.BILLER_ID, response.getTransactionData().get(ParameterName.BILLER_ID));
                    request.getTransactionData().put(ParameterName.TRANSACTION_ID, transaction.getId());
                }

                response = sendMessageToHost(request, hostName, GENERIC_CARD_LOAD_WAIT_TIME, false);
                if (!response.wasApproved()) {

                    response.setErrorCode("FAILED LOADING CARD");

                    if (hostName.equals(NomHost.TECNICARD.toString())) {
                        response.setResultCode(ResultCode.FAILED);
                        response.setResultMessage(ResultMessage.TECNICARD_FAILED.getMessage());

                        transaction.addSubTransactionList(response.getTransaction().getSub_Transaction());

                        CoreTransactionUtil.subTransactionFailed(transaction, response, jmsManager.getCoreOutQueue(), correlationId);
                        sendAnswerToTerminal(TransactionType.TECNICARD_CONFIRMATION, response.getResultCode(), response.getTerminalResultMessage(), hostName);

                    }

//                    generateNotificationEmail("There was a problem trying to load a card", null);
                    String action = "decline";
                    sendIstreamCheckAuthSubmit(request, action);

                    return;
                } else {
                    if (response.getTransactionData() != null
                            && response.getTransactionData().containsKey(ParameterName.BALANCE)) {
                        transaction.setBalanceAfterLoad((String) response.getTransactionData().get(ParameterName.BALANCE));
                    }

                    String action = "submit";
                    sendIstreamCheckAuthSubmit(request, action);
                    sendAnswerToTerminal(TransactionType.TECNICARD_CONFIRMATION, ResultCode.SUCCESS, estimatedPostingTime, hostName);

                }
            } catch (Exception be) {

//                generateNotificationEmail("There was a problem trying to load a card", null);
                String action = "decline";
                sendIstreamCheckAuthSubmit(request, action);

                throw be;
            }

            transaction.addSubTransactionList(response.getTransaction().getSub_Transaction());

            transaction.setResultCode(ResultCode.SUCCESS.getCode());
            transaction.setResultMessage(ResultMessage.SUCCESS.getMessage());

            CoreTransactionUtil.persistTransaction(transaction);

            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[NewCoreComplexTransactionBusinessLogic] Transaction finished successfully", null);

        } catch (Exception e) {
            e.printStackTrace();
            response = DirexTransactionResponse.forException(ResultCode.CORE_ERROR, e);

            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] Exception in state :: " + state, null);
            // SI OCURRE UNA EXCEPCION, SE LE NOTIFICA A LOS FRONTS QUE ESTEN ESPERANDO MSG.

            switch (state) {
                case 1:
                    CoreTransactionUtil.subTransactionFailed(transaction, response, jmsManager.getCoreOutQueue(), correlationId);
                    break;
                case 2:
                    sendResponseToIStreamFront(false, checkId);
                default:
                    transaction.setResultCode(ResultCode.CORE_ERROR.getCode());
                    String msg = e.getMessage();
                    if (msg != null) {
                        transaction.setResultMessage((msg.length() > 254) ? msg.substring(0, 254) : msg);
                    } else {
                        transaction.setResultMessage("Message Error was printed, Please check the server logs");
                    }
                    CoreTransactionUtil.persistTransaction(transaction);
            }
            sendAnswerToTerminal(TransactionType.TECNICARD_CONFIRMATION, ResultCode.CORE_ERROR, " Check Transaction Error, check server logs for mor inf.", null);

        }
    }

    private void sendAnswerToTerminal(TransactionType transactionType, ResultCode resultCode, String estimated_posting_time, String host) throws JMSException {
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[NewCoreComplexTransactionBusinessLogic] Send answer to TERMINAL", null);

        DirexTransactionResponse provissionalResponse = new DirexTransactionResponse();
        provissionalResponse.setResultCode(resultCode);
        provissionalResponse.setResultMessage(estimated_posting_time);

        Queue queue;

        if (transactionType == TransactionType.TECNICARD_CONFIRMATION) {
            if (host.equals("FUZE")) {
                provissionalResponse.getTransactionData().put(ParameterName.PRINTLOGO, "02");
            } else {
                provissionalResponse.getTransactionData().put(ParameterName.PRINTLOGO, "01");
            }
            queue = jmsManager.getCore2OutQueue();
        } else {
            queue = jmsManager.getCoreOutQueue();
        }

        provissionalResponse.getTransactionData().put("host", host);
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[NewCoreComplexTransactionBusinessLogic] Send message to TERMINAL", null);
        JMSManager.get().send(provissionalResponse, queue, correlationId);
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[NewCoreComplexTransactionBusinessLogic] Send message to TERMINAL Done.", null);
    }

    private DirexTransactionResponse sendMessageToHost(DirexTransactionRequest request, String hostName, long waitTime, boolean notifyToIstream) throws JMSException, Exception {
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[NewCoreComplexTransactionBusinessLogic] Send message to host " + hostName, null);

        Properties props = new Properties();
        props.setProperty("hostName", hostName);

        jmsManager.sendWithProps(request, jmsManager.getHostInQueue(), correlationId, props);

        if (request.getTransactionType() != TransactionType.ISTREAM_CHECK_AUTH_SUBMIT) {
            return receiveMessageFromHost(request.getTransactionType(), hostName, waitTime, notifyToIstream);
        } else {
            return null;
        }
    }

    private DirexTransactionResponse receiveMessageFromHost(TransactionType transactionType, String hostName, long waitTime, boolean notifyToIstream) throws Exception {
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[NewCoreComplexTransactionBusinessLogic] Receiving message from host " + hostName, null);

        Message message = null;
        DirexTransactionResponse response;
        try {
            message = jmsManager.receive(jmsManager.getHostOutQueue(), correlationId, waitTime);
        } catch (Exception e) {
            if (notifyToIstream) {
                sendResponseToIStreamFront(false, checkId);
            }
            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[NewCoreComplexTransactionBusinessLogic] receiving message on receiveMessageFromHost() throws an exeption.", null);
            response = DirexTransactionResponse.forException(transactionType, ResultCode.RESPONSE_TIME_EXCEEDED, ResultMessage.RESPONSE_TIME_EXCEEDED, " " + hostName);
            response.setTransactionType(transactionType);
            CoreTransactionUtil.subTransactionFailed(transaction, response, jmsManager.getCoreOutQueue(), correlationId);
            throw new Exception();
        }

        if (message == null || !(message instanceof ObjectMessage)) {
            if (notifyToIstream) {
                sendResponseToIStreamFront(false, checkId);
            }
            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[NewCoreComplexTransactionBusinessLogic] receiveMessageFromHost() has a message null or is not an Obj message instance.", null);
            response = DirexTransactionResponse.forException(transactionType, ResultCode.CORE_RECEIVED_NULL_FROM_HOST, ResultMessage.CORE_RECEIVED_NULL_FROM_HOST, " " + hostName);
            response.setTransactionType(transactionType);
            CoreTransactionUtil.subTransactionFailed(transaction, response, jmsManager.getCoreOutQueue(), correlationId);
            throw new Exception();
        }

        ObjectMessage objectMessage = (ObjectMessage) message;
        Serializable s = objectMessage.getObject();
        response = (DirexTransactionResponse) s;

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[NewCoreComplexTransactionBusinessLogic] Message received", null);

        return response;
    }

    private DirexTransactionRequest receiveMessageFromFront(TransactionType transactionType) throws Exception {
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[NewCoreComplexTransactionBusinessLogic] Waiting " + transactionType + " from FRONT", null);

        DirexTransactionResponse response;
        Message message = null;

        // in error case
        ResultCode errorCode = null;
        ResultMessage errorMessage = null;
        String correlation = null;
        Long waitTime = null;
        Queue queue = null;

        switch (transactionType) {
            case PERSONAL_INFO:
                errorCode = ResultCode.ISTREAM_FRONT_PERSONAL_INFO_NOT_RECEIVED;
                errorMessage = ResultMessage.ISTREAM_FRONT_PERSONAL_INFO_RECEIVED_AS_NULL;
                correlation = checkId;
                waitTime = PERSONAL_INFO_WAIT_TIME;
                queue = jmsManager.getFrontIStreamOutQueue();
                break;
            case CERTEGY_INFO:
                errorCode = ResultCode.ISTREAM_FRONT_CERTEGY_INFO_NOT_RECEIVED;
                errorMessage = ResultMessage.ISTREAM_FRONT_CERTEGY_INFO_NOT_RECEIVED;
                correlation = checkId;
                waitTime = CERTEGY_INFO_WAIT_TIME;
                queue = jmsManager.getFrontIStreamOutQueue();
                break;
            case TECNICARD_CONFIRMATION:
                errorCode = ResultCode.TERMINAL_CONFIRMATION_TIME_EXCEED;
                errorMessage = ResultMessage.TERMINAL_CONFIRMATION_TIME_EXCEED;
                correlation = correlationId;
                waitTime = TECNICARD_CONFIRMATION_WAIT_TIME;
                queue = jmsManager.getCore2InQueue();
                CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[NewCoreComplexTransactionBusinessLogic] receiveMessageFromFront(...) queue = jmsManager.getCore2InQueue(); correlationId = " + correlationId, null);
                break;
        }

        try {
            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[NewCoreComplexTransactionBusinessLogic] receiveMessageFromFront(...) jmsManager.receiving(...) correlation = " + correlation, null);
            message = jmsManager.receive(queue, correlation, waitTime);
            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[NewCoreComplexTransactionBusinessLogic] Recived " + transactionType, null);
        } catch (IOException | JMSException e) {
            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[NewCoreComplexTransactionBusinessLogic] ERROR receiveMessageFromFront ", null);
            response = DirexTransactionResponse.forException(errorCode, errorMessage, transactionType + " not received.", "");
            response.setTransactionType(transactionType);
            CoreTransactionUtil.subTransactionFailed(transaction, response, jmsManager.getCoreOutQueue(), correlationId);
            e.printStackTrace();
            throw new Exception();
        }

        if (message == null || !(message instanceof ObjectMessage)) {
            CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[NewCoreComplexTransactionBusinessLogic] Message from " + transactionType + " is null or is not an ObjMessage instance.", null);
            response = DirexTransactionResponse.forException(errorCode, errorMessage, transactionType + " not received.", "");
            response.setTransactionType(transactionType);
            CoreTransactionUtil.subTransactionFailed(transaction, response, jmsManager.getCoreOutQueue(), correlationId);
            throw new Exception();
        }

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[NewCoreComplexTransactionBusinessLogic] Recived " + transactionType + " successfully.", null);

        ObjectMessage objectMessage = (ObjectMessage) message;
        Serializable s = objectMessage.getObject();
        DirexTransactionRequest request = (DirexTransactionRequest) s;
        DirexTransactionResponse iStreamResponse = new DirexTransactionResponse();
        if (request.getTransactionType() != transactionType) {
            iStreamResponse.getTransactionData().put(ParameterName.CHECK_ID, checkId);

            iStreamResponse.setResultCode(ResultCode.CORE_ERROR);
            iStreamResponse.setResultMessage(ResultMessage.FAILED.getMessage() + " TransactionType expected " + transactionType + " and received " + request.getTransactionType());
            iStreamResponse.setApproved(false);

            jmsManager.send(iStreamResponse, jmsManager.getFrontIStreamInQueue(), checkId);
            CoreTransactionUtil.subTransactionFailed(transaction, iStreamResponse, jmsManager.getCoreOutQueue(), correlationId);

        }

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[NewCoreComplexTransactionBusinessLogic] Message received", null);
        return request;
    }

    private void sendResponseToIStreamFront(boolean success, String checkId) throws JMSException {
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[NewCoreComplexTransactionBusinessLogic] Send response back to IStreamFront with result :: " + (success ? "SUCCESS" : "FAILED"), null);

        DirexTransactionResponse iStreamResponse = new DirexTransactionResponse();
        iStreamResponse.getTransactionData().put(ParameterName.CHECK_ID, checkId);

        iStreamResponse.setResultCode(success ? ResultCode.SUCCESS : ResultCode.FAILED);
        iStreamResponse.setResultMessage(success ? ResultMessage.SUCCESS.getMessage() : ResultMessage.FAILED.getMessage());
        iStreamResponse.setApproved(success);

        jmsManager.send(iStreamResponse, jmsManager.getFrontIStreamInQueue(), checkId);
    }

    public void postprocess(DirexTransactionRequest direxTransactionRequest, DirexTransactionResponse direxTransactionResponse) throws Exception {

    }

    private String getFromMap(Map map, ParameterName parameterName) {
        if (map.containsKey(parameterName)) {
            return (String) map.get(parameterName);
        } else {
            return null;
        }
    }

    private void fillOutClient(Map transactionMap) throws SQLException, Exception {
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] fillOutClient(...) ::", null);
        try {
            if (transactionMap.containsKey(ParameterName.FIRST_NAME)) {
                transaction.getClient().setFirstName((String) transactionMap.get(ParameterName.FIRST_NAME));
            }
            if (transactionMap.containsKey(ParameterName.LAST_NAME)) {
                transaction.getClient().setLastName((String) transactionMap.get(ParameterName.LAST_NAME));
            }
            if (transactionMap.containsKey(ParameterName.MIDDLE_NAME)) {
                if (transaction.getClient().getFirstName() == null) {
                    transaction.getClient().setFirstName((String) transactionMap.get(ParameterName.MIDDLE_NAME));
                } else {
                    transaction.getClient().setFirstName(transaction.getClient().getFirstName() + " " + ((String) transactionMap.get(ParameterName.MIDDLE_NAME)));
                }
            }
            if (transactionMap.containsKey(ParameterName.MAIDEN_NAME)) { //in case the last name comes in the maiden name
                if (transaction.getClient().getLastName() == null || transaction.getClient().getLastName().isEmpty()) {
                    transaction.getClient().setLastName((String) transactionMap.get(ParameterName.MAIDEN_NAME));
                }
            }
            if (transactionMap.containsKey(ParameterName.TELEPHONE)) {
                transaction.getClient().setTelephone((String) transactionMap.get(ParameterName.TELEPHONE));
            }
            if (transactionMap.containsKey(ParameterName.EMAIL)) {
                transaction.getClient().setEmail((String) transactionMap.get(ParameterName.EMAIL));
            }
            if (transactionMap.containsKey(ParameterName.BORNDATE_AS_DATE)) {
                transaction.getClient().setBornDate((Date) transactionMap.get(ParameterName.BORNDATE_AS_DATE));
            }

            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] fillOutClient(...) transaction.getClient().getBornDate() = " + transaction.getClient().getBornDate(), null);

            if (transactionMap.containsKey(ParameterName.ADDRESS_CORRECT) && transactionMap.get(ParameterName.ADDRESS_CORRECT) != null) {
                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] fillOutClient(...) ADDRESS_CORRECT != null: true.", null);
                if ((((String) transactionMap.get(ParameterName.ADDRESS_CORRECT)).contains("n")) || ((String) transactionMap.get(ParameterName.ADDRESS_CORRECT)).contains("N")) {

                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] fillOutClient(...) ADDRESS_CORRECT = [" + transactionMap.get(ParameterName.ADDRESS_CORRECT) + "]", null);
                    if (transactionMap.containsKey(ParameterName.ADDRESS_FORM) && transactionMap.get(ParameterName.ADDRESS_FORM) != null) {
                        byte[] addressForm = (byte[]) transactionMap.get(ParameterName.ADDRESS_FORM);
                        if (addressForm != null) {
                            if (addressForm.length > 0) {
                                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] fillOutClient(...) addressForm.length > 0", null);
                                java.sql.Blob addressFormBlob = new SerialBlob(addressForm);
                                transaction.getClient().setAddressForm(addressFormBlob);
                            } else {
                                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] fillOutClient(...) addressForm.length = 0", null);
                            }

                        } else {
                            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] fillOutClient(...) addressForm is null", null);
                        }
                    }
                }
            }
        } catch (Exception e) {
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] Error 3 ", e.getMessage());
            e.printStackTrace();
            throw new Exception();
        }
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] fillOutClient(...) DONE", null);

    }

    private void fillOutClientAddress(Map transactionMap) {

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] fillOutClientAddress(...) ", null);
        if (transaction.getClient().getAddress() == null) {
            transaction.getClient().setAddress(new Address());
            transaction.getClient().getAddress().setClient(transaction.getClient());
        }

        if (transactionMap.containsKey(ParameterName.ADDRESS)) {
            transaction.getClient().getAddress().setAddress((String) transactionMap.get(ParameterName.ADDRESS));
        }
        if (transactionMap.containsKey(ParameterName.CITY)) {
            transaction.getClient().getAddress().setCity((String) transactionMap.get(ParameterName.CITY));
        }
        if (transactionMap.containsKey(ParameterName.ZIPCODE)) {
            transaction.getClient().getAddress().setZipcode((String) transactionMap.get(ParameterName.ZIPCODE));
        }
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] fillOutClientAddress(...) DONE", null);
    }

    private PersonalIdentification fillOutPersonalIdentification(Map transactionMap) throws SQLException {

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] fillOutPersonalIdentification(...) ", null);

        PersonalIdentification identidication = new PersonalIdentification();
        identidication.setClient(transaction.getClient());

        if (transactionMap.containsKey(ParameterName.ID)) {
            identidication.setIdentification((String) transactionMap.get(ParameterName.ID));
        }
        if (transactionMap.containsKey(ParameterName.IDTYPE)) {
            identidication.setIdType(((IdType) transactionMap.get(ParameterName.IDTYPE)).getId());
        }

        if (transactionMap.containsKey(ParameterName.EXPIRATION_DATE_AS_DATE)) {
            identidication.setExpirationDate(((Date) transactionMap.get(ParameterName.EXPIRATION_DATE_AS_DATE)));
        }
        if (transactionMap.containsKey(ParameterName.IDFRONT) && transactionMap.get(ParameterName.IDFRONT) != null) {
            byte[] idFront = (byte[]) transactionMap.get(ParameterName.IDFRONT);
            java.sql.Blob idFrontBlob = new SerialBlob(idFront);
            identidication.setIdFront(idFrontBlob);
        }
        if (transactionMap.containsKey(ParameterName.IDBACK) && transactionMap.get(ParameterName.IDBACK) != null) {
            byte[] idBack = (byte[]) transactionMap.get(ParameterName.IDBACK);
            java.sql.Blob idBackBlob = new SerialBlob(idBack);
            identidication.setIdBack(idBackBlob);
        }
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] fillOutPersonalIdentification(...) DONE", null);
        return identidication;
    }

    private void fillOutCheck(Map transactionMap) throws SQLException {
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] fillOutCheck(...) ", null);
        Check check = new Check();

        if (transactionMap.containsKey(ParameterName.MICR)) {
            check.setMicr((String) transactionMap.get(ParameterName.MICR));
        }
        if (transactionMap.containsKey(ParameterName.CRC)) {
            check.setCrc((String) transactionMap.get(ParameterName.CRC));
        }
        if (transactionMap.containsKey(ParameterName.MAKER_NAME)) {
            check.setMakerName((String) transactionMap.get(ParameterName.MAKER_NAME));
        }
        if (transactionMap.containsKey(ParameterName.MAKER_ADDRESS)) {
            check.setMakerAddress((String) transactionMap.get(ParameterName.MAKER_ADDRESS));
        }
        if (transactionMap.containsKey(ParameterName.MAKER_CITY)) {
            check.setMakerCity((String) transactionMap.get(ParameterName.MAKER_CITY));
        }
        if (transactionMap.containsKey(ParameterName.MAKER_STATE)) {
            check.setMakerState((String) transactionMap.get(ParameterName.MAKER_STATE));
        }
        if (transactionMap.containsKey(ParameterName.MAKER_ZIP)) {
            check.setMakerZip((String) transactionMap.get(ParameterName.MAKER_ZIP));
        }
        if (transactionMap.containsKey(ParameterName.MAKER_PHONE)) {
            check.setMakerPhone((String) transactionMap.get(ParameterName.MAKER_PHONE));
        }
        if (transactionMap.containsKey(ParameterName.LOCATION_ID)) {
            check.setLocationId((String) transactionMap.get(ParameterName.LOCATION_ID));
        }
        if (transactionMap.containsKey(ParameterName.PAYMENTCHECK)) {
            check.setPaymentCheck((String) transactionMap.get(ParameterName.PAYMENTCHECK));
        }

        if (transactionMap.containsKey(ParameterName.CHECK_BACK) && transactionMap.get(ParameterName.CHECK_BACK) != null) {
            byte[] checkBack = (byte[]) transactionMap.get(ParameterName.CHECK_BACK);
            java.sql.Blob checkBackBlob = new SerialBlob(checkBack);
            check.setCheckBack(checkBackBlob);
        }

        if (transactionMap.containsKey(ParameterName.CHECK_FRONT) && transactionMap.get(ParameterName.CHECK_FRONT) != null) {
            byte[] checkFront = (byte[]) transactionMap.get(ParameterName.CHECK_FRONT);
            java.sql.Blob checkFrontBlob = new SerialBlob(checkFront);
            check.setCheckFront(checkFrontBlob);
        }

        check.setTransaction(transaction);
        check.setClient1(transaction.getClient());
        transaction.setCheck(check);
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] fillOutCheck(...) DONE", null);
    }

    private void extractTecnicardConfirmationInformation(DirexTransactionRequest request) throws Exception, SQLException {
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] extractTecnicardConfirmationInformation(...) ", null);
        Map transactionData = request.getTransactionData();
        try {
            if (transactionData.containsKey(ParameterName.TRUNCATED_CHECK) && transactionData.get(ParameterName.TRUNCATED_CHECK) != null) {
                byte[] truncatedCheck = (byte[]) transactionData.get(ParameterName.TRUNCATED_CHECK);
                if (truncatedCheck != null) {
                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] extractTecnicardConfirmationInformation(...) truncatedCheck != null", null);
                    java.sql.Blob truncatedCheckBlob = new SerialBlob(truncatedCheck);
                    transaction.setTruncatedCheck(truncatedCheckBlob);
                } else {
                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] extractTecnicardConfirmationInformation(...) truncatedCheck IS null", null);
                    throw new Exception("[NewCoreComplexTransactionBusinessLogic] extractTecnicardConfirmationInformation(...) truncatedCheck IS null");
                }
            } else {
                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] extractTecnicardConfirmationInformation(...) truncatedCheck IS null", null);
                throw new Exception("[NewCoreComplexTransactionBusinessLogic] extractTecnicardConfirmationInformation(.) truncatedCheck IS null");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        }
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] extractTecnicardConfirmationInformation(...) DONE", null);
    }

    public void sendIstreamCheckAuthSubmit(DirexTransactionRequest request, String action) throws Exception {

        DirexTransactionRequest istreamCancelRequest = new DirexTransactionRequest();

        Map istreamCancelMap = request.getTransactionData();

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] sendIstreamCheckAuthSubmit pass " + istreamCancelMap.get(ParameterName.TERMINAL_PASSWORD_ISTREAM), null);
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] sendIstreamCheckAuthSubmit user " + istreamCancelMap.get(ParameterName.TERMINAL_USER_ISTREAM), null);
        istreamCancelRequest.setTransactionType(TransactionType.ISTREAM_CHECK_AUTH_SUBMIT);
        istreamCancelRequest.getTransactionData().put(ParameterName.ACTION, action);
        istreamCancelRequest.getTransactionData().put(ParameterName.REQUEST_ID, checkId);
        istreamCancelRequest.getTransactionData().put(ParameterName.PASSWORD, istreamCancelMap.get(ParameterName.TERMINAL_PASSWORD_ISTREAM));
        istreamCancelRequest.getTransactionData().put(ParameterName.USER, istreamCancelMap.get(ParameterName.TERMINAL_USER_ISTREAM));

        //------ CREATE ISTREAM_CHECKAUTHSUBMIT SUBTRANSACTION ------
        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[NewCoreComplexTransactionBusinessLogic] Saving IstreamCheckAutSubmitSubTransaction", null);
        SubTransaction istreamCheckAutSubmitSubTransaction = new SubTransaction();
        istreamCheckAutSubmitSubTransaction.setType(TransactionType.ISTREAM_CASH_AUTH_SUBMIT.getCode());
        istreamCheckAutSubmitSubTransaction.setResultCode(ResultCode.SUCCESS.getCode());
        istreamCheckAutSubmitSubTransaction.setResultMessage(ResultMessage.SUCCESS.getMessage());
        transaction.addSubTransaction(istreamCheckAutSubmitSubTransaction);

        sendMessageToHost(istreamCancelRequest, NomHost.ISTREAM.toString(), ISTREAM_HOST_WAIT_TIME, false);

    }

    public void fixPersonalInfoName(DirexTransactionRequest requestData) throws Exception {

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] fixPersonalInfoName(...)", null);
        try {

            String name = (String) requestData.getTransactionData().get(ParameterName.FIRST_NAME);
            String middleName = (String) requestData.getTransactionData().get(ParameterName.MIDDLE_NAME);
            String lastName = (String) requestData.getTransactionData().get(ParameterName.LAST_NAME);
            String[] aux;

            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] fixPersonalInfoName to fix name: " + name, null);
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] fixPersonalInfoName to fix Middle name: " + middleName, null);
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] fixPersonalInfoName to fix last name: " + lastName, null);

            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] fixPersonalInfoName with name: " + name, null);
            if (name != null && name.contains(" ")) {
                aux = name.split(" ");
                name = aux[0];
                middleName = aux[1];
            }

            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] fixPersonalInfoName fixed with name: " + name, null);
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] fixPersonalInfoName fixed with Middle name: " + middleName, null);
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] fixPersonalInfoName fixed with last name: " + lastName, null);

            if (name != null) {
                requestData.getTransactionData().put(ParameterName.FIRST_NAME, name);
            }
            if (middleName != null) {
                requestData.getTransactionData().put(ParameterName.MIDDLE_NAME, middleName);
            }
            if (lastName != null) {
                requestData.getTransactionData().put(ParameterName.LAST_NAME, lastName);
            }
        } catch (Exception e) {
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] fixPersonalInfoName() Error fixing full name.", null);
            e.printStackTrace();
            throw new Exception();
        }
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] fixPersonalInfoName fixing DONE ", null);
    }

    /**
     * ***********************ID LICENSE READER*****************************
     */
    public DirexTransactionResponse getPersonalInfoFromIDReader(DirexTransactionRequest request) {

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[NewCoreComplexTransactionBusinessLogic] Into the method getPersonalInfoFromIDReader(...)", null);

        DirexTransactionResponse dtr = new DirexTransactionResponse();
        dtr.getTransactionData().putAll(request.getTransactionData());
        try {
            if (request.getTransactionData().containsKey(ParameterName.DLDATASCAN) || request.getTransactionData().containsKey(ParameterName.DLDATASWIPE)) {
                CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[NewCoreComplexTransactionBusinessLogic] getPersonalInfoFromIDReader() contains datascan or dataswipe", null);

                Map personalInfoMap = null;
                String dlData = "";

                if (request.getTransactionData().get(ParameterName.DLDATASCAN) != null && !request.getTransactionData().get(ParameterName.DLDATASCAN).equals("")) {

                    dlData = (String) request.getTransactionData().get(ParameterName.DLDATASCAN);
                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] getPersonalInfoFromIDReader(...) with xmlStringfrom DLDATASCAN: [" + dlData + "]", null);

                } else {

                    dlData = (String) request.getTransactionData().get(ParameterName.DLDATASWIPE);
                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] getPersonalInfoFromIDReader(...) with xmlString from DLDATASWIPE: [" + dlData + "]", null);

                }

                if (dlData != null && !dlData.isEmpty()) {
                    try {
                        personalInfoMap = IDScanner.parseID(CoreTransactionManager.ID_SCAN_AUTH_KEY, dlData);

                        System.out.println("Printing IDScanner output:");
                        System.out.println(Arrays.toString(personalInfoMap.entrySet().toArray()));

                    } catch (Exception e) {
                        e.printStackTrace();
                        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] Null personInfo from DLicense WS.", null);
                        return DirexTransactionResponse.forException(ResultCode.CORE_ERROR, ResultMessage.FAILED, " Null personInfo from DLicense WS ", "");
                    }
                }

                if (personalInfoMap != null) {

                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] ----------------- Printing PersonInfo map  -----------------  ", null);
                    String ssn = (String) request.getTransactionData().get(ParameterName.SSN);
                    dtr.getTransactionData().put(ParameterName.IDTYPE, CoreTransactionUtil.getIdTypeFromId(ssn));
                    dtr.getTransactionData().put(ParameterName.ID, personalInfoMap.get(ParameterName.ID));
                    fixPersonInfoName(personalInfoMap);
                    dtr.getTransactionData().putAll(personalInfoMap);
                    dtr.setResultCode(ResultCode.SUCCESS);
                    dtr.setResultMessage(ResultMessage.SUCCESS.getMessage());
                    dtr.setTerminalResultMessage(ResultMessage.SUCCESS.getMessage());
                } else {
                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] Null personInfo from DLicense WS.", null);
                    dtr = DirexTransactionResponse.forException(ResultCode.CORE_ERROR, ResultMessage.FAILED, " Null personInfo from DLicense WS ", "");
                }

            } else {
                dtr = DirexTransactionResponse.forException(ResultCode.CORE_ERROR, ResultMessage.FAILED, " The request doesn't contain DLDATASCAN or DLDATASWIPE ", "");
            }
        } catch (Exception e) {
            dtr = null;
            e.printStackTrace();
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] Error", e.getMessage());
        }

        return dtr;
    }

    public static void fixPersonInfoName(Map<ParameterName, String> personalInfo) {

        String name = personalInfo.get(ParameterName.FIRST_NAME).trim();
        String middleName = "";
        if (personalInfo.containsKey(ParameterName.MIDDLE_NAME) && personalInfo.get(ParameterName.MIDDLE_NAME) != null) {
            middleName = personalInfo.get(ParameterName.MIDDLE_NAME).trim();
        }
        String lastName = personalInfo.get(ParameterName.LAST_NAME).trim();
        String[] aux;
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] fixPersonalInfoName with name: " + name, null);
        if (name != null && name.contains(" ")) {
            aux = name.split(" ");
            name = aux[0];
            middleName = aux[1];
        }

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] fixPersonalInfoName -> name: " + name, null);
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] fixPersonalInfoName -> Middle name: " + middleName, null);
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexTransactionBusinessLogic] fixPersonalInfoName -> last name: " + lastName, null);

        if (name != null) {
            personalInfo.put(ParameterName.FIRST_NAME, name);
        }
        if (name != null) {
            personalInfo.put(ParameterName.MIDDLE_NAME, middleName);
        }
        if (name != null) {
            personalInfo.put(ParameterName.LAST_NAME, lastName);
        }
    }

    private void feeCalculator(DirexTransactionRequest request, Transaction transaction) {

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[NewCoreComplexCashBL] feeCalculator() start ...", null);

        Map map = new HashMap();

        try {
            HibernateUtil.beginTransaction();

            FeeBucketsManager feeBucketsManager = new FeeBucketsManager();
            map = (Map) feeBucketsManager.getFees(request.getTransactionData().get(ParameterName.IDMERCHANT) + "",
                    request.getTransactionData().get(ParameterName.OPERATION) + "",
                    request.getTransactionData().get(ParameterName.AMMOUNT) + "");

            HibernateUtil.commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            HibernateUtil.rollbackTransaction();
        }

        Float feeAmount = (Float) map.get(ParameterName.CRDLDF);

        Double amount = (Double) request.getTransactionData().get(ParameterName.AMMOUNT);

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreComplexCashBL] FEE_AMOUNT applied: " + feeAmount, null);
        Double payOut = amount - feeAmount;//No se le resta ese fee a peticion de carlos aparicio dic/04/2014

        request.getTransactionData().put(ParameterName.PAYOUT_AMMOUNT, payOut);
        request.getTransactionData().put(ParameterName.FEE_AMMOUNT, feeAmount);

        transaction.setFeeAmmount(feeAmount.doubleValue());
        transaction.setPayoutAmmount(payOut);
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[CoreComplexCashBL] feeCalculator() done with PAYOUT_AMOUNT: " + payOut, null);
    }

}
