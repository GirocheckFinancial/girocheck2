package com.smartbt.girocheck.servercommon.display;
  
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date; 
/**
 *
 * @author Roberto
 */
 
public class CBKCDisplay {  
    private static final DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
     private String source; 
     private String cipid;
     private String ideologyResult;
     private String idNotes; 
     private String applicationDisposition; 
     private Date enrollmentDate;   
      
   public String toString() {
        StringBuilder sb = new StringBuilder();
        if (enrollmentDate != null) {
            sb.append("enrollmentDate=" + df.format(enrollmentDate) + ",");
        }
        sb.append("source=" + source + ",");
        sb.append("cipId=" + cipid + ",");
        sb.append("ideologyResult=" + ideologyResult + ",");
        sb.append("idNotes=" + idNotes + ",");
        sb.append("applicationDisposition=" + applicationDisposition);

        return sb.toString();
    }
 
    /**
     * @return the source
     */
    public String getSource() {
        return source;
    }

    /**
     * @param source the source to set
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * @return the cipid
     */
    public String getCipid() {
        return cipid;
    }

    /**
     * @param cipid the cipid to set
     */
    public void setCipid(String cipid) {
        this.cipid = cipid;
    }

    /**
     * @return the ideologyResult
     */
    public String getIdeologyResult() {
        return ideologyResult;
    }

    /**
     * @param ideologyResult the ideologyResult to set
     */
    public void setIdeologyResult(String ideologyResult) {
        this.ideologyResult = ideologyResult;
    }

    /**
     * @return the idNotes
     */
    public String getIdNotes() {
        return idNotes;
    }

    /**
     * @param idNotes the idNotes to set
     */
    public void setIdNotes(String idNotes) {
        this.idNotes = idNotes;
    }

    /**
     * @return the applicationDisposition
     */
    public String getApplicationDisposition() {
        return applicationDisposition;
    }

    /**
     * @param applicationDisposition the applicationDisposition to set
     */
    public void setApplicationDisposition(String applicationDisposition) {
        this.applicationDisposition = applicationDisposition;
    }

    /**
     * @return the enrollmentDate
     */
    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    /**
     * @param enrollmentDate the enrollmentDate to set
     */
    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }
 
     
}
