
package com.smartbt.vtsuite.dev;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.8
 * Generated source version: 2.0
 * 
 */
@WebService(name = "CheckServiceSoap", targetNamespace = "http://10.10.11.152/eFraudAPI")
public interface CheckServiceSoap {


    /**
     * 
     * @param idProof2DBarcode
     * @param checkBack
     * @param checkFront
     * @param username
     * @param password
     * @param idProof
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "CheckProcess", action = "http://10.10.11.152/eFraudAPI/CheckProcess")
    @WebResult(name = "CheckProcessResult", targetNamespace = "http://10.10.11.152/eFraudAPI")
    @RequestWrapper(localName = "CheckProcess", targetNamespace = "http://10.10.11.152/eFraudAPI", className = "_152._11._10._10.efraudapi.CheckProcess")
    @ResponseWrapper(localName = "CheckProcessResponse", targetNamespace = "http://10.10.11.152/eFraudAPI", className = "_152._11._10._10.efraudapi.CheckProcessResponse")
    public String checkProcess(
        @WebParam(name = "username", targetNamespace = "http://10.10.11.152/eFraudAPI")
        String username,
        @WebParam(name = "password", targetNamespace = "http://10.10.11.152/eFraudAPI")
        String password,
        @WebParam(name = "checkFront", targetNamespace = "http://10.10.11.152/eFraudAPI")
        byte[] checkFront,
        @WebParam(name = "checkBack", targetNamespace = "http://10.10.11.152/eFraudAPI")
        byte[] checkBack,
        @WebParam(name = "idProof", targetNamespace = "http://10.10.11.152/eFraudAPI")
        byte[] idProof,
        @WebParam(name = "idProof2dBarcode", targetNamespace = "http://10.10.11.152/eFraudAPI")
        String idProof2DBarcode);

}
