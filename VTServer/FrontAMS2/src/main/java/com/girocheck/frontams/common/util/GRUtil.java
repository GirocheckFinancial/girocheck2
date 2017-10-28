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
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Criterion;

/**
 *
 * @author rrodriguez
 */
public class GRUtil {

    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
    public static final String PARAMS_SEPARATOR = "@p@";//"@@";
    public static final String PARAMS_EQUAL = "@is@";
   

    public static List<Criterion> parseParamsToExpressions(String paramsStr) {
        List<Criterion> expressions = new ArrayList<>();

        if (paramsStr == null) {
            return expressions;
        }
        paramsStr = paramsStr.replaceAll("%20", " "); 

        if (paramsStr != null && !paramsStr.isEmpty()) {
            String[] prefixValues = paramsStr.split(PARAMS_SEPARATOR); 
            
            for (String prefixValue : prefixValues) {
                if (prefixValue.length() > 0 && prefixValue.contains(PARAMS_EQUAL)) { 
                    String[] kv = prefixValue.split(PARAMS_EQUAL);

                    String key = kv[0];
                    String value = kv[1];

                    Criterion expression = paramToExpression(key, value);

                    if (expression != null) {
                        expressions.add(expression);
                    }
                }
            }
        }
        return expressions;
    }

    private static Criterion paramToExpression(String key, String value) {
        Criterion criterion = null;

        try {
            String prefix = key;

            if (value.trim().startsWith("(") && value.contains(")")) {
                prefix = value.trim().substring(0, value.indexOf(")") + 1);
                value = value.trim().substring(value.indexOf(")") + 1);
            }

            if (value.isEmpty() || value.equalsIgnoreCase("null")) {
                return null;
            }

            switch (prefix) {
                case "(S)":
                    if (key.contains(",")) {
                        String[] keys = key.split(",");
                        Criterion[] subExpressions = new Criterion[keys.length];

                        for (int i = 0; i < keys.length; i++) {
                            subExpressions[i] = Restrictions.like(keys[i], value, MatchMode.ANYWHERE);
                        }

                        criterion = Restrictions.or(subExpressions);
                    } else {
                        criterion = Restrictions.like(key, value, MatchMode.ANYWHERE);
                    }
                    break;
                case "(I)":
                case "(L)":
                case "(d)":
                    criterion = parseNumber(key, value, prefix);
                    break;
                case "(D)":
                    criterion = parseDate(key, value);
                    break;
                case "(B)":
                    criterion = Restrictions.eq(key, Boolean.parseBoolean(value));
                    break;
                default:
                    criterion = Restrictions.eq(key, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return criterion;
    }

    private static Criterion parseNumber(String key, String valueStr, String type) {
        String operator = "";
        Object value = null;
        Criterion criterion = null;

        System.out.println("parseNumber:: valueStr = " + valueStr);
        if (valueStr.trim().startsWith("[")) {
            operator = valueStr.trim().substring(1, valueStr.indexOf("]"));
            valueStr = valueStr.trim().substring(valueStr.indexOf("]") + 1);
        }

        switch (type) {
            case "(I)":
                value = Integer.parseInt(valueStr);
                break;
            case "(L)":
                value = Long.parseLong(valueStr);
                break;
            case "(d)":
                value = Double.parseDouble(valueStr);
                break;
            default:
                value = valueStr;
        }
        switch (operator) {
            case "ne":
                criterion = Restrictions.ne(key, value);
                break;
            case "gt":
                criterion = Restrictions.gt(key, value);
                break;
            case "lt":
                criterion = Restrictions.lt(key, value);
                break;
            case "ge":
                criterion = Restrictions.ge(key, value);
                break;
            case "le":
                criterion = Restrictions.le(key, value);
                break;
            case "eq":
            default:
                criterion = Restrictions.eq(key, value);
                break;

        }
        
        if(criterion == null){
            System.out.println("criterion is NULL");
        }

        return criterion;
    }

    private static Criterion parseDate(String key, String value) {
        Criterion criterion = null;
        if (value.contains(",")) {
            System.out.println("value = " + value);
            String[] dates = value.split(",");

            System.out.println("date1 = " + dates[0]);
            System.out.println("date2 = " + dates[1]);
            Criterion startDateCriterion = null, endDateCriterion = null;
            if (hasValue(dates[0])) {
                startDateCriterion = Restrictions.ge(key, new Date(dates[0]));
            }

            if (hasValue(dates[1])) {
                endDateCriterion = Restrictions.le(key, new Date(dates[1]));
            }

            if (startDateCriterion != null && endDateCriterion != null) {
                criterion = Restrictions.and(startDateCriterion, endDateCriterion);
            } else {
                criterion = startDateCriterion == null ? endDateCriterion : startDateCriterion;
            }

        } else {
            criterion = Restrictions.eq(key, new Date(value));
        }
        return criterion;
    }

    private static boolean hasValue(String str) {
        return str != null && !str.isEmpty() && !str.equalsIgnoreCase("null");
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
