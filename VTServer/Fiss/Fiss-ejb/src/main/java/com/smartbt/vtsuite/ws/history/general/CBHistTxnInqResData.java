
package com.smartbt.vtsuite.ws.history.general;

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
 *         &lt;element name="E130013" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E130643" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E304" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E130202" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E130206" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E309" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E130551" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CBHistTxnInqTxnDataLst" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="CBHistTxnInqTxnData" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="E130203" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="E130207" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="E130210" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="E130211" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="E130379" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="E130213" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="E130214" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="E130215" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="E130217" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="E130218" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="E130478" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="E130307" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="E130200" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="E130205" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="E130208" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="E130209" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="E130219" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="E130220" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="E130369" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="E130370" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="E130371" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="E130380" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="E130416" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="E130419" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="E130420" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="E130438" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="E130553" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="E131596" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
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
    "e130643",
    "e304",
    "e130202",
    "e130206",
    "e309",
    "e130551",
    "cbHistTxnInqTxnDataLst",
    "errCde",
    "errResnCde",
    "applMsgLst"
})
@XmlRootElement(name = "CBHistTxnInqResData", namespace = "mtvnCWCBHistTxnInqResData")
public class CBHistTxnInqResData {

    @XmlElement(name = "E130013", namespace = "mtvnCWCBHistTxnInqResData")
    protected String e130013;
    @XmlElement(name = "E130643", namespace = "mtvnCWCBHistTxnInqResData")
    protected String e130643;
    @XmlElement(name = "E304", namespace = "mtvnCWCBHistTxnInqResData")
    protected String e304;
    @XmlElement(name = "E130202", namespace = "mtvnCWCBHistTxnInqResData")
    protected String e130202;
    @XmlElement(name = "E130206", namespace = "mtvnCWCBHistTxnInqResData")
    protected String e130206;
    @XmlElement(name = "E309", namespace = "mtvnCWCBHistTxnInqResData")
    protected String e309;
    @XmlElement(name = "E130551", namespace = "mtvnCWCBHistTxnInqResData")
    protected String e130551;
    @XmlElement(name = "CBHistTxnInqTxnDataLst", namespace = "mtvnCWCBHistTxnInqResData")
    protected CBHistTxnInqResData.CBHistTxnInqTxnDataLst cbHistTxnInqTxnDataLst;
    @XmlElement(name = "ErrCde", namespace = "mtvnCWCBHistTxnInqResData")
    protected String errCde;
    @XmlElement(name = "ErrResnCde", namespace = "mtvnCWCBHistTxnInqResData")
    protected String errResnCde;
    @XmlElement(name = "ApplMsgLst", namespace = "mtvnCWCBHistTxnInqResData")
    protected CBHistTxnInqResData.ApplMsgLst applMsgLst;

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
     * Gets the value of the e304 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE304() {
        return e304;
    }

    /**
     * Sets the value of the e304 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE304(String value) {
        this.e304 = value;
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

    /**
     * Gets the value of the e309 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE309() {
        return e309;
    }

    /**
     * Sets the value of the e309 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE309(String value) {
        this.e309 = value;
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
     * Gets the value of the cbHistTxnInqTxnDataLst property.
     * 
     * @return
     *     possible object is
     *     {@link CBHistTxnInqResData.CBHistTxnInqTxnDataLst }
     *     
     */
    public CBHistTxnInqResData.CBHistTxnInqTxnDataLst getCBHistTxnInqTxnDataLst() {
        return cbHistTxnInqTxnDataLst;
    }

    /**
     * Sets the value of the cbHistTxnInqTxnDataLst property.
     * 
     * @param value
     *     allowed object is
     *     {@link CBHistTxnInqResData.CBHistTxnInqTxnDataLst }
     *     
     */
    public void setCBHistTxnInqTxnDataLst(CBHistTxnInqResData.CBHistTxnInqTxnDataLst value) {
        this.cbHistTxnInqTxnDataLst = value;
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
     *     {@link CBHistTxnInqResData.ApplMsgLst }
     *     
     */
    public CBHistTxnInqResData.ApplMsgLst getApplMsgLst() {
        return applMsgLst;
    }

    /**
     * Sets the value of the applMsgLst property.
     * 
     * @param value
     *     allowed object is
     *     {@link CBHistTxnInqResData.ApplMsgLst }
     *     
     */
    public void setApplMsgLst(CBHistTxnInqResData.ApplMsgLst value) {
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

        @XmlElement(name = "ApplMsg", namespace = "mtvnCWCBHistTxnInqResData", required = true)
        protected List<CBHistTxnInqResData.ApplMsgLst.ApplMsg> applMsg;

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
         * {@link CBHistTxnInqResData.ApplMsgLst.ApplMsg }
         * 
         * 
         */
        public List<CBHistTxnInqResData.ApplMsgLst.ApplMsg> getApplMsg() {
            if (applMsg == null) {
                applMsg = new ArrayList<CBHistTxnInqResData.ApplMsgLst.ApplMsg>();
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

            @XmlElement(name = "ApplMsgApplId", namespace = "mtvnCWCBHistTxnInqResData")
            protected String applMsgApplId;
            @XmlElement(name = "ApplMsgNbr", namespace = "mtvnCWCBHistTxnInqResData")
            protected String applMsgNbr;
            @XmlElement(name = "ApplMsgTxt", namespace = "mtvnCWCBHistTxnInqResData")
            protected String applMsgTxt;
            @XmlElement(name = "ApplMsgErrInd", namespace = "mtvnCWCBHistTxnInqResData")
            protected String applMsgErrInd;
            @XmlElement(name = "ApplMsgFld", namespace = "mtvnCWCBHistTxnInqResData")
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
     *         &lt;element name="CBHistTxnInqTxnData" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="E130203" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="E130207" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="E130210" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="E130211" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="E130379" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="E130213" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="E130214" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="E130215" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="E130217" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="E130218" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="E130478" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="E130307" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="E130200" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="E130205" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="E130208" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="E130209" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="E130219" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="E130220" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="E130369" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="E130370" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="E130371" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="E130380" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="E130416" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="E130419" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="E130420" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="E130438" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="E130553" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="E131596" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "cbHistTxnInqTxnData"
    })
    public static class CBHistTxnInqTxnDataLst {

        @XmlElement(name = "CBHistTxnInqTxnData", namespace = "mtvnCWCBHistTxnInqResData")
        protected List<CBHistTxnInqResData.CBHistTxnInqTxnDataLst.CBHistTxnInqTxnData> cbHistTxnInqTxnData;

        /**
         * Gets the value of the cbHistTxnInqTxnData property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the cbHistTxnInqTxnData property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getCBHistTxnInqTxnData().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link CBHistTxnInqResData.CBHistTxnInqTxnDataLst.CBHistTxnInqTxnData }
         * 
         * 
         */
        public List<CBHistTxnInqResData.CBHistTxnInqTxnDataLst.CBHistTxnInqTxnData> getCBHistTxnInqTxnData() {
            if (cbHistTxnInqTxnData == null) {
                cbHistTxnInqTxnData = new ArrayList<CBHistTxnInqResData.CBHistTxnInqTxnDataLst.CBHistTxnInqTxnData>();
            }
            return this.cbHistTxnInqTxnData;
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
         *         &lt;element name="E130203" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="E130207" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="E130210" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="E130211" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="E130379" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="E130213" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="E130214" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="E130215" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="E130217" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="E130218" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="E130478" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="E130307" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="E130200" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="E130205" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="E130208" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="E130209" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="E130219" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="E130220" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="E130369" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="E130370" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="E130371" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="E130380" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="E130416" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="E130419" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="E130420" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="E130438" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="E130553" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="E131596" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
            "e130203",
            "e130207",
            "e130210",
            "e130211",
            "e130379",
            "e130213",
            "e130214",
            "e130215",
            "e130217",
            "e130218",
            "e130478",
            "e130307",
            "e130200",
            "e130205",
            "e130208",
            "e130209",
            "e130219",
            "e130220",
            "e130369",
            "e130370",
            "e130371",
            "e130380",
            "e130416",
            "e130419",
            "e130420",
            "e130438",
            "e130553",
            "e131596"
        })
        public static class CBHistTxnInqTxnData {

            @XmlElement(name = "E130203", namespace = "mtvnCWCBHistTxnInqResData")
            protected String e130203;
            @XmlElement(name = "E130207", namespace = "mtvnCWCBHistTxnInqResData")
            protected String e130207;
            @XmlElement(name = "E130210", namespace = "mtvnCWCBHistTxnInqResData")
            protected String e130210;
            @XmlElement(name = "E130211", namespace = "mtvnCWCBHistTxnInqResData")
            protected String e130211;
            @XmlElement(name = "E130379", namespace = "mtvnCWCBHistTxnInqResData")
            protected String e130379;
            @XmlElement(name = "E130213", namespace = "mtvnCWCBHistTxnInqResData")
            protected String e130213;
            @XmlElement(name = "E130214", namespace = "mtvnCWCBHistTxnInqResData")
            protected String e130214;
            @XmlElement(name = "E130215", namespace = "mtvnCWCBHistTxnInqResData")
            protected String e130215;
            @XmlElement(name = "E130217", namespace = "mtvnCWCBHistTxnInqResData")
            protected String e130217;
            @XmlElement(name = "E130218", namespace = "mtvnCWCBHistTxnInqResData")
            protected String e130218;
            @XmlElement(name = "E130478", namespace = "mtvnCWCBHistTxnInqResData")
            protected String e130478;
            @XmlElement(name = "E130307", namespace = "mtvnCWCBHistTxnInqResData")
            protected String e130307;
            @XmlElement(name = "E130200", namespace = "mtvnCWCBHistTxnInqResData")
            protected String e130200;
            @XmlElement(name = "E130205", namespace = "mtvnCWCBHistTxnInqResData")
            protected String e130205;
            @XmlElement(name = "E130208", namespace = "mtvnCWCBHistTxnInqResData")
            protected String e130208;
            @XmlElement(name = "E130209", namespace = "mtvnCWCBHistTxnInqResData")
            protected String e130209;
            @XmlElement(name = "E130219", namespace = "mtvnCWCBHistTxnInqResData")
            protected String e130219;
            @XmlElement(name = "E130220", namespace = "mtvnCWCBHistTxnInqResData")
            protected String e130220;
            @XmlElement(name = "E130369", namespace = "mtvnCWCBHistTxnInqResData")
            protected String e130369;
            @XmlElement(name = "E130370", namespace = "mtvnCWCBHistTxnInqResData")
            protected String e130370;
            @XmlElement(name = "E130371", namespace = "mtvnCWCBHistTxnInqResData")
            protected String e130371;
            @XmlElement(name = "E130380", namespace = "mtvnCWCBHistTxnInqResData")
            protected String e130380;
            @XmlElement(name = "E130416", namespace = "mtvnCWCBHistTxnInqResData")
            protected String e130416;
            @XmlElement(name = "E130419", namespace = "mtvnCWCBHistTxnInqResData")
            protected String e130419;
            @XmlElement(name = "E130420", namespace = "mtvnCWCBHistTxnInqResData")
            protected String e130420;
            @XmlElement(name = "E130438", namespace = "mtvnCWCBHistTxnInqResData")
            protected String e130438;
            @XmlElement(name = "E130553", namespace = "mtvnCWCBHistTxnInqResData")
            protected String e130553;
            @XmlElement(name = "E131596", namespace = "mtvnCWCBHistTxnInqResData")
            protected String e131596;

            /**
             * Gets the value of the e130203 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getE130203() {
                return e130203;
            }

            /**
             * Sets the value of the e130203 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setE130203(String value) {
                this.e130203 = value;
            }

            /**
             * Gets the value of the e130207 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getE130207() {
                return e130207;
            }

            /**
             * Sets the value of the e130207 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setE130207(String value) {
                this.e130207 = value;
            }

            /**
             * Gets the value of the e130210 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getE130210() {
                return e130210;
            }

            /**
             * Sets the value of the e130210 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setE130210(String value) {
                this.e130210 = value;
            }

            /**
             * Gets the value of the e130211 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getE130211() {
                return e130211;
            }

            /**
             * Sets the value of the e130211 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setE130211(String value) {
                this.e130211 = value;
            }

            /**
             * Gets the value of the e130379 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getE130379() {
                return e130379;
            }

            /**
             * Sets the value of the e130379 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setE130379(String value) {
                this.e130379 = value;
            }

            /**
             * Gets the value of the e130213 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getE130213() {
                return e130213;
            }

            /**
             * Sets the value of the e130213 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setE130213(String value) {
                this.e130213 = value;
            }

            /**
             * Gets the value of the e130214 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getE130214() {
                return e130214;
            }

            /**
             * Sets the value of the e130214 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setE130214(String value) {
                this.e130214 = value;
            }

            /**
             * Gets the value of the e130215 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getE130215() {
                return e130215;
            }

            /**
             * Sets the value of the e130215 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setE130215(String value) {
                this.e130215 = value;
            }

            /**
             * Gets the value of the e130217 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getE130217() {
                return e130217;
            }

            /**
             * Sets the value of the e130217 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setE130217(String value) {
                this.e130217 = value;
            }

            /**
             * Gets the value of the e130218 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getE130218() {
                return e130218;
            }

            /**
             * Sets the value of the e130218 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setE130218(String value) {
                this.e130218 = value;
            }

            /**
             * Gets the value of the e130478 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getE130478() {
                return e130478;
            }

            /**
             * Sets the value of the e130478 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setE130478(String value) {
                this.e130478 = value;
            }

            /**
             * Gets the value of the e130307 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getE130307() {
                return e130307;
            }

            /**
             * Sets the value of the e130307 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setE130307(String value) {
                this.e130307 = value;
            }

            /**
             * Gets the value of the e130200 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getE130200() {
                return e130200;
            }

            /**
             * Sets the value of the e130200 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setE130200(String value) {
                this.e130200 = value;
            }

            /**
             * Gets the value of the e130205 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getE130205() {
                return e130205;
            }

            /**
             * Sets the value of the e130205 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setE130205(String value) {
                this.e130205 = value;
            }

            /**
             * Gets the value of the e130208 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getE130208() {
                return e130208;
            }

            /**
             * Sets the value of the e130208 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setE130208(String value) {
                this.e130208 = value;
            }

            /**
             * Gets the value of the e130209 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getE130209() {
                return e130209;
            }

            /**
             * Sets the value of the e130209 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setE130209(String value) {
                this.e130209 = value;
            }

            /**
             * Gets the value of the e130219 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getE130219() {
                return e130219;
            }

            /**
             * Sets the value of the e130219 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setE130219(String value) {
                this.e130219 = value;
            }

            /**
             * Gets the value of the e130220 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getE130220() {
                return e130220;
            }

            /**
             * Sets the value of the e130220 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setE130220(String value) {
                this.e130220 = value;
            }

            /**
             * Gets the value of the e130369 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getE130369() {
                return e130369;
            }

            /**
             * Sets the value of the e130369 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setE130369(String value) {
                this.e130369 = value;
            }

            /**
             * Gets the value of the e130370 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getE130370() {
                return e130370;
            }

            /**
             * Sets the value of the e130370 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setE130370(String value) {
                this.e130370 = value;
            }

            /**
             * Gets the value of the e130371 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getE130371() {
                return e130371;
            }

            /**
             * Sets the value of the e130371 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setE130371(String value) {
                this.e130371 = value;
            }

            /**
             * Gets the value of the e130380 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getE130380() {
                return e130380;
            }

            /**
             * Sets the value of the e130380 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setE130380(String value) {
                this.e130380 = value;
            }

            /**
             * Gets the value of the e130416 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getE130416() {
                return e130416;
            }

            /**
             * Sets the value of the e130416 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setE130416(String value) {
                this.e130416 = value;
            }

            /**
             * Gets the value of the e130419 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getE130419() {
                return e130419;
            }

            /**
             * Sets the value of the e130419 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setE130419(String value) {
                this.e130419 = value;
            }

            /**
             * Gets the value of the e130420 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getE130420() {
                return e130420;
            }

            /**
             * Sets the value of the e130420 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setE130420(String value) {
                this.e130420 = value;
            }

            /**
             * Gets the value of the e130438 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getE130438() {
                return e130438;
            }

            /**
             * Sets the value of the e130438 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setE130438(String value) {
                this.e130438 = value;
            }

            /**
             * Gets the value of the e130553 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getE130553() {
                return e130553;
            }

            /**
             * Sets the value of the e130553 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setE130553(String value) {
                this.e130553 = value;
            }

            /**
             * Gets the value of the e131596 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getE131596() {
                return e131596;
            }

            /**
             * Sets the value of the e131596 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setE131596(String value) {
                this.e131596 = value;
            }

        }

    }

}
