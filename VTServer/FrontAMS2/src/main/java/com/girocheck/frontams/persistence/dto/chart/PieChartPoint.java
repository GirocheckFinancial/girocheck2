/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.girocheck.frontams.persistence.dto.chart;
 
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List; 

/**
 *
 * @author rrodriguez
 */
public class PieChartPoint {

    private BigInteger day;
    private BigInteger month;
    private BigInteger year;
    private String typeLabel;
    private Double check = 0D;
    private Double cash = 0D;
    private Double card2bank = 0D;

    public List<Point> getData() {
        List<Point> list = new ArrayList<>();
        list.add(new Point("Check", round(check)));
        list.add(new Point("Cash", round(cash)));
        list.add(new Point("Card to Bank", round(card2bank)));
        return list;
    }

    private Double round(Double number) { 
        if(number == null){
            return 0D;
        }else{
           return (double)Math.round(number * 100d) / 100d; 
        } 
    }

    /**
     * @return the typeLabel
     */
    public String getTypeLabel() {
        return typeLabel;
    }

    /**
     * @param typeLabel the typeLabel to set
     */
    public void setTypeLabel(String typeLabel) {
        this.typeLabel = typeLabel;
    }

    /**
     * @return the check
     */
    public Double getCheck() {
        return round(check);
    }

    /**
     * @param check the check to set
     */
    public void setCheck(Double check) {
        this.check = check;
    }

    /**
     * @return the cash
     */
    public Double getCash() {
        return round(cash);
    }

    /**
     * @param cash the cash to set
     */
    public void setCash(Double cash) {
        this.cash = cash;
    }

    /**
     * @return the card2bank
     */
    public Double getCard2bank() {
        return  round(card2bank);
    }

    /**
     * @param card2bank the card2bank to set
     */
    public void setCard2bank(Double card2bank) {
        this.card2bank = card2bank;
    }

    /**
     * @return the day
     */
    public BigInteger getDay() {
        return day;
    }

    /**
     * @param day the day to set
     */
    public void setDay(BigInteger day) {
        this.day = day;
    }

    /**
     * @return the month
     */
    public BigInteger getMonth() {
        return month;
    }

    /**
     * @param month the month to set
     */
    public void setMonth(BigInteger month) {
        this.month = month;
    }

    /**
     * @return the year
     */
    public BigInteger getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(BigInteger year) {
        this.year = year;
    }

}

class Point {

    private Double value;
    private String label;

    public Point() {
    }

    public Point(String label, Double value) {
        this.value = value;
        this.label = label;
    }

    /**
     * @return the value
     */
    public Double getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(Double value) {
        this.value = value;
    }

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }

}
