package com.girocheck.frontams.persistence.dao;

import com.girocheck.frontams.common.dao.AbstractBaseDAO;
import com.girocheck.frontams.persistence.dto.ClientDTO;
import com.smartbt.girocheck.servercommon.model.Client;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
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
                .add(Projections.property("isMobileClient").as("isMobileClient"))
                .add(Projections.property("successfulLoads").as("successfulLoads"))
                .add(Projections.property("maskSSN").as("maskSSN"));
         
        criteria.setProjection(projectionList)
                .setResultTransformer(Transformers.aliasToBean(ClientDTO.class));
    }
    
    //Pure Client is the one that is not mobile client
    public List<Integer> listActivePureClientsIds(){  
       return (List<Integer>)getSession().createSQLQuery("select id from client where is_mobile_client = false AND active = true" ).list();   
    }
 
}
