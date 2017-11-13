
package com.smartbt.vtsuite.ws.setProductId;

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
 *         &lt;element name="E130579" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E130580" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "e130579",
    "e130580"
})
@XmlRootElement(name = "CBProdIDMaintReqData")
public class CBProdIDMaintReqData {

    @XmlElement(name = "E130013")
    protected String e130013;
    @XmlElement(name = "E130579")
    protected String e130579;
    @XmlElement(name = "E130580")
    protected String e130580;

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
     * Gets the value of the e130579 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE130579() {
        return e130579;
    }

    /**
     * Sets the value of the e130579 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE130579(String value) {
        this.e130579 = value;
    }

    /**
     * Gets the value of the e130580 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE130580() {
        return e130580;
    }

    /**
     * Sets the value of the e130580 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE130580(String value) {
        this.e130580 = value;
    }

}
