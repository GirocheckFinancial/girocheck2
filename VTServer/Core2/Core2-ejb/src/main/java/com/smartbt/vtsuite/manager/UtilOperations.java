/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.manager;

import com.smartbt.girocheck.servercommon.enums.CheckStatus;
import com.smartbt.girocheck.servercommon.enums.EnumCountry;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.enums.ResultCode;
import com.smartbt.girocheck.servercommon.enums.ResultMessage;
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.manager.FeeBucketsManager;
import com.smartbt.girocheck.servercommon.manager.PersonalIdentificationManager;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;
import com.smartbt.girocheck.servercommon.messageFormat.IdType;
import com.smartbt.girocheck.servercommon.model.Address;
import com.smartbt.girocheck.servercommon.model.Check;
import com.smartbt.girocheck.servercommon.model.PersonalIdentification;
import com.smartbt.girocheck.servercommon.model.State;
import com.smartbt.girocheck.servercommon.model.Transaction;
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.girocheck.servercommon.utils.IDScanner;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import static com.smartbt.vtsuite.manager.AbstractCommonBusinessLogic.stateManager;
import com.smartbt.vtsuite.util.CoreTransactionUtil;
import static com.smartbt.vtsuite.util.CoreTransactionUtil.addSuccessfulSubTransaction;
import com.smartbt.vtsuite.util.TransactionalException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.sql.rowset.serial.SerialBlob;

/**
 *
 * @author rrodriguez
 */
public class UtilOperations {
    
    public static void processPersonalInfo(Transaction transaction, DirexTransactionRequest request, Map personalInfoRequestMap) throws SQLException, Exception {
        //------ CREATE PERSONAL INFO SUBTRANSACTION ------
        addSuccessfulSubTransaction(transaction, TransactionType.PERSONAL_INFO);

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[AbstractCommonBusinessLogic] Persist personal Info", null);
        //PERSIST PERSONAL INFO

        fixPersonInfoName(request.getTransactionData());
        fillOutClient(request.getTransactionData(), transaction);
        fillOutClientAddress(request.getTransactionData(), transaction);
        PersonalIdentification identification = fillOutPersonalIdentification(request.getTransactionData(), transaction);

        if (transaction.getOperation().equals("01")) {
            fillOutCheck(request.getTransactionData(), transaction);
        }

        if (personalInfoRequestMap.containsKey(ParameterName.IDCOUNTRY)
                || personalInfoRequestMap.containsKey(ParameterName.COUNTRY)
                || personalInfoRequestMap.containsKey(ParameterName.STATE)
                || personalInfoRequestMap.containsKey(ParameterName.STATE_ABBREVIATION)) {
            try {
                HibernateUtil.beginTransaction();

                if (identification.getIdType() != null) {
                    System.out.println("Removing Personal Identification");
                    PersonalIdentificationManager.get().removeByClientAndType(transaction.getClient().getId(), identification.getIdType(), identification.getId());
                }

                request.getTransactionData().put(ParameterName.IDCOUNTRY, EnumCountry.EUA.getCode() + "");

                request.getTransactionData().put(ParameterName.COUNTRY, EnumCountry.EUA.getCode() + "");

                String stateAbbreviation = (String) personalInfoRequestMap.get(ParameterName.STATE_ABBREVIATION);
                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AbstractCommonBusinessLogic] processPersonalInfo -> StateAbbreviation = " + stateAbbreviation, null);

                if (stateAbbreviation != null && !stateAbbreviation.isEmpty()
                        && (!personalInfoRequestMap.containsKey(ParameterName.STATE) //Do this just if STATE is not in the map
                        || (transaction.getClient().getAddress() != null && transaction.getClient().getAddress().getState() == null))) { //Do it when state is null in Client's address
                    State state = stateManager.getByAbbreviation(stateAbbreviation);

                    transaction.getClient().getAddress().setState(state);
                    request.getTransactionData().put(ParameterName.STATE, state.getCode() + "");
                }

                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AbstractCommonBusinessLogic] STATE = " + request.getTransactionData().get(ParameterName.STATE), null);

                if (request.getTransactionData().containsKey(ParameterName.PHONE)) {
                    String cell_area_code = (String) request.getTransactionData().get(ParameterName.PHONE);
                    request.getTransactionData().put(ParameterName.CELL_PHONE_AREA, cell_area_code.substring(0, 3));

                    String cell_phone = (String) request.getTransactionData().get(ParameterName.PHONE);
                    request.getTransactionData().put(ParameterName.CELL_PHONE, cell_phone.substring(3));
                }

                HibernateUtil.commitTransaction();
            } catch (Exception e) {
                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AbstractCommonBusinessLogic] Error 2 ", e.getMessage());
                HibernateUtil.rollbackTransaction();

                throw new TransactionalException(ResultCode.CORE_ERROR, TransactionType.PERSONAL_INFO, e);
            }
        }

        identification.setClient(transaction.getClient());
        Set set = new HashSet();
        set.add(identification);
        transaction.getClient().setData_SD(set);
    }

    
    private static  void fillOutClient(Map transactionMap, Transaction transaction) throws SQLException, Exception {

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
//        if (transactionMap.containsKey(ParameterName.EMAIL)) {
//            transaction.getClient().setEmail((String) transactionMap.get(ParameterName.EMAIL));
//        }
        if (transactionMap.containsKey(ParameterName.BORNDATE_AS_DATE)) {
            transaction.getClient().setBornDate((Date) transactionMap.get(ParameterName.BORNDATE_AS_DATE));
        }

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AbstractCommonBusinessLogic] fillOutClient(...) transaction.getClient().getBornDate() = " + transaction.getClient().getBornDate(), null);

        if (transactionMap.containsKey(ParameterName.ADDRESS_CORRECT) && transactionMap.get(ParameterName.ADDRESS_CORRECT) != null) {
            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AbstractCommonBusinessLogic] fillOutClient(...) ADDRESS_CORRECT != null: true.", null);
            if ((((String) transactionMap.get(ParameterName.ADDRESS_CORRECT)).contains("n")) || ((String) transactionMap.get(ParameterName.ADDRESS_CORRECT)).contains("N")) {

                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AbstractCommonBusinessLogic] fillOutClient(...) ADDRESS_CORRECT = [" + transactionMap.get(ParameterName.ADDRESS_CORRECT) + "]", null);
                if (transactionMap.containsKey(ParameterName.ADDRESS_FORM) && transactionMap.get(ParameterName.ADDRESS_FORM) != null) {
                    byte[] addressForm = (byte[]) transactionMap.get(ParameterName.ADDRESS_FORM);
                    if (addressForm != null) {
                        if (addressForm.length > 0) {
                            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AbstractCommonBusinessLogic] fillOutClient(...) addressForm.length > 0", null);
                            java.sql.Blob addressFormBlob = new SerialBlob(addressForm);
                            transaction.getClient().setAddressForm(addressFormBlob);
                        } else {
                            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AbstractCommonBusinessLogic] fillOutClient(...) addressForm.length = 0", null);
                        }

                    } else {
                        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AbstractCommonBusinessLogic] fillOutClient(...) addressForm is null", null);
                    }
                }
            }
        }
    }

    private static  void fillOutClientAddress(Map transactionMap, Transaction transaction) {

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AbstractCommonBusinessLogic] fillOutClientAddress(...) ", null);
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
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AbstractCommonBusinessLogic] fillOutClientAddress(...) DONE", null);
    }

    //TODO Make sure this is not creating a new Personl Identification
    private static  PersonalIdentification fillOutPersonalIdentification(Map transactionMap, Transaction transaction) throws SQLException {

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AbstractCommonBusinessLogic] fillOutPersonalIdentification(...) ", null);

        PersonalIdentification identification = new PersonalIdentification();
        identification.setClient(transaction.getClient());

        if (transactionMap.containsKey(ParameterName.ID)) {
            identification.setIdentification((String) transactionMap.get(ParameterName.ID));
        }
        if (transactionMap.containsKey(ParameterName.IDTYPE)) {
            identification.setIdType(((IdType) transactionMap.get(ParameterName.IDTYPE)).getId());
        }

        if (transactionMap.containsKey(ParameterName.EXPIRATION_DATE_AS_DATE)) {
            identification.setExpirationDate(((Date) transactionMap.get(ParameterName.EXPIRATION_DATE_AS_DATE)));
        }
        if (transactionMap.containsKey(ParameterName.IDFRONT) && transactionMap.get(ParameterName.IDFRONT) != null) {
            byte[] idFront = (byte[]) transactionMap.get(ParameterName.IDFRONT);
            java.sql.Blob idFrontBlob = new SerialBlob(idFront);
            identification.setIdFront(idFrontBlob);
        }
        if (transactionMap.containsKey(ParameterName.IDBACK) && transactionMap.get(ParameterName.IDBACK) != null) {
            byte[] idBack = (byte[]) transactionMap.get(ParameterName.IDBACK);
            java.sql.Blob idBackBlob = new SerialBlob(idBack);
            identification.setIdBack(idBackBlob);
        }
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AbstractCommonBusinessLogic] fillOutPersonalIdentification(...) DONE", null);
        return identification;
    }

    public static  void fillOutCheck(Map transactionMap, Transaction transaction) throws SQLException {
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AbstractCommonBusinessLogic] fillOutCheck(...) ", null);
        Check check = new Check();

        if (transactionMap.containsKey(ParameterName.MICR)) {
            check.setMicr((String) transactionMap.get(ParameterName.MICR));
        }
        if (transactionMap.containsKey(ParameterName.CRC)) {
            check.setCrc((String) transactionMap.get(ParameterName.CRC));
        }
        if (transactionMap.containsKey(ParameterName.MAKER_NAME)) {
            String makerName = filterAlphanumericAndDashes((String) transactionMap.get(ParameterName.MAKER_NAME));
            check.setMakerName(makerName);
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
        if (transactionMap.containsKey(ParameterName.TERMINAL_ID_ISTREAM)) {
            check.setLocationId((String) transactionMap.get(ParameterName.TERMINAL_ID_ISTREAM));
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

        check.setStatus(CheckStatus.PROCESSING.getStatus());
        check.setCreationDate(new Date());

        check.setTransaction(transaction);
        check.setClient1(transaction.getClient());
        transaction.setCheck(check);
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AbstractCommonBusinessLogic] fillOutCheck(...) DONE", null);
    }

    public static DirexTransactionResponse getPersonalInfoFromIDReader(DirexTransactionRequest request) throws TransactionalException {

        CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[AbstractCommonBusinessLogic] Into the method getPersonalInfoFromIDReader(...)", null);

        DirexTransactionResponse dtr = new DirexTransactionResponse();
        dtr.getTransactionData().putAll(request.getTransactionData());
        try {
            if (request.getTransactionData().containsKey(ParameterName.DLDATASCAN) || request.getTransactionData().containsKey(ParameterName.DLDATASWIPE)) {
                CustomeLogger.Output(CustomeLogger.OutputStates.Info, "[AbstractCommonBusinessLogic] getPersonalInfoFromIDReader() contains datascan or dataswipe", null);

                Map personalInfoMap = null;
                String dlData = "";

                if (request.getTransactionData().get(ParameterName.DLDATASCAN) != null && !request.getTransactionData().get(ParameterName.DLDATASCAN).equals("")) {

                    dlData = (String) request.getTransactionData().get(ParameterName.DLDATASCAN);
                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AbstractCommonBusinessLogic] getPersonalInfoFromIDReader(...) with xmlStringfrom DLDATASCAN", null);

                } else {

                    dlData = (String) request.getTransactionData().get(ParameterName.DLDATASWIPE);
                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AbstractCommonBusinessLogic] getPersonalInfoFromIDReader(...) with xmlString from DLDATASWIPE", null);

                }

                if (dlData != null && !dlData.isEmpty()) {
                    personalInfoMap = IDScanner.parseID(CoreTransactionManager.ID_SCAN_AUTH_KEY, dlData);
                }

                if (personalInfoMap != null) {
                    CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AbstractCommonBusinessLogic] ----------------- Printing IDScanner output map  -----------------  ", null);
                    System.out.println(Arrays.toString(personalInfoMap.entrySet().toArray()));

                    String ssn = (String) request.getTransactionData().get(ParameterName.SSN);
                    dtr.getTransactionData().put(ParameterName.IDTYPE, CoreTransactionUtil.getIdTypeFromId(ssn));
                    dtr.getTransactionData().put(ParameterName.ID, personalInfoMap.get(ParameterName.ID));
                    fixPersonInfoName(personalInfoMap);
                    dtr.getTransactionData().putAll(personalInfoMap);
                    dtr.setResultCode(ResultCode.SUCCESS);
                    dtr.setResultMessage(ResultMessage.SUCCESS.getMessage());
                    dtr.setTerminalResultMessage(ResultMessage.SUCCESS.getMessage());
                } else {
                    throw new TransactionalException(ResultCode.CORE_ERROR, TransactionType.PERSONAL_INFO, "IDReader failed");
                }

            } else {
                throw new TransactionalException(ResultCode.CORE_ERROR, TransactionType.PERSONAL_INFO, " The request doesn't contain DLDATASCAN or DLDATASWIPE ");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new TransactionalException(ResultCode.CORE_ERROR, TransactionType.PERSONAL_INFO, e);
        }

        return dtr;
    }

    private static void fixPersonInfoName(Map<ParameterName, String> personalInfo) {
        String name = personalInfo.get(ParameterName.FIRST_NAME).trim();
        name = name.replaceAll("' ", "'");
        String middleName = "";
        if (personalInfo.containsKey(ParameterName.MIDDLE_NAME) && personalInfo.get(ParameterName.MIDDLE_NAME) != null) {
            middleName = personalInfo.get(ParameterName.MIDDLE_NAME).trim();
        }
        String lastName = personalInfo.get(ParameterName.LAST_NAME).trim();
        String[] aux;
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AbstractCommonBusinessLogic] fixPersonalInfoName with name: " + name, null);
        if (name != null && name.contains(" ")) {
            aux = name.split(" ");
            name = aux[0];
            middleName = aux[1];
        }

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AbstractCommonBusinessLogic] fixPersonalInfoName fixed with name: " + name, null);
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AbstractCommonBusinessLogic] fixPersonalInfoName fixed with Middle name: " + middleName, null);
        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AbstractCommonBusinessLogic] fixPersonalInfoName fixed with last name: " + lastName, null);

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

    
    private static  String filterAlphanumericAndDashes(String src) {
        if (src != null) {
            return src.replaceAll("[^a-zA-Z0-9 _-]", "");
        }
        return null;
    }

    
    
    public static void feeCalculator(DirexTransactionRequest request, Transaction transaction) throws TransactionalException {

        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AbstractCommonBusinessLogic] feeCalculator() start ...", null);

        try {
            HibernateUtil.beginTransaction();

            FeeBucketsManager feeBucketsManager = new FeeBucketsManager();
            Map map = (Map) feeBucketsManager.getFees(request.getTransactionData().get(ParameterName.IDMERCHANT) + "",
                    request.getTransactionData().get(ParameterName.OPERATION) + "",
                    request.getTransactionData().get(ParameterName.AMMOUNT) + "");

            String finalFee = map.get(ParameterName.CRDLDF) + "";
            Double feeAmount = Double.parseDouble(finalFee);

            Double amount = (Double) request.getTransactionData().get(ParameterName.AMMOUNT);

            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AbstractCommonBusinessLogic] FEE_AMOUNT applied: " + feeAmount, null);
            Double payOut = amount - feeAmount;//No se le resta ese fee a peticion de carlos aparicio dic/04/2014

            request.getTransactionData().put(ParameterName.PAYOUT_AMMOUNT, payOut);
            request.getTransactionData().put(ParameterName.FEE_AMMOUNT, feeAmount);

            transaction.setFeeAmmount(feeAmount);
            transaction.setPayoutAmmount(payOut);

            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[AbstractCommonBusinessLogic] feeCalculator() done with PAYOUT_AMOUNT: " + payOut, null);

            HibernateUtil.commitTransaction();
        } catch (Exception e) {

            HibernateUtil.rollbackTransaction();
            throw new TransactionalException(ResultCode.CORE_FEE_CALCULATION_ERROR, request.getTransactionType(), e);
        }
    }
}
