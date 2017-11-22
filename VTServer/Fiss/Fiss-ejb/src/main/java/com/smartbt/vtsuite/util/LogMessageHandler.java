package com.smartbt.vtsuite.util;

import java.util.HashSet;
import java.util.Set;
import javax.xml.namespace.QName; 
import javax.xml.soap.SOAPMessage; 
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

/**
 *
 * @author rrodriguez
 */
public class LogMessageHandler implements SOAPHandler<SOAPMessageContext> {

    @Override
    public Set<QName> getHeaders() {
       return new HashSet<QName>();
    }

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        System.out.println("************* Logging *************** ");

        SOAPMessage msg = context.getMessage(); //Line 1
        try {
            msg.writeTo(System.out);  //Line 3
        } catch (Exception ex) {
           ex.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
       return true;
    }

    @Override
    public void close(MessageContext context) {
    }

}
