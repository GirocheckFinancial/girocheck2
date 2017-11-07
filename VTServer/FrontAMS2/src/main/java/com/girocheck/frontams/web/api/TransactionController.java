/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.girocheck.frontams.web.api;
 
import com.girocheck.frontams.common.controller.AbstractController;
import com.girocheck.frontams.common.manager.AbstractManager; 
import com.girocheck.frontams.persistence.manager.TransactionImageManager;
import com.girocheck.frontams.persistence.manager.TxManager;
import com.girocheck.frontams.persistence.manager.TxReportManager;
import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Roberto Rodriguez
 */
@RestController
@RequestMapping(value = "/{pageId}/transaction")
public class TransactionController extends AbstractController{
    
    @Autowired
    protected TxManager txManager;
    
    @Autowired
    protected TxReportManager txReportManager;
    
      @RequestMapping(value = "/chart", method = RequestMethod.GET)
    public Map<String, List> chart(
            @PathVariable("pageId") String pageId, 
            @RequestParam(value = "chartType") String chartType, //Type Chart
            @RequestParam(value = "timeType", defaultValue = "0") Integer timeType, //0-daily  1-monthly  2-yearly
            @RequestParam(value = "transactionType", defaultValue = "0") Integer transactionType,
            @RequestParam(value = "month", defaultValue = "-1") Integer month, 
            @RequestParam(value = "year", defaultValue = "-1") Integer year, 
            @RequestParam(value = "operationPattern", defaultValue = "1111") String operationPattern, //check|cash|card2Bank|commission
            HttpSession session) throws Exception {
   
        System.out.println("FrontAMS.chart / chartType = " + chartType);
        System.out.println("FrontAMS.chart / timeType = " + timeType);
        System.out.println("FrontAMS.chart / transactionType = " + transactionType);
        System.out.println("FrontAMS.chart / month = " + month);
        System.out.println("FrontAMS.chart / year = " + year);
        
        Date today = new Date();
        
        if(month == -1){
          month = today.getMonth();
        }
        month += 1;
        
        if(year == -1){
           year = today.getYear() + 1900;
        } 
         
        return txReportManager.transactionChart(chartType, timeType, transactionType, month, year, operationPattern); 
    }
    
    
    @RequestMapping(value = "/idImage/{transactionId}")
    public ResponseData checkAccess(@PathVariable("pageId") String pageId, @PathVariable("transactionId") Integer transactionId) throws Exception {
        System.out.println("222 Calling PING...");
        ResponseData response = null;
        try {
            HibernateUtil.beginTransaction();
            response = TransactionImageManager.get().getTransactionImage(transactionId, true);
            HibernateUtil.commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            HibernateUtil.rollbackTransaction();
        }

        return response;
    }
      

    @Override
    public AbstractManager getAbstractManager() {
       return txManager;
    }
     
  public AbstractManager getReportManager(){
      return txReportManager;
  }
}
 
   
