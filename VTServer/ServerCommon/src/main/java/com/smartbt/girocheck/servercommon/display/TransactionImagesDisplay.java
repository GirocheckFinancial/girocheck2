package com.smartbt.girocheck.servercommon.display;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.sql.Blob;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Roberto
 */
@XmlRootElement
public class TransactionImagesDisplay implements Serializable {

    private static final long serialVersionUID = 1L;
    @JsonIgnore
    private Blob checkFront;

    private String checkFrontImage;

    @JsonIgnore
    private Blob checkBack;

    private String checkBackImage;

    @JsonIgnore
    private Blob idFront;

    private String idFrontImage;

    @JsonIgnore
    private Blob idBack;

    private String idBackImage;

    @JsonIgnore
    private Integer clientId;
    @JsonIgnore
    private Integer transactionId;
    @JsonIgnore
    private boolean imagesConverted;
    
    private Long remainingConvertions;
    @JsonIgnore
    private Boolean showIdImages;

    public boolean hasIdFront() {
        return idFront != null;
    }

    public boolean hasIdBack() {
        return idBack != null;
    }

    /**
     * @return the checkFront
     */
    public Blob getCheckFront() {
        return checkFront;
    }

    /**
     * @param checkFront the checkFront to set
     */
    public void setCheckFront(Blob checkFront) {
        this.checkFront = checkFront;
    }

    /**
     * @return the checkFrontImage
     */
    public String getCheckFrontImage() {
        return checkFrontImage;
    }

    /**
     * @param checkFrontImage the checkFrontImage to set
     */
    public void setCheckFrontImage(String checkFrontImage) {
        this.checkFrontImage = checkFrontImage;
    }

    /**
     * @return the checkBack
     */
    public Blob getCheckBack() {
        return checkBack;
    }

    /**
     * @param checkBack the checkBack to set
     */
    public void setCheckBack(Blob checkBack) {
        this.checkBack = checkBack;
    }

    /**
     * @return the checkBackImage
     */
    public String getCheckBackImage() {
        return checkBackImage;
    }

    /**
     * @param checkBackImage the checkBackImage to set
     */
    public void setCheckBackImage(String checkBackImage) {
        this.checkBackImage = checkBackImage;
    }

    /**
     * @return the idFront
     */
    public Blob getIdFront() {
        return idFront;
    }

    /**
     * @param idFront the idFront to set
     */
    public void setIdFront(Blob idFront) {
        this.idFront = idFront;
    }

    /**
     * @return the idFrontImage
     */
    public String getIdFrontImage() {
        return idFrontImage;
    }

    /**
     * @param idFrontImage the idFrontImage to set
     */
    public void setIdFrontImage(String idFrontImage) {
        this.idFrontImage = idFrontImage;
    }

    /**
     * @return the idBack
     */
    public Blob getIdBack() {
        return idBack;
    }

    /**
     * @param idBack the idBack to set
     */
    public void setIdBack(Blob idBack) {
        this.idBack = idBack;
    }

    /**
     * @return the idBackImage
     */
    public String getIdBackImage() {
        return idBackImage;
    }

    /**
     * @param idBackImage the idBackImage to set
     */
    public void setIdBackImage(String idBackImage) {
        this.idBackImage = idBackImage;
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
     * @return the transactionId
     */
    public Integer getTransactionId() {
        return transactionId;
    }

    /**
     * @param transactionId the transactionId to set
     */
    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * @return the imagesConverted
     */
    public boolean isImagesConverted() {
        return imagesConverted;
    }

    /**
     * @param imagesConverted the imagesConverted to set
     */
    public void setImagesConverted(boolean imagesConverted) {
        this.imagesConverted = imagesConverted;
    }

    /**
     * @return the remainingConvertions
     */
    public Long getRemainingConvertions() {
        return remainingConvertions;
    }

    /**
     * @param remainingConvertions the remainingConvertions to set
     */
    public void setRemainingConvertions(Long remainingConvertions) {
        this.remainingConvertions = remainingConvertions;
    }

    /**
     * @return the showIdImages
     */
    public Boolean getShowIdImages() {
        return showIdImages;
    }

    /**
     * @param showIdImages the showIdImages to set
     */
    public void setShowIdImages(Boolean showIdImages) {
        this.showIdImages = showIdImages;
    }

}
