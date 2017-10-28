/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.girocheck.frontams.web.api;
 
import com.girocheck.frontams.common.controller.AbstractController;
import com.girocheck.frontams.common.manager.AbstractManager; 
import com.girocheck.frontams.persistence.manager.SubTransactionManager;
import com.girocheck.frontams.persistence.manager.TxManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Roberto Rodriguez
 */
@RestController
@RequestMapping(value = "/{pageId}/subTransaction")
public class SubTransactionController extends AbstractController{
    
    @Autowired
    protected SubTransactionManager subTransactionManager;
     

    @Override
    public AbstractManager getAbstractManager() {
       return subTransactionManager;
    }
     
  
}
 
   
