/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.girocheck.servercommon.dao;

import com.smartbt.girocheck.servercommon.display.mobile.MobileNotificationDisplay;
import com.smartbt.girocheck.servercommon.model.MobileClient;
import com.smartbt.girocheck.servercommon.model.MobileNotification;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

/**
 *
 * @author Roberto
 */
public class MobileNotificationDao extends BaseDAO<MobileClient> {

    protected static MobileNotificationDao dao;

    public MobileNotificationDao() {
    }

    public static MobileNotificationDao get() {
        if (dao == null) {
            dao = new MobileNotificationDao();
        }
        return dao;
    }

    public List<MobileNotificationDisplay> listMobileNotifications(Integer mobileClientId) {

        Criteria criteria = HibernateUtil.getSession().createCriteria(MobileNotification.class)
                .createAlias("mobileClient", "mobileClient")
                .add(Restrictions.eq("mobileClient.id", mobileClientId))
                .addOrder(Order.desc("creationDate"))
                .setMaxResults(10);

        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("id").as("id"))
                .add(Projections.property("title").as("title"))
                .add(Projections.property("text").as("text"))
                .add(Projections.property("seenByUser").as("seenByUser"))
                .add(Projections.property("creationDate").as("creationDate"));

        criteria.setProjection(projectionList);
        criteria.setResultTransformer(Transformers.aliasToBean(MobileNotificationDisplay.class));

        return criteria.list();
    }

    public Long countUnreadNotifications(Integer mobileClientId){
        return (Long)HibernateUtil.getSession().createCriteria(MobileNotification.class)
                .createAlias("mobileClient", "mobileClient")
                .add(Restrictions.eq("mobileClient.id", mobileClientId))
                .add(Restrictions.eq("seenByUser", false))
                .setProjection(Projections.rowCount())
                .uniqueResult();
    }
    
    public void markNotificationsAsRead(Integer mobileClientId) { 
        StringBuilder query = new StringBuilder("update mobile_notification set seen_by_user = true where mobile_client = " + mobileClientId);
         
        HibernateUtil.getSession().createSQLQuery( query.toString() ).executeUpdate();
    }
    
    public void deleteNotificationsById(Integer id) { 
        StringBuilder query = new StringBuilder("delete from mobile_notification  where id = " + id);
         
        HibernateUtil.getSession().createSQLQuery( query.toString() ).executeUpdate();
    }
    
}
