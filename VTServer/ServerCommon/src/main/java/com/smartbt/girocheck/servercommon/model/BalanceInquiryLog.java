/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 *
 * This is an automatic generated file. It will be regenerated every time you
 * generate persistence class.
 *
 * Modifying its content may cause the program not work, or your work may lost.
 */
/**
 * Licensee: License Type: Evaluation
 */
package com.smartbt.girocheck.servercommon.model;

import java.io.Serializable;
import java.util.Date;

public class BalanceInquiryLog implements Serializable {

    public BalanceInquiryLog() {
    }

    private int id;

    private Integer resultCode;

    private String resultMessage;

    private Date dateTime;

    private com.smartbt.girocheck.servercommon.model.CreditCard data_sc1;

    private String errorCode;

    private String last4cc;

    private Double amount;

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
     * @return the resultCode
     */
    public Integer getResultCode() {
        return resultCode;
    }

    /**
     * @param resultCode the resultCode to set
     */
    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    /**
     * @return the resultMessage
     */
    public String getResultMessage() {
        return resultMessage;
    }

    /**
     * @param resultMessage the resultMessage to set
     */
    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    /**
     * @return the dateTime
     */
    public Date getDateTime() {
        return dateTime;
    }

    /**
     * @param dateTime the dateTime to set
     */
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * @return the data_sc1
     */
    public com.smartbt.girocheck.servercommon.model.CreditCard getData_sc1() {
        return data_sc1;
    }

    /**
     * @param data_sc1 the data_sc1 to set
     */
    public void setData_sc1(com.smartbt.girocheck.servercommon.model.CreditCard data_sc1) {
        this.data_sc1 = data_sc1;
    }

    /**
     * @return the errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode the errorCode to set
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @return the last4cc
     */
    public String getLast4cc() {
        return last4cc;
    }

    /**
     * @param last4cc the last4cc to set
     */
    public void setLast4cc(String last4cc) {
        this.last4cc = last4cc;
    }
 

    /**
     * @return the amount
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

}
