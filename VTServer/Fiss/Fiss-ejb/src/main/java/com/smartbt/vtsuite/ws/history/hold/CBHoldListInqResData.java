
package com.smartbt.vtsuite.ws.history.hold;

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
 *         &lt;element name="E130679" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="E130680" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E130681" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E130682" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E130683" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E130684" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E130685" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E130709" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E130710" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CBHoldListInqHoldDataLst" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="CBHoldListInqHoldData" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="E130686" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="E130687" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="E130688" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="E130689" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="E130690" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="E130691" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="E130692" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="E130700" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="E130698" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="E130699" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="E130701" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="E130702" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="E130711" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "e130679",
    "e130680",
    "e130681",
    "e130682",
    "e130683",
    "e130684",
    "e130685",
    "e130709",
    "e130710",
    "cbHoldListInqHoldDataLst",
    "errCde",
    "errResnCde",
    "applMsgLst"
})
@XmlRootElement(name = "CBHoldListInqResData", namespace = "mtvnCWCBHoldListInqResData")
public class CBHoldListInqResData {

    @XmlElement(name = "E130013", namespace = "mtvnCWCBHoldListInqResData", required = true)
    protected String e130013;
    @XmlElement(name = "E130679", namespace = "mtvnCWCBHoldListInqResData", required = true)
    protected String e130679;
    @XmlElement(name = "E130680", namespace = "mtvnCWCBHoldListInqResData")
    protected String e130680;
    @XmlElement(name = "E130681", namespace = "mtvnCWCBHoldListInqResData")
    protected String e130681;
    @XmlElement(name = "E130682", namespace = "mtvnCWCBHoldListInqResData")
    protected String e130682;
    @XmlElement(name = "E130683", namespace = "mtvnCWCBHoldListInqResData")
    protected String e130683;
    @XmlElement(name = "E130684", namespace = "mtvnCWCBHoldListInqResData")
    protected String e130684;
    @XmlElement(name = "E130685", namespace = "mtvnCWCBHoldListInqResData")
    protected String e130685;
    @XmlElement(name = "E130709", namespace = "mtvnCWCBHoldListInqResData")
    protected String e130709;
    @XmlElement(name = "E130710", namespace = "mtvnCWCBHoldListInqResData")
    protected String e130710;
    @XmlElement(name = "CBHoldListInqHoldDataLst", namespace = "mtvnCWCBHoldListInqResData")
    protected CBHoldListInqResData.CBHoldListInqHoldDataLst cbHoldListInqHoldDataLst;
    @XmlElement(name = "ErrCde", namespace = "mtvnCWCBHoldListInqResData")
    protected String errCde;
    @XmlElement(name = "ErrResnCde", namespace = "mtvnCWCBHoldListInqResData")
    protected String errResnCde;
    @XmlElement(name = "ApplMsgLst", namespace = "mtvnCWCBHoldListInqResData")
    protected CBHoldListInqResData.ApplMsgLst applMsgLst;

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
     * Gets the value of the e130679 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE130679() {
        return e130679;
    }

    /**
     * Sets the value of the e130679 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE130679(String value) {
        this.e130679 = value;
    }

    /**
     * Gets the value of the e130680 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE130680() {
        return e130680;
    }

    /**
     * Sets the value of the e130680 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE130680(String value) {
        this.e130680 = value;
    }

    /**
     * Gets the value of the e130681 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE130681() {
        return e130681;
    }

    /**
     * Sets the value of the e130681 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE130681(String value) {
        this.e130681 = value;
    }

    /**
     * Gets the value of the e130682 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE130682() {
        return e130682;
    }

    /**
     * Sets the value of the e130682 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE130682(String value) {
        this.e130682 = value;
    }

    /**
     * Gets the value of the e130683 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE130683() {
        return e130683;
    }

    /**
     * Sets the value of the e130683 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE130683(String value) {
        this.e130683 = value;
    }

    /**
     * Gets the value of the e130684 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE130684() {
        return e130684;
    }

    /**
     * Sets the value of the e130684 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE130684(String value) {
        this.e130684 = value;
    }

    /**
     * Gets the value of the e130685 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE130685() {
        return e130685;
    }

    /**
     * Sets the value of the e130685 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE130685(String value) {
        this.e130685 = value;
    }

    /**
     * Gets the value of the e130709 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE130709() {
        return e130709;
    }

    /**
     * Sets the value of the e130709 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE130709(String value) {
        this.e130709 = value;
    }

    /**
     * Gets the value of the e130710 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE130710() {
        return e130710;
    }

    /**
     * Sets the value of the e130710 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE130710(String value) {
        this.e130710 = value;
    }

    /**
     * Gets the value of the cbHoldListInqHoldDataLst property.
     * 
     * @return
     *     possible object is
     *     {@link CBHoldListInqResData.CBHoldListInqHoldDataLst }
     *     
     */
    public CBHoldListInqResData.CBHoldListInqHoldDataLst getCBHoldListInqHoldDataLst() {
        return cbHoldListInqHoldDataLst;
    }

    /**
     * Sets the value of the cbHoldListInqHoldDataLst property.
     * 
     * @param value
     *     allowed object is
     *     {@link CBHoldListInqResData.CBHoldListInqHoldDataLst }
     *     
     */
    public void setCBHoldListInqHoldDataLst(CBHoldListInqResData.CBHoldListInqHoldDataLst value) {
        this.cbHoldListInqHoldDataLst = value;
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
     *     {@link CBHoldListInqResData.ApplMsgLst }
     *     
     */
    public CBHoldListInqResData.ApplMsgLst getApplMsgLst() {
        return applMsgLst;
    }

    /**
     * Sets the value of the applMsgLst property.
     * 
     * @param value
     *     allowed object is
     *     {@link CBHoldListInqResData.ApplMsgLst }
     *     
     */
    public void setApplMsgLst(CBHoldListInqResData.ApplMsgLst value) {
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

        @XmlElement(name = "ApplMsg", namespace = "mtvnCWCBHoldListInqResData", required = true)
        protected List<CBHoldListInqResData.ApplMsgLst.ApplMsg> applMsg;

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
         * {@link CBHoldListInqResData.ApplMsgLst.ApplMsg }
         * 
         * 
         */
        public List<CBHoldListInqResData.ApplMsgLst.ApplMsg> getApplMsg() {
            if (applMsg == null) {
                applMsg = new ArrayList<CBHoldListInqResData.ApplMsgLst.ApplMsg>();
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

            @XmlElement(name = "ApplMsgApplId", namespace = "mtvnCWCBHoldListInqResData")
            protected String applMsgApplId;
            @XmlElement(name = "ApplMsgNbr", namespace = "mtvnCWCBHoldListInqResData")
            protected String applMsgNbr;
            @XmlElement(name = "ApplMsgTxt", namespace = "mtvnCWCBHoldListInqResData")
            protected String applMsgTxt;
            @XmlElement(name = "ApplMsgErrInd", namespace = "mtvnCWCBHoldListInqResData")
            protected String applMsgErrInd;
            @XmlElement(name = "ApplMsgFld", namespace = "mtvnCWCBHoldListInqResData")
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
     *         &lt;element name="CBHoldListInqHoldData" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="E130686" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="E130687" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="E130688" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="E130689" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="E130690" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="E130691" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="E130692" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="E130700" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="E130698" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="E130699" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="E130701" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="E130702" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="E130711" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "cbHoldListInqHoldData"
    })
    public static class CBHoldListInqHoldDataLst {

        @XmlElement(name = "CBHoldListInqHoldData", namespace = "mtvnCWCBHoldListInqResData")
        protected List<CBHoldListInqResData.CBHoldListInqHoldDataLst.CBHoldListInqHoldData> cbHoldListInqHoldData;

        /**
         * Gets the value of the cbHoldListInqHoldData property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the cbHoldListInqHoldData property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getCBHoldListInqHoldData().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link CBHoldListInqResData.CBHoldListInqHoldDataLst.CBHoldListInqHoldData }
         * 
         * 
         */
        public List<CBHoldListInqResData.CBHoldListInqHoldDataLst.CBHoldListInqHoldData> getCBHoldListInqHoldData() {
            if (cbHoldListInqHoldData == null) {
                cbHoldListInqHoldData = new ArrayList<CBHoldListInqResData.CBHoldListInqHoldDataLst.CBHoldListInqHoldData>();
            }
            return this.cbHoldListInqHoldData;
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
         *         &lt;element name="E130686" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="E130687" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="E130688" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="E130689" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="E130690" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="E130691" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="E130692" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="E130700" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="E130698" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="E130699" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="E130701" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="E130702" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="E130711" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
            "e130686",
            "e130687",
            "e130688",
            "e130689",
            "e130690",
            "e130691",
            "e130692",
            "e130700",
            "e130698",
            "e130699",
            "e130701",
            "e130702",
            "e130711"
        })
        public static class CBHoldListInqHoldData {

            @XmlElement(name = "E130686", namespace = "mtvnCWCBHoldListInqResData")
            protected String e130686;
            @XmlElement(name = "E130687", namespace = "mtvnCWCBHoldListInqResData")
            protected String e130687;
            @XmlElement(name = "E130688", namespace = "mtvnCWCBHoldListInqResData")
            protected String e130688;
            @XmlElement(name = "E130689", namespace = "mtvnCWCBHoldListInqResData")
            protected String e130689;
            @XmlElement(name = "E130690", namespace = "mtvnCWCBHoldListInqResData")
            protected String e130690;
            @XmlElement(name = "E130691", namespace = "mtvnCWCBHoldListInqResData")
            protected String e130691;
            @XmlElement(name = "E130692", namespace = "mtvnCWCBHoldListInqResData")
            protected String e130692;
            @XmlElement(name = "E130700", namespace = "mtvnCWCBHoldListInqResData")
            protected String e130700;
            @XmlElement(name = "E130698", namespace = "mtvnCWCBHoldListInqResData")
            protected String e130698;
            @XmlElement(name = "E130699", namespace = "mtvnCWCBHoldListInqResData")
            protected String e130699;
            @XmlElement(name = "E130701", namespace = "mtvnCWCBHoldListInqResData")
            protected String e130701;
            @XmlElement(name = "E130702", namespace = "mtvnCWCBHoldListInqResData")
            protected String e130702;
            @XmlElement(name = "E130711", namespace = "mtvnCWCBHoldListInqResData")
            protected String e130711;

            /**
             * Gets the value of the e130686 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getE130686() {
                return e130686;
            }

            /**
             * Sets the value of the e130686 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setE130686(String value) {
                this.e130686 = value;
            }

            /**
             * Gets the value of the e130687 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getE130687() {
                return e130687;
            }

            /**
             * Sets the value of the e130687 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setE130687(String value) {
                this.e130687 = value;
            }

            /**
             * Gets the value of the e130688 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getE130688() {
                return e130688;
            }

            /**
             * Sets the value of the e130688 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setE130688(String value) {
                this.e130688 = value;
            }

            /**
             * Gets the value of the e130689 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getE130689() {
                return e130689;
            }

            /**
             * Sets the value of the e130689 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setE130689(String value) {
                this.e130689 = value;
            }

            /**
             * Gets the value of the e130690 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getE130690() {
                return e130690;
            }

            /**
             * Sets the value of the e130690 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setE130690(String value) {
                this.e130690 = value;
            }

            /**
             * Gets the value of the e130691 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getE130691() {
                return e130691;
            }

            /**
             * Sets the value of the e130691 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setE130691(String value) {
                this.e130691 = value;
            }

            /**
             * Gets the value of the e130692 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getE130692() {
                return e130692;
            }

            /**
             * Sets the value of the e130692 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setE130692(String value) {
                this.e130692 = value;
            }

            /**
             * Gets the value of the e130700 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getE130700() {
                return e130700;
            }

            /**
             * Sets the value of the e130700 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setE130700(String value) {
                this.e130700 = value;
            }

            /**
             * Gets the value of the e130698 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getE130698() {
                return e130698;
            }

            /**
             * Sets the value of the e130698 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setE130698(String value) {
                this.e130698 = value;
            }

            /**
             * Gets the value of the e130699 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getE130699() {
                return e130699;
            }

            /**
             * Sets the value of the e130699 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setE130699(String value) {
                this.e130699 = value;
            }

            /**
             * Gets the value of the e130701 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getE130701() {
                return e130701;
            }

            /**
             * Sets the value of the e130701 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setE130701(String value) {
                this.e130701 = value;
            }

            /**
             * Gets the value of the e130702 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getE130702() {
                return e130702;
            }

            /**
             * Sets the value of the e130702 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setE130702(String value) {
                this.e130702 = value;
            }

            /**
             * Gets the value of the e130711 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getE130711() {
                return e130711;
            }

            /**
             * Sets the value of the e130711 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setE130711(String value) {
                this.e130711 = value;
            }

        }

    }

}
