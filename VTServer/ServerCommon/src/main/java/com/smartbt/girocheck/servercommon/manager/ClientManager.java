/*
 * Copyright @ 2004-2014 Smart Business Technology, Inc.
 *
 * All rights reserved. No part of this software may be 
 * reproduced, transmitted, transcribed, stored in a retrieval
 * system, or translated into any language or computer language, 
 * in any form or by any means, electronic, mechanical, magnetic,  
 * optical, chemical, manual or otherwise, without the prior  
 * written permission of Smart Business Technology, Inc.  
 *
 *
 */
package com.smartbt.girocheck.servercommon.manager;

import com.smartbt.girocheck.servercommon.dao.AddressDAO;
import com.smartbt.girocheck.servercommon.dao.ClientDAO;
import com.smartbt.girocheck.servercommon.dao.CountryDAO;
import com.smartbt.girocheck.servercommon.dao.CreditCardDAO;
import com.smartbt.girocheck.servercommon.dao.PersonalIdentificationDAO;
import com.smartbt.girocheck.servercommon.dao.StateDAO;
import com.smartbt.girocheck.servercommon.display.ClientDisplay;
import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.girocheck.servercommon.model.Client;
import com.smartbt.girocheck.servercommon.model.Country;
import com.smartbt.girocheck.servercommon.model.PersonalIdentification;
import com.smartbt.girocheck.servercommon.model.State;
import java.sql.SQLException;

/**
 *
 * @author Roberto Rodriguez :: <roberto.rodriguez@smartbt.com>
 */
public class ClientManager {

    protected static ClientManager INSTANCE;

    public ClientManager() {
    }

    public static ClientManager get() {
        if (INSTANCE == null) {
            INSTANCE = new ClientManager();
        }
        return INSTANCE;
    }
    private ClientDAO clientDAO = ClientDAO.get();
    private AddressDAO addressDAO = AddressDAO.get();
    private CountryDAO countryDAO = CountryDAO.get();
    private StateDAO stateDAO = StateDAO.get();
    private CreditCardDAO creditCardDAO = CreditCardDAO.get();
    private PersonalIdentificationDAO personalIdentificationDAO = PersonalIdentificationDAO.get();

    public Client createOrGet(String ssn, byte[] addressForm) throws SQLException {
        return clientDAO.createOrGet(ssn, addressForm);
    }

    public void updateSuccessfulLoads(Integer clientId, Integer successfulLoads){
        clientDAO.updateSuccessfulLoads(clientId, successfulLoads);
    }
    
    public void saveOrUpdate(Client client) {
        client.setBlacklistCard2bank(false);
        if (client.getAddress() != null) {
            if (client.getAddress().getCountry() != null && client.getAddress().getCountry().getAbbreviation() != null) {
                Country country = countryDAO.getByAbbreviation(client.getAddress().getCountry().getAbbreviation());
                if (country != null) {
                    client.getAddress().setCountry(country);
                }
            }
            if (client.getAddress().getState() != null && client.getAddress().getState().getAbbreviation() != null) {
                State state = stateDAO.getByAbbreviation(client.getAddress().getState().getAbbreviation());
                if (state != null) {
                    client.getAddress().setState(state);
                } else {
                    client.getAddress().setState(null);
                }
            }
        } 
        clientDAO.saveOrUpdate(client);
    } 

    public ResponseDataList searchClients(String searchFilter, int pageNumber, int rowsPerPage, Integer blackListCode, Boolean optOut) throws Exception {


        return clientDAO.searchClients(searchFilter, pageNumber * rowsPerPage, rowsPerPage, blackListCode, optOut);

    }

    public PersonalIdentification getIdentificationByClientId(int idClient) throws SQLException {

        return personalIdentificationDAO.getByClientId(idClient);

    }

    public ResponseData updateClientBlackList(ClientDisplay clientDisplay) {
        return clientDAO.updateClientBlackList(clientDisplay);
    }

    public ResponseData updateClientOptOut(int id) {
        return clientDAO.updateClientOptOut(id);
    }
}
