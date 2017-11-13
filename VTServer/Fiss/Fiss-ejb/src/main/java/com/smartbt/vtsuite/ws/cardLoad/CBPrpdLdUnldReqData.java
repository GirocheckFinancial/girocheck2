
package com.smartbt.vtsuite.ws.cardLoad;

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
 *         &lt;element name="E130013" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="E130484" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E130485" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E130582" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E131150" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "e130484",
    "e130485",
    "e130582",
    "e131150"
})
@XmlRootElement(name = "CBPrpdLdUnldReqData", namespace = "mtvnCWCBPrpdLdUnldReqData")
public class CBPrpdLdUnldReqData {

    @XmlElement(name = "E130013", namespace = "mtvnCWCBPrpdLdUnldReqData", required = true)
    protected String e130013;
    @XmlElement(name = "E130484", namespace = "mtvnCWCBPrpdLdUnldReqData")
    protected String e130484;
    @XmlElement(name = "E130485", namespace = "mtvnCWCBPrpdLdUnldReqData")
    protected String e130485;
    @XmlElement(name = "E130582", namespace = "mtvnCWCBPrpdLdUnldReqData")
    protected String e130582;
    @XmlElement(name = "E131150", namespace = "mtvnCWCBPrpdLdUnldReqData")
    protected String e131150;

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
     * Gets the value of the e130484 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE130484() {
        return e130484;
    }

    /**
     * Sets the value of the e130484 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE130484(String value) {
        this.e130484 = value;
    }

    /**
     * Gets the value of the e130485 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE130485() {
        return e130485;
    }

    /**
     * Sets the value of the e130485 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE130485(String value) {
        this.e130485 = value;
    }

    /**
     * Gets the value of the e130582 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE130582() {
        return e130582;
    }

    /**
     * Sets the value of the e130582 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE130582(String value) {
        this.e130582 = value;
    }

    /**
     * Gets the value of the e131150 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE131150() {
        return e131150;
    }

    /**
     * Sets the value of the e131150 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE131150(String value) {
        this.e131150 = value;
    }

}
