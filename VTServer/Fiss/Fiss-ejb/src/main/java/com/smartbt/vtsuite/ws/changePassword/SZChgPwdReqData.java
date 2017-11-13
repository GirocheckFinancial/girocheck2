
package com.smartbt.vtsuite.ws.changePassword;

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
 *         &lt;element name="E300205" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="E300206" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E300207" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E300208" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "e300205",
    "e300206",
    "e300207",
    "e300208"
})
@XmlRootElement(name = "SZChgPwdReqData", namespace = "mtvnCWSZChgPwdReqData")
public class SZChgPwdReqData {

    @XmlElement(name = "E300205", namespace = "mtvnCWSZChgPwdReqData", required = true)
    protected String e300205;
    @XmlElement(name = "E300206", namespace = "mtvnCWSZChgPwdReqData")
    protected String e300206;
    @XmlElement(name = "E300207", namespace = "mtvnCWSZChgPwdReqData")
    protected String e300207;
    @XmlElement(name = "E300208", namespace = "mtvnCWSZChgPwdReqData")
    protected String e300208;

    /**
     * Gets the value of the e300205 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE300205() {
        return e300205;
    }

    /**
     * Sets the value of the e300205 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE300205(String value) {
        this.e300205 = value;
    }

    /**
     * Gets the value of the e300206 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE300206() {
        return e300206;
    }

    /**
     * Sets the value of the e300206 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE300206(String value) {
        this.e300206 = value;
    }

    /**
     * Gets the value of the e300207 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE300207() {
        return e300207;
    }

    /**
     * Sets the value of the e300207 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE300207(String value) {
        this.e300207 = value;
    }

    /**
     * Gets the value of the e300208 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE300208() {
        return e300208;
    }

    /**
     * Sets the value of the e300208 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE300208(String value) {
        this.e300208 = value;
    }

}
