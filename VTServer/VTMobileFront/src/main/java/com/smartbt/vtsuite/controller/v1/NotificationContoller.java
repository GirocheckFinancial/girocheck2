/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.controller.v1;

import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import com.smartbt.girocheck.servercommon.display.mobile.MobileNotificationDisplay;
import com.smartbt.girocheck.servercommon.manager.MobileNotificationManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author suresh
 */
@RestController
@RequestMapping("/v1/notification")
public class NotificationContoller { 

    @RequestMapping(value = "/listNotifications", method = RequestMethod.GET)
    public List<MobileNotificationDisplay>  list(HttpServletRequest request, HttpSession session) throws Exception {
        String clientIdStr = (String) request.getParameter("clientId"); 
        
        Integer clientId = Integer.parseInt(clientIdStr);
        return MobileNotificationManager.get().listMobileNotifications(clientId);
    }
    
     
    @RequestMapping(value = "/deleteNotificationsById", method = RequestMethod.GET)
    public ResponseData  deleteNotificationsById(HttpServletRequest request, HttpSession session) throws Exception {
        String idStr = (String) request.getParameter("id");  
        Integer id = Integer.parseInt(idStr);
        MobileNotificationManager.get().deleteNotificationsById(id);
        
        return ResponseData.OK();
    }
     
}
