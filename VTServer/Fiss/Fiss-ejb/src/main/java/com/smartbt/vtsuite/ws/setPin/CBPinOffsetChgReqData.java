
package com.smartbt.vtsuite.ws.setPin;

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
 *         &lt;element name="E130015" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E130013" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="E130021" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E130305" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "e130015",
    "e130013",
    "e130021",
    "e130305"
})
@XmlRootElement(name = "CBPinOffsetChgReqData", namespace = "mtvnCWCBPinOffsetChgReqData")
public class CBPinOffsetChgReqData {

    @XmlElement(name = "E130015", namespace = "mtvnCWCBPinOffsetChgReqData")
    protected String e130015;
    @XmlElement(name = "E130013", namespace = "mtvnCWCBPinOffsetChgReqData", required = true)
    protected String e130013;
    @XmlElement(name = "E130021", namespace = "mtvnCWCBPinOffsetChgReqData")
    protected String e130021;
    @XmlElement(name = "E130305", namespace = "mtvnCWCBPinOffsetChgReqData")
    protected String e130305;

    /**
     * Gets the value of the e130015 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE130015() {
        return e130015;
    }

    /**
     * Sets the value of the e130015 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE130015(String value) {
        this.e130015 = value;
    }

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
     * Gets the value of the e130021 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE130021() {
        return e130021;
    }

    /**
     * Sets the value of the e130021 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE130021(String value) {
        this.e130021 = value;
    }

    /**
     * Gets the value of the e130305 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE130305() {
        return e130305;
    }

    /**
     * Sets the value of the e130305 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE130305(String value) {
        this.e130305 = value;
    }

}
