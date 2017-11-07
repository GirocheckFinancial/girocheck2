/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.girocheck.servercommon.dao;

import com.smartbt.girocheck.servercommon.dao.BaseDAO;
import com.smartbt.girocheck.servercommon.display.mobile.MobileClientDisplay;
import com.smartbt.girocheck.servercommon.model.MobileClient;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

/**
 *
 * @author Roberto
 */
public class MobileClientDao extends BaseDAO<MobileClient> {

    private static String DECRYPT_SSN = " AES_DECRYPT(FROM_BASE64(c.ssn), SHA2(SHA2(CONCAT((SELECT concat(c.reference, c.sessions, c.types, c.mode) FROM configs c), 'SELECT * FROM configs'), 512), 512)) ";
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
                .add(Projections.property("allowNotifications").as("allowNotifications"))
                .add(Projections.property("card.cardNumber").as("card"))
                .add(Projections.property("client.firstName").as("clientName"))
                .add(Projections.property("client.email").as("clientEmail"))
                .add(Projections.property("client.telephone").as("clientPhone"))
                .add(Projections.property("client.excludeSms").as("excludeSMS"))
                .add(Projections.property("userName").as("mobileClientUserName"));

        criteria.setProjection(projectionList);
        criteria.setResultTransformer(Transformers.aliasToBean(MobileClientDisplay.class));

        MobileClientDisplay result = (MobileClientDisplay) criteria.uniqueResult();

        if (result != null) {
            updateToken(result.getClientId(), token, pushToken, version, lang, os);
            result.setUnreadNotifications(MobileNotificationDao.get().countUnreadNotifications(result.getClientId()));
         }

        
        return result;
    }

    public String getCardNumberByMobileClientId(Integer mobileClientId) {
        return (String) HibernateUtil.getSession().createCriteria(MobileClient.class)
                .createAlias("card", "card")
                .add(Restrictions.eq("id", mobileClientId))
                .setProjection(Projections.property("card.cardNumber"))
                .uniqueResult();
    }

    public MobileClientDisplay getMobileClientById(int mobileclientid) {
        String query = "SELECT m.password as password, m.id as id, client as clientId  FROM mobile_client m WHERE m.id = :mobileclientid";

        System.out.println("getMobileClientById.. query = ");
        System.out.println( query );
        
        return (MobileClientDisplay) HibernateUtil.getSession().createSQLQuery(query)
                .setParameter("mobileclientid", mobileclientid)
                .setResultTransformer(Transformers.aliasToBean(MobileClientDisplay.class))
                .uniqueResult();
    }

    public MobileClientDisplay getMobileClientByClient(int clientId) {
        String query = "select allow_notifications as allowNotifications, push_token as pushToken, device_type as deviceType, lang from mobile_client where client = :clientid";

        return (MobileClientDisplay) HibernateUtil.getSession().createSQLQuery(query)
                .setParameter("clientid", clientId)
                .setResultTransformer(Transformers.aliasToBean(MobileClientDisplay.class))
                .uniqueResult();
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

    public MobileClientDisplay getMobileClientByCardNumberAndMaskSSN(String maskSSN, String cardNumber) {
        StringBuilder sb = new StringBuilder("SELECT ");
        sb.append("m.id as id, m.forgot_password_key as forgotPasswordKey, key_expiration_time as keyExpirationTime ");
        sb.append(", c.telephone as clientPhone, c.email as clientEmail, m.username as mobileClientUserName ");
        sb.append(" from (mobile_client m INNER JOIN client c  ON m.client = c.id ) INNER JOIN card a ON a.client = c.id WHERE ");
        sb.append(" AES_DECRYPT(FROM_BASE64(a.data_s), SHA2(SHA2(CONCAT((SELECT concat(c.mode, c.reference, c.sessions, c.types) FROM configs c), 'SELECT * FROM configs'), 512), 512)) = :card ");
        sb.append(" AND c.mask_ssn = :maskssn limit 1 ");

        return (MobileClientDisplay) HibernateUtil.getSession().createSQLQuery(sb.toString())
                .setParameter("card", cardNumber)
                .setParameter("maskssn", maskSSN)
                .setResultTransformer(Transformers.aliasToBean(MobileClientDisplay.class))
                .uniqueResult();
    }

    public MobileClient getMobileClientByTelphone(String recipentNumber) {

        Criteria criteria = HibernateUtil.getSession().createCriteria(MobileClient.class)
                .createAlias("client", "client")
                .add(Restrictions.eq("client.telephone", recipentNumber))
                .setMaxResults(1);

        return (MobileClient) criteria.uniqueResult();
    }

    public void updateExcludeSMS(String phoneNumber, Boolean excludeSMS) {
        try {
            HibernateUtil.getSession().createQuery("update Client set excludeSms = " + excludeSMS + " where telephone like '" + phoneNumber + "'").executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateToken(Integer mobileClientId, String token, String pushToken, Integer version, String lang, String os) {
        StringBuilder query = new StringBuilder("update mobile_client set token = :token, last_login = now()");

        if (pushToken != null) {
            query.append(", push_token = :push_token ");
        }

        if (version != null) {
            query.append(", version = :version ");
        }

        if (lang != null) {
            query.append(", lang = :lang ");
        }

        if (os != null) {
            query.append(", device_type = :device_type ");
        }

        query.append(" where id = :id ");

        System.out.println("MobileClientDAO.updateToken:: query = " + query);

        SQLQuery sqlQuery = HibernateUtil.getSession().createSQLQuery(query.toString());
        sqlQuery.setParameter("token", token);
        sqlQuery.setParameter("id", mobileClientId);

        if (pushToken != null) {
            sqlQuery.setParameter("push_token", pushToken);
        }

        if (version != null) {
            sqlQuery.setParameter("version", version);
        }

        if (lang != null) {
            sqlQuery.setParameter("lang", lang);
        }

        if (os != null) {
            sqlQuery.setParameter("device_type", os);
        }

        sqlQuery.executeUpdate();
    }

    //TODO when the new app be stable, we should also validate the clientID, 
    //and send the clientID in the header of every request
//    public Boolean validateToken(String token) {
//       Date lastLogin = (Date)HibernateUtil.getSession().createSQLQuery("select last_login from mobile_client where token = '" + token + "'" ).uniqueResult();
//       
//       System.out.println("[MobileClientDao.validateToken] lastLogin = " + lastLogin);
//       
//       if(lastLogin != null){ 
//            Date now = new Date(); 
//            boolean isActive = (now.getTime() - lastLogin.getTime()) < 30 * 60 * 1000;
//            
//            if(isActive){
//                HibernateUtil.getSession().createSQLQuery("update mobile_client set last_login = now() where token = '" + token + "'").executeUpdate();
//            }
//            return isActive;
//       }else{
//           return false;
//       } 
//    }
    public void updateAllowNotifications(int mobileClientId, Boolean allowNotifications) {
        String query = "update mobile_client set allow_notifications = " + allowNotifications + " where id = " + mobileClientId;
        HibernateUtil.getSession().createSQLQuery(query).executeUpdate();
    }

    public void updateExcludeSMSFromClientByMobileClientId(int mobileClientId, Boolean excludeSMS) {
        String query = "update client set exclude_sms = :exclude where id = (select client from mobile_client where id = :mobileclientid)";
        HibernateUtil.getSession().createSQLQuery(query)
                .setParameter("exclude", excludeSMS)
                .setParameter("mobileclientid", mobileClientId)
                .executeUpdate();
    }

    public void resetPassword(int mobileClientId, String password, String token) {
        String query = "update mobile_client set password = :password, token = :token, last_login = now() where id = :id";
        HibernateUtil.getSession().createSQLQuery(query)
                .setParameter("password", password)
                .setParameter("token", token)
                .setParameter("id", mobileClientId)
                .executeUpdate();
    }

    public void setCardToMobileClient(int mobileClientId, int cardId) {
        String query = "update mobile_client set card = :cardid where id = :id";
        HibernateUtil.getSession().createSQLQuery(query)
                .setParameter("cardid", cardId)
                .setParameter("id", mobileClientId)
                .executeUpdate();
    }

    public void updateMobileClientUsername(int mobileClientId, String username) {
        String query = "update mobile_client set username = :username where id = :id";
        HibernateUtil.getSession().createSQLQuery(query)
                .setParameter("username", username)
                .setParameter("id", mobileClientId)
                .executeUpdate();
    }

    public void updateMobileClientPassword(int mobileClientId, String password) {
        String query = "update mobile_client set password = :password where id = :id";
        HibernateUtil.getSession().createSQLQuery(query)
                .setParameter("password", password)
                .setParameter("id", mobileClientId)
                .executeUpdate();
    }

    public void updateClientEmailAndTelephone(int clientId, String email, String telephone) {
        String query = "update client set telephone = :telephone, email = :email where id = :id";
        HibernateUtil.getSession().createSQLQuery(query)
                .setParameter("email", email)
                .setParameter("telephone", telephone)
                .setParameter("id", clientId)
                .executeUpdate();
    }

    public void updateForgotPasswordKey(Integer mobileClientId, String accessCode) {
        StringBuilder query = new StringBuilder("update mobile_client set forgot_password_key = :accesscode, key_expiration_time = now() WHERE id = :mobileclientid");

        HibernateUtil.getSession().createSQLQuery(query.toString())
                .setParameter("accesscode", accessCode)
                .setParameter("mobileclientid", mobileClientId)
                .executeUpdate();
    }

}
