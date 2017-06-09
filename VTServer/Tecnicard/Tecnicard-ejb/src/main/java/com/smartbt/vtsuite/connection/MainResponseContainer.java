
package com.smartbt.vtsuite.connection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MainResponseContainer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MainResponseContainer">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SessionTag" type="{https://SistemasGalileo.com/Services/}SessionTag" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MainResponseContainer", propOrder = {
    "sessionTag"
})
public abstract class MainResponseContainer {

    @XmlElement(name = "SessionTag")
    protected SessionTag sessionTag;

    /**
     * Gets the value of the sessionTag property.
     * 
     * @return
     *     possible object is
     *     {@link SessionTag }
     *     
     */
    public SessionTag getSessionTag() {
        return sessionTag;
    }

    /**
     * Sets the value of the sessionTag property.
     * 
     * @param value
     *     allowed object is
     *     {@link SessionTag }
     *     
     */
    public void setSessionTag(SessionTag value) {
        this.sessionTag = value;
    }

}
