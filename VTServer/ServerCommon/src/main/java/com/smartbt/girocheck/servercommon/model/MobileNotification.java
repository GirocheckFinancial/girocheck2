package com.smartbt.girocheck.servercommon.model;

import java.io.Serializable; 
/**
 *
 * @author suresh
 */

//@XmlRootElement
public class MobileNotification implements Serializable { 
     private int id;
    
     private com.smartbt.girocheck.servercommon.model.MobileClient mobileClient;
    
     private String title; 
     private String text;
     private Boolean seenByUser;   
     private java.util.Date creationDate;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the mobileClient
     */
    public com.smartbt.girocheck.servercommon.model.MobileClient getMobileClient() {
        return mobileClient;
    }

    /**
     * @param mobileClient the mobileClient to set
     */
    public void setMobileClient(com.smartbt.girocheck.servercommon.model.MobileClient mobileClient) {
        this.mobileClient = mobileClient;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the seenByUser
     */
    public Boolean getSeenByUser() {
        return seenByUser;
    }

    /**
     * @param seenByUser the seenByUser to set
     */
    public void setSeenByUser(Boolean seenByUser) {
        this.seenByUser = seenByUser;
    }

    /**
     * @return the creationDate
     */
    public java.util.Date getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate the creationDate to set
     */
    public void setCreationDate(java.util.Date creationDate) {
        this.creationDate = creationDate;
    }
      
     
}