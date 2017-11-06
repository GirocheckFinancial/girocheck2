package com.smartbt.vtsuite.entity;

import java.io.StringReader;
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

    private String error;
    private String failed;
    private String restriction;
    private String velocityResults;

    public static IdeologyResponse getFromXML(String xml) {
        if (xml == null || xml.isEmpty()) {
            return new IdeologyResponse();
        }
        return (IdeologyResponse) JAXB.unmarshal(new StringReader(xml), IdeologyResponse.class);
    }

    @Override
    public String toString() {
        System.out.println("IdeologyResponse.toString");
        StringBuilder sb = new StringBuilder("{ ");

        if (error != null) {
            sb.append("error: " + error);
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

    /**
     * @return the error
     */
    public String getError() {
        return error;
    }

    /**
     * @param error the error to set
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * @return the failed
     */
    public String getFailed() {
        return failed;
    }

    /**
     * @param failed the failed to set
     */
    public void setFailed(String failed) {
        this.failed = failed;
    }

    /**
     * @return the restriction
     */
    public String getRestriction() {
        return restriction;
    }

    /**
     * @param restriction the restriction to set
     */
    public void setRestriction(String restriction) {
        this.restriction = restriction;
    }

    /**
     * @return the velocityResults
     */
    public String getVelocityResults() {
        return velocityResults;
    }

    @XmlElement(name = "velocity_results")
    public void setVelocityResults(String velocityResults) {
        this.velocityResults = velocityResults;
    }
}
