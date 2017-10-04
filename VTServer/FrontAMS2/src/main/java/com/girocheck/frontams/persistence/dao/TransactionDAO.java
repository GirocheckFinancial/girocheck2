package com.girocheck.frontams.persistence.dao;

import com.girocheck.frontams.common.dao.AbstractBaseDAO;
import com.girocheck.frontams.persistence.dto.TransactionDTO; 
import com.smartbt.girocheck.servercommon.model.Transaction;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionDAO extends AbstractBaseDAO<Transaction, TransactionDTO> {

    public Criteria buildCriteria() {
        return getCriteria() 
                .createAlias("data_sc1", "card")
                .createAlias("client", "client")
                .createAlias("merchant", "merchant");
    }

    public void addOrder(Criteria criteria) {
        criteria.addOrder(Order.asc("dateTime"));
    } 

    @Override
    public void applyListProjection(Criteria criteria) {
        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("id").as("id"))
                .add(Projections.property("card.maskCardNumber").as("card"))
                .add(Projections.property("card.id").as("cardId"))
                .add(Projections.property("client.firstName").as("clientName"))
                .add(Projections.property("client.lastName").as("clientLastName"))
                .add(Projections.property("client.id").as("clientId"))
                .add(Projections.property("merchant.legalName").as("merchant"))
                .add(Projections.property("merchant.id").as("merchantId"))  
                .add(Projections.property("operation").as("operation"))  
                .add(Projections.property("dateTime").as("dateTime"))  
                .add(Projections.property("transactionType").as("transactionType"))  
                .add(Projections.property("ammount").as("amount"))  
                .add(Projections.property("feeAmmount").as("feeAmmount"))  
                .add(Projections.property("payoutAmmount").as("payoutAmmount"))  
                .add(Projections.property("transactionFinished").as("completed"))  
                .add(Projections.property("resultCode").as("resultCode"))
                .add(Projections.property("resultMessage").as("resultMessage"));
 
        criteria.setProjection(projectionList)
                .setResultTransformer(Transformers.aliasToBean(TransactionDTO.class));
    } 
 
}
