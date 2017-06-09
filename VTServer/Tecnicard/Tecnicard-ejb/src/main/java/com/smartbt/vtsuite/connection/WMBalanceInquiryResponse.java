
package com.smartbt.vtsuite.connection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="WMBalanceInquiryResult" type="{https://SistemasGalileo.com/Services/}BalanceInquiryResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "wmBalanceInquiryResult"
})
@XmlRootElement(name = "WMBalanceInquiryResponse")
public class WMBalanceInquiryResponse {

    @XmlElement(name = "WMBalanceInquiryResult")
    protected BalanceInquiryResponse wmBalanceInquiryResult;

    /**
     * Gets the value of the wmBalanceInquiryResult property.
     * 
     * @return
     *     possible object is
     *     {@link BalanceInquiryResponse }
     *     
     */
    public BalanceInquiryResponse getWMBalanceInquiryResult() {
        return wmBalanceInquiryResult;
    }

    /**
     * Sets the value of the wmBalanceInquiryResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link BalanceInquiryResponse }
     *     
     */
    public void setWMBalanceInquiryResult(BalanceInquiryResponse value) {
        this.wmBalanceInquiryResult = value;
    }

}
