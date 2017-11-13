package com.smartbt.vtsuite.util;

import com.smartbt.girocheck.servercommon.utils.Utils;
import java.util.Map;

/**
 *
 * @author rrodriguez
 */
public class FissUtil {

    public static Boolean LOG_REQUEST = true;

    public static final String FISS_SERVICE_VERSION = "1.0";

    //Source ID:  For Connectware use, this will be the vendor ID.
    public static String SOURCE_ID = "source_id_goes_here";    //TODO
    public static String ROUTING_ID = "routing_id_goes_here";    //TODO
    public static String FISS_USERNAME = "username_id_goes_here";    //TODO
    public static String FISS_PASSWORD  = "password_id_goes_here";    //TODO
    public static String INSTITUTION_NUMBER  = "institution_number_goes_here";    //TODO //card personalization
    public static String CARD_STATUS  = "card_status_goes_here";    //TODO //card personalization
    public static String PRODUCT_ID  = "product_id_goes_here";    //TODO //card personalization
    
    

    //Indicates which holds to return. 
    //01 Return all holds. 
    //02 Return only active holds.
    public static String HOLD_STATUS_FILTER = "02";    //TODO
    

    //	Used to indicate that transaction filtering should be performed. 
    //01 Filter for a cardholder-facing API.
    //Returns settled transactions only; omits rejected transactions, 
    //authorizations, statement prints, and $0.00 transactions.
    public static String PENDING_TRANSACTION_FILTER = "01";    //TODO

    //format XXXXXXXX-XXXX-XXXX-XXXX-XXXXXXXXXXXX
    public static String generateUUID() {
        StringBuilder sb = new StringBuilder();
        sb.append(Utils.rndstr(8) + "-");
        sb.append(Utils.rndstr(4) + "-");
        sb.append(Utils.rndstr(4) + "-");
        sb.append(Utils.rndstr(4) + "-");
        sb.append(Utils.rndstr(12));

        return sb.toString();
    }

    public static void startTag(String space, String tag) {
        if (!LOG_REQUEST) {
            return;
        }
        System.out.println(space + "<" + tag + ">");
    }

    public static void closeTag(String space, String tag) {
        if (!LOG_REQUEST) {
            return;
        }
        System.out.println(space + "</" + tag + ">");
    }

    public static void print(String tagsStr, Map<FissParam, String> map, String space, FissParam... params) {
        if (!LOG_REQUEST) {
            return;
        }

        String[] tags = null;

        if (tagsStr != null) {
             tags = tagsStr.split("/");
            for (String tag : tags) {
                space = "   " + space;
                System.out.println(space + "<" + tag + ">");
            }
        }

        space = "   " + space;

        for (FissParam param : params) {
            System.out.println(space + "<" + param + ">" + map.get(param) + "</" + param + ">");
        }

        space = space.substring(3);

        if (tags != null) {
            for (String tag : tags) {
                System.out.println(space + "</" + tag + ">");
                space = space = space.substring(3);;
            }
        }

    }
    
}
