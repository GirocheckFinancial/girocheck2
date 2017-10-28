/*
 @Autor: Roberto Rodriguez
 Email: robertoSoftwareEngineer@gmail.com

 @Copyright 2016 
 */
package com.girocheck.frontams.persistence.manager;

import com.girocheck.frontams.common.dao.AbstractBaseDAO;
import com.girocheck.frontams.common.manager.AbstractManager; 
import com.girocheck.frontams.persistence.dao.TransactionDAO;
import com.girocheck.frontams.persistence.dto.TransactionDTO;
import com.smartbt.girocheck.servercommon.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rrodriguez
 */
@Service
@Transactional
public class TxManager extends AbstractManager<Transaction, TransactionDTO> {

    @Autowired
    private TransactionDAO transactionDAO;
      
    @Override
    public AbstractBaseDAO dao() {
        return transactionDAO;
    }
 
}
