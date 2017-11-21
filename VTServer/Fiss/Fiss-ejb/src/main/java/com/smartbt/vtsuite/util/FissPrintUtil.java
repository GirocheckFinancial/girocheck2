package com.smartbt.vtsuite.util;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author rrodriguez
 */
public class FissPrintUtil {
    
    public static Boolean ACTIVE_FISS_LOGS = true;
    
    public static void printResponse(String tag, Map<FissParam, Object> response) {
        if (ACTIVE_FISS_LOGS) {
            StringBuilder sb = new StringBuilder(" --------- Printing " + tag + " -----------\n");
            System.out.println(mapToXML(sb, "", tag, response));
        }
    }
    
    private static StringBuilder mapToXML(StringBuilder sb, String space, String tag, Map<FissParam, Object> response) {
         sb.append(startTag(space, tag));
        
        Iterator<FissParam> it = response.keySet().iterator();
        while (it.hasNext()) {
            FissParam param = it.next();
            Object value = response.get(param);
            
            if (value instanceof Map) {
                sb.append(mapToXMLFromParamMap(new StringBuilder(), "   ", param.toString(), (Map) value).toString());
            } else {
                sb.append(space + "   <" + param + ">" + value + "</" + param + "> \n");
            }
            
        }
        sb.append(endTag(space, tag));
        return sb; 
    }
    
       private static StringBuilder mapToXMLFromParamMap(StringBuilder sb, String space, String tag, Map<ParameterName, Object> response) {
         sb.append(startTag(space, tag));
        
        Iterator<ParameterName> it = response.keySet().iterator();
        while (it.hasNext()) {
            ParameterName param = it.next();
            Object value = response.get(param);
            
            if (value instanceof Map) {
                sb.append(mapToXML(new StringBuilder(), "   ", param.toString(), (Map) value).toString());
            } else {
                sb.append(space + "   <" + param + ">" + value + "</" + param + "> \n");
            }
            
        }
        sb.append(endTag(space, tag));
        return sb; 
    }
    
    public static String startTag(String space, String tag) {        
        return space + "<" + tag + ">\n";
    }
    
    public static String endTag(String space, String tag) {        
        return space + "</" + tag + ">\n";
    }
    
    public static String buildXML(String tagsStr, Map<FissParam, String> map, String space, FissParam... params) {
        
        String[] tags = null;
        StringBuilder sb = new StringBuilder();
        
        if (tagsStr != null) {
            tags = tagsStr.split("/");
            for (String tag : tags) {
                space = "   " + space;
                sb.append(startTag(space, tag));                
            }
        }
        
        space = "   " + space;
        
        for (FissParam param : params) {
            String paramName = param.toString();
            
            if(param.getField() != null){
                paramName = param.getField();
            }
             
            sb.append(space + "<" + paramName + ">" + map.get(param) + "</" + paramName + ">");
            
            if(param.getField() != null){
               sb.append(" (" + param + ")\n");
            }else{
                sb.append("\n");
            }
        }
        
        space = space.substring(3);
        
        if (tags != null) {
            for (String tag : tags) {
                sb.append(endTag(space, tag));
                space = space = space.substring(3);                
            }
        }
        return sb.toString();
    }
    
}
