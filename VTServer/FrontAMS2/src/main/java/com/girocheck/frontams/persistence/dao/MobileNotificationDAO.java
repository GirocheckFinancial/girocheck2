package com.girocheck.frontams.persistence.dao;

import com.girocheck.frontams.common.dao.AbstractBaseDAO;
import com.girocheck.frontams.persistence.dto.MobileNotificationDTO; 
import com.smartbt.girocheck.servercommon.model.MobileNotification; 
import org.springframework.stereotype.Repository;

@Repository
public class MobileNotificationDAO extends AbstractBaseDAO<MobileNotification, MobileNotificationDTO> {
  
    //Pure Client is the one that is not mobile client
    public void addNotification(int mobileClient, String title, String text){  
        getSession().createSQLQuery("insert into mobile_notification (title,text,mobile_client,creation_date) VALUES ('" + title + "', '" + text +"', " + mobileClient + ", now())" ).executeUpdate();
    }
  
}
