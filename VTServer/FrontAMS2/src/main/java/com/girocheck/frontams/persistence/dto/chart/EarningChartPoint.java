/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.girocheck.frontams.persistence.dto.chart;
 

/**
 *
 * @author rrodriguez
 */
public class EarningChartPoint  extends BaseChartPoint {
    private Double check = 0D;
    private Double cash = 0D;
    private Double card2bank = 0D;
    private Double commission = 0D;
 

    /**
     * @return the check
     */
    public Double getCheck() {
        return check != null ? check : 0D;
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
        return cash != null ? cash : 0D; 
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
        return  card2bank != null ? card2bank : 0D;  
    }

    /**
     * @param card2bank the card2bank to set
     */
    public void setCard2bank(Double card2bank) {
        this.card2bank = card2bank;
    }

    /**
     * @return the commission
     */
    public Double getCommission() {
        return commission != null ? commission : 0D;  
    }

    /**
     * @param commission the commission to set
     */
    public void setCommission(Double commission) {
        this.commission = commission;
    }
}
