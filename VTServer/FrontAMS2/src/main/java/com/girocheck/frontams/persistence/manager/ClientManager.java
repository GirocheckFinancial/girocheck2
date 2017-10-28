/*
 @Autor: Roberto Rodriguez
 Email: robertoSoftwareEngineer@gmail.com

 @Copyright 2016 
 */
package com.girocheck.frontams.persistence.manager;

import com.girocheck.frontams.common.dao.AbstractBaseDAO;
import com.girocheck.frontams.common.manager.AbstractManager; 
import com.girocheck.frontams.persistence.dao.ClientDAO;
import com.girocheck.frontams.persistence.dto.ClientDTO;
import com.smartbt.girocheck.servercommon.model.Client;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rrodriguez
 */
@Service
@Transactional
public class ClientManager extends AbstractManager<Client, ClientDTO> {

    @Autowired
    private ClientDAO clientDAO;
      
    @Override
    public AbstractBaseDAO dao() {
        return clientDAO;
    }

    @Override
    protected Client create(Map<String, Object> data) throws Exception {
        Client user = new Client(); 
        update(user, data);

        return user;
    }

    @Override
    protected void update(Client entity, Map<String, Object> data) { 
    }

}
