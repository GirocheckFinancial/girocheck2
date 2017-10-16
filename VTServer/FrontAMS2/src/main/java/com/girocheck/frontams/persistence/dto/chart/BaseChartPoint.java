/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.girocheck.frontams.persistence.dto.chart;

import java.math.BigInteger;

/**
 *
 * @author rrodriguez
 */
public class BaseChartPoint { 
    private static final String[] MONTHS = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
 
    private int day;
    private int month;
    private int year;
    private BigInteger timeType;
    private String dateLabel;

   
    public String getDateLabel() {
        switch(timeType.intValue()){
            case 0: return day + "";
            case 1: return MONTHS[month - 1];
            case 2:return year + "";
        } 
        return day + "";// Should never get here
    }
 

   
    /**
     * @return the dateLabel
     */
    /**
     * @param dateLabel the dateLabel to set
     */
    public void setDateLabel(String dateLabel) {
        this.dateLabel = dateLabel;
    }

    /**
     * @return the day
     */
    public int getDay() {
        return day;
    }

    /**
     * @param day the day to set
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * @return the month
     */
    public int getMonth() {
        return month;
    }

    /**
     * @param month the month to set
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * @return the timeType
     */
    public BigInteger getTimeType() {
        return timeType;
    }

    /**
     * @param timeType the timeType to set
     */
    public void setTimeType(BigInteger timeType) {
        this.timeType = timeType;
    }
 
}
