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

public class TransactionReportDTO implements Serializable {

    private static final DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

    public TransactionReportDTO() {
    }

    private int id;
    private String certegyApprovalNumber;
    private String merchant;
    private String terminal;
    private Integer transactionType;
    private String card;
    private String operation;
    private Double amount;
    private Double feeAmmount;
    private Double payoutAmmount;

    private String clientName;
    private String clientLastName;
    private String clientPhone;
    private String makerName;
    private String checkNumber;
    private String resultMessage;
    private Integer resultCode;

    private Date dateTime;

    private String address;
    private String state;
    private String city;
    private String zipcode;

    public String getFullAddress() {
        return address + ", " + city + ", " + state+ ", " + zipcode;
    }

    public String getClientFullName() {
        return getClientName() + " " + getClientLastName();
    }

    public String getDateStr() {
        if (getDateTime() != null) {
            try {
                return df.format(getDateTime());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public String getTransactionTypeStr() {
        if (transactionType == null) {
            return "";
        }
        switch (getTransactionType()) {
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
        if (getFeeAmmount() == null || getFeeAmmount().equals("null")) {
            return "";
        } else {
            return getFeeAmmount() + "";
        }
    }

    public String getOperationStr() {
        if (operation != null && !operation.isEmpty()) {
            switch (operation) {
                case "01":
                    return "Check";
                case "02":
                    return "Cash";
                default:
                    return "";
            }
        } else {
            return "";
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
     * @return the certegyApprovalNumber
     */
    public String getCertegyApprovalNumber() {
        if(certegyApprovalNumber == null){
            return "";
        }
        return certegyApprovalNumber;
    }

    /**
     * @param certegyApprovalNumber the certegyApprovalNumber to set
     */
    public void setCertegyApprovalNumber(String certegyApprovalNumber) {
        this.certegyApprovalNumber = certegyApprovalNumber;
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
     * @return the terminal
     */
    public String getTerminal() {
        return terminal;
    }

    /**
     * @param terminal the terminal to set
     */
    public void setTerminal(String terminal) {
        this.terminal = terminal;
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
     * @return the makerName
     */
    public String getMakerName() {
        if(makerName == null) return "";
        return makerName;
    }

    /**
     * @param makerName the makerName to set
     */
    public void setMakerName(String makerName) {
        this.makerName = makerName;
    }

    /**
     * @return the checkNumber
     */
    public String getCheckNumber() {
        if(checkNumber == null) return "";
        return checkNumber;
    }

    /**
     * @param checkNumber the checkNumber to set
     */
    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
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
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the zipcode
     */
    public String getZipcode() {
        return zipcode;
    }

    /**
     * @param zipcode the zipcode to set
     */
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
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
}
