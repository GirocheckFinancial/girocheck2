
package com.smartbt.vtsuite.ws.history.pending;

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
 *         &lt;element name="E130242" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E130245" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E130246" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E130551" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "e130242",
    "e130245",
    "e130246",
    "e130551"
})
@XmlRootElement(name = "CBPndTxnInqReqData", namespace = "mtvnCWCBPndTxnInqReqData")
public class CBPndTxnInqReqData {

    @XmlElement(name = "E130013", namespace = "mtvnCWCBPndTxnInqReqData")
    protected String e130013;
    @XmlElement(name = "E130643", namespace = "mtvnCWCBPndTxnInqReqData")
    protected String e130643;
    @XmlElement(name = "E130242", namespace = "mtvnCWCBPndTxnInqReqData")
    protected String e130242;
    @XmlElement(name = "E130245", namespace = "mtvnCWCBPndTxnInqReqData")
    protected String e130245;
    @XmlElement(name = "E130246", namespace = "mtvnCWCBPndTxnInqReqData")
    protected String e130246;
    @XmlElement(name = "E130551", namespace = "mtvnCWCBPndTxnInqReqData")
    protected String e130551;

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
     * Gets the value of the e130242 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE130242() {
        return e130242;
    }

    /**
     * Sets the value of the e130242 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE130242(String value) {
        this.e130242 = value;
    }

    /**
     * Gets the value of the e130245 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE130245() {
        return e130245;
    }

    /**
     * Sets the value of the e130245 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE130245(String value) {
        this.e130245 = value;
    }

    /**
     * Gets the value of the e130246 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE130246() {
        return e130246;
    }

    /**
     * Sets the value of the e130246 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE130246(String value) {
        this.e130246 = value;
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

}
