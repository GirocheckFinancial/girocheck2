
package com.smartbt.vtsuite.ws.cardActivation;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="E130015" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E130050" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E130051" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E130362" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E130442" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E130443" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E130444" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E130445" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E130446" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E130447" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E130448" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E130449" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E130450" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E130451" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E130452" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E130453" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E130454" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E130455" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E130456" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E130457" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E130458" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E130459" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E130460" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ErrCde" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ErrResnCde" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ApplMsgLst" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ApplMsg" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;all>
 *                             &lt;element name="ApplMsgApplId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ApplMsgNbr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ApplMsgTxt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ApplMsgErrInd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ApplMsgFld" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                           &lt;/all>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
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
    "e130015",
    "e130050",
    "e130051",
    "e130362",
    "e130442",
    "e130443",
    "e130444",
    "e130445",
    "e130446",
    "e130447",
    "e130448",
    "e130449",
    "e130450",
    "e130451",
    "e130452",
    "e130453",
    "e130454",
    "e130455",
    "e130456",
    "e130457",
    "e130458",
    "e130459",
    "e130460",
    "errCde",
    "errResnCde",
    "applMsgLst"
})
@XmlRootElement(name = "CBNegFleMaintResData", namespace = "mtvnCWCBNegFleMaintResData")
public class CBNegFleMaintResData {

    @XmlElement(name = "E130013", namespace = "mtvnCWCBNegFleMaintResData", required = true)
    protected String e130013;
    @XmlElement(name = "E130015", namespace = "mtvnCWCBNegFleMaintResData")
    protected String e130015;
    @XmlElement(name = "E130050", namespace = "mtvnCWCBNegFleMaintResData")
    protected String e130050;
    @XmlElement(name = "E130051", namespace = "mtvnCWCBNegFleMaintResData")
    protected String e130051;
    @XmlElement(name = "E130362", namespace = "mtvnCWCBNegFleMaintResData")
    protected String e130362;
    @XmlElement(name = "E130442", namespace = "mtvnCWCBNegFleMaintResData")
    protected String e130442;
    @XmlElement(name = "E130443", namespace = "mtvnCWCBNegFleMaintResData")
    protected String e130443;
    @XmlElement(name = "E130444", namespace = "mtvnCWCBNegFleMaintResData")
    protected String e130444;
    @XmlElement(name = "E130445", namespace = "mtvnCWCBNegFleMaintResData")
    protected String e130445;
    @XmlElement(name = "E130446", namespace = "mtvnCWCBNegFleMaintResData")
    protected String e130446;
    @XmlElement(name = "E130447", namespace = "mtvnCWCBNegFleMaintResData")
    protected String e130447;
    @XmlElement(name = "E130448", namespace = "mtvnCWCBNegFleMaintResData")
    protected String e130448;
    @XmlElement(name = "E130449", namespace = "mtvnCWCBNegFleMaintResData")
    protected String e130449;
    @XmlElement(name = "E130450", namespace = "mtvnCWCBNegFleMaintResData")
    protected String e130450;
    @XmlElement(name = "E130451", namespace = "mtvnCWCBNegFleMaintResData")
    protected String e130451;
    @XmlElement(name = "E130452", namespace = "mtvnCWCBNegFleMaintResData")
    protected String e130452;
    @XmlElement(name = "E130453", namespace = "mtvnCWCBNegFleMaintResData")
    protected String e130453;
    @XmlElement(name = "E130454", namespace = "mtvnCWCBNegFleMaintResData")
    protected String e130454;
    @XmlElement(name = "E130455", namespace = "mtvnCWCBNegFleMaintResData")
    protected String e130455;
    @XmlElement(name = "E130456", namespace = "mtvnCWCBNegFleMaintResData")
    protected String e130456;
    @XmlElement(name = "E130457", namespace = "mtvnCWCBNegFleMaintResData")
    protected String e130457;
    @XmlElement(name = "E130458", namespace = "mtvnCWCBNegFleMaintResData")
    protected String e130458;
    @XmlElement(name = "E130459", namespace = "mtvnCWCBNegFleMaintResData")
    protected String e130459;
    @XmlElement(name = "E130460", namespace = "mtvnCWCBNegFleMaintResData")
    protected String e130460;
    @XmlElement(name = "ErrCde", namespace = "mtvnCWCBNegFleMaintResData")
    protected String errCde;
    @XmlElement(name = "ErrResnCde", namespace = "mtvnCWCBNegFleMaintResData")
    protected String errResnCde;
    @XmlElement(name = "ApplMsgLst", namespace = "mtvnCWCBNegFleMaintResData")
    protected CBNegFleMaintResData.ApplMsgLst applMsgLst;

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
     * Gets the value of the e130050 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE130050() {
        return e130050;
    }

    /**
     * Sets the value of the e130050 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE130050(String value) {
        this.e130050 = value;
    }

    /**
     * Gets the value of the e130051 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE130051() {
        return e130051;
    }

    /**
     * Sets the value of the e130051 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE130051(String value) {
        this.e130051 = value;
    }

    /**
     * Gets the value of the e130362 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE130362() {
        return e130362;
    }

    /**
     * Sets the value of the e130362 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE130362(String value) {
        this.e130362 = value;
    }

    /**
     * Gets the value of the e130442 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE130442() {
        return e130442;
    }

    /**
     * Sets the value of the e130442 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE130442(String value) {
        this.e130442 = value;
    }

    /**
     * Gets the value of the e130443 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE130443() {
        return e130443;
    }

    /**
     * Sets the value of the e130443 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE130443(String value) {
        this.e130443 = value;
    }

    /**
     * Gets the value of the e130444 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE130444() {
        return e130444;
    }

    /**
     * Sets the value of the e130444 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE130444(String value) {
        this.e130444 = value;
    }

    /**
     * Gets the value of the e130445 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE130445() {
        return e130445;
    }

    /**
     * Sets the value of the e130445 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE130445(String value) {
        this.e130445 = value;
    }

    /**
     * Gets the value of the e130446 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE130446() {
        return e130446;
    }

    /**
     * Sets the value of the e130446 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE130446(String value) {
        this.e130446 = value;
    }

    /**
     * Gets the value of the e130447 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE130447() {
        return e130447;
    }

    /**
     * Sets the value of the e130447 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE130447(String value) {
        this.e130447 = value;
    }

    /**
     * Gets the value of the e130448 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE130448() {
        return e130448;
    }

    /**
     * Sets the value of the e130448 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE130448(String value) {
        this.e130448 = value;
    }

    /**
     * Gets the value of the e130449 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE130449() {
        return e130449;
    }

    /**
     * Sets the value of the e130449 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE130449(String value) {
        this.e130449 = value;
    }

    /**
     * Gets the value of the e130450 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE130450() {
        return e130450;
    }

    /**
     * Sets the value of the e130450 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE130450(String value) {
        this.e130450 = value;
    }

    /**
     * Gets the value of the e130451 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE130451() {
        return e130451;
    }

    /**
     * Sets the value of the e130451 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE130451(String value) {
        this.e130451 = value;
    }

    /**
     * Gets the value of the e130452 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE130452() {
        return e130452;
    }

    /**
     * Sets the value of the e130452 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE130452(String value) {
        this.e130452 = value;
    }

    /**
     * Gets the value of the e130453 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE130453() {
        return e130453;
    }

    /**
     * Sets the value of the e130453 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE130453(String value) {
        this.e130453 = value;
    }

    /**
     * Gets the value of the e130454 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE130454() {
        return e130454;
    }

    /**
     * Sets the value of the e130454 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE130454(String value) {
        this.e130454 = value;
    }

    /**
     * Gets the value of the e130455 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE130455() {
        return e130455;
    }

    /**
     * Sets the value of the e130455 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE130455(String value) {
        this.e130455 = value;
    }

    /**
     * Gets the value of the e130456 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE130456() {
        return e130456;
    }

    /**
     * Sets the value of the e130456 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE130456(String value) {
        this.e130456 = value;
    }

    /**
     * Gets the value of the e130457 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE130457() {
        return e130457;
    }

    /**
     * Sets the value of the e130457 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE130457(String value) {
        this.e130457 = value;
    }

    /**
     * Gets the value of the e130458 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE130458() {
        return e130458;
    }

    /**
     * Sets the value of the e130458 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE130458(String value) {
        this.e130458 = value;
    }

    /**
     * Gets the value of the e130459 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE130459() {
        return e130459;
    }

    /**
     * Sets the value of the e130459 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE130459(String value) {
        this.e130459 = value;
    }

    /**
     * Gets the value of the e130460 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE130460() {
        return e130460;
    }

    /**
     * Sets the value of the e130460 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE130460(String value) {
        this.e130460 = value;
    }

    /**
     * Gets the value of the errCde property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrCde() {
        return errCde;
    }

    /**
     * Sets the value of the errCde property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrCde(String value) {
        this.errCde = value;
    }

    /**
     * Gets the value of the errResnCde property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrResnCde() {
        return errResnCde;
    }

    /**
     * Sets the value of the errResnCde property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrResnCde(String value) {
        this.errResnCde = value;
    }

    /**
     * Gets the value of the applMsgLst property.
     * 
     * @return
     *     possible object is
     *     {@link CBNegFleMaintResData.ApplMsgLst }
     *     
     */
    public CBNegFleMaintResData.ApplMsgLst getApplMsgLst() {
        return applMsgLst;
    }

    /**
     * Sets the value of the applMsgLst property.
     * 
     * @param value
     *     allowed object is
     *     {@link CBNegFleMaintResData.ApplMsgLst }
     *     
     */
    public void setApplMsgLst(CBNegFleMaintResData.ApplMsgLst value) {
        this.applMsgLst = value;
    }


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
     *         &lt;element name="ApplMsg" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;all>
     *                   &lt;element name="ApplMsgApplId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ApplMsgNbr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ApplMsgTxt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ApplMsgErrInd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ApplMsgFld" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                 &lt;/all>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
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
        "applMsg"
    })
    public static class ApplMsgLst {

        @XmlElement(name = "ApplMsg", namespace = "mtvnCWCBNegFleMaintResData", required = true)
        protected List<CBNegFleMaintResData.ApplMsgLst.ApplMsg> applMsg;

        /**
         * Gets the value of the applMsg property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the applMsg property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getApplMsg().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link CBNegFleMaintResData.ApplMsgLst.ApplMsg }
         * 
         * 
         */
        public List<CBNegFleMaintResData.ApplMsgLst.ApplMsg> getApplMsg() {
            if (applMsg == null) {
                applMsg = new ArrayList<CBNegFleMaintResData.ApplMsgLst.ApplMsg>();
            }
            return this.applMsg;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;all>
         *         &lt;element name="ApplMsgApplId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ApplMsgNbr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ApplMsgTxt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ApplMsgErrInd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ApplMsgFld" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *       &lt;/all>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {

        })
        public static class ApplMsg {

            @XmlElement(name = "ApplMsgApplId", namespace = "mtvnCWCBNegFleMaintResData")
            protected String applMsgApplId;
            @XmlElement(name = "ApplMsgNbr", namespace = "mtvnCWCBNegFleMaintResData")
            protected String applMsgNbr;
            @XmlElement(name = "ApplMsgTxt", namespace = "mtvnCWCBNegFleMaintResData")
            protected String applMsgTxt;
            @XmlElement(name = "ApplMsgErrInd", namespace = "mtvnCWCBNegFleMaintResData")
            protected String applMsgErrInd;
            @XmlElement(name = "ApplMsgFld", namespace = "mtvnCWCBNegFleMaintResData")
            protected String applMsgFld;

            /**
             * Gets the value of the applMsgApplId property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getApplMsgApplId() {
                return applMsgApplId;
            }

            /**
             * Sets the value of the applMsgApplId property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setApplMsgApplId(String value) {
                this.applMsgApplId = value;
            }

            /**
             * Gets the value of the applMsgNbr property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getApplMsgNbr() {
                return applMsgNbr;
            }

            /**
             * Sets the value of the applMsgNbr property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setApplMsgNbr(String value) {
                this.applMsgNbr = value;
            }

            /**
             * Gets the value of the applMsgTxt property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getApplMsgTxt() {
                return applMsgTxt;
            }

            /**
             * Sets the value of the applMsgTxt property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setApplMsgTxt(String value) {
                this.applMsgTxt = value;
            }

            /**
             * Gets the value of the applMsgErrInd property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getApplMsgErrInd() {
                return applMsgErrInd;
            }

            /**
             * Sets the value of the applMsgErrInd property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setApplMsgErrInd(String value) {
                this.applMsgErrInd = value;
            }

            /**
             * Gets the value of the applMsgFld property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getApplMsgFld() {
                return applMsgFld;
            }

            /**
             * Sets the value of the applMsgFld property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setApplMsgFld(String value) {
                this.applMsgFld = value;
            }

        }

    }

}
