package com.smartbt.girocheck.servercommon.display;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Roberto
 */
public class CBKCDisplay {

    private static final DateFormat df = new SimpleDateFormat("yyyyMMdd");
    private String source;
    private String cipid;
    private String ideologyResult;
    private String idNotes;
    private String applicationDisposition;
    private Date enrollmentDate;

    //address
    private String address;
    private String city;
    private String state;
    private String zipcode;

    private String phone;
    private Date dob;
    private String ssn;

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("|");  //This is for the email that we don't collect;
        sb.append(getFullAddress() + "|");  //This is for the email that we don't collect;
        sb.append("|");  //This is for the ip address;
        sb.append("|");  //This is for the device id;
        sb.append(phone + "|");
        sb.append(getDateString(dob) + "|");
        sb.append(ssn + "|");
        sb.append(getDateString(enrollmentDate) + "|");
        sb.append(source + "|");
        sb.append(cipid + "|");
        sb.append(ideologyResult + "|");
        sb.append(idNotes + "|");
        sb.append(applicationDisposition + "|");

        return sb.toString();
    }

    private String getDateString(Date date) {
        String dateStr = "";
        if (date != null) {
            try {
                dateStr = df.format(date);
            } catch (Exception e) {
            }
        }
        return dateStr;
    }

    private String getFullAddress() {
        return address + ", " + city + ", " + state + ", " + zipcode;
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
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the dob
     */
    public Date getDob() {
        return dob;
    }

    /**
     * @param dob the dob to set
     */
    public void setDob(Date dob) {
        this.dob = dob;
    }

    /**
     * @return the ssn
     */
    public String getSsn() {
        return ssn;
    }

    /**
     * @param ssn the ssn to set
     */
    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

}
