 
package com.smartbt.girocheck.servercommon.model;

import java.util.Date;

/**
 *
 * @author rrodriguez
 */


public class IdeologyResultInfo extends BaseEntity{
    private int id;
    private String key; 
    private String message; 
    private Integer type;
    private IdeologyResult ideologyResult;

    public IdeologyResultInfo() {
    }
    
    

    public IdeologyResultInfo( String key, String message, IdeologyResult ideologyResult, Integer type) { 
        this.key = key;
        this.message = message;
        this.type = type;
        this.ideologyResult = ideologyResult;
    }

    
    
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
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the ideologyResult
     */
    public IdeologyResult getIdeologyResult() {
        return ideologyResult;
    }

    /**
     * @param ideologyResult the ideologyResult to set
     */
    public void setIdeologyResult(IdeologyResult ideologyResult) {
        this.ideologyResult = ideologyResult;
    }
 
    /**
     * @return the type
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(Integer type) {
        this.type = type;
    }
    
    
}
