
package com.smartbt.vtsuite.ws.balanceInquiry;

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
 *         &lt;element name="E130013" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E130643" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "e130013",
    "e130643"
})
@XmlRootElement(name = "CBAcctInqReqData", namespace = "mtvnCWCBAcctInqReqData")
public class CBAcctInqReqData {

    @XmlElement(name = "E130013", namespace = "mtvnCWCBAcctInqReqData")
    protected String e130013;
    @XmlElement(name = "E130643", namespace = "mtvnCWCBAcctInqReqData")
    protected String e130643;

    /**
     * Gets the value of the e130013 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE130013() {
        return e130013;
    }

    /**
     * Sets the value of the e130013 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE130013(String value) {
        this.e130013 = value;
    }

    /**
     * Gets the value of the e130643 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE130643() {
        return e130643;
    }

    /**
     * Sets the value of the e130643 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE130643(String value) {
        this.e130643 = value;
    }

}
