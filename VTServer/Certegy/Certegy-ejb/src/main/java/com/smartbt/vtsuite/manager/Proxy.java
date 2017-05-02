package com.smartbt.vtsuite.manager;
 
import com.smartbt.vtsuite.boundary.PCA;
import com.smartbt.vtsuite.boundary.PCAEchoRequest;
import com.smartbt.vtsuite.boundary.PCAEchoResponse;
import static com.smartbt.vtsuite.manager.CertegyBusinessLogic.CERTEGY_SITE_ID;
import java.math.BigInteger;


/**
 *
 * @author rrodriguez
 */
public class Proxy { 
    private Boolean isProd;
    private PCA port;
    private com.smartbt.vtsuite.boundary.prod.PCAService primary;
    private com.smartbt.vtsuite.boundary.PCAService secondary;
    

    public Proxy() { 
       if(!getPrimaryPort()){
           getSecondaryPort();
       }
    }

    public PCA getPort() {
        return port;
    } 

    private boolean getPrimaryPort(){ 
        try {
 
                System.out.println("*************** CONNECTING TO CERTEGY PRIMARY ************");                
                 primary = new com.smartbt.vtsuite.boundary.prod.PCAService();                    
                        
                 port = primary.getPCAPort();
                
                System.out.println("(primary)-port succesfully created for primary url...");
                System.out.println("(primary)-sending ping to using site id:: " + CERTEGY_SITE_ID);
                
                PCAEchoRequest request = new PCAEchoRequest();
                request.setSiteID(CERTEGY_SITE_ID);
                request.setEchoNumber(new BigInteger("1000"));
                PCAEchoResponse certegyResponse = port.echo(request);
                System.out.println("(primary)-ping response was:: " + (certegyResponse == null ? "NULL" : "Successful"));
                return true;
        } catch (Exception e) { 
            System.out.println("*************** UNABLE TO CONNECT CERTEGY PRIMARY URL ");
            e.printStackTrace();
            port = null;
            return false;
        } 
    }   
    
      private void getSecondaryPort(){ 
        try {
 
                System.out.println("*************** CONNECTING TO CERTEGY SECONDARY ************");                
                 secondary = new com.smartbt.vtsuite.boundary.PCAService();                    
                        
                 port = secondary.getPCAPort();
                
                System.out.println("(secondary)-port succesfully created for secondary url...");
                System.out.println("(secondary)-sending ping to using site id:: " + CERTEGY_SITE_ID);
                
                PCAEchoRequest request = new PCAEchoRequest();
                request.setSiteID(CERTEGY_SITE_ID);
                request.setEchoNumber(new BigInteger("1000"));
                PCAEchoResponse certegyResponse = port.echo(request);
                System.out.println("(secondary)-ping response was:: " + (certegyResponse == null ? "NULL" : "Successful"));
        } catch (Exception e) { 
            System.out.println("*************** UNABLE TO CONNECT CERTEGY SECONDARY URL ");
            e.printStackTrace();
        } 
    } 
}
