/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.girocheck.servercommon.dao;

import com.smartbt.girocheck.servercommon.model.Transaction;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import java.util.Date;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartbt.girocheck.servercommon.model.ReportFilters;
import com.smartbt.girocheck.servercommon.utils.Utils;
import org.hibernate.Session;

/**
 *
 * @author Alejo
 */
public class TransactionReportDAO extends BaseDAO<Transaction>{
        protected static TransactionReportDAO dao;
        ObjectMapper objectMapper = new ObjectMapper();

    public TransactionReportDAO() {
    }

    public static TransactionReportDAO get() {
        if ( dao == null ) {
            dao = new TransactionReportDAO();
        }
        return dao;
    }
    
    public int saveReportFilters(ReportFilters filters){
        System.out.println("saveReportFilters()");
        String token = Utils.generateToken();
        filters.setCreateAt(new Date());
        Session session = HibernateUtil.getSession();
        session.saveOrUpdate( filters );
        session.flush();

        return filters.getId();
    }
    
    public ReportFilters getReportFilters(int id){
//        System.out.println("getReportFilters()");
        Criteria cri = HibernateUtil.getSession().createCriteria( ReportFilters.class )
                .add( Restrictions.eq( "id", id ) );
        
        ReportFilters filters = (ReportFilters)cri.uniqueResult();
        
        Date createdAt = filters.getCreateAt();
        
        if(createdAt == null) {
           createdAt = new Date();
        }
        
        long created = createdAt.getTime() + 180000;//3 min
        long actualTime = new Date().getTime();
        
        System.out.println(" [TransactionReportDAO] ACTUAL TIME: "+ actualTime +" CREATED TIME: " + created);
        
        if(actualTime > created){
            return null;
        } 
        
        Session session = HibernateUtil.getSession();
            session.delete(filters);
            session.flush();
        
        return filters;
        
    }
    }
