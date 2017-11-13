
package com.smartbt.vtsuite.ws.cardActivation;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.8
 * Generated source version: 2.0
 * 
 */
@WebServiceClient(name = "mtvnCWCBNegFleMaintWSV1", targetNamespace = "mtvnCBNegFleMaintWSV1", wsdlLocation = "file:/C:/Glassfish/fiss_wsdl/cardActivation/CBNegFleMaintWSV1.wsdl")
public class MtvnCWCBNegFleMaintWSV1
    extends Service
{

    private final static URL MTVNCWCBNEGFLEMAINTWSV1_WSDL_LOCATION;
    private final static WebServiceException MTVNCWCBNEGFLEMAINTWSV1_EXCEPTION;
    private final static QName MTVNCWCBNEGFLEMAINTWSV1_QNAME = new QName("mtvnCBNegFleMaintWSV1", "mtvnCWCBNegFleMaintWSV1");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/C:/Glassfish/fiss_wsdl/cardActivation/CBNegFleMaintWSV1.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        MTVNCWCBNEGFLEMAINTWSV1_WSDL_LOCATION = url;
        MTVNCWCBNEGFLEMAINTWSV1_EXCEPTION = e;
    }

    public MtvnCWCBNegFleMaintWSV1() {
        super(__getWsdlLocation(), MTVNCWCBNEGFLEMAINTWSV1_QNAME);
    }

    public MtvnCWCBNegFleMaintWSV1(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    /**
     * 
     * @return
     *     returns MtvnCWCBNegFleMaintWSV1Interface
     */
    @WebEndpoint(name = "mtvnCWCBNegFleMaintWSV1Port")
    public MtvnCWCBNegFleMaintWSV1Interface getMtvnCWCBNegFleMaintWSV1Port() {
        return super.getPort(new QName("mtvnCBNegFleMaintWSV1", "mtvnCWCBNegFleMaintWSV1Port"), MtvnCWCBNegFleMaintWSV1Interface.class);
    }

    private static URL __getWsdlLocation() {
        if (MTVNCWCBNEGFLEMAINTWSV1_EXCEPTION!= null) {
            throw MTVNCWCBNEGFLEMAINTWSV1_EXCEPTION;
        }
        return MTVNCWCBNEGFLEMAINTWSV1_WSDL_LOCATION;
    }

}