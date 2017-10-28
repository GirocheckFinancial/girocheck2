/*
 * Copyright @ 2004-2014 Smart Business Technology, Inc.
 *
 * All rights reserved. No part of this software may be 
 * reproduced, transmitted, transcribed, stored in a retrieval
 * system, or translated into any language or computer language, 
 * in any form or by any means, electronic, mechanical, magnetic,  
 * optical, chemical, manual or otherwise, without the prior  
 * written permission of Smart Business Technology, Inc.  
 *
 *
 */
package com.smartbt.girocheck.servercommon.manager;

import com.smartbt.girocheck.servercommon.dao.MobileNotificationDao;
import com.smartbt.girocheck.servercommon.display.mobile.MobileNotificationDisplay;
import java.util.List;

/**
 *
 * @author Roberto Rodriguez :: <roberto.rodriguez@smartbt.com>
 */
public class MobileNotificationManager {
    public static MobileNotificationManager INSTANCE; 
    
       public static MobileNotificationManager get() {
        if ( INSTANCE == null ) {
            INSTANCE = new MobileNotificationManager();
        }
        return INSTANCE;
    }

    public List<MobileNotificationDisplay> listMobileNotifications(Integer mobileClientId){
        List<MobileNotificationDisplay>  list = MobileNotificationDao.get().listMobileNotifications(mobileClientId);
        MobileNotificationDao.get().markNotificationsAsRead( mobileClientId);
        return list;
    }
    
    public Long countUnreadNotifications(Integer mobileClientId){
        return MobileNotificationDao.get().countUnreadNotifications(mobileClientId);
    }
     
    
    public void deleteNotificationsById(Integer id) { 
         MobileNotificationDao.get().deleteNotificationsById(id);
    }
}
