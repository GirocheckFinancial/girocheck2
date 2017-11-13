
package com.smartbt.vtsuite.ws.cardLoad;

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
 *         &lt;element name="E130484" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E130485" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E130582" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="E131150" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "e130484",
    "e130485",
    "e130582",
    "e131150",
    "errCde",
    "errResnCde",
    "applMsgLst"
})
@XmlRootElement(name = "CBPrpdLdUnldResData", namespace = "mtvnCWCBPrpdLdUnldResData")
public class CBPrpdLdUnldResData {

    @XmlElement(name = "E130013", namespace = "mtvnCWCBPrpdLdUnldResData")
    protected String e130013;
    @XmlElement(name = "E130484", namespace = "mtvnCWCBPrpdLdUnldResData")
    protected String e130484;
    @XmlElement(name = "E130485", namespace = "mtvnCWCBPrpdLdUnldResData")
    protected String e130485;
    @XmlElement(name = "E130582", namespace = "mtvnCWCBPrpdLdUnldResData")
    protected String e130582;
    @XmlElement(name = "E131150", namespace = "mtvnCWCBPrpdLdUnldResData")
    protected String e131150;
    @XmlElement(name = "ErrCde", namespace = "mtvnCWCBPrpdLdUnldResData")
    protected String errCde;
    @XmlElement(name = "ErrResnCde", namespace = "mtvnCWCBPrpdLdUnldResData")
    protected String errResnCde;
    @XmlElement(name = "ApplMsgLst", namespace = "mtvnCWCBPrpdLdUnldResData")
    protected CBPrpdLdUnldResData.ApplMsgLst applMsgLst;

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
     *     {@link CBPrpdLdUnldResData.ApplMsgLst }
     *     
     */
    public CBPrpdLdUnldResData.ApplMsgLst getApplMsgLst() {
        return applMsgLst;
    }

    /**
     * Sets the value of the applMsgLst property.
     * 
     * @param value
     *     allowed object is
     *     {@link CBPrpdLdUnldResData.ApplMsgLst }
     *     
     */
    public void setApplMsgLst(CBPrpdLdUnldResData.ApplMsgLst value) {
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

        @XmlElement(name = "ApplMsg", namespace = "mtvnCWCBPrpdLdUnldResData", required = true)
        protected List<CBPrpdLdUnldResData.ApplMsgLst.ApplMsg> applMsg;

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
         * {@link CBPrpdLdUnldResData.ApplMsgLst.ApplMsg }
         * 
         * 
         */
        public List<CBPrpdLdUnldResData.ApplMsgLst.ApplMsg> getApplMsg() {
            if (applMsg == null) {
                applMsg = new ArrayList<CBPrpdLdUnldResData.ApplMsgLst.ApplMsg>();
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

            @XmlElement(name = "ApplMsgApplId", namespace = "mtvnCWCBPrpdLdUnldResData")
            protected String applMsgApplId;
            @XmlElement(name = "ApplMsgNbr", namespace = "mtvnCWCBPrpdLdUnldResData")
            protected String applMsgNbr;
            @XmlElement(name = "ApplMsgTxt", namespace = "mtvnCWCBPrpdLdUnldResData")
            protected String applMsgTxt;
            @XmlElement(name = "ApplMsgErrInd", namespace = "mtvnCWCBPrpdLdUnldResData")
            protected String applMsgErrInd;
            @XmlElement(name = "ApplMsgFld", namespace = "mtvnCWCBPrpdLdUnldResData")
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
