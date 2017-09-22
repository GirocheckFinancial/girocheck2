/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.girocheck.servercommon.utils.pushNotification;

/**
 *
 * @author rrodriguez
 */
public class PushNotificationManager {
    
    public static void sendWelcomeMessage(String os, String pushToken, String lang, String clientName){
        String title = PushNotificationMessages.WELCOME_TITLE.get(lang);
        String msg = clientName + PushNotificationMessages.WELCOME_MSG.get(lang);
        
        PushNotificationsSender.sendMessage(os, pushToken, title, msg);
    } 
     
    public static void sendCardLoadMessage(String os, String pushToken, String lang, Double amount){
        String title = PushNotificationMessages.CARD_LOAD_TITLE.get(lang);
        String msg = PushNotificationMessages.CARD_LOAD_MSG.get(lang).replace("[amount]", amount + "");
        
        PushNotificationsSender.sendMessage(os, pushToken, title, msg);
    } 
    
}
