
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
 *         &lt;element name="MtvnSvcVer">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="1.0"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="MsgUUID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PrcsParms">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="SrcID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="TestInd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
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
 *                                   &lt;enumeration value="CBNegFleMaint"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="SvcVer">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;enumeration value="1.0"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="SvcNme" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="RqstUUID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="RoutingID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="Src" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="FieldList" minOccurs="0">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="Field" maxOccurs="unbounded">
 *                                                   &lt;complexType>
 *                                                     &lt;complexContent>
 *                                                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                         &lt;sequence>
 *                                                           &lt;element name="FieldNme" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                           &lt;element name="FieldVal" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                         &lt;/sequence>
 *                                                       &lt;/restriction>
 *                                                     &lt;/complexContent>
 *                                                   &lt;/complexType>
 *                                                 &lt;/element>
 *                                               &lt;/sequence>
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                       &lt;element name="GrpBusFunc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                       &lt;element name="TranInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                       &lt;element name="CustDefMsgSrc1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                       &lt;element name="CustDefMsgSrc2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="Security">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;choice>
 *                             &lt;element name="NoAuth" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="BasicAuth">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="UsrID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                       &lt;element name="Pwd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="SessionAuth">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;choice>
 *                                       &lt;element name="SessionID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                       &lt;element name="HttpSessionID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                     &lt;/choice>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="SAMLAuth">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="Assertion" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/choice>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="MsgData">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element ref="{mtvnCWCBNegFleMaintReqData}CBNegFleMaintReqData"/>
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
    "prcsParms",
    "svc"
})
@XmlRootElement(name = "CBNegFleMaintMtvnSvcReq", namespace = "mtvnCWCBNegFleMaintSvcReq")
public class CBNegFleMaintMtvnSvcReq {

    @XmlElement(name = "MtvnSvcVer", namespace = "mtvnCWCBNegFleMaintSvcReq", required = true)
    protected String mtvnSvcVer;
    @XmlElement(name = "MsgUUID", namespace = "mtvnCWCBNegFleMaintSvcReq", required = true)
    protected String msgUUID;
    @XmlElement(name = "PrcsParms", namespace = "mtvnCWCBNegFleMaintSvcReq", required = true)
    protected CBNegFleMaintMtvnSvcReq.PrcsParms prcsParms;
    @XmlElement(name = "Svc", namespace = "mtvnCWCBNegFleMaintSvcReq", required = true)
    protected List<CBNegFleMaintMtvnSvcReq.Svc> svc;

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
     * Gets the value of the prcsParms property.
     * 
     * @return
     *     possible object is
     *     {@link CBNegFleMaintMtvnSvcReq.PrcsParms }
     *     
     */
    public CBNegFleMaintMtvnSvcReq.PrcsParms getPrcsParms() {
        return prcsParms;
    }

    /**
     * Sets the value of the prcsParms property.
     * 
     * @param value
     *     allowed object is
     *     {@link CBNegFleMaintMtvnSvcReq.PrcsParms }
     *     
     */
    public void setPrcsParms(CBNegFleMaintMtvnSvcReq.PrcsParms value) {
        this.prcsParms = value;
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
     * {@link CBNegFleMaintMtvnSvcReq.Svc }
     * 
     * 
     */
    public List<CBNegFleMaintMtvnSvcReq.Svc> getSvc() {
        if (svc == null) {
            svc = new ArrayList<CBNegFleMaintMtvnSvcReq.Svc>();
        }
        return this.svc;
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
     *         &lt;element name="SrcID" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="TestInd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "srcID",
        "testInd"
    })
    public static class PrcsParms {

        @XmlElement(name = "SrcID", namespace = "mtvnCWCBNegFleMaintSvcReq", required = true)
        protected String srcID;
        @XmlElement(name = "TestInd", namespace = "mtvnCWCBNegFleMaintSvcReq")
        protected String testInd;

        /**
         * Gets the value of the srcID property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSrcID() {
            return srcID;
        }

        /**
         * Sets the value of the srcID property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSrcID(String value) {
            this.srcID = value;
        }

        /**
         * Gets the value of the testInd property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTestInd() {
            return testInd;
        }

        /**
         * Sets the value of the testInd property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTestInd(String value) {
            this.testInd = value;
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
     *                         &lt;enumeration value="CBNegFleMaint"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="SvcVer">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;enumeration value="1.0"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="SvcNme" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="RqstUUID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="RoutingID" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="Src" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="FieldList" minOccurs="0">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="Field" maxOccurs="unbounded">
     *                                         &lt;complexType>
     *                                           &lt;complexContent>
     *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                               &lt;sequence>
     *                                                 &lt;element name="FieldNme" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                                 &lt;element name="FieldVal" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                               &lt;/sequence>
     *                                             &lt;/restriction>
     *                                           &lt;/complexContent>
     *                                         &lt;/complexType>
     *                                       &lt;/element>
     *                                     &lt;/sequence>
     *                                   &lt;/restriction>
     *                                 &lt;/complexContent>
     *                               &lt;/complexType>
     *                             &lt;/element>
     *                             &lt;element name="GrpBusFunc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                             &lt;element name="TranInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                             &lt;element name="CustDefMsgSrc1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                             &lt;element name="CustDefMsgSrc2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
     *         &lt;element name="Security">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;choice>
     *                   &lt;element name="NoAuth" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="BasicAuth">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="UsrID" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                             &lt;element name="Pwd" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="SessionAuth">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;choice>
     *                             &lt;element name="SessionID" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                             &lt;element name="HttpSessionID" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                           &lt;/choice>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="SAMLAuth">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="Assertion" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/choice>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="MsgData">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element ref="{mtvnCWCBNegFleMaintReqData}CBNegFleMaintReqData"/>
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
        "svcParms",
        "security",
        "msgData"
    })
    public static class Svc {

        @XmlElement(name = "SvcParms", namespace = "mtvnCWCBNegFleMaintSvcReq", required = true)
        protected CBNegFleMaintMtvnSvcReq.Svc.SvcParms svcParms;
        @XmlElement(name = "Security", namespace = "mtvnCWCBNegFleMaintSvcReq", required = true)
        protected CBNegFleMaintMtvnSvcReq.Svc.Security security;
        @XmlElement(name = "MsgData", namespace = "mtvnCWCBNegFleMaintSvcReq", required = true)
        protected CBNegFleMaintMtvnSvcReq.Svc.MsgData msgData;

        /**
         * Gets the value of the svcParms property.
         * 
         * @return
         *     possible object is
         *     {@link CBNegFleMaintMtvnSvcReq.Svc.SvcParms }
         *     
         */
        public CBNegFleMaintMtvnSvcReq.Svc.SvcParms getSvcParms() {
            return svcParms;
        }

        /**
         * Sets the value of the svcParms property.
         * 
         * @param value
         *     allowed object is
         *     {@link CBNegFleMaintMtvnSvcReq.Svc.SvcParms }
         *     
         */
        public void setSvcParms(CBNegFleMaintMtvnSvcReq.Svc.SvcParms value) {
            this.svcParms = value;
        }

        /**
         * Gets the value of the security property.
         * 
         * @return
         *     possible object is
         *     {@link CBNegFleMaintMtvnSvcReq.Svc.Security }
         *     
         */
        public CBNegFleMaintMtvnSvcReq.Svc.Security getSecurity() {
            return security;
        }

        /**
         * Sets the value of the security property.
         * 
         * @param value
         *     allowed object is
         *     {@link CBNegFleMaintMtvnSvcReq.Svc.Security }
         *     
         */
        public void setSecurity(CBNegFleMaintMtvnSvcReq.Svc.Security value) {
            this.security = value;
        }

        /**
         * Gets the value of the msgData property.
         * 
         * @return
         *     possible object is
         *     {@link CBNegFleMaintMtvnSvcReq.Svc.MsgData }
         *     
         */
        public CBNegFleMaintMtvnSvcReq.Svc.MsgData getMsgData() {
            return msgData;
        }

        /**
         * Sets the value of the msgData property.
         * 
         * @param value
         *     allowed object is
         *     {@link CBNegFleMaintMtvnSvcReq.Svc.MsgData }
         *     
         */
        public void setMsgData(CBNegFleMaintMtvnSvcReq.Svc.MsgData value) {
            this.msgData = value;
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
         *         &lt;element ref="{mtvnCWCBNegFleMaintReqData}CBNegFleMaintReqData"/>
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
            "cbNegFleMaintReqData"
        })
        public static class MsgData {

            @XmlElement(name = "CBNegFleMaintReqData", required = true)
            protected CBNegFleMaintReqData cbNegFleMaintReqData;

            /**
             * Gets the value of the cbNegFleMaintReqData property.
             * 
             * @return
             *     possible object is
             *     {@link CBNegFleMaintReqData }
             *     
             */
            public CBNegFleMaintReqData getCBNegFleMaintReqData() {
                return cbNegFleMaintReqData;
            }

            /**
             * Sets the value of the cbNegFleMaintReqData property.
             * 
             * @param value
             *     allowed object is
             *     {@link CBNegFleMaintReqData }
             *     
             */
            public void setCBNegFleMaintReqData(CBNegFleMaintReqData value) {
                this.cbNegFleMaintReqData = value;
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
         *       &lt;choice>
         *         &lt;element name="NoAuth" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="BasicAuth">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="UsrID" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                   &lt;element name="Pwd" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="SessionAuth">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;choice>
         *                   &lt;element name="SessionID" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                   &lt;element name="HttpSessionID" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                 &lt;/choice>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="SAMLAuth">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="Assertion" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
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
            "noAuth",
            "basicAuth",
            "sessionAuth",
            "samlAuth"
        })
        public static class Security {

            @XmlElement(name = "NoAuth", namespace = "mtvnCWCBNegFleMaintSvcReq")
            protected String noAuth;
            @XmlElement(name = "BasicAuth", namespace = "mtvnCWCBNegFleMaintSvcReq")
            protected CBNegFleMaintMtvnSvcReq.Svc.Security.BasicAuth basicAuth;
            @XmlElement(name = "SessionAuth", namespace = "mtvnCWCBNegFleMaintSvcReq")
            protected CBNegFleMaintMtvnSvcReq.Svc.Security.SessionAuth sessionAuth;
            @XmlElement(name = "SAMLAuth", namespace = "mtvnCWCBNegFleMaintSvcReq")
            protected CBNegFleMaintMtvnSvcReq.Svc.Security.SAMLAuth samlAuth;

            /**
             * Gets the value of the noAuth property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getNoAuth() {
                return noAuth;
            }

            /**
             * Sets the value of the noAuth property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setNoAuth(String value) {
                this.noAuth = value;
            }

            /**
             * Gets the value of the basicAuth property.
             * 
             * @return
             *     possible object is
             *     {@link CBNegFleMaintMtvnSvcReq.Svc.Security.BasicAuth }
             *     
             */
            public CBNegFleMaintMtvnSvcReq.Svc.Security.BasicAuth getBasicAuth() {
                return basicAuth;
            }

            /**
             * Sets the value of the basicAuth property.
             * 
             * @param value
             *     allowed object is
             *     {@link CBNegFleMaintMtvnSvcReq.Svc.Security.BasicAuth }
             *     
             */
            public void setBasicAuth(CBNegFleMaintMtvnSvcReq.Svc.Security.BasicAuth value) {
                this.basicAuth = value;
            }

            /**
             * Gets the value of the sessionAuth property.
             * 
             * @return
             *     possible object is
             *     {@link CBNegFleMaintMtvnSvcReq.Svc.Security.SessionAuth }
             *     
             */
            public CBNegFleMaintMtvnSvcReq.Svc.Security.SessionAuth getSessionAuth() {
                return sessionAuth;
            }

            /**
             * Sets the value of the sessionAuth property.
             * 
             * @param value
             *     allowed object is
             *     {@link CBNegFleMaintMtvnSvcReq.Svc.Security.SessionAuth }
             *     
             */
            public void setSessionAuth(CBNegFleMaintMtvnSvcReq.Svc.Security.SessionAuth value) {
                this.sessionAuth = value;
            }

            /**
             * Gets the value of the samlAuth property.
             * 
             * @return
             *     possible object is
             *     {@link CBNegFleMaintMtvnSvcReq.Svc.Security.SAMLAuth }
             *     
             */
            public CBNegFleMaintMtvnSvcReq.Svc.Security.SAMLAuth getSAMLAuth() {
                return samlAuth;
            }

            /**
             * Sets the value of the samlAuth property.
             * 
             * @param value
             *     allowed object is
             *     {@link CBNegFleMaintMtvnSvcReq.Svc.Security.SAMLAuth }
             *     
             */
            public void setSAMLAuth(CBNegFleMaintMtvnSvcReq.Svc.Security.SAMLAuth value) {
                this.samlAuth = value;
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
             *         &lt;element name="UsrID" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *         &lt;element name="Pwd" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
                "usrID",
                "pwd"
            })
            public static class BasicAuth {

                @XmlElement(name = "UsrID", namespace = "mtvnCWCBNegFleMaintSvcReq", required = true)
                protected String usrID;
                @XmlElement(name = "Pwd", namespace = "mtvnCWCBNegFleMaintSvcReq", required = true)
                protected String pwd;

                /**
                 * Gets the value of the usrID property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getUsrID() {
                    return usrID;
                }

                /**
                 * Sets the value of the usrID property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setUsrID(String value) {
                    this.usrID = value;
                }

                /**
                 * Gets the value of the pwd property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getPwd() {
                    return pwd;
                }

                /**
                 * Sets the value of the pwd property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setPwd(String value) {
                    this.pwd = value;
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
             *         &lt;element name="Assertion" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
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
                "assertion"
            })
            public static class SAMLAuth {

                @XmlElement(name = "Assertion", namespace = "mtvnCWCBNegFleMaintSvcReq", required = true)
                protected List<String> assertion;

                /**
                 * Gets the value of the assertion property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the assertion property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getAssertion().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link String }
                 * 
                 * 
                 */
                public List<String> getAssertion() {
                    if (assertion == null) {
                        assertion = new ArrayList<String>();
                    }
                    return this.assertion;
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
             *       &lt;choice>
             *         &lt;element name="SessionID" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *         &lt;element name="HttpSessionID" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
                "sessionID",
                "httpSessionID"
            })
            public static class SessionAuth {

                @XmlElement(name = "SessionID", namespace = "mtvnCWCBNegFleMaintSvcReq")
                protected String sessionID;
                @XmlElement(name = "HttpSessionID", namespace = "mtvnCWCBNegFleMaintSvcReq")
                protected String httpSessionID;

                /**
                 * Gets the value of the sessionID property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getSessionID() {
                    return sessionID;
                }

                /**
                 * Sets the value of the sessionID property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setSessionID(String value) {
                    this.sessionID = value;
                }

                /**
                 * Gets the value of the httpSessionID property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getHttpSessionID() {
                    return httpSessionID;
                }

                /**
                 * Sets the value of the httpSessionID property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setHttpSessionID(String value) {
                    this.httpSessionID = value;
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
         *               &lt;enumeration value="CBNegFleMaint"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="SvcVer">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;enumeration value="1.0"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="SvcNme" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="RqstUUID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="RoutingID" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="Src" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="FieldList" minOccurs="0">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="Field" maxOccurs="unbounded">
         *                               &lt;complexType>
         *                                 &lt;complexContent>
         *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                                     &lt;sequence>
         *                                       &lt;element name="FieldNme" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                                       &lt;element name="FieldVal" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                                     &lt;/sequence>
         *                                   &lt;/restriction>
         *                                 &lt;/complexContent>
         *                               &lt;/complexType>
         *                             &lt;/element>
         *                           &lt;/sequence>
         *                         &lt;/restriction>
         *                       &lt;/complexContent>
         *                     &lt;/complexType>
         *                   &lt;/element>
         *                   &lt;element name="GrpBusFunc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                   &lt;element name="TranInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                   &lt;element name="CustDefMsgSrc1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                   &lt;element name="CustDefMsgSrc2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
            "applID",
            "svcID",
            "svcVer",
            "svcNme",
            "rqstUUID",
            "routingID",
            "src"
        })
        public static class SvcParms {

            @XmlElement(name = "ApplID", namespace = "mtvnCWCBNegFleMaintSvcReq", required = true)
            protected String applID;
            @XmlElement(name = "SvcID", namespace = "mtvnCWCBNegFleMaintSvcReq", required = true)
            protected String svcID;
            @XmlElement(name = "SvcVer", namespace = "mtvnCWCBNegFleMaintSvcReq", required = true)
            protected String svcVer;
            @XmlElement(name = "SvcNme", namespace = "mtvnCWCBNegFleMaintSvcReq")
            protected String svcNme;
            @XmlElement(name = "RqstUUID", namespace = "mtvnCWCBNegFleMaintSvcReq")
            protected String rqstUUID;
            @XmlElement(name = "RoutingID", namespace = "mtvnCWCBNegFleMaintSvcReq", required = true)
            protected String routingID;
            @XmlElement(name = "Src", namespace = "mtvnCWCBNegFleMaintSvcReq")
            protected CBNegFleMaintMtvnSvcReq.Svc.SvcParms.Src src;

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
             * Gets the value of the svcNme property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSvcNme() {
                return svcNme;
            }

            /**
             * Sets the value of the svcNme property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSvcNme(String value) {
                this.svcNme = value;
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

            /**
             * Gets the value of the routingID property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getRoutingID() {
                return routingID;
            }

            /**
             * Sets the value of the routingID property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setRoutingID(String value) {
                this.routingID = value;
            }

            /**
             * Gets the value of the src property.
             * 
             * @return
             *     possible object is
             *     {@link CBNegFleMaintMtvnSvcReq.Svc.SvcParms.Src }
             *     
             */
            public CBNegFleMaintMtvnSvcReq.Svc.SvcParms.Src getSrc() {
                return src;
            }

            /**
             * Sets the value of the src property.
             * 
             * @param value
             *     allowed object is
             *     {@link CBNegFleMaintMtvnSvcReq.Svc.SvcParms.Src }
             *     
             */
            public void setSrc(CBNegFleMaintMtvnSvcReq.Svc.SvcParms.Src value) {
                this.src = value;
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
             *         &lt;element name="FieldList" minOccurs="0">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="Field" maxOccurs="unbounded">
             *                     &lt;complexType>
             *                       &lt;complexContent>
             *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                           &lt;sequence>
             *                             &lt;element name="FieldNme" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *                             &lt;element name="FieldVal" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
             *         &lt;element name="GrpBusFunc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
             *         &lt;element name="TranInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
             *         &lt;element name="CustDefMsgSrc1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
             *         &lt;element name="CustDefMsgSrc2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
                "fieldList",
                "grpBusFunc",
                "tranInfo",
                "custDefMsgSrc1",
                "custDefMsgSrc2"
            })
            public static class Src {

                @XmlElement(name = "FieldList", namespace = "mtvnCWCBNegFleMaintSvcReq")
                protected CBNegFleMaintMtvnSvcReq.Svc.SvcParms.Src.FieldList fieldList;
                @XmlElement(name = "GrpBusFunc", namespace = "mtvnCWCBNegFleMaintSvcReq")
                protected String grpBusFunc;
                @XmlElement(name = "TranInfo", namespace = "mtvnCWCBNegFleMaintSvcReq")
                protected String tranInfo;
                @XmlElement(name = "CustDefMsgSrc1", namespace = "mtvnCWCBNegFleMaintSvcReq")
                protected String custDefMsgSrc1;
                @XmlElement(name = "CustDefMsgSrc2", namespace = "mtvnCWCBNegFleMaintSvcReq")
                protected String custDefMsgSrc2;

                /**
                 * Gets the value of the fieldList property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link CBNegFleMaintMtvnSvcReq.Svc.SvcParms.Src.FieldList }
                 *     
                 */
                public CBNegFleMaintMtvnSvcReq.Svc.SvcParms.Src.FieldList getFieldList() {
                    return fieldList;
                }

                /**
                 * Sets the value of the fieldList property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link CBNegFleMaintMtvnSvcReq.Svc.SvcParms.Src.FieldList }
                 *     
                 */
                public void setFieldList(CBNegFleMaintMtvnSvcReq.Svc.SvcParms.Src.FieldList value) {
                    this.fieldList = value;
                }

                /**
                 * Gets the value of the grpBusFunc property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getGrpBusFunc() {
                    return grpBusFunc;
                }

                /**
                 * Sets the value of the grpBusFunc property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setGrpBusFunc(String value) {
                    this.grpBusFunc = value;
                }

                /**
                 * Gets the value of the tranInfo property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getTranInfo() {
                    return tranInfo;
                }

                /**
                 * Sets the value of the tranInfo property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setTranInfo(String value) {
                    this.tranInfo = value;
                }

                /**
                 * Gets the value of the custDefMsgSrc1 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getCustDefMsgSrc1() {
                    return custDefMsgSrc1;
                }

                /**
                 * Sets the value of the custDefMsgSrc1 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setCustDefMsgSrc1(String value) {
                    this.custDefMsgSrc1 = value;
                }

                /**
                 * Gets the value of the custDefMsgSrc2 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getCustDefMsgSrc2() {
                    return custDefMsgSrc2;
                }

                /**
                 * Sets the value of the custDefMsgSrc2 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setCustDefMsgSrc2(String value) {
                    this.custDefMsgSrc2 = value;
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
                 *         &lt;element name="Field" maxOccurs="unbounded">
                 *           &lt;complexType>
                 *             &lt;complexContent>
                 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *                 &lt;sequence>
                 *                   &lt;element name="FieldNme" type="{http://www.w3.org/2001/XMLSchema}string"/>
                 *                   &lt;element name="FieldVal" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
                    "field"
                })
                public static class FieldList {

                    @XmlElement(name = "Field", namespace = "mtvnCWCBNegFleMaintSvcReq", required = true)
                    protected List<CBNegFleMaintMtvnSvcReq.Svc.SvcParms.Src.FieldList.Field> field;

                    /**
                     * Gets the value of the field property.
                     * 
                     * <p>
                     * This accessor method returns a reference to the live list,
                     * not a snapshot. Therefore any modification you make to the
                     * returned list will be present inside the JAXB object.
                     * This is why there is not a <CODE>set</CODE> method for the field property.
                     * 
                     * <p>
                     * For example, to add a new item, do as follows:
                     * <pre>
                     *    getField().add(newItem);
                     * </pre>
                     * 
                     * 
                     * <p>
                     * Objects of the following type(s) are allowed in the list
                     * {@link CBNegFleMaintMtvnSvcReq.Svc.SvcParms.Src.FieldList.Field }
                     * 
                     * 
                     */
                    public List<CBNegFleMaintMtvnSvcReq.Svc.SvcParms.Src.FieldList.Field> getField() {
                        if (field == null) {
                            field = new ArrayList<CBNegFleMaintMtvnSvcReq.Svc.SvcParms.Src.FieldList.Field>();
                        }
                        return this.field;
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
                     *         &lt;element name="FieldNme" type="{http://www.w3.org/2001/XMLSchema}string"/>
                     *         &lt;element name="FieldVal" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
                        "fieldNme",
                        "fieldVal"
                    })
                    public static class Field {

                        @XmlElement(name = "FieldNme", namespace = "mtvnCWCBNegFleMaintSvcReq", required = true)
                        protected String fieldNme;
                        @XmlElement(name = "FieldVal", namespace = "mtvnCWCBNegFleMaintSvcReq", required = true)
                        protected String fieldVal;

                        /**
                         * Gets the value of the fieldNme property.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getFieldNme() {
                            return fieldNme;
                        }

                        /**
                         * Sets the value of the fieldNme property.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setFieldNme(String value) {
                            this.fieldNme = value;
                        }

                        /**
                         * Gets the value of the fieldVal property.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getFieldVal() {
                            return fieldVal;
                        }

                        /**
                         * Sets the value of the fieldVal property.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setFieldVal(String value) {
                            this.fieldVal = value;
                        }

                    }

                }

            }

        }

    }

}
