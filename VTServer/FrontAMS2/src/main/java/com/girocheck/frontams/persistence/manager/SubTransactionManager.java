/*
 @Autor: Roberto Rodriguez
 Email: robertoSoftwareEngineer@gmail.com

 @Copyright 2016 
 */
package com.girocheck.frontams.persistence.manager;

import com.girocheck.frontams.common.dao.AbstractBaseDAO;
import com.girocheck.frontams.common.manager.AbstractManager; 
import com.girocheck.frontams.persistence.dao.SubTransactionDAO;
import com.girocheck.frontams.persistence.dto.SubTransactionDTO;
import com.smartbt.girocheck.servercommon.model.SubTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rrodriguez
 */
@Service
@Transactional
public class SubTransactionManager extends AbstractManager<SubTransaction, SubTransactionDTO> {

    @Autowired
    private SubTransactionDAO subTransactionDAO;
      
    @Override
    public AbstractBaseDAO dao() {
        return subTransactionDAO;
    }
 
}
