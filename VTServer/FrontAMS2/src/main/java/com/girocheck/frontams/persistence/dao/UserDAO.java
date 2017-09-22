package com.girocheck.frontams.persistence.dao;

import com.girocheck.frontams.common.dao.AbstractBaseDAO;
import com.girocheck.frontams.persistence.dto.Principal;
import com.smartbt.girocheck.servercommon.model.User;
import com.girocheck.frontams.persistence.dto.UserDTO; 
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO extends AbstractBaseDAO<User, UserDTO> {

    public Criteria buildCriteria() {
        return getCriteria().createAlias("role", "role");
    }

    public void addOrder(Criteria criteria) {
        criteria.addOrder(Order.asc("name"));
    } 

    @Override
    public void applyListProjection(Criteria criteria) {
        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("id").as("id"))
                .add(Projections.property("firstName").as("name"))
                .add(Projections.property("lastName").as("lastName"))
                .add(Projections.property("username").as("username"))
                .add(Projections.property("password").as("password"))  
                .add(Projections.property("email").as("email"))  
                .add(Projections.property("role.name").as("role"))
                .add(Projections.property("role.id").as("roleId"));

        criteria.setProjection(projectionList)
                .setResultTransformer(Transformers.aliasToBean(UserDTO.class));
    }

    public Principal getPrincipal(String username, String password) {
        Criteria criteria = buildCriteria()
                .add(Restrictions.eq("username", username))
                .add(Restrictions.eq("password", password))
                .setMaxResults(1);

        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("id").as("id"))
                .add(Projections.property("firstName").as("name"))
                .add(Projections.property("lastName").as("lastName")) 
                .add(Projections.property("role.id").as("roleId"));
        //Ignore for this version
//                .add(Projections.property("role.accessAll").as("accessAll"));

        criteria.setProjection(projectionList)
                .setResultTransformer(Transformers.aliasToBean(Principal.class));

        return (Principal) criteria.uniqueResult();
    }

}
