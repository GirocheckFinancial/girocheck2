package com.smartbt.girocheck.servercommon.display.mobile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author rrodriguez
 */
public class MobileClientDisplay implements Serializable {

    @JsonIgnore
    private Integer id; 
    @JsonIgnore
    private String card;
//    @JsonIgnore
//    private String ssn;
    @JsonIgnore
    private String pushToken;
    @JsonIgnore
    private String deviceType;
    @JsonIgnore
    private String lang;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private String forgotPasswordKey;
    @JsonIgnore
    private Date keyExpirationTime;
    
    private Integer clientId;
    private String token;
    private String balance;
    private String clientName;
    private String clientEmail;
    private String clientPhone;
    private String mobileClientUserName;
    private Long unreadNotifications;
    private Boolean allowNotifications;
    private Integer lastMobileVersion = 3;
    private Boolean excludeSMS;

    /**
     * @return the card
     */
    public String getCard() {
        return card;
    }

    /**
     * @param card the card to set
     */
    public void setCard(String card) {
        this.card = card;
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
     * @return the balance
     */
    public String getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(String balance) {
        this.balance = balance;
    }

    /**
     * @return the clientId
     */
    public Integer getClientId() {
        return clientId;
    }

    /**
     * @param clientId the clientId to set
     */
    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    /**
     * @return the clientName
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * @param clientName the clientName to set
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    /**
     * @return the clientEmail
     */
    public String getClientEmail() {
        return clientEmail;
    }

    /**
     * @param clientEmail the clientEmail to set
     */
    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    /**
     * @return the clientPhone
     */
    public String getClientPhone() {
        return clientPhone;
    }

    /**
     * @param clientPhone the clientPhone to set
     */
    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    /**
     * @return the mobileClientUserName
     */
    public String getMobileClientUserName() {
        return mobileClientUserName;
    }

    /**
     * @param mobileClientUserName the mobileClientUserName to set
     */
    public void setMobileClientUserName(String mobileClientUserName) {
        this.mobileClientUserName = mobileClientUserName;
    }

    /**
     * @return the unreadNotifications
     */
    public Long getUnreadNotifications() {
        return unreadNotifications;
    }

    /**
     * @param unreadNotifications the unreadNotifications to set
     */
    public void setUnreadNotifications(Long unreadNotifications) {
        this.unreadNotifications = unreadNotifications;
    }

    /**
     * @return the lastMobileVersion
     */
    public Integer getLastMobileVersion() {
        return lastMobileVersion;
    }

    /**
     * @param lastMobileVersion the lastMobileVersion to set
     */
    public void setLastMobileVersion(Integer lastMobileVersion) {
        this.lastMobileVersion = lastMobileVersion;
    }

    /**
     * @return the excludeSMS
     */
    public Boolean getExcludeSMS() {
        return excludeSMS;
    }

    /**
     * @param excludeSMS the excludeSMS to set
     */
    public void setExcludeSMS(Boolean excludeSMS) {
        this.excludeSMS = excludeSMS;
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
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
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
     * @return the forgotPasswordKey
     */
    public String getForgotPasswordKey() {
        return forgotPasswordKey;
    }

    /**
     * @param forgotPasswordKey the forgotPasswordKey to set
     */
    public void setForgotPasswordKey(String forgotPasswordKey) {
        this.forgotPasswordKey = forgotPasswordKey;
    }

    /**
     * @return the keyExpirationTime
     */
    public Date getKeyExpirationTime() {
        return keyExpirationTime;
    }

    /**
     * @param keyExpirationTime the keyExpirationTime to set
     */
    public void setKeyExpirationTime(Date keyExpirationTime) {
        this.keyExpirationTime = keyExpirationTime;
    }

}
