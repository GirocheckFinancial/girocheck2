
package com.smartbt.vtsuite.ws.balanceInquiry;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;


/**
 * CBAcctInqWSV15
 * 
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.8
 * Generated source version: 2.0
 * 
 */
@WebService(name = "mtvnCWCBAcctInqWSV15Interface", targetNamespace = "mtvnCBAcctInqWSV15")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface MtvnCWCBAcctInqWSV15Interface {


    /**
     * 
     * @param messagePart
     * @return
     *     returns com.smartbt.vtsuite.ws.balanceInquiry.CBAcctInqMtvnSvcRes
     */
    @WebMethod(operationName = "CBAcctInq", action = "CBAcctInqWSV15:CBAcctInqMtvnSvcReqMsg")
    @WebResult(name = "CBAcctInqMtvnSvcRes", targetNamespace = "mtvnCWCBAcctInqSvcRes", partName = "messagePart")
    public CBAcctInqMtvnSvcRes cbAcctInq(
        @WebParam(name = "CBAcctInqMtvnSvcReq", targetNamespace = "mtvnCWCBAcctInqSvcReq", partName = "messagePart")
        CBAcctInqMtvnSvcReq messagePart);

}
