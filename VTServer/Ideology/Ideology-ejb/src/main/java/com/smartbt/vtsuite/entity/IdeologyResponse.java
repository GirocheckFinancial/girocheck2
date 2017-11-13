package com.smartbt.vtsuite.entity;

import com.smartbt.girocheck.servercommon.enums.IdeologyResultInfoType;
import com.smartbt.girocheck.servercommon.model.IdeologyResult;
import java.io.StringReader;
import java.util.Date;
import javax.xml.bind.JAXB;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rrodriguez
 */
@XmlRootElement(name = "response")
public class IdeologyResponse {

    private String idNumber;
    private KeyMessage results;
    private KeyMessage summaryResult;

    private String error;           //reproduced
    private KeyMessage failed;
    private KeyMessage restriction;     //reproduced 
    private KeyMessage idLiveError;     //reproduced

    private QualifierList qualifiers;
    private VelocityResultList velocityResults;  //reproduced

    public static IdeologyResponse getFromXML(String xml) {
        if (xml == null || xml.isEmpty()) {
            return new IdeologyResponse();
        }
        return (IdeologyResponse) JAXB.unmarshal(new StringReader(xml), IdeologyResponse.class);
    }

    public Boolean wasSuccess() {
        return summaryResult != null && summaryResult.getKey() != null && summaryResult.getKey().equalsIgnoreCase("id.success");
    }

    public String getErrorMessage() {
        String errorMessage = "";

        if (summaryResult != null && summaryResult.getMessage() != null) {
            errorMessage = summaryResult.getMessage();
        } else {
            if (results != null && results.getMessage() != null) {
                errorMessage = results.getMessage();
            } else {
                if (error != null && !error.isEmpty()) {
                    errorMessage = error;
                } else {
                    if (restriction != null && restriction.getMessage() != null) {
                        errorMessage = restriction.getMessage();
                    }
                }
            }
        }

        return errorMessage;
    }

    public IdeologyResult toEntity() {
        IdeologyResult result = new IdeologyResult();
        result.setCreationDate(new Date());

        if (idNumber != null) {
            result.setIdNumber(idNumber);
        }

        if (results != null) {
            result.setResultKey(results.getKey());
            result.setResultMessage(results.getMessage());
        }

        if (summaryResult != null) {
            result.setSummaryResultKey(summaryResult.getKey());
            result.setSummaryResultMessage(summaryResult.getMessage());
        }

        if (getError() != null) {
            System.out.println("error = " + error);
            result.setTagErrorMessage(error);
        }

        if (getFailed() != null) {
            result.setTagFailedKey(failed.getKey());
            result.setTagFailedMessage(failed.getMessage());
        }

        if (getRestriction() != null) {
            result.setTagRestrictionKey(restriction.getKey());
            result.setTagRestrictionMessage(restriction.getMessage());
        }

        if (velocityResults != null && velocityResults.getVelocityResults() != null) {
            result.setVelocityResultsCount(velocityResults.getVelocityResults().size());

            for (VelocityResult velocityResult : velocityResults.getVelocityResults()) {
                result.getQualifiers().add(velocityResult.toEntity(result, IdeologyResultInfoType.VELOCITY));
            }

        } else {
            result.setVelocityResultsCount(0);
        }

        if (getIdLiveError() != null) {
            result.setTagIdLiveErrorKey(idLiveError.getKey());
            result.setTagIdLiveErrorMessage(idLiveError.getMessage());
        }

        if (qualifiers != null && qualifiers.getQualifiers() != null) {
            result.setQualifiersCount(qualifiers.getQualifiers().size());

            for (Qualifier qualifier : qualifiers.getQualifiers()) {
                result.getQualifiers().add(qualifier.toEntity(result, IdeologyResultInfoType.QUALIFIER));
            }

        } else {
            result.setQualifiersCount(0);
        }
        return result;
    }

    @Override
    public String toString() { 
        StringBuilder sb = new StringBuilder("{ ");

        if (getError() != null) {
            sb.append("error: " + getError());
        }

        if (idNumber != null) {
            sb.append("idNumber: " + idNumber);
        }

        if (results != null) {
            sb.append(", results: " + results);
        }

        if (summaryResult != null) {
            sb.append(", summaryResult: " + summaryResult);
        }

        if (qualifiers != null && qualifiers.getQualifiers() != null) {
            sb.append(", qualifiers: [");

            for (Qualifier qualifier : qualifiers.getQualifiers()) {
                sb.append(" qualifier: " + qualifier);
            }

            sb.append(" ] ");
        }

        sb.append("}");
        return sb.toString();
    }

    /**
     * @return the idNumber
     */
    public String getIdNumber() {
        return idNumber;
    }

    @XmlElement(name = "id-number")
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    /**
     * @return the results
     */
    public KeyMessage getResults() {
        return results;
    }

    @XmlElement(name = "results")
    public void setResults(KeyMessage results) {
        this.results = results;
    }

    /**
     * @return the summaryResult
     */
    public KeyMessage getSummaryResult() {
        return summaryResult;
    }

    @XmlElement(name = "summary-result")
    public void setSummaryResult(KeyMessage summaryResult) {
        this.summaryResult = summaryResult;
    }

    public QualifierList getQualifiers() {
        return qualifiers;
    }

    @XmlElement(name = "qualifiers")
    public void setQualifiers(QualifierList qualifiers) {
        this.qualifiers = qualifiers;
    }

    /**
     * @return the error
     */
    public String getError() {
        return error;
    }

    @XmlElement(name = "error")
    public void setError(String error) {
        this.error = error;
    }

    /**
     * @return the failed
     */
    public KeyMessage getFailed() {
        return failed;
    }

    /**
     * @param failed the failed to set
     */
    public void setFailed(KeyMessage failed) {
        this.failed = failed;
    }

    /**
     * @return the restriction
     */
    public KeyMessage getRestriction() {
        return restriction;
    }

    /**
     * @param restriction the restriction to set
     */
    public void setRestriction(KeyMessage restriction) {
        this.restriction = restriction;
    }

    /**
     * @return the idLiveError
     */
    public KeyMessage getIdLiveError() {
        return idLiveError;
    }

    @XmlElement(name = "idliveq-error")
    public void setIdLiveError(KeyMessage idLiveError) {
        this.idLiveError = idLiveError;
    }

    /**
     * @return the velocityResults
     */
    public VelocityResultList getVelocityResults() {
        return velocityResults;
    }

    @XmlElement(name = "velocity-results")
    public void setVelocityResults(VelocityResultList velocityResults) {
        this.velocityResults = velocityResults;
    }
}
