/*
 @Autor: Roberto Rodriguez
 Email: robertoSoftwareEngineer@gmail.com

 @Copyright 2016 
 */
package com.girocheck.frontams.common.util;

import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
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
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

/**
 *
 * @author rrodriguez
 */
public class GRUtil {

    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
 

    public static List<SimpleExpression> parseParamsToExpressions(String paramsStr) {
        List<SimpleExpression> expressions = new ArrayList<>();

        if (paramsStr == null) {
            return expressions;
        }
        paramsStr = paramsStr.replaceAll("%20", " ");

        if (paramsStr != null && !paramsStr.isEmpty()) {
            String[] prefixValues = paramsStr.split("&");
            for (String prefixValue : prefixValues) {
                if (prefixValue.length() > 0 && prefixValue.contains("=")) {
                    String[] kv = prefixValue.split("=");

                    String key = kv[0];
                    String value = kv[1];

                    SimpleExpression expression = paramToExpression(key, value);

                    if (expression != null) {
                        expressions.add(expression);
                    }
                }
            }
        }
        return expressions;
    }

    private static SimpleExpression paramToExpression(String key, String value) {
        SimpleExpression simpleExpression = null;

        try {
            String prefix = key;

            if (value.trim().startsWith("(") && value.contains(")")) {
                prefix = value.trim().substring(0, value.indexOf(")") + 1);
                value = value.substring(value.indexOf(")") + 1);
            }

            if (value.isEmpty() || value.equalsIgnoreCase("null")) {
                return null;
            }

            switch (prefix) {
                case "(S)":
                    if(key.contains(",")){
                       String[] keys = key.split(",");
                        Criterion[]  subExpressions = null;
//                        for (String k : keys) { 
//                            subExpressions.add(Restrictions.like(k, value, MatchMode.ANYWHERE));
//                        }
//                        
                        Criteria criteria = HibernateUtil.getSession().createCriteria(String.class);
                       // criteria.add
                       Criterion s  = Restrictions.or(subExpressions);
                    }else{
                      simpleExpression = Restrictions.like(key, value, MatchMode.ANYWHERE);  
                    } 
                    break;
                case "(I)":
                    simpleExpression = Restrictions.eq(key, Integer.parseInt(value));
                    break;
                case "(L)":
                    simpleExpression = Restrictions.eq(key, Long.parseLong(value));
                    break;
                case "(D)":
                    simpleExpression = Restrictions.eq(key, new Date(value));
                    break;
                case "(d)":
                    simpleExpression = Restrictions.eq(key, Double.parseDouble(value));
                    break;
                case "(B)":
                    simpleExpression = Restrictions.eq(key, Boolean.parseBoolean(value));
                    break;
                default:
                    simpleExpression = Restrictions.eq(key, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return simpleExpression;
    }
    
    
    public static Map<String, Object> parseRequestMap(Map<String, Object> map) throws ParseException {

        List names = new ArrayList(map.keySet());

        for (Object name : names) {
            String value = (String) map.get(name);
            parse((String) name, value, map);
        }

        return map;
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
                    case "(I)":
                        map.put(key, Integer.parseInt(value));
                        break;
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

    public static boolean equalAmounts(Double a, Double b) {
        return (a != null) && (b != null) && (Math.abs(a - b) < 0.01);
    }
}
