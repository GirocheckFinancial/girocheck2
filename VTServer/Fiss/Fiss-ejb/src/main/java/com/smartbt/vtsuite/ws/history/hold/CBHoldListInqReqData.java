package com.smartbt.vtsuite.ws.history.hold;

import com.smartbt.vtsuite.util.FissParam;
import static com.smartbt.vtsuite.util.FissParam.CARD_NUMBER;
import static com.smartbt.vtsuite.util.FissParam.HOLD_STATUS_FILTER; 
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for anonymous complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
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
    "e130681"
})
@XmlRootElement(name = "CBHoldListInqReqData")
public class CBHoldListInqReqData {

    @XmlElement(name = "E130013", required = true)
    protected String e130013;
    @XmlElement(name = "E130679", required = true)
    protected String e130679;
    @XmlElement(name = "E130680")
    protected String e130680;
    @XmlElement(name = "E130681")
    protected String e130681;
 

    /**
     * Gets the value of the e130013 property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getE130013() {
        return e130013;
    }

    /**
     * Sets the value of the e130013 property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setE130013(String value) {
        this.e130013 = value;
    }

    /**
     * Gets the value of the e130679 property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getE130679() {
        return e130679;
    }

    /**
     * Sets the value of the e130679 property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setE130679(String value) {
        this.e130679 = value;
    }

    /**
     * Gets the value of the e130680 property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getE130680() {
        return e130680;
    }

    /**
     * Sets the value of the e130680 property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setE130680(String value) {
        this.e130680 = value;
    }

    /**
     * Gets the value of the e130681 property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getE130681() {
        return e130681;
    }

    /**
     * Sets the value of the e130681 property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setE130681(String value) {
        this.e130681 = value;
    }

}
