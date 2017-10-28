/*
 @Autor: Roberto Rodriguez
 Email: robertoSoftwareEngineer@gmail.com

 @Copyright 2016 
 */
package com.girocheck.frontams.persistence.manager;

import com.girocheck.frontams.common.dao.AbstractBaseDAO;
import com.girocheck.frontams.common.manager.AbstractManager; 
import com.girocheck.frontams.persistence.dao.MobileClientDAO;
import com.girocheck.frontams.persistence.dto.MobileClientDTO;
import com.smartbt.girocheck.servercommon.model.MobileClient;
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
public class MobileClientManager extends AbstractManager<MobileClient, MobileClientDTO> {

    @Autowired
    private MobileClientDAO mobileClientDAO;
      
    @Override
    public AbstractBaseDAO dao() {
        return mobileClientDAO;
    }

    @Override
    protected MobileClient create(Map<String, Object> data) throws Exception {
        return new MobileClient();  
    } 
}
