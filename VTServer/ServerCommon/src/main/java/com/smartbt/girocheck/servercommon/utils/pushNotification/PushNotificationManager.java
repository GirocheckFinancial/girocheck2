/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.girocheck.servercommon.utils.pushNotification;

import java.text.DecimalFormat;

/**
 *
 * @author rrodriguez
 */
public class PushNotificationManager {
    private static final DecimalFormat AMOUNT_FORMATTER = new DecimalFormat( "$###,###.##");
    
    public static void sendWelcomeMessage(String os, String pushToken, String lang, String clientName){
        String title = PushNotificationMessages.WELCOME_TITLE.get(lang);
        String msg = clientName + PushNotificationMessages.WELCOME_TEXT.get(lang);
        
        PushNotificationsSender.sendMessage(os, pushToken, title, msg);
    } 
     
    public static void sendCardLoadMessage(String os, String pushToken, String lang, Double amount){
        String title = PushNotificationMessages.CARD_LOAD_TITLE.get(lang);
        String formattedAmount = formatAmount(amount);
        String msg = PushNotificationMessages.CARD_LOAD_TEXT.get(lang).replace("[amount]", formattedAmount + "");
        
        PushNotificationsSender.sendMessage(os, pushToken, title, msg);
    } 
     
    public static boolean sendMessage(String os, String pushToken, String lang, String text){
        String title = PushNotificationMessages.NEW_MESSAGE_TITLE.get(lang); 
        
        return PushNotificationsSender.sendMessage(os, pushToken, title, text);
    } 
   
    
     private static String formatAmount( Double value ) {
         try{
             if(value != null){
             return AMOUNT_FORMATTER.format(value );
              }
         }catch(Exception e){}
         return value + "";
       
   }

   static public void main(String[] args) {
 
       System.out.println( formatAmount( 12345.67) );  
   }
}
