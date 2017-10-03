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
public enum PushNotificationMessages {
    WELCOME_TITLE("Welcome to VoltCash", "Bienvenido a VoltCash"),
    WELCOME_TEXT(" we are pleased to have you as a new VoltCash customer.", " nos complace tenerlo como nuevo cliente de VoltCash."),
    
    CARD_LOAD_TITLE("VoltCash card load", "VoltCash monto adicionado"),
    CARD_LOAD_TEXT("An amount of [amount] was added to your ColtCash credit card", "Un monto de [amount] fue adicionado a su tarjeta VoltCash"),
    
    NEW_MESSAGE_TITLE("New VoltCash message", "Nuevo mensaje de VoltCash");
   
    private String en;
    private String es;

    private PushNotificationMessages(String en, String es){
        this.es = es;
        this.en = en;
    }
    
    public String get(String lang){
        if(lang == null || lang.toUpperCase().startsWith("EN")){
            return en;
        }else{
            return es;
        }
    }
}
