/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: 
 * License Type: Evaluation
 */
package com.girocheck.frontams.persistence.dto;

import java.io.Serializable;
public class ClientDTO implements Serializable {
	public ClientDTO() {
	}
	
	private int id;
	 
	private String firstName;
	
	private String lastName;
	
	private String telephone;
	
	private String email;
	
	private Boolean active;
        
        private java.util.Date createdAt;
	  
	private Integer successfulLoads;
	  
	private String maskSSN; 
        
	private Boolean blacklistCard2bank; 
        
        private Boolean blackListAll;
	
	

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
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * @param telephone the telephone to set
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the active
     */
    public Boolean getActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(Boolean active) {
        this.active = active;
    }

    /**
     * @return the blacklistCard2bank
     */
    public Boolean getBlacklistCard2bank() {
        return blacklistCard2bank;
    }

    /**
     * @param blacklistCard2bank the blacklistCard2bank to set
     */
    public void setBlacklistCard2bank(Boolean blacklistCard2bank) {
        this.blacklistCard2bank = blacklistCard2bank;
    }

    /**
     * @return the blackListAll
     */
    public Boolean getBlackListAll() {
        return blackListAll;
    }

    /**
     * @param blackListAll the blackListAll to set
     */
    public void setBlackListAll(Boolean blackListAll) {
        this.blackListAll = blackListAll;
    }

    /**
     * @return the createdAt
     */
    public java.util.Date getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt the createdAt to set
     */
    public void setCreatedAt(java.util.Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return the successfulLoads
     */
    public Integer getSuccessfulLoads() {
        return successfulLoads;
    }

    /**
     * @param successfulLoads the successfulLoads to set
     */
    public void setSuccessfulLoads(Integer successfulLoads) {
        this.successfulLoads = successfulLoads;
    }

    /**
     * @return the maskSSN
     */
    public String getMaskSSN() {
        return maskSSN;
    }

    /**
     * @param maskSSN the maskSSN to set
     */
    public void setMaskSSN(String maskSSN) {
        this.maskSSN = maskSSN;
    }
	 
        
	 }
