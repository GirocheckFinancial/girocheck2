
package com.smartbt.vtsuite.ws.history.details;

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
 *         &lt;element name="E130551" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E130202" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E130206" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "e130643",
    "e130551",
    "e130202",
    "e130206"
})
@XmlRootElement(name = "CBHistTxnDtlInqReqData")
public class CBHistTxnDtlInqReqData {

    @XmlElement(name = "E130013")
    protected String e130013;
    @XmlElement(name = "E130643")
    protected String e130643;
    @XmlElement(name = "E130551")
    protected String e130551;
    @XmlElement(name = "E130202")
    protected String e130202;
    @XmlElement(name = "E130206")
    protected String e130206;

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

    /**
     * Gets the value of the e130551 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE130551() {
        return e130551;
    }

    /**
     * Sets the value of the e130551 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE130551(String value) {
        this.e130551 = value;
    }

    /**
     * Gets the value of the e130202 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE130202() {
        return e130202;
    }

    /**
     * Sets the value of the e130202 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE130202(String value) {
        this.e130202 = value;
    }

    /**
     * Gets the value of the e130206 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE130206() {
        return e130206;
    }

    /**
     * Sets the value of the e130206 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE130206(String value) {
        this.e130206 = value;
    }

}
