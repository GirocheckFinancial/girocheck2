package com.girocheck.frontams.persistence.dao;

import com.girocheck.frontams.common.dao.AbstractBaseDAO;
import com.girocheck.frontams.persistence.dto.SubTransactionDTO;
import com.smartbt.girocheck.servercommon.model.SubTransaction;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

@Repository
public class SubTransactionDAO extends AbstractBaseDAO<SubTransaction, SubTransactionDTO> {

    public Criteria buildCriteria() {
        return getCriteria() 
                .createAlias("transaction", "card");
    }

    public void addOrder(Criteria criteria) {
        criteria.addOrder(Order.asc("order"));
    }  
    @Override
    public void applyListProjection(Criteria criteria) {
        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("id").as("id")) 
                .add(Projections.property("transaction.id").as("transactionId"))
                .add(Projections.property("type").as("type"))
                .add(Projections.property("host").as("host"))
                .add(Projections.property("resultCode").as("resultCode"))
                .add(Projections.property("resultMessage").as("resultMessage"))
                .add(Projections.property("errorCode").as("errorCode"))
                .add(Projections.property("order").as("order"));
 
        criteria.setProjection(projectionList)
                .setResultTransformer(Transformers.aliasToBean(SubTransactionDTO.class));
    } 
 
}
