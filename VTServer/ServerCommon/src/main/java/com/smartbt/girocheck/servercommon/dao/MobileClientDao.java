/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.girocheck.servercommon.dao;

import com.smartbt.girocheck.servercommon.dao.BaseDAO;
import com.smartbt.girocheck.servercommon.display.mobile.MobileClientDisplay;
import com.smartbt.girocheck.servercommon.model.CreditCard;
import com.smartbt.girocheck.servercommon.model.MobileClient;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import java.util.Date;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

/**
 *
 * @author suresh
 */
public class MobileClientDao extends BaseDAO<MobileClient> {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(MobileClientDao.class);
    protected static MobileClientDao dao;

    public MobileClientDao() {
    }

    public static MobileClientDao get() {
        if (dao == null) {
            dao = new MobileClientDao();
        }
        return dao;
    }

    public MobileClientDisplay getMobileClientDisplayByUserAndPassword(String userName, String password, String token, String pushToken, Integer version, String lang, String os) {

        Criteria criteria = HibernateUtil.getSession().createCriteria(MobileClient.class)
                .createAlias("card", "card")
                .createAlias("client", "client")
                .add(Restrictions.eq("userName", userName))
                .add(Restrictions.eq("password", password))
                .setMaxResults(1);

        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("id").as("clientId"))
                .add(Projections.property("card.cardNumber").as("card"))
                .add(Projections.property("client.firstName").as("clientName"))
                .add(Projections.property("client.email").as("clientEmail"))
                .add(Projections.property("client.telephone").as("clientPhone"))
                .add(Projections.property("userName").as("mobileClientUserName"));

        criteria.setProjection(projectionList);
        criteria.setResultTransformer(Transformers.aliasToBean(MobileClientDisplay.class));

        MobileClientDisplay result = (MobileClientDisplay) criteria.uniqueResult();
        
        if(result != null){
            updateToken(result.getClientId(), token, pushToken, version, lang, os);   
        }
        
        result.setUnreadNotifications(MobileNotificationDao.get().countUnreadNotifications( result.getClientId() ));
        
        return result;
    }

    public String getCardNumberByMobileClientId(Integer mobileClientId) {
        return (String) HibernateUtil.getSession().createCriteria(MobileClient.class)
                .createAlias("card", "card")
                .add(Restrictions.eq("id", mobileClientId))
                .setProjection(Projections.property("card.cardNumber"))
                .uniqueResult();
    }

    public MobileClient getMobileClientById(int clientId) {
        Criteria criteria = HibernateUtil.getSession().createCriteria(MobileClient.class)
                .add(Restrictions.eq("id", clientId));
        return (MobileClient) criteria.uniqueResult();
    }

    public MobileClient getMobileClientByClient(int clientId) {
        Criteria criteria = HibernateUtil.getSession().createCriteria(MobileClient.class)
                .createAlias("client", "client")
                .add(Restrictions.eq("client.id", clientId));
        return (MobileClient) criteria.uniqueResult();
    }

    public MobileClient saveOrUpdateMobileClient(MobileClient client) {
        HibernateUtil.getSession().saveOrUpdate(client);
        return client;
    }

    public Boolean existMobileClientBySSN(String ssn) {
        return (Long) HibernateUtil.getSession().createCriteria(MobileClient.class).
                createAlias("client", "client")
                .add(Restrictions.eq("client.ssn", ssn))
                .setProjection(Projections.rowCount())
                .uniqueResult() > 0;
    }

    public Boolean existMobileClientByUsername(String userName) {
        return (Long) HibernateUtil.getSession().createCriteria(MobileClient.class)
                .add(Restrictions.eq("userName", userName))
                .setProjection(Projections.rowCount())
                .uniqueResult() > 0;
    }

    public Boolean existMobileAssociatedToCard(String cardNumber) {
        return (Long) HibernateUtil.getSession().createCriteria(MobileClient.class)
                .createAlias("card", "card")
                .add(Restrictions.eq("card.cardNumber", cardNumber))
                .setProjection(Projections.rowCount())
                .uniqueResult() > 0;
    }

    public MobileClient getMobileClientByCardNumberAndMaskSSN(String maskSSN, String cardNumber) {

        Criteria criteria = HibernateUtil.getSession().createCriteria(MobileClient.class)
                .createAlias("card", "card")
                .createAlias("client", "client")
                .add(Restrictions.eq("card.cardNumber", cardNumber))
                .add(Restrictions.eq("client.maskSSN", maskSSN))
                .setMaxResults(1);

        return (MobileClient) criteria.uniqueResult();
    }

    public MobileClient getMobileClientByTelphone(String recipentNumber) {

        Criteria criteria = HibernateUtil.getSession().createCriteria(MobileClient.class)
                .createAlias("client", "client")
                .add(Restrictions.eq("client.telephone", recipentNumber))
                .setMaxResults(1);

        return (MobileClient) criteria.uniqueResult();
    }

    public void updateExcludeSMS(String phoneNumber) {
        System.out.println("Call #1");
        try {
            int r1 = HibernateUtil.getSession().createQuery("update Client set excludeSms = true where telephone like '" + phoneNumber + "'").executeUpdate();
            System.out.println("r1 = " + r1);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Call #2");
            int r2 = HibernateUtil.getSession().createQuery("update Client set exclude_sms = 1 where telephone like '" + phoneNumber + "'").executeUpdate();
            System.out.println("r2 = " + r2);
        } 
    }
    
    public void updateToken(Integer mobileClientId, String token, String pushToken, Integer version, String lang, String os) {
        StringBuilder query = new StringBuilder("update mobile_client set token = '" + token + "', last_login = now()");
        
        if(pushToken != null){
            query.append(", push_token = '" + pushToken + "'");
        }
        
        if(version != null){
            query.append(", version = " + version);
        }
        
        if(lang != null){
            query.append(", lang = '" + lang + "'");
        }
         
        if(os != null){
            query.append(", os = '" + os + "'");
        }
        
        query.append( " where id = " + mobileClientId );
        
        System.out.println("MobileClientDAO.updateToken:: query = " + query);
        
        HibernateUtil.getSession().createSQLQuery( query.toString() ).executeUpdate();
    }
    
    //TODO when the new app be stable, we should also validate the clientID, 
    //and send the clientID in the header of every request
    public Boolean validateToken(String token) {
       Date lastLogin = (Date)HibernateUtil.getSession().createSQLQuery("select last_login from mobile_client where token = '" + token + "'" ).uniqueResult();
       
       System.out.println("[MobileClientDao.validateToken] lastLogin = " + lastLogin);
       
       if(lastLogin != null){ 
            Date now = new Date(); 
            boolean isActive = (now.getTime() - lastLogin.getTime()) < 30 * 60 * 1000;
            
            if(isActive){
                HibernateUtil.getSession().createSQLQuery("update mobile_client set last_login = now() where token = '" + token + "'").executeUpdate();
            }
            return isActive;
       }else{
           return false;
       } 
    }
     
}
