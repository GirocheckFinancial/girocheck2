package com.girocheck.frontams.persistence.dao;

import com.girocheck.frontams.common.dao.AbstractBaseDAO;
import com.girocheck.frontams.persistence.dto.TransactionReportDTO;
import com.girocheck.frontams.persistence.dto.chart.EarningChartPoint;
import com.girocheck.frontams.persistence.dto.chart.PieChartPoint;
import com.girocheck.frontams.persistence.dto.chart.TransactionChartPoint;
import com.smartbt.girocheck.servercommon.model.Transaction;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionReportDAO extends AbstractBaseDAO<Transaction, TransactionReportDTO> {

    public Criteria buildCriteria() {
        return getCriteria()
                .createAlias("data_sc1", "card")
                .createAlias("check", "check", JoinType.LEFT_OUTER_JOIN)
                .createAlias("client", "client", JoinType.LEFT_OUTER_JOIN)
                .createAlias("merchant", "merchant")
                .createAlias("terminal", "terminal")
                .createAlias("merchant.address", "address")
                .createAlias("address.state", "state");
    }

    public void addOrder(Criteria criteria) {
        criteria.addOrder(Order.asc("dateTime"));
    }

    @Override
    public void applyListProjection(Criteria criteria) {
        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("id").as("id"))
                .add(Projections.property("certegyApprovalNumber").as("certegyApprovalNumber"))
                .add(Projections.property("terminal.serialNumber").as("terminal"))
                .add(Projections.property("card.maskCardNumber").as("card"))
                .add(Projections.property("client.firstName").as("clientName"))
                .add(Projections.property("client.lastName").as("clientLastName"))
                .add(Projections.property("client.telephone").as("clientPhone"))
                .add(Projections.property("merchant.legalName").as("merchant"))
                .add(Projections.property("operation").as("operation"))
                .add(Projections.property("dateTime").as("dateTime"))
                .add(Projections.property("transactionType").as("transactionType"))
                .add(Projections.property("ammount").as("amount"))
                .add(Projections.property("feeAmmount").as("feeAmmount"))
                .add(Projections.property("payoutAmmount").as("payoutAmmount"))
                .add(Projections.property("address.address").as("address"))
                .add(Projections.property("address.city").as("city"))
                .add(Projections.property("address.zipcode").as("zipcode"))
                .add(Projections.property("state.abbreviation").as("state"))
                .add(Projections.property("check.paymentCheck").as("checkNumber"))
                .add(Projections.property("check.makerName").as("makerName"))
                .add(Projections.property("resultCode").as("resultCode"))
                .add(Projections.property("resultMessage").as("resultMessage"));

        criteria.setProjection(projectionList)
                .setResultTransformer(Transformers.aliasToBean(TransactionReportDTO.class));
    }

    
    public List<TransactionChartPoint> transactionCountChart(Integer timeType, Integer transactionType, Integer month, Integer year){
         
        System.out.println("chartByMonthYear**");
       // Map<QueryParam, String> params = getQueryParams(request);

        StringBuffer str = new StringBuffer("SELECT ");
 
        str.append( timeType + " as timeType, "); 
        str.append("count(*) as total, "); 
        str.append("count(case result_code when 0 then 1 else null end) as successful, "); 
            
        switch(timeType){
            case 0: str.append("extract(day from this_.date_time) as day, "); 
            case 1: str.append("extract(month from this_.date_time) as month, ");
            case 2: str.append("extract(year from this_.date_time) as  year ");
        }     
        str.append("FROM transaction this_ WHERE "); 
   
        
         switch(timeType){
            case 0: str.append("extract(month from this_.date_time) = " + month + " AND ");
            case 1: str.append("extract(year from this_.date_time) = " + year); 
                break;
            case 2: str.append("true"); //This is to avoid: WHERE GROUP BY
        }     
         
        switch(transactionType){
            case 1: str.append(" AND operation = '01' ");
                break;
            case 2: str.append(" AND operation = '02' ");
                break;
            case 3: str.append(" AND transaction_type = 19 ");
                break;
        }

        str.append(" GROUP BY ");
        
        switch(timeType){
            case 0: str.append("day, "); 
            case 1: str.append("month, ");
            case 2: str.append("year ");
        }   
  
        str.append("ORDER BY ");
        
           switch(timeType){
            case 0: str.append("day asc, "); 
            case 1: str.append("month asc, ");
            case 2: str.append("year asc");
        } 
 
        System.out.println("query = " + str.toString());
            
       SQLQuery query = getSession().createSQLQuery(str.toString());

       query.setResultTransformer(Transformers.aliasToBean(TransactionChartPoint.class));
       return query.list();  
    }
    
    
     public List<EarningChartPoint> earningsChart(Integer timeType, Integer month, Integer year, String operationPattern){
      
        StringBuffer str = new StringBuffer("SELECT ");
 
        str.append( timeType + " as timeType, ");  
         if(operationPattern.charAt(0) == '1'){
             str.append("sum(case operation when '01' then ammount else null end) as `check`, ");  
         }
         
         if(operationPattern.charAt(1) == '1'){
             str.append("sum(case operation when '02' then ammount else null end) as cash, ");  
         }
         
         if(operationPattern.charAt(2) == '1'){
             str.append("sum(case transaction_type when 19 then ammount else null end) as card2bank, ");  
         }
         
         if(operationPattern.charAt(3) == '1'){
             str.append("sum(fee_ammount) as commission, ");  
         }
            
        switch(timeType){
            case 0: str.append("extract(day from this_.date_time) as day, "); 
            case 1: str.append("extract(month from this_.date_time) as month, ");
            case 2: str.append("extract(year from this_.date_time) as  year ");
        }     
        str.append("FROM transaction this_ WHERE result_code = 0 "); 
    
         switch(timeType){
            case 0: str.append(" AND extract(month from this_.date_time) = " + month);
            case 1: str.append(" AND extract(year from this_.date_time) = " + year); 
        }     

        str.append(" GROUP BY ");
        
        switch(timeType){
            case 0: str.append("day, "); 
            case 1: str.append("month, ");
            case 2: str.append("year ");
        }   
  
        str.append("ORDER BY ");
        
           switch(timeType){
            case 0: str.append("day asc, "); 
            case 1: str.append("month asc, ");
            case 2: str.append("year asc");
        } 
 
        System.out.println("query = " + str.toString());
            
       SQLQuery query = getSession().createSQLQuery(str.toString());

       query.setResultTransformer(Transformers.aliasToBean(EarningChartPoint.class));
       return query.list();  
    }
    
    
     public PieChartPoint pieChart(Integer timeType, Integer month, Integer year, String operationPattern){
      
        StringBuffer str = new StringBuffer("SELECT ");
  
         if(operationPattern.charAt(0) == '1'){
             str.append("sum(case operation when '01' then ammount else null end) as `check`, ");  
         }
         
         if(operationPattern.charAt(1) == '1'){
             str.append("sum(case operation when '02' then ammount else null end) as cash, ");  
         }
         
         if(operationPattern.charAt(2) == '1'){
             str.append("sum(case transaction_type when 19 then ammount else null end) as card2bank, ");  
         } 
         
        switch(timeType){
            case 0: str.append("extract(day from this_.date_time) as day, "); 
            case 1: str.append("extract(month from this_.date_time) as month, ");
            case 2: str.append("extract(year from this_.date_time) as  year ");
        }     
        str.append("FROM transaction this_ WHERE result_code = 0 "); 
    
         switch(timeType){
            case 0: str.append(" AND extract(month from this_.date_time) = " + month);
            case 1: str.append(" AND extract(year from this_.date_time) = " + year); 
        }     
 
        System.out.println("query = " + str.toString());
            
       SQLQuery query = getSession().createSQLQuery(str.toString());

       query.setResultTransformer(Transformers.aliasToBean(PieChartPoint.class));
       return (PieChartPoint)query.uniqueResult();  
    }
 
}
