package com.smartbt.vtsuite.manager;

import com.smartbt.vtsuite.dev.CheckServiceSoap;
import javax.jws.WebParam;

/**
 *
 * @author rrodriguez
 */
public class Proxy { 
    private Boolean isProd;
    private com.smartbt.vtsuite.dev.CheckServiceSoap dev;
    private com.smartbt.vtsuite.boundary.CheckServiceSoap prod;

    public Proxy() {
        String prodProperty = System.getProperty("PROD");
        this.isProd = prodProperty != null && prodProperty.equalsIgnoreCase("true");;

        if (isProd) {
            com.smartbt.vtsuite.boundary.CheckService prodService = new com.smartbt.vtsuite.boundary.CheckService();
            prod = prodService.getCheckServiceSoap();
        } else {
            com.smartbt.vtsuite.dev.CheckService prodService = new com.smartbt.vtsuite.dev.CheckService();
            dev = prodService.getCheckServiceSoap();
        }
    }

    public String checkProcess(String username, String password, byte[] checkFront, byte[] checkBack, byte[] idProof, String idProof2DBarcode) {
        if (isProd) {
            return prod.checkProcess(username, password, checkFront, checkBack, idProof, idProof2DBarcode);
        } else {
            return dev.checkProcess(username, password, checkFront, checkBack, idProof, idProof2DBarcode);
        }
    }

}
