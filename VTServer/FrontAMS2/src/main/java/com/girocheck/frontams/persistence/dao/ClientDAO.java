package com.girocheck.frontams.persistence.dao;

import com.girocheck.frontams.common.dao.AbstractBaseDAO;
import com.girocheck.frontams.persistence.dto.ClientDTO;
import com.girocheck.frontams.persistence.dto.Principal;
import com.smartbt.girocheck.servercommon.model.User;
import com.girocheck.frontams.persistence.dto.UserDTO; 
import com.smartbt.girocheck.servercommon.model.Client;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

@Repository
public class ClientDAO extends AbstractBaseDAO<Client, ClientDTO> {
 

    public void addOrder(Criteria criteria) {
        criteria.addOrder(Order.asc("firstName"));
    } 
     

    @Override
    public void applyListProjection(Criteria criteria) {
        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("id").as("id"))
                .add(Projections.property("firstName").as("firstName"))
                .add(Projections.property("lastName").as("lastName"))
                .add(Projections.property("telephone").as("telephone"))
                .add(Projections.property("email").as("email"))  
                .add(Projections.property("active").as("active"))  
                .add(Projections.property("blacklistCard2bank").as("blacklistCard2bank"))
                .add(Projections.property("blackListAll").as("blackListAll"))
                .add(Projections.property("createdAt").as("createdAt"))
                .add(Projections.property("successfulLoads").as("successfulLoads"))
                .add(Projections.property("maskSSN").as("maskSSN"));

        criteria.setProjection(projectionList)
                .setResultTransformer(Transformers.aliasToBean(ClientDTO.class));
    }
 
}
