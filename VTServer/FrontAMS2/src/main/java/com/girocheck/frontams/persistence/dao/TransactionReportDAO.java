package com.girocheck.frontams.persistence.dao;

import com.girocheck.frontams.common.dao.AbstractBaseDAO;
import com.girocheck.frontams.persistence.dto.TransactionReportDTO;
import com.smartbt.girocheck.servercommon.model.Transaction;
import org.hibernate.Criteria;
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

}
