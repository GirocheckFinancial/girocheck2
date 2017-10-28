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
package com.girocheck.frontams.persistence.dto;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TransactionDTO implements Serializable {

    private static final DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

    public TransactionDTO() {
    }

    private int id;

    private String clientName;
    private String clientLastName;
    private int clientId;

    private String operation;

    private Integer resultCode;

    private String resultMessage;

    private Date dateTime;

    private String card;
    private int cardId;

    private Integer transactionType;

    private Double amount;
    private Double feeAmmount;
    private Double payoutAmmount;

    private String merchant;
    private int merchantId;
    private Boolean completed;

    public String getClientFullName() {
        return clientName + " " + clientLastName;
    }

    public String getDateStr() {
        if (dateTime != null) {
            try {
                return df.format(dateTime);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public String getTransactionTypeStr() {
        switch (transactionType) {
            case 1:
                return "New Card Load";
            case 2:
                return "Card Reload";
            case 3:
                return "Card to Bank";
            default:
                return "New Card Load";
        }
    }
    
    

    public String getFeeStr() {
        if (feeAmmount == null || feeAmmount.equals("null")) {
            return "";
        } else {
            return feeAmmount + "";
        }
    }

    public String getResultStr() {
        if (resultMessage != null && resultMessage.equalsIgnoreCase("Success")) {
            return "Success";
        } else {
            return "Failed";
        }
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
     * @return the clientId
     */
    public int getClientId() {
        return clientId;
    }

    /**
     * @param clientId the clientId to set
     */
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    /**
     * @return the operation
     */
    public String getOperation() {
        return operation;
    }

    /**
     * @param operation the operation to set
     */
    public void setOperation(String operation) {
        this.operation = operation;
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
     * @return the card
     */
    public String getCard() {
        if (card != null && card.length() > 8) {
            return card.substring(8);
        } else {
            return card;
        }

    }

    /**
     * @param card the card to set
     */
    public void setCard(String card) {
        this.card = card;
    }

    /**
     * @return the transactionType
     */
    public Integer getTransactionType() {
        return transactionType;
    }

    /**
     * @param transactionType the transactionType to set
     */
    public void setTransactionType(Integer transactionType) {
        this.transactionType = transactionType;
    }

    /**
     * @return the feeAmmount
     */
    public Double getFeeAmmount() {
        return feeAmmount;
    }

    /**
     * @param feeAmmount the feeAmmount to set
     */
    public void setFeeAmmount(Double feeAmmount) {
        this.feeAmmount = feeAmmount;
    }

    /**
     * @return the payoutAmmount
     */
    public Double getPayoutAmmount() {
        return payoutAmmount;
    }

    /**
     * @param payoutAmmount the payoutAmmount to set
     */
    public void setPayoutAmmount(Double payoutAmmount) {
        this.payoutAmmount = payoutAmmount;
    }

    /**
     * @return the merchant
     */
    public String getMerchant() {
        return merchant;
    }

    /**
     * @param merchant the merchant to set
     */
    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    /**
     * @return the merchantId
     */
    public int getMerchantId() {
        return merchantId;
    }

    /**
     * @param merchantId the merchantId to set
     */
    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * @return the cardId
     */
    public int getCardId() {
        return cardId;
    }

    /**
     * @param cardId the cardId to set
     */
    public void setCardId(int cardId) {
        this.cardId = cardId;
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
     * @return the clientLastName
     */
    public String getClientLastName() {
        return clientLastName;
    }

    /**
     * @param clientLastName the clientLastName to set
     */
    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
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

    /**
     * @return the completed
     */
    public Boolean getCompleted() {
        return completed;
    }

    /**
     * @param completed the completed to set
     */
    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

}
