
package com.smartbt.vtsuite.ws.cardCashing;

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
 *         &lt;element name="E130487" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E130488" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E130486" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E130582" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E131150" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E202270" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E202271" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E202272" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E202273" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E202274" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "e130487",
    "e130488",
    "e130486",
    "e130582",
    "e131150",
    "e202270",
    "e202271",
    "e202272",
    "e202273",
    "e202274",
    "errCde",
    "errResnCde",
    "applMsgLst"
})
@XmlRootElement(name = "CBPrpdAdjResData", namespace = "mtvnCWCBPrpdAdjResData")
public class CBPrpdAdjResData {

    @XmlElement(name = "E130013", namespace = "mtvnCWCBPrpdAdjResData")
    protected String e130013;
    @XmlElement(name = "E130643", namespace = "mtvnCWCBPrpdAdjResData")
    protected String e130643;
    @XmlElement(name = "E130487", namespace = "mtvnCWCBPrpdAdjResData")
    protected String e130487;
    @XmlElement(name = "E130488", namespace = "mtvnCWCBPrpdAdjResData")
    protected String e130488;
    @XmlElement(name = "E130486", namespace = "mtvnCWCBPrpdAdjResData")
    protected String e130486;
    @XmlElement(name = "E130582", namespace = "mtvnCWCBPrpdAdjResData")
    protected String e130582;
    @XmlElement(name = "E131150", namespace = "mtvnCWCBPrpdAdjResData")
    protected String e131150;
    @XmlElement(name = "E202270", namespace = "mtvnCWCBPrpdAdjResData")
    protected String e202270;
    @XmlElement(name = "E202271", namespace = "mtvnCWCBPrpdAdjResData")
    protected String e202271;
    @XmlElement(name = "E202272", namespace = "mtvnCWCBPrpdAdjResData")
    protected String e202272;
    @XmlElement(name = "E202273", namespace = "mtvnCWCBPrpdAdjResData")
    protected String e202273;
    @XmlElement(name = "E202274", namespace = "mtvnCWCBPrpdAdjResData")
    protected String e202274;
    @XmlElement(name = "ErrCde", namespace = "mtvnCWCBPrpdAdjResData")
    protected String errCde;
    @XmlElement(name = "ErrResnCde", namespace = "mtvnCWCBPrpdAdjResData")
    protected String errResnCde;
    @XmlElement(name = "ApplMsgLst", namespace = "mtvnCWCBPrpdAdjResData")
    protected CBPrpdAdjResData.ApplMsgLst applMsgLst;

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
     * Gets the value of the e130487 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE130487() {
        return e130487;
    }

    /**
     * Sets the value of the e130487 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE130487(String value) {
        this.e130487 = value;
    }

    /**
     * Gets the value of the e130488 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE130488() {
        return e130488;
    }

    /**
     * Sets the value of the e130488 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE130488(String value) {
        this.e130488 = value;
    }

    /**
     * Gets the value of the e130486 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE130486() {
        return e130486;
    }

    /**
     * Sets the value of the e130486 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE130486(String value) {
        this.e130486 = value;
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

    /**
     * Gets the value of the e202270 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE202270() {
        return e202270;
    }

    /**
     * Sets the value of the e202270 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE202270(String value) {
        this.e202270 = value;
    }

    /**
     * Gets the value of the e202271 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE202271() {
        return e202271;
    }

    /**
     * Sets the value of the e202271 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE202271(String value) {
        this.e202271 = value;
    }

    /**
     * Gets the value of the e202272 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE202272() {
        return e202272;
    }

    /**
     * Sets the value of the e202272 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE202272(String value) {
        this.e202272 = value;
    }

    /**
     * Gets the value of the e202273 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE202273() {
        return e202273;
    }

    /**
     * Sets the value of the e202273 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE202273(String value) {
        this.e202273 = value;
    }

    /**
     * Gets the value of the e202274 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE202274() {
        return e202274;
    }

    /**
     * Sets the value of the e202274 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE202274(String value) {
        this.e202274 = value;
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
     *     {@link CBPrpdAdjResData.ApplMsgLst }
     *     
     */
    public CBPrpdAdjResData.ApplMsgLst getApplMsgLst() {
        return applMsgLst;
    }

    /**
     * Sets the value of the applMsgLst property.
     * 
     * @param value
     *     allowed object is
     *     {@link CBPrpdAdjResData.ApplMsgLst }
     *     
     */
    public void setApplMsgLst(CBPrpdAdjResData.ApplMsgLst value) {
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

        @XmlElement(name = "ApplMsg", namespace = "mtvnCWCBPrpdAdjResData", required = true)
        protected List<CBPrpdAdjResData.ApplMsgLst.ApplMsg> applMsg;

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
         * {@link CBPrpdAdjResData.ApplMsgLst.ApplMsg }
         * 
         * 
         */
        public List<CBPrpdAdjResData.ApplMsgLst.ApplMsg> getApplMsg() {
            if (applMsg == null) {
                applMsg = new ArrayList<CBPrpdAdjResData.ApplMsgLst.ApplMsg>();
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

            @XmlElement(name = "ApplMsgApplId", namespace = "mtvnCWCBPrpdAdjResData")
            protected String applMsgApplId;
            @XmlElement(name = "ApplMsgNbr", namespace = "mtvnCWCBPrpdAdjResData")
            protected String applMsgNbr;
            @XmlElement(name = "ApplMsgTxt", namespace = "mtvnCWCBPrpdAdjResData")
            protected String applMsgTxt;
            @XmlElement(name = "ApplMsgErrInd", namespace = "mtvnCWCBPrpdAdjResData")
            protected String applMsgErrInd;
            @XmlElement(name = "ApplMsgFld", namespace = "mtvnCWCBPrpdAdjResData")
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
