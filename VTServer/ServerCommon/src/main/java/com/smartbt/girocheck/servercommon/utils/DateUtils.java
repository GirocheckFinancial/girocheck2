/*
 ** File: DateUtils.java
 **
 ** Date Created: October 2013
 **
 ** Copyright @ 2004-2013 Smart Business Technology, Inc.
 **
 ** All rights reserved. No part of this software may be 
 ** reproduced, transmitted, transcribed, stored in a retrieval 
 ** system, or translated into any language or computer language, 
 ** in any form or by any means, electronic, mechanical, magnetic, 
 ** optical, chemical, manual or otherwise, without the prior 
 ** written permission of Smart Business Technology, Inc.
 **
 */
package com.smartbt.girocheck.servercommon.utils;

import com.smartbt.girocheck.servercommon.validators.utils.Validator;
import com.smartbt.vtsuite.vtcommon.validator.RegExp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Roberto Rodriguez
 */
public class DateUtils {

    public static Date startDate() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        DateFormat dateFormatComple = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String date = dateFormat.format(Calendar.getInstance().getTime());
        String startDate = date + " 00:00:00.0";
        return dateFormatComple.parse(startDate);

    }

    public static Date endDate() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        DateFormat dateFormatComple = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String date = dateFormat.format(Calendar.getInstance().getTime());
        String endDate = date + " 23:59:59.9";
        return dateFormatComple.parse(endDate);

    }

    public static Date lessDays(GregorianCalendar date, int numerOfDays) {
        GregorianCalendar newDate = (GregorianCalendar) date.clone();
        newDate.set(Calendar.DAY_OF_MONTH, date.get(Calendar.DAY_OF_MONTH) - numerOfDays);
        return newDate.getTime();
    }

    public static Date getDateByString(String dateStr, String pattern) {
        try {
            if (dateStr == null) {
                return null;
            }
            DateFormat f = new SimpleDateFormat(pattern);
            Date date = f.parse(dateStr);
            return date;
        } catch (ParseException ex) {
            return null;
        }
    }

    public static Date getDateByString(String dateStr) {
        return getDateByString(dateStr, "yyyy-MM-dd");
    }

    public static Criterion getRestrictionForDateFilter(String dateFilter, String dateField) {
        try {
            dateFilter = dateFilter.trim();
            if (Validator.isFullDate(dateFilter)) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
                return Restrictions.between(dateField,
                        dateFormat.parse(dateFilter + ":00"),
                        dateFormat.parse(dateFilter + ":59"));
            }
            if (Validator.isDate(dateFilter)) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
                return Restrictions.between(dateField,
                        dateFormat.parse(dateFilter + " 00:00:00"),
                        dateFormat.parse(dateFilter + " 23:59:59"));
            }
//            if (ValidatorsPatterns.isTime(dateFilter)) {
//                SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
//                return Restrictions.between(dateField,
//                        dateFormat.parse("01/01/1900" + dateFilter),
//                        dateFormat.parse("01" + dateFilter));
//            }

        } catch (ParseException ex) {
        }
        return null;
    }
 
    public static boolean isDate(String var) {
        return matchPattern(var, RegExp.EXP_REG_DATE);
    }

    public static boolean isFullDate(String date) {
        return matchPattern(date, RegExp.EXP_REG_FULL_DATE);
    }

    public static boolean matchPattern(String value, String regexpr) {
        CharSequence inputStr = value;
        Pattern pattern = Pattern.compile(regexpr);
        Matcher matcher = pattern.matcher(inputStr);
        return matcher.matches();
    }

    public static String dateToISOFormat(Date date) {
        if (date == null) {
            return "";
        }
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

            return df.format(date);
        } catch (Exception e) {
            return date.toString();
        }

    }
}
