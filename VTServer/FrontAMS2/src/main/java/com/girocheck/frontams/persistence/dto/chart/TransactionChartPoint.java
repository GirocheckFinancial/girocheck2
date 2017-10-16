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
public class TransactionChartPoint  extends BaseChartPoint { 
    private BigInteger successful;
    private BigInteger total;
    private BigInteger failed;
    

    /**
     * @return the successful
     */
    public BigInteger getSuccessful() {
        return successful;
    }

    /**
     * @param successful the successful to set
     */
    public void setSuccessful(BigInteger successful) {
        this.successful = successful;
    }

   
    

    /**
     * @return the total
     */
    public BigInteger getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(BigInteger total) {
        this.total = total;
    }

    /**
     * @return the failed
     */
    public BigInteger getFailed() {
        return total.subtract(successful);
    }

    /**
     * @param failed the failed to set
     */
    public void setFailed(BigInteger failed) {
        this.failed = failed;
    }
}
