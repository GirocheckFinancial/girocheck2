package com.smartbt.vtsuite.manager;

import com.smartbt.vtsuite.validators.BoardingAMSValidator;

/**
 * @author Roberto Rodriguez :: < roberto.rodriguez@smartbt.com >
 */
public class BoardingAMSManager {

    private BoardingAMSValidator boardingValidator = new BoardingAMSValidator();
// 
//    private Merchant merchantCompleteNullFields(Merchant transientMerchant) throws ValidationException {
//
//        Merchant persistentMerchant = (Merchant) HibernateUtil.getSession().get(Merchant.class, transientMerchant.getId());
//
//        if (persistentMerchant == null) {
//           // boardingValidator.validateNewMerchant(transientMerchant);
//
//            Customer customer = CustomerDAO.get().findById(1);
//            transientMerchant.setCustomer(customer);
//            transientMerchant.setActivationDate(new Date());
//
//            transientMerchant.setAliveSessionTime(600);
//            transientMerchant.setActive(true);
//        } else {
//            boardingValidator.validateUpdateMerchant(persistentMerchant);
//        }
//
//        Merchant merchantPointer = (persistentMerchant == null) ? transientMerchant : persistentMerchant;
//
//        //-----------  filling MERCHANT_ADDRESS ------------------
//        if (transientMerchant.getMerchantAddress() != null) {
//            for (MerchantAddress merchantAddress : transientMerchant.getMerchantAddress()) {
//                int idAddressType = (merchantAddress.getAddressType() == null || merchantAddress.getAddressType().getId() == 0) ? 1 : merchantAddress.getAddressType().getId();
//                merchantAddress.setAddressType(AddressTypeDAO.get().findById(idAddressType));
//                int idState = (merchantAddress.getState() == null || merchantAddress.getState().getId() == 0) ? 1 : merchantAddress.getState().getId();
//                merchantAddress.setState(StateDAO.get().findById(idState));
//                int idCountry = (merchantAddress.getCountry() == null || merchantAddress.getCountry().getId() == 0) ? NomCountry.EUA.getId() : merchantAddress.getCountry().getId();
//                merchantAddress.setCountry(CountryDAO.get().findById(idCountry));
//
//                merchantAddress.setMerchant(merchantPointer);
//
//                if (persistentMerchant != null) {
//                    if (merchantAddress.getId() == 0) { // if is a new address
//                        persistentMerchant.getMerchantAddress().add(merchantAddress);
//                    } else {
//                        for (MerchantAddress ma : persistentMerchant.getMerchantAddress()) {
//                            if (ma.getId() == merchantAddress.getId()) {
//                                ma.setAddressType(merchantAddress.getAddressType());
//                                ma.setState(merchantAddress.getState());
//                                ma.setCountry(merchantAddress.getCountry());
//                                ma.setAddress1(merchantAddress.getAddress1());
//                                ma.setAddress2(merchantAddress.getAddress2());
//                                //ma.setCity(merchantAddress.getCity());
//                                ma.setZip(merchantAddress.getZip());
//                                ma.setMerchant(merchantPointer);
//                            }
//                        }
//                    }
//                }
//
//            }
//        }
//
//        //-----------  filling MERCHANT_TELEPHONE ------------------
//        if (transientMerchant.getMerchantTelephone() != null) {
//            for (MerchantTelephone merchantTelephone : transientMerchant.getMerchantTelephone()) {
//                int idTelephoneType = (((Telephone) merchantTelephone).getTelephoneType() == null || ((Telephone) merchantTelephone).getTelephoneType().getId() == 0)
//                        ? 1 : ((Telephone) merchantTelephone).getTelephoneType().getId();
//                TelephoneType telephoneType = TelephoneTypeDAO.get().findById(idTelephoneType);
//                if (telephoneType != null) {
//                    merchantTelephone.setTelephoneType(telephoneType);
//                }
//
//                merchantTelephone.setMerchant(merchantPointer);
//
//                if (persistentMerchant != null) {
//                    if (merchantTelephone.getId() == 0) { // if is a new address
//                        persistentMerchant.getMerchantTelephone().add(merchantTelephone);
//                    } else {
//                        for (MerchantTelephone ma : persistentMerchant.getMerchantTelephone()) {
//                            if (ma.getId() == merchantTelephone.getId()) {
//                                ma.setTelephoneType(merchantTelephone.getTelephoneType());
//                                ma.setNumber(merchantTelephone.getNumber());
//                                ma.setMerchant(merchantPointer);
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//        //-------------------------------------------------------
//        if (persistentMerchant == null) {
//            return transientMerchant;
//        }
//
//        if (transientMerchant.getNumber() != null) {
//            persistentMerchant.setNumber(transientMerchant.getNumber());
//        }
//        if (transientMerchant.getName() != null) {
//            persistentMerchant.setName(transientMerchant.getName());
//        }
//        if (transientMerchant.getDescription() != null) {
//            persistentMerchant.setDescription(transientMerchant.getDescription());
//        }
//        if (transientMerchant.getActive() != null) {
//            persistentMerchant.setActive(transientMerchant.getActive());
//        }
//        if (transientMerchant.getMonitor() != null) {
//            persistentMerchant.setMonitor(transientMerchant.getMonitor());
//        }
//        if (transientMerchant.getDeactivationDate() != null) {
//            persistentMerchant.setDeactivationDate(transientMerchant.getDeactivationDate());
//        }
//        if (transientMerchant.getSic() != null) {
//            persistentMerchant.setSic(transientMerchant.getSic());
//        }
//        if (transientMerchant.getIndustryType() != null) {
//            persistentMerchant.setIndustryType(transientMerchant.getIndustryType());
//        }
//        if (transientMerchant.getAliveSessionTime() != null) {
//            persistentMerchant.setAliveSessionTime(transientMerchant.getAliveSessionTime());
//        }
//        if (transientMerchant.getLogo() != null) {
//            persistentMerchant.setLogo(transientMerchant.getLogo());
//        }
//
//        persistentMerchant.setTerminal(new ArrayList<Terminal>());
//        persistentMerchant.setClerk(new ArrayList<Clerk>());
//        persistentMerchant.setMerchantParameterValue(new ArrayList<MerchantParameterValue>());
//        persistentMerchant.setHostModeMerchant(new ArrayList<HostModeMerchant>());
//        persistentMerchant.setMerchantHost(new ArrayList<MerchantHost>());
//        return persistentMerchant;
//    }
//
//    private Terminal terminalCompleteNullFields(Terminal transientTerminal) throws ValidationException {
//
//        Terminal persistentTerminal = (Terminal) HibernateUtil.getSession().get(Terminal.class, transientTerminal.getId());
//
//        if (persistentTerminal == null) { // if Iam inserting a new Terminal
//            boardingValidator.validateNewTerminal(transientTerminal);
//            transientTerminal.setMonitored(false);
//            transientTerminal.setActivationDate(new Date());
//        } else {
//            boardingValidator.validateUpdateTerminal(transientTerminal);
//        }
//
//        Terminal terminalPointer = (persistentTerminal == null) ? transientTerminal : persistentTerminal;
//
//        if (transientTerminal.getApplication() != null && transientTerminal.getApplication().getId() != 0) {
//            Application persistentApplication = ApplicationDAO.get().findById(transientTerminal.getApplication().getId());
//            terminalPointer.setApplication(persistentApplication);
//        }
//
//        if (persistentTerminal == null) {
//            return transientTerminal;
//        }
//
//        //----------------------- FILLING NULL VALUES ------------------------------------------------------------
//        if (transientTerminal.getTerminalId() != null) {
//            persistentTerminal.setTerminalId(transientTerminal.getTerminalId());
//        }
//        if (transientTerminal.getSerialNumber() != null) {
//            persistentTerminal.setSerialNumber(transientTerminal.getSerialNumber());
//        }
//        if (transientTerminal.getDescription() != null) {
//            persistentTerminal.setDescription(transientTerminal.getDescription());
//        }
//        if (transientTerminal.getActive() != null) {
//            persistentTerminal.setActive(transientTerminal.getActive());
//        }
//
//        if (transientTerminal.getStartedMonitorAt() != null) {
//            persistentTerminal.setStartedMonitorAt(transientTerminal.getStartedMonitorAt());
//        }
//        if (transientTerminal.getActivationDate() != null) {
//            persistentTerminal.setActivationDate(transientTerminal.getActivationDate());
//        }
//        if (transientTerminal.getDeactivationDate() != null) {
//            persistentTerminal.setDeactivationDate(transientTerminal.getDeactivationDate());
//        }
//
////        if (transientTerminal.getProductType() != null && transientTerminal.getProductType().getId() != 0) {
////            if (persistentTerminal.getProductType().getId() != transientTerminal.getProductType().getId()) {
////                ProductType productType = ProductTypeDAO.get().findById(transientTerminal.getProductType().getId());
////                persistentTerminal.setProductType(productType);
////            }
////        }
//        persistentTerminal.setClerk(new ArrayList<Clerk>());
//        persistentTerminal.setTerminalParameterValues(new ArrayList<TerminalParameterValue>());
//        return persistentTerminal;
//    }
//
//    private Clerk clerkCompleteNullFields(Map<String, String> userPasswList, Clerk transientClerk) throws ValidationException {
//
//        Clerk persistentClerk = (Clerk) HibernateUtil.getSession().get(Clerk.class, transientClerk.getId());
//
//        if (persistentClerk == null) {
//            boardingValidator.validateClerk(transientClerk);
//
//            transientClerk.setFirstTime(true);
//            transientClerk.setActive(Boolean.TRUE);
//            if (transientClerk.getClerkRole() != null && transientClerk.getClerkRole().getId() != 0) {
//                ClerkRole clerkRole = ClerkRoleDAO.get().findById(transientClerk.getClerkRole().getId());
//                transientClerk.setClerkRole(clerkRole);
//            }
//
//            userPasswList.put(transientClerk.getUsername(), transientClerk.getPassword());
//        }
//
//        String encryptPassword = "";
//        if (transientClerk.getPassword() != null) {
//            try {
//                Clerk clerkPointer = (persistentClerk == null) ? transientClerk : persistentClerk;
//                
//                encryptPassword = CryptoUtils.encryptPassword(transientClerk.getPassword());
//                clerkPointer.setPassword(encryptPassword);
//            } catch (NoSuchAlgorithmException ex) {
//                Logger.getLogger(BoardingAMSManager.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//        }
//
//        if (persistentClerk == null) {
//            return transientClerk;
//        }
//
//        if (transientClerk.getUsername() != null) {
//            persistentClerk.setUsername(transientClerk.getUsername());
//        }
//        if (transientClerk.getPassword() != null) {
//            persistentClerk.setPassword(encryptPassword);
//        }
//
//        if (transientClerk.getFirstName() != null) {
//            persistentClerk.setFirstName(transientClerk.getFirstName());
//        }
//        if (transientClerk.getLastName() != null) {
//            persistentClerk.setLastName(transientClerk.getLastName());
//        }
//        if (transientClerk.getActive() != null) {
//            persistentClerk.setActive(transientClerk.getActive());
//        }
//        if (transientClerk.getMiddleInitial() != null) {
//            persistentClerk.setMiddleInitial(transientClerk.getMiddleInitial());
//        }
//        if (transientClerk.getDeactivationDate() != null) {
//            persistentClerk.setDeactivationDate(transientClerk.getDeactivationDate());
//        }
//        if (transientClerk.getClerkRole() != null && transientClerk.getClerkRole().getId() != 0) {
//            if (persistentClerk.getClerkRole().getId() != transientClerk.getClerkRole().getId()) {
//                ClerkRole clerkRole = ClerkRoleDAO.get().findById(transientClerk.getClerkRole().getId());
//
//                persistentClerk.setClerkRole(clerkRole);
//            }
//
//        }
//
//        return persistentClerk;
//    }
//
//    private void merchantCompleteHostParameters(Merchant persistentMerchant) {
//        //------ SAVING HOST_MODE_MERCHANT ------------
//
//        List<HostMode> hostModeList = HostModeDAO.get().list();
//        HostModeMerchant hostModeMerchant;
//        for (HostMode hostMode : hostModeList) {
//            hostModeMerchant = new HostModeMerchant();
//            hostModeMerchant.setHostMode(hostMode);
//            hostModeMerchant.setMerchant(persistentMerchant);
//            persistentMerchant.getHostModeMerchant().add(hostModeMerchant);
//
//        }
//
//        //------ SAVING MERCHANT_HOST --------------------
//        Host host = HostDAO.get().findById(NomHost.ISTREAM.getId());
//
//        MerchantHost merchantHost;
//        MerchantHostParameterValue merchantHostParameterValue;
//
//        merchantHost = new MerchantHost();
//        merchantHost.setMerchant(persistentMerchant);
//        merchantHost.setHost(host);
//        merchantHost.setHostCapture(true);
//        for (MerchantHostParameter merchantHostParameter : host.getMerchantHostParameters()) {
//            merchantHostParameterValue = new MerchantHostParameterValue();
//            merchantHostParameterValue.setMerchantHostParameters(merchantHostParameter);
//            merchantHostParameterValue.setValue(persistentMerchant.getNumber());
//            merchantHostParameterValue.setMerchantHost(merchantHost);
//
//            merchantHost.getMerchantHostParameterValue().add(merchantHostParameterValue);
//
//        }
//
//        persistentMerchant.getMerchantHost().add(merchantHost);
//    }
//
//    public void updateMerchantParameterValue(Merchant transientMerchant, Merchant merchantPointer, boolean isNewMerchant) {
//        //-----------  MERCHANT PARAMETER VALUE -----------------
//
//        MerchantParameterValue parameterValue = new MerchantParameterValue();
//
//        if (transientMerchant.getName() != null) {
//
//            if (!isNewMerchant) {
//                MerchantParameterValueDAO.get().removeMerchantParameterValueByIdMerchant_IdMerchantParameter(merchantPointer.getId(), NomMerchantParameter.HEADER_LINE_1.getId());
//            }
//
//            parameterValue.setValue(transientMerchant.getName());
//            parameterValue.setMerchant(merchantPointer);
//            parameterValue.setMerchantParameter(MerchantParameterDAO.get().findById(NomMerchantParameter.HEADER_LINE_1.getId()));
//            merchantPointer.getMerchantParameterValue().add(parameterValue);
//        }
//
//        if (isNewMerchant) {
//            parameterValue = new MerchantParameterValue();
//            parameterValue.setValue("-----");
//            parameterValue.setMerchant(merchantPointer);
//            parameterValue.setMerchantParameter(MerchantParameterDAO.get().findById(NomMerchantParameter.HEADER_LINE_2.getId()));
//            merchantPointer.getMerchantParameterValue().add(parameterValue);
//        }
//
//        if (!transientMerchant.getMerchantAddress().isEmpty()) {
//            if (!isNewMerchant) {
//                MerchantParameterValueDAO.get().removeMerchantParameterValueByIdMerchant_IdMerchantParameter(merchantPointer.getId(), NomMerchantParameter.HEADER_LINE_3.getId());
//                MerchantParameterValueDAO.get().removeMerchantParameterValueByIdMerchant_IdMerchantParameter(merchantPointer.getId(), NomMerchantParameter.HEADER_LINE_4.getId());
//            }
//
//            MerchantParameter merchantParameter3 = MerchantParameterDAO.get().findById(NomMerchantParameter.HEADER_LINE_3.getId());
//            MerchantParameter merchantParameter4 = MerchantParameterDAO.get().findById(NomMerchantParameter.HEADER_LINE_4.getId());
//            for (MerchantAddress merchantAddress : merchantPointer.getMerchantAddress()) {
//                parameterValue = new MerchantParameterValue();
//                parameterValue.setValue(merchantAddress.getAddress1());
//                parameterValue.setMerchant(merchantPointer);
//                parameterValue.setMerchantParameter(merchantParameter3);
//                merchantPointer.getMerchantParameterValue().add(parameterValue);
//
//                parameterValue = new MerchantParameterValue();
//                String address = merchantAddress.getZip() + ", " + merchantAddress.getState().getAbbreviation();
//                parameterValue.setValue(address);
//                parameterValue.setMerchant(merchantPointer);
//                parameterValue.setMerchantParameter(merchantParameter4);
//                merchantPointer.getMerchantParameterValue().add(parameterValue);
//            }
//        }
//
//        if (!transientMerchant.getMerchantTelephone().isEmpty()) {
//            if (!isNewMerchant) {
//                MerchantParameterValueDAO.get().removeMerchantParameterValueByIdMerchant_IdMerchantParameter(merchantPointer.getId(), NomMerchantParameter.HEADER_LINE_5.getId());
//            }
//
//            MerchantParameter merchantParameter5 = MerchantParameterDAO.get().findById(NomMerchantParameter.HEADER_LINE_5.getId());
//            for (MerchantTelephone merchantTelephone : merchantPointer.getMerchantTelephone()) {
//                parameterValue = new MerchantParameterValue();
//                parameterValue.setValue(merchantTelephone.getNumber());
//                parameterValue.setMerchant(merchantPointer);
//                parameterValue.setMerchantParameter(merchantParameter5);
//                merchantPointer.getMerchantParameterValue().add(parameterValue);
//            }
//        }
//
//        if (isNewMerchant) {
//            parameterValue = new MerchantParameterValue();
//            parameterValue.setValue(MerchantParameterDAO.get().findById(NomMerchantParameter.PROMISSORY_VERBIAGE_1.getId()).getDefaultValue());
//            parameterValue.setMerchant(merchantPointer);
//            parameterValue.setMerchantParameter(MerchantParameterDAO.get().findById(NomMerchantParameter.PROMISSORY_VERBIAGE_1.getId()));
//            merchantPointer.getMerchantParameterValue().add(parameterValue);
//
//            parameterValue = new MerchantParameterValue();
//            parameterValue.setValue(MerchantParameterDAO.get().findById(NomMerchantParameter.PROMISSORY_VERBIAGE_2.getId()).getDefaultValue());
//            parameterValue.setMerchant(merchantPointer);
//            parameterValue.setMerchantParameter(MerchantParameterDAO.get().findById(NomMerchantParameter.PROMISSORY_VERBIAGE_2.getId()));
//            merchantPointer.getMerchantParameterValue().add(parameterValue);
//
//            parameterValue = new MerchantParameterValue();
//            parameterValue.setValue(MerchantParameterDAO.get().findById(NomMerchantParameter.PROMISSORY_VERBIAGE_3.getId()).getDefaultValue());
//            parameterValue.setMerchant(merchantPointer);
//            parameterValue.setMerchantParameter(MerchantParameterDAO.get().findById(NomMerchantParameter.PROMISSORY_VERBIAGE_3.getId()));
//            merchantPointer.getMerchantParameterValue().add(parameterValue);
//        }
//    }
//
//    private void terminalCompleteHostParameters(Terminal persistentTerminal) {
//        //------ SAVING TERMINAL_HOST --------------------
//
//        Host host = HostDAO.get().findById(NomHost.ISTREAM.getId());
//
//        TerminalHostParameterValue terminalHostParameterValue;
//
//        TerminalHost terminalHost = new TerminalHost();
//        terminalHost.setTerminal(persistentTerminal);
//        terminalHost.setHost(host);
//        terminalHost.setTerminalCapture(true);
//
//        terminalHostParameterValue = new TerminalHostParameterValue();
//        terminalHostParameterValue.setTerminalHostParameter(TerminalHostParameterDAO.get().findById((NomTerminalHostParameter.WORLDPAY_SEQNUM.getId())));
//        terminalHostParameterValue.setValue("0");
//        terminalHostParameterValue.setTerminalHost(terminalHost);
//        terminalHost.getTerminalHostParameterValue().add(terminalHostParameterValue);
//
//        terminalHostParameterValue = new TerminalHostParameterValue();
//        terminalHostParameterValue.setTerminalHostParameter(TerminalHostParameterDAO.get().findById((NomTerminalHostParameter.WORLDPAY_CHK.getId())));
//        terminalHostParameterValue.setValue("8");
//        terminalHostParameterValue.setTerminalHost(terminalHost);
//        terminalHost.getTerminalHostParameterValue().add(terminalHostParameterValue);
//
//        terminalHostParameterValue = new TerminalHostParameterValue();
//        terminalHostParameterValue.setTerminalHostParameter(TerminalHostParameterDAO.get().findById((NomTerminalHostParameter.WORLDPAY_TERMINALID.getId())));
//        terminalHostParameterValue.setValue(persistentTerminal.getTerminalId());
//        terminalHostParameterValue.setTerminalHost(terminalHost);
//        terminalHost.getTerminalHostParameterValue().add(terminalHostParameterValue);
//
//        persistentTerminal.getTerminalHost().add(terminalHost);
//    }
//
//    public void updateTerminalParameterValues(Terminal persistentTerminal, boolean isNewTerminal) {
//        //----------------------- UPDATING TERMINAL_PARAMETER_VALUE for this terminal -----------------------------------------------------------
//        if (persistentTerminal.getApplication() != null) {
//            if (!isNewTerminal) {
//                TerminalParameterValueDAO.get().removeTerminalParameterValueByIdTerminal(persistentTerminal.getId());
//            }
//
//            Application persistentApplication = persistentTerminal.getApplication();
//
//            List<TerminalParameterValue> listTerminalParameterValue = new ArrayList<TerminalParameterValue>();
//            List<ApplicationParameterValue> list = ApplicationParameterValueDAO.get().getApplicationParameterValueByIdApplication(persistentApplication.getId());
//
//            TerminalParameterValue terminalParameterValue;
//            for (ApplicationParameterValue applicationParameterValue : list) {
//                terminalParameterValue = new TerminalParameterValue();
//                terminalParameterValue.setApplicationParameter(applicationParameterValue.getApplicationParameter());
//                terminalParameterValue.setValue(applicationParameterValue.getValue());
//                terminalParameterValue.setTerminal(persistentTerminal);
//
//                listTerminalParameterValue.add(terminalParameterValue);
//            }
//            persistentTerminal.setTerminalParameterValues(listTerminalParameterValue);
//        }
//    }
//
//    public void sendEmailToNewClerks(Map<String, String> userPasswList) throws Exception {
//        String receiptTittle = I18N.get(Messages.AMS_BOARDING_EMAIL_TITTLE);
//
//        String server_address = SystemPropertyDAO.get().searchSysProperty(NomSystemProperties.EMAIL_SERVER_ADDRESS.getViewValue());
//        String server_port = SystemPropertyDAO.get().searchSysProperty(NomSystemProperties.EMAIL_SERVER_PORT.getViewValue());
//        String server_username = SystemPropertyDAO.get().searchSysProperty(NomSystemProperties.EMAIL_SERVER_USERNAME.getViewValue());
//        String server_password = SystemPropertyDAO.get().searchSysProperty(NomSystemProperties.EMAIL_SERVER_PASSWORD.getViewValue());
//        String server_from_address = SystemPropertyDAO.get().searchSysProperty(NomSystemProperties.EMAIL_FROM_ADDRESS.getViewValue());
//        String email_debug_setting = SystemPropertyDAO.get().searchSysProperty(NomSystemProperties.EMAIL_DEBUG.getViewValue());
//
//        boolean email_debug = false;
//
//        if (email_debug_setting != null && email_debug_setting.toLowerCase().compareTo("true") == 0) {
//            email_debug = true;
//        }
//
//        for (Map.Entry<String, String> entry : userPasswList.entrySet()) {
//            String userName = entry.getKey();
//            String password = entry.getValue();
//
//            String[] recipients = new String[]{userName};
//
//            EmailUtils email;
//
//            if (server_username != null && !server_username.isEmpty()) {
//                email = new EmailUtils(server_address, server_port, server_username, server_password);
//            } else {
//                email = new EmailUtils(server_address, server_port);
//            }
//
//            email.setMessage("<html><head><meta http-equiv=\"content-type\" content=\"text/html; charset=Shift_JIS\"><style type=\"text/css\">body{border:0px none;}</style></head><body>" + I18N.get(Messages.AMS_BOARDING_EMAIL_BODY, "<br/>", userName, password) + "</body></html>", "text/html; charset=Shift_JIS");
//
//        }
//
//    }
}
