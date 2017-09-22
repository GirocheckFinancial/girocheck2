/*
 @Autor: Roberto Rodriguez
 Email: robertoSoftwareEngineer@gmail.com

 @Copyright 2016 
 */
package com.girocheck.frontams.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author rrodriguez
 */
public class GRUtil {

    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");

//    public static LinkedHashMap parseRequestMap(LinkedHashMap map) throws ParseException {
    public static Map<String, Object> parseRequestMap(Map<String, Object> map) throws ParseException {

        List names = new ArrayList(map.keySet());

        for (Object name : names) {
            String value = (String) map.get(name); 
            parse((String)name, value, map);
        }
         
        return map;
    }

    public static Map<String, Object> parseParams(String paramsStr) {
        paramsStr = paramsStr.replaceAll("%20", " ");
        
        Map<String, Object> params = new HashMap<>();
        if (paramsStr != null && !paramsStr.isEmpty()) {
            String[] prefixValues = paramsStr.split("&");
            for (String prefixValue : prefixValues) {
                if (prefixValue.length() > 0 && prefixValue.contains("=")) {
                    String[] kv = prefixValue.split("=");

                    String key = kv[0];
                    String value = kv[1];

                    parse(key, value, params);
                }
            }
        }
        return params;
    }

    private static <T extends Map> void parse(String key, String value, T map) {
        try {
            if (value.length() >= 3) {
                String prefix = value.substring(0, 3);
                value = value.substring(3);

                if (value.isEmpty() || value.equalsIgnoreCase("null")) {
                    map.remove(key);
                    return;
                }

                switch (prefix) {
                    case "(L)":
                        map.put(key, Long.parseLong(value));
                        break;
                    case "(D)":
                        map.put(key, new Date(value));
                        break;
                    case "(d)":
                        map.put(key, Double.parseDouble(value));
                        break;
                    case "(B)":
                        map.put(key, Boolean.parseBoolean(value));
                        break;
                    default:
                        map.put(key, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put(key, value);
        }
    }

     public static String getMaskedNumber(String cardNumber) {
        String maskedNumber = "";
        if (cardNumber != null && cardNumber.length() >= 4) {
            maskedNumber += cardNumber.substring(0, 4);

            if (cardNumber.length() >= 8) {
                maskedNumber += StringUtils.repeat("*", cardNumber.length() - 8);
                maskedNumber += cardNumber.substring(cardNumber.length() - 4);
            }
        }
        return maskedNumber;
    } 
     
     public static boolean equalAmounts(Double a, Double b){
         return (a != null) && (b != null) && ( Math.abs(a - b) < 0.01);
     }
}
