package com.smartbt.girocheck.servercommon.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author rrodriguez
 */
public class IdeologyResult extends BaseEntity {

    private int id;
    private String idNumber;
    private String resultKey;
    private String resultMessage;
    private String summaryResultKey;
    private String summaryResultMessage;

    private Integer qualifiersCount;
    private Integer velocityResultsCount;
    private Date creationDate;
    private Client client;

    private String tagFailedKey;
    private String tagFailedMessage;
 
    private String tagErrorMessage;

    private String tagRestrictionKey;
    private String tagRestrictionMessage;

    private String tagVelocityKey;
    private String tagVelocityMessage;

    private String tagIdLiveErrorKey;
    private String tagIdLiveErrorMessage;

    private Set<IdeologyResultInfo> qualifiers = new HashSet();

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the idNumber
     */
    public String getIdNumber() {
        return idNumber;
    }

    /**
     * @param idNumber the idNumber to set
     */
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    /**
     * @return the resultKey
     */
    public String getResultKey() {
        return resultKey;
    }

    /**
     * @param resultKey the resultKey to set
     */
    public void setResultKey(String resultKey) {
        this.resultKey = resultKey;
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
     * @return the summaryResultKey
     */
    public String getSummaryResultKey() {
        return summaryResultKey;
    }

    /**
     * @param summaryResultKey the summaryResultKey to set
     */
    public void setSummaryResultKey(String summaryResultKey) {
        this.summaryResultKey = summaryResultKey;
    }

    /**
     * @return the summaryResultMessage
     */
    public String getSummaryResultMessage() {
        return summaryResultMessage;
    }

    /**
     * @param summaryResultMessage the summaryResultMessage to set
     */
    public void setSummaryResultMessage(String summaryResultMessage) {
        this.summaryResultMessage = summaryResultMessage;
    }
    /**
     * @return the creationDate
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate the creationDate to set
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @return the client
     */
    public Client getClient() {
        return client;
    }

    /**
     * @param client the client to set
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the qualifiers
     */
    public Set<IdeologyResultInfo> getQualifiers() {
        return qualifiers;
    }

    /**
     * @param qualifiers the qualifiers to set
     */
    public void setQualifiers(Set<IdeologyResultInfo> qualifiers) {
        this.qualifiers = qualifiers;
    }

    /**
     * @return the tagFailedKey
     */
    public String getTagFailedKey() {
        return tagFailedKey;
    }

    /**
     * @param tagFailedKey the tagFailedKey to set
     */
    public void setTagFailedKey(String tagFailedKey) {
        this.tagFailedKey = tagFailedKey;
    }

    /**
     * @return the tagFailedMessage
     */
    public String getTagFailedMessage() {
        return tagFailedMessage;
    }

    /**
     * @param tagFailedMessage the tagFailedMessage to set
     */
    public void setTagFailedMessage(String tagFailedMessage) {
        this.tagFailedMessage = tagFailedMessage;
    } 
    /**
     * @return the tagErrorMessage
     */
    public String getTagErrorMessage() {
        return tagErrorMessage;
    }

    /**
     * @param tagErrorMessage the tagErrorMessage to set
     */
    public void setTagErrorMessage(String tagErrorMessage) {
        this.tagErrorMessage = tagErrorMessage;
    }

    /**
     * @return the tagRestrictionKey
     */
    public String getTagRestrictionKey() {
        return tagRestrictionKey;
    }

    /**
     * @param tagRestrictionKey the tagRestrictionKey to set
     */
    public void setTagRestrictionKey(String tagRestrictionKey) {
        this.tagRestrictionKey = tagRestrictionKey;
    }

    /**
     * @return the tagRestrictionMessage
     */
    public String getTagRestrictionMessage() {
        return tagRestrictionMessage;
    }

    /**
     * @param tagRestrictionMessage the tagRestrictionMessage to set
     */
    public void setTagRestrictionMessage(String tagRestrictionMessage) {
        this.tagRestrictionMessage = tagRestrictionMessage;
    }

    /**
     * @return the tagVelocityKey
     */
    public String getTagVelocityKey() {
        return tagVelocityKey;
    }

    /**
     * @param tagVelocityKey the tagVelocityKey to set
     */
    public void setTagVelocityKey(String tagVelocityKey) {
        this.tagVelocityKey = tagVelocityKey;
    }

    /**
     * @return the tagVelocityMessage
     */
    public String getTagVelocityMessage() {
        return tagVelocityMessage;
    }

    /**
     * @param tagVelocityMessage the tagVelocityMessage to set
     */
    public void setTagVelocityMessage(String tagVelocityMessage) {
        this.tagVelocityMessage = tagVelocityMessage;
    }
 
    /**
     * @return the tagIdLiveErrorKey
     */
    public String getTagIdLiveErrorKey() {
        return tagIdLiveErrorKey;
    }

    /**
     * @param tagIdLiveErrorKey the tagIdLiveErrorKey to set
     */
    public void setTagIdLiveErrorKey(String tagIdLiveErrorKey) {
        this.tagIdLiveErrorKey = tagIdLiveErrorKey;
    }

    /**
     * @return the tagIdLiveErrorMessage
     */
    public String getTagIdLiveErrorMessage() {
        return tagIdLiveErrorMessage;
    }

    /**
     * @param tagIdLiveErrorMessage the tagIdLiveErrorMessage to set
     */
    public void setTagIdLiveErrorMessage(String tagIdLiveErrorMessage) {
        this.tagIdLiveErrorMessage = tagIdLiveErrorMessage;
    }

    /**
     * @return the qualifiersCount
     */
    public Integer getQualifiersCount() {
        return qualifiersCount;
    }

    /**
     * @param qualifiersCount the qualifiersCount to set
     */
    public void setQualifiersCount(Integer qualifiersCount) {
        this.qualifiersCount = qualifiersCount;
    }

    /**
     * @return the velocityResultsCount
     */
    public Integer getVelocityResultsCount() {
        return velocityResultsCount;
    }

    /**
     * @param velocityResultsCount the velocityResultsCount to set
     */
    public void setVelocityResultsCount(Integer velocityResultsCount) {
        this.velocityResultsCount = velocityResultsCount;
    }

}
