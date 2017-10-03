/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.girocheck.frontams.persistence.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map; 

/**
 *
 * @author rrodriguez
 */
 
public class MessageDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String params;
    private String esTitle;
    private String enTitle;
    private String esText;
    private String enText;
    
    private Integer pushNotificationsSent;
    private Integer pushNotificationsNotSent;
    private Integer messagesSent;
    private Integer messagesNotSent; 
    private String unsentClientNames = "";
     
    private Map<String, Object> paramsMap = new HashMap<>();
    
    public String getTitle(String lang){
        if(lang == null || lang.equalsIgnoreCase("en")){
            return enTitle;
        }else{
            return esTitle;
        }
    }

    public String getText(String lang){
          if(lang == null || lang.equalsIgnoreCase("en")){
            return enText;
        }else{
            return esText;
        }
    }
    
    public void addUnsentClientName(String clientName){
        if(unsentClientNames.isEmpty()){
            unsentClientNames = clientName;
        }else{
            unsentClientNames += ", " + clientName;
        }
    }
    /**
     * @return the params
     */
    public String getParams() {
        return params;
    }

    /**
     * @param params the params to set
     */
    public void setParams(String params) {
        this.params = params;
    }

    /**
     * @return the esTitle
     */
    public String getEsTitle() {
        return esTitle;
    }

    /**
     * @param esTitle the esTitle to set
     */
    public void setEsTitle(String esTitle) {
        this.esTitle = esTitle;
    }

    /**
     * @return the enTitle
     */
    public String getEnTitle() {
        return enTitle;
    }

    /**
     * @param enTitle the enTitle to set
     */
    public void setEnTitle(String enTitle) {
        this.enTitle = enTitle;
    }

    /**
     * @return the esText
     */
    public String getEsText() {
        return esText;
    }

    /**
     * @param esText the esText to set
     */
    public void setEsText(String esText) {
        this.esText = esText;
    }

    /**
     * @return the enText
     */
    public String getEnText() {
        return enText;
    }

    /**
     * @param enText the enText to set
     */
    public void setEnText(String enText) {
        this.enText = enText;
    }

    /**
     * @return the paramsMap
     */
    public Map<String, Object> getParamsMap() {
        return paramsMap;
    }

    /**
     * @param paramsMap the paramsMap to set
     */
    public void setParamsMap(Map<String, Object> paramsMap) {
        this.paramsMap = paramsMap;
    }

    /**
     * @return the pushNotificationsSent
     */
    public Integer getPushNotificationsSent() {
        return pushNotificationsSent;
    }

    /**
     * @param pushNotificationsSent the pushNotificationsSent to set
     */
    public void setPushNotificationsSent(Integer pushNotificationsSent) {
        this.pushNotificationsSent = pushNotificationsSent;
    }

    /**
     * @return the pushNotificationsNotSent
     */
    public Integer getPushNotificationsNotSent() {
        return pushNotificationsNotSent;
    }

    /**
     * @param pushNotificationsNotSent the pushNotificationsNotSent to set
     */
    public void setPushNotificationsNotSent(Integer pushNotificationsNotSent) {
        this.pushNotificationsNotSent = pushNotificationsNotSent;
    }

    /**
     * @return the messagesSent
     */
    public Integer getMessagesSent() {
        return messagesSent;
    }

    /**
     * @param messagesSent the messagesSent to set
     */
    public void setMessagesSent(Integer messagesSent) {
        this.messagesSent = messagesSent;
    }

    /**
     * @return the unsentClientNames
     */
    public String getUnsentClientNames() {
        return unsentClientNames;
    }

    /**
     * @param unsentClientNames the unsentClientNames to set
     */
    public void setUnsentClientNames(String unsentClientNames) {
        this.unsentClientNames = unsentClientNames;
    }
 

    /**
     * @return the messagesNotSent
     */
    public Integer getMessagesNotSent() {
        return messagesNotSent;
    }

    /**
     * @param messagesNotSent the messagesNotSent to set
     */
    public void setMessagesNotSent(Integer messagesNotSent) {
        this.messagesNotSent = messagesNotSent;
    }

  
}
