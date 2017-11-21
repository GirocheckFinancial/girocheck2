package com.smartbt.vtsuite.util;

import com.smartbt.girocheck.servercommon.utils.Utils;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author rrodriguez
 */
public class FissUtil {
 
    public static final String FISS_SERVICE_VERSION = "1.0";

    //Source ID:  For Connectware use, this will be the vendor ID.
    public static String SOURCE_ID = "CRD008";    //TODO
    public static String ROUTING_ID = "G7V";    //TODO
    public static String FISS_USERNAME = "CG7V032";    //TODO
    public static String FISS_PASSWORD = "Monday@2";    //TODO
    public static String INSTITUTION_NUMBER = "";    //TODO //card personalization
    public static String CARD_STATUS = "card_status_goes_here";    //TODO //card personalization
    public static String PRODUCT_ID = "product_id_goes_here";    //TODO //set product id

    //Indicates which holds to return. 
    //01 Return all holds. 
    //02 Return only active holds.
    //use hold status of 02 to get active holds
    public static String HOLD_STATUS_FILTER = "02";

    //	Used to indicate that transaction filtering should be performed. 
    //01 Filter for a cardholder-facing API.
    //Returns settled transactions only; omits rejected transactions, 
    //authorizations, statement prints, and $0.00 transactions.
    //use trans filter 01 to get pending items
    public static String PENDING_TRANSACTION_FILTER = "01";

    //The maximum number of transactions to be returned. 
    //If left blank, all transactions will be returned. 
    //Output: The actual number of transactions returned. 
    //If output is zero, no transactions exist
    public static String NUMBER_OF_OCCURS = "40";
    
    public static Map<Integer,String> FISS_REASON;
    
    static{
        FISS_REASON = new HashMap<>();
        FISS_REASON.put(1, "Active");
        FISS_REASON.put(2, "Hot card");
        FISS_REASON.put(3, "");
        FISS_REASON.put(4, "Warm card");
        FISS_REASON.put(5, "");
        FISS_REASON.put(6, "");
        FISS_REASON.put(7, "Card activation");
        FISS_REASON.put(8, "Compromised"); 
        FISS_REASON.put(9, "Closed"); 
    }

    //format XXXXXXXX-XXXX-XXXX-XXXX-XXXXXXXXXXXX
    public static void main(String[] args) {
        System.out.println(generateUUID());
    }

    public static String generateUUID() {
        StringBuilder sb = new StringBuilder();
        sb.append(Utils.rndstr(8) + "-");
        sb.append(Utils.rndstr(4) + "-");
        sb.append(Utils.rndstr(4) + "-");
        sb.append(Utils.rndstr(4) + "-");
        sb.append(Utils.rndstr(12));

        return sb.toString();
    }

   
}
