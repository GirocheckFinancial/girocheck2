
package com.smartbt.vtsuite.ws.history.details;

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
 *         &lt;element name="MtvnSvcVer">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="1.0"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="MsgUUID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Svc" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="SvcParms">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="ApplID">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;enumeration value="CB"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="SvcID">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;enumeration value="CBHistTxnDtlInq"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="SvcVer">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;enumeration value="6.0"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="RqstUUID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="MsgData">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;choice>
 *                             &lt;element name="DefaultResData" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element ref="{mtvnCWCBHistTxnDtlInqResData}CBHistTxnDtlInqResData"/>
 *                           &lt;/choice>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element ref="{mtvnCWCBHistTxnDtlInqSvcRes}ErrCde"/>
 *                   &lt;element ref="{mtvnCWCBHistTxnDtlInqSvcRes}ErrMsg" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element ref="{mtvnCWCBHistTxnDtlInqSvcRes}ErrCde"/>
 *         &lt;element ref="{mtvnCWCBHistTxnDtlInqSvcRes}ErrMsg" minOccurs="0"/>
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
    "mtvnSvcVer",
    "msgUUID",
    "svc",
    "errCde",
    "errMsg"
})
@XmlRootElement(name = "CBHistTxnDtlInqMtvnSvcRes", namespace = "mtvnCWCBHistTxnDtlInqSvcRes")
public class CBHistTxnDtlInqMtvnSvcRes {

    @XmlElement(name = "MtvnSvcVer", namespace = "mtvnCWCBHistTxnDtlInqSvcRes", required = true)
    protected String mtvnSvcVer;
    @XmlElement(name = "MsgUUID", namespace = "mtvnCWCBHistTxnDtlInqSvcRes", required = true)
    protected String msgUUID;
    @XmlElement(name = "Svc", namespace = "mtvnCWCBHistTxnDtlInqSvcRes", required = true)
    protected List<CBHistTxnDtlInqMtvnSvcRes.Svc> svc;
    @XmlElement(name = "ErrCde", namespace = "mtvnCWCBHistTxnDtlInqSvcRes", required = true)
    protected String errCde;
    @XmlElement(name = "ErrMsg", namespace = "mtvnCWCBHistTxnDtlInqSvcRes")
    protected String errMsg;

    /**
     * Gets the value of the mtvnSvcVer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMtvnSvcVer() {
        return mtvnSvcVer;
    }

    /**
     * Sets the value of the mtvnSvcVer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMtvnSvcVer(String value) {
        this.mtvnSvcVer = value;
    }

    /**
     * Gets the value of the msgUUID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMsgUUID() {
        return msgUUID;
    }

    /**
     * Sets the value of the msgUUID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMsgUUID(String value) {
        this.msgUUID = value;
    }

    /**
     * Gets the value of the svc property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the svc property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSvc().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CBHistTxnDtlInqMtvnSvcRes.Svc }
     * 
     * 
     */
    public List<CBHistTxnDtlInqMtvnSvcRes.Svc> getSvc() {
        if (svc == null) {
            svc = new ArrayList<CBHistTxnDtlInqMtvnSvcRes.Svc>();
        }
        return this.svc;
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
     * Gets the value of the errMsg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrMsg() {
        return errMsg;
    }

    /**
     * Sets the value of the errMsg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrMsg(String value) {
        this.errMsg = value;
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
     *         &lt;element name="SvcParms">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="ApplID">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;enumeration value="CB"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="SvcID">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;enumeration value="CBHistTxnDtlInq"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="SvcVer">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;enumeration value="6.0"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="RqstUUID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="MsgData">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;choice>
     *                   &lt;element name="DefaultResData" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element ref="{mtvnCWCBHistTxnDtlInqResData}CBHistTxnDtlInqResData"/>
     *                 &lt;/choice>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element ref="{mtvnCWCBHistTxnDtlInqSvcRes}ErrCde"/>
     *         &lt;element ref="{mtvnCWCBHistTxnDtlInqSvcRes}ErrMsg" minOccurs="0"/>
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
        "svcParms",
        "msgData",
        "errCde",
        "errMsg"
    })
    public static class Svc {

        @XmlElement(name = "SvcParms", namespace = "mtvnCWCBHistTxnDtlInqSvcRes", required = true)
        protected CBHistTxnDtlInqMtvnSvcRes.Svc.SvcParms svcParms;
        @XmlElement(name = "MsgData", namespace = "mtvnCWCBHistTxnDtlInqSvcRes", required = true)
        protected CBHistTxnDtlInqMtvnSvcRes.Svc.MsgData msgData;
        @XmlElement(name = "ErrCde", namespace = "mtvnCWCBHistTxnDtlInqSvcRes", required = true)
        protected String errCde;
        @XmlElement(name = "ErrMsg", namespace = "mtvnCWCBHistTxnDtlInqSvcRes")
        protected String errMsg;

        /**
         * Gets the value of the svcParms property.
         * 
         * @return
         *     possible object is
         *     {@link CBHistTxnDtlInqMtvnSvcRes.Svc.SvcParms }
         *     
         */
        public CBHistTxnDtlInqMtvnSvcRes.Svc.SvcParms getSvcParms() {
            return svcParms;
        }

        /**
         * Sets the value of the svcParms property.
         * 
         * @param value
         *     allowed object is
         *     {@link CBHistTxnDtlInqMtvnSvcRes.Svc.SvcParms }
         *     
         */
        public void setSvcParms(CBHistTxnDtlInqMtvnSvcRes.Svc.SvcParms value) {
            this.svcParms = value;
        }

        /**
         * Gets the value of the msgData property.
         * 
         * @return
         *     possible object is
         *     {@link CBHistTxnDtlInqMtvnSvcRes.Svc.MsgData }
         *     
         */
        public CBHistTxnDtlInqMtvnSvcRes.Svc.MsgData getMsgData() {
            return msgData;
        }

        /**
         * Sets the value of the msgData property.
         * 
         * @param value
         *     allowed object is
         *     {@link CBHistTxnDtlInqMtvnSvcRes.Svc.MsgData }
         *     
         */
        public void setMsgData(CBHistTxnDtlInqMtvnSvcRes.Svc.MsgData value) {
            this.msgData = value;
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
         * Gets the value of the errMsg property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getErrMsg() {
            return errMsg;
        }

        /**
         * Sets the value of the errMsg property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setErrMsg(String value) {
            this.errMsg = value;
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
         *       &lt;choice>
         *         &lt;element name="DefaultResData" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element ref="{mtvnCWCBHistTxnDtlInqResData}CBHistTxnDtlInqResData"/>
         *       &lt;/choice>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "defaultResData",
            "cbHistTxnDtlInqResData"
        })
        public static class MsgData {

            @XmlElement(name = "DefaultResData", namespace = "mtvnCWCBHistTxnDtlInqSvcRes")
            protected String defaultResData;
            @XmlElement(name = "CBHistTxnDtlInqResData", namespace = "mtvnCWCBHistTxnDtlInqResData")
            protected CBHistTxnDtlInqResData cbHistTxnDtlInqResData;

            /**
             * Gets the value of the defaultResData property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDefaultResData() {
                return defaultResData;
            }

            /**
             * Sets the value of the defaultResData property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDefaultResData(String value) {
                this.defaultResData = value;
            }

            /**
             * Gets the value of the cbHistTxnDtlInqResData property.
             * 
             * @return
             *     possible object is
             *     {@link CBHistTxnDtlInqResData }
             *     
             */
            public CBHistTxnDtlInqResData getCBHistTxnDtlInqResData() {
                return cbHistTxnDtlInqResData;
            }

            /**
             * Sets the value of the cbHistTxnDtlInqResData property.
             * 
             * @param value
             *     allowed object is
             *     {@link CBHistTxnDtlInqResData }
             *     
             */
            public void setCBHistTxnDtlInqResData(CBHistTxnDtlInqResData value) {
                this.cbHistTxnDtlInqResData = value;
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
         *         &lt;element name="ApplID">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;enumeration value="CB"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="SvcID">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;enumeration value="CBHistTxnDtlInq"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="SvcVer">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;enumeration value="6.0"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="RqstUUID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
            "applID",
            "svcID",
            "svcVer",
            "rqstUUID"
        })
        public static class SvcParms {

            @XmlElement(name = "ApplID", namespace = "mtvnCWCBHistTxnDtlInqSvcRes", required = true)
            protected String applID;
            @XmlElement(name = "SvcID", namespace = "mtvnCWCBHistTxnDtlInqSvcRes", required = true)
            protected String svcID;
            @XmlElement(name = "SvcVer", namespace = "mtvnCWCBHistTxnDtlInqSvcRes", required = true)
            protected String svcVer;
            @XmlElement(name = "RqstUUID", namespace = "mtvnCWCBHistTxnDtlInqSvcRes")
            protected String rqstUUID;

            /**
             * Gets the value of the applID property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getApplID() {
                return applID;
            }

            /**
             * Sets the value of the applID property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setApplID(String value) {
                this.applID = value;
            }

            /**
             * Gets the value of the svcID property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSvcID() {
                return svcID;
            }

            /**
             * Sets the value of the svcID property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSvcID(String value) {
                this.svcID = value;
            }

            /**
             * Gets the value of the svcVer property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSvcVer() {
                return svcVer;
            }

            /**
             * Sets the value of the svcVer property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSvcVer(String value) {
                this.svcVer = value;
            }

            /**
             * Gets the value of the rqstUUID property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getRqstUUID() {
                return rqstUUID;
            }

            /**
             * Sets the value of the rqstUUID property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setRqstUUID(String value) {
                this.rqstUUID = value;
            }

        }

    }

}