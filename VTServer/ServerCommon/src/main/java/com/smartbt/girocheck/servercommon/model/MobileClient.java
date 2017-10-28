package com.smartbt.girocheck.servercommon.model;
 
import java.sql.Timestamp;
import java.util.Date; 
/**
 *
 * @author suresh
 */

//@XmlRootElement
public class MobileClient extends BaseEntity{
    public MobileClient(){
        
    }
    
     private int id;
    
     private com.smartbt.girocheck.servercommon.model.Client client;
    
     private String deviceType;
    
     private String forgotPasswordKey;
     
     private com.smartbt.girocheck.servercommon.model.CreditCard card;
     
     private java.util.Date registrationDate;
     
     private java.util.Date keyExpirationTime;
     
     private String userName;
     
     private String password;  
     private String token;  
     private String pushToken;  
     private Integer version;  
     private String lang;   
     private Date lastLogin;  
     private Boolean allowNotifications;  
     
      


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
     * @return the deviceType
     */
    public String getDeviceType() {
        return deviceType;
    }

    /**
     * @param deviceType the deviceType to set
     */
    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    /**
     * @return the ForgotPasswordKey
     */
    public String getForgotPasswordKey() {
        return forgotPasswordKey;
    }

    /**
     * @param forgotPasswordKey the ForgotPasswordKey to set
     */
    public void setForgotPasswordKey(String forgotPasswordKey) {
        this.forgotPasswordKey = forgotPasswordKey;
    }

    /**
     * @return the card
     */
    public com.smartbt.girocheck.servercommon.model.CreditCard getCard() {
        return card;
    }

    /**
     * @param card the card to set
     */
    public void setCard(com.smartbt.girocheck.servercommon.model.CreditCard card) {
        this.card = card;
    }

    

    /**
     * @param registrationDate the registrationDate to set
     */
    public void setRegistrationDate(Timestamp registrationDate) {
        this.setRegistrationDate(registrationDate);
    }

    /**
     * @return the client
     */
    public com.smartbt.girocheck.servercommon.model.Client getClient() {
        return client;
    }

    /**
     * @param client the client to set
     */
    public void setClient(com.smartbt.girocheck.servercommon.model.Client client) {
        this.client = client;
    }

    
    

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
 
    /**
     * @return the registrationDate
     */
    public java.util.Date getRegistrationDate() {
        return registrationDate;
    }

    /**
     * @param registrationDate the registrationDate to set
     */
    public void setRegistrationDate(java.util.Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    /**
     * @return the keyExpirationTime
     */
    public java.util.Date getKeyExpirationTime() {
        return keyExpirationTime;
    }

    public void setKeyExpirationTime(Date keyExpirationTime) {
        this.keyExpirationTime = keyExpirationTime;
    }   

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return the lastLogin
     */
    public Date getLastLogin() {
        return lastLogin;
    }

    /**
     * @param lastLogin the lastLogin to set
     */
    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    /**
     * @return the pushToken
     */
    public String getPushToken() {
        return pushToken;
    }

    /**
     * @param pushToken the pushToken to set
     */
    public void setPushToken(String pushToken) {
        this.pushToken = pushToken;
    }

    
    /**
     * @return the lang
     */
    public String getLang() {
        return lang;
    }

    /**
     * @param lang the lang to set
     */
    public void setLang(String lang) {
        this.lang = lang;
    }

    /**
     * @return the allowNotifications
     */
    public Boolean getAllowNotifications() {
        return allowNotifications;
    }

    /**
     * @param allowNotifications the allowNotifications to set
     */
    public void setAllowNotifications(Boolean allowNotifications) {
        this.allowNotifications = allowNotifications;
    }
 

    /**
     * @return the version
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(Integer version) {
        this.version = version;
    }
     
    
}
