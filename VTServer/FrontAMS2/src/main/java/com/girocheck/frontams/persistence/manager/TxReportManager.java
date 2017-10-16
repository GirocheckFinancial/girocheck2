/*
 @Autor: Roberto Rodriguez
 Email: robertoSoftwareEngineer@gmail.com

 @Copyright 2016 
 */
package com.girocheck.frontams.persistence.manager;

import com.girocheck.frontams.common.dao.AbstractBaseDAO;
import com.girocheck.frontams.common.manager.AbstractManager; 
import com.girocheck.frontams.persistence.dao.TransactionReportDAO;
import com.girocheck.frontams.persistence.dto.TransactionReportDTO;
import com.girocheck.frontams.persistence.dto.chart.EarningChartPoint;
import com.girocheck.frontams.persistence.dto.chart.PieChartPoint;
import com.smartbt.girocheck.servercommon.model.Transaction;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
public class TxReportManager extends AbstractManager<Transaction, TransactionReportDTO> {

    @Autowired
    private TransactionReportDAO transactionReportDAO;
      
    @Override
    public AbstractBaseDAO dao() {
        return transactionReportDAO;
    }
 
    public Map<String, List> transactionChart(String chartType,  Integer timeType, Integer transactionType, Integer month, Integer year, String operationPattern){
        Map<String, List> result = new HashMap<>();
       
        System.out.println("TxReportManager.transactionChart chartType = " + chartType);
        switch(chartType){
            case "transactionsChart":
               result.put("list", transactionReportDAO.transactionCountChart(  timeType, transactionType,   month,   year));
               break;
            case "earnings": 
                 result.put("list", transactionReportDAO.earningsChart(  timeType, month, year, operationPattern)); 
                 break;
            case "servicesPieChart": 
                 PieChartPoint pieChartData = transactionReportDAO.pieChart(timeType, month, year, operationPattern);
                
                 if(pieChartData == null){
                     System.out.println("pieChartData is NULL");
                 }else{
                     result.put("data", pieChartData.getData());    
                 } 
        }
        return result;
    }
}

class Data{
   private String xvalue;
   private Integer yvalue;

    public Data() {
    }

    public Data(String xvalue, Integer yvalue) {
        this.xvalue = xvalue;
        this.yvalue = yvalue;
    }

   
    /**
     * @return the xvalue
     */
    public String getXvalue() {
        return xvalue;
    }

    /**
     * @param xvalue the xvalue to set
     */
    public void setXvalue(String xvalue) {
        this.xvalue = xvalue;
    }

    /**
     * @return the yvalue
     */
    public Integer getYvalue() {
        return yvalue;
    }

    /**
     * @param yvalue the yvalue to set
     */
    public void setYvalue(Integer yvalue) {
        this.yvalue = yvalue;
    }
}
