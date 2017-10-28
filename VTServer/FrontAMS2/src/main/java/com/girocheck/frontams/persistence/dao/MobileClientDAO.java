package com.girocheck.frontams.persistence.dao;

import com.girocheck.frontams.common.dao.AbstractBaseDAO; 
import com.girocheck.frontams.persistence.dto.MobileClientDTO; 
import com.smartbt.girocheck.servercommon.model.MobileClient;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

@Repository
public class MobileClientDAO extends AbstractBaseDAO<MobileClient, MobileClientDTO> {

    public Criteria buildCriteria() {
        return getCriteria().createAlias("client", "client");
    }

    public void addOrder(Criteria criteria) {
        criteria.addOrder(Order.asc("firstName"));
    }

    @Override
    public void applyListProjection(Criteria criteria) {
        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("id").as("id"))
                .add(Projections.property("client.firstName").as("firstName"))
                .add(Projections.property("client.lastName").as("lastName"))
                .add(Projections.property("client.id").as("clientId"))
                .add(Projections.property("client.telephone").as("telephone"))
                .add(Projections.property("client.email").as("email"))
                .add(Projections.property("deviceType").as("deviceType"))
                .add(Projections.property("registrationDate").as("registrationDate"))
                .add(Projections.property("version").as("version"))
                .add(Projections.property("lang").as("lang"))
                .add(Projections.property("lastLogin").as("lastLogin"));

        criteria.setProjection(projectionList)
                .setResultTransformer(Transformers.aliasToBean(MobileClientDTO.class));
    }
  
}
