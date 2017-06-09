package com.smartbt.girocheck.servercommon.model;

import java.io.Serializable; 
import java.util.Date;

/**
 *
 * @author Roberto
 */


public class CheckBlacklistRule implements Serializable{
    
    private int id;
    private String dda;
    private String aba;
    private String maker;
    private Date lastUpdated;
    
    public CheckBlacklistRule(){}
    
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
     * @return the dda
     */
    public String getDda() {
        return dda;
    }

    /**
     * @param dda the dda to set
     */
    public void setDda(String dda) {
        this.dda = dda;
    }

    /**
     * @return the aba
     */
    public String getAba() {
        return aba;
    }

    /**
     * @param aba the aba to set
     */
    public void setAba(String aba) {
        this.aba = aba;
    }

    /**
     * @return the maker
     */
    public String getMaker() {
        return maker;
    }

    /**
     * @param maker the maker to set
     */
    public void setMaker(String maker) {
        this.maker = maker;
    }

    /**
     * @return the lastUpdated
     */
    public Date getLastUpdated() {
        return lastUpdated;
    }

    /**
     * @param lastUpdated the lastUpdated to set
     */
    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    
}
