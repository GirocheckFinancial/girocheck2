package com.smartbt.girocheck.servercommon.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Roberto
 */
public class IDImagePng implements Serializable {

    private int id;
    private int client;
    private Date createdAt;
    private java.sql.Blob idFront; 
    private java.sql.Blob idBack;

    public IDImagePng() {
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
     * @return the client
     */
    public int getClient() {
        return client;
    }

    /**
     * @param client the client to set
     */
    public void setClient(int client) {
        this.client = client;
    }

    /**
     * @return the createdAt
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt the createdAt to set
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return the idFront
     */
    public java.sql.Blob getIdFront() {
        return idFront;
    }

    /**
     * @param idFront the idFront to set
     */
    public void setIdFront(java.sql.Blob idFront) {
        this.idFront = idFront;
    }

    /**
     * @return the idBack
     */
    public java.sql.Blob getIdBack() {
        return idBack;
    }

    /**
     * @param idBack the idBack to set
     */
    public void setIdBack(java.sql.Blob idBack) {
        this.idBack = idBack;
    }
}
