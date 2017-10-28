package com.girocheck.ams.reports;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author @Roberto Rodriguez :: <RobertoSoftwareEngineer@gmail.com>
 */
public class ReportItem implements Serializable {

    public static final DecimalFormat twoPlaces = new DecimalFormat("0.00");

    private String quantity;
    private String product;
    private Double sales;
    private Double freeDollar;
    private Double salesPayment;

    private String reportDate;
    private String reportNumber;

    private String storeName;
    private String storeManager;
    private String storeAddress;

    private String salesPromotion;
    private String totalToPay;
    private String commission;

    public ReportItem() {
    }
 
    public void complete(Double totalSales, Double totalCommission, Double totalTransaction, String storeName, String storeManager, String storeAddress) {
        this.salesPromotion = getDoubleString(totalSales);
        this.commission = getDoubleString(totalCommission);
        this.totalToPay = getDoubleString(totalTransaction);
        this.storeName = storeName;
        this.storeManager = storeManager;
        this.storeAddress = storeAddress;

        this.reportDate = getToday();
    }

    private String getDoubleString(Double d) {
        String str = "0.00";
        if (d != null) {
            str = twoPlaces.format(d);
        } 

        return str;
    }

    private String getToday() {
        Date d = new Date();
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        return df.format(d);
    }

    public ReportItem(String quantity, String product, Double sales, Double freeDollar, Double salesPayment) {
        this.quantity = quantity;
        this.product = product;
        this.sales = sales;
        this.freeDollar = freeDollar;
        this.salesPayment = salesPayment;
    }

    public void setReportNumber(Integer index) {
        DateFormat df = new SimpleDateFormat("MMdd");
        this.reportNumber = "ALO-" + df.format(new Date()) + String.format("%03d", index);
    }

    /**
     * @return the reportDate
     */
    public String getReportDate() {
        return reportDate;
    }

    /**
     * @param reportDate the reportDate to set
     */
    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    /**
     * @return the reportNumber
     */
    public String getReportNumber() {
        return reportNumber;
    }

    /**
     * @return the storeManager
     */
    public String getStoreManager() {
        return storeManager;
    }

    /**
     * @param storeManager the storeManager to set
     */
    public void setStoreManager(String storeManager) {
        this.storeManager = storeManager;
    }

    /**
     * @return the storeAddress
     */
    public String getStoreAddress() {
        return storeAddress;
    }

    /**
     * @param storeAddress the storeAddress to set
     */
    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    /**
     * @return the storeName
     */
    public String getStoreName() {
        return storeName;
    }

    /**
     * @param storeName the storeName to set
     */
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    /**
     * @return the salesPromotion
     */
    public String getSalesPromotion() {
        return salesPromotion;
    }

    /**
     * @param salesPromotion the salesPromotion to set
     */
    public void setSalesPromotion(String salesPromotion) {
        this.salesPromotion = salesPromotion;
    }

    /**
     * @return the totalToPay
     */
    public String getTotalToPay() {
        return totalToPay;
    }

    /**
     * @param totalToPay the totalToPay to set
     */
    public void setTotalToPay(String totalToPay) {
        this.totalToPay = totalToPay;
    }

    /**
     * @return the commission
     */
    public String getCommission() {
        return commission;
    }

    /**
     * @param commission the commission to set
     */
    public void setCommission(String commission) {
        this.commission = commission;
    }

    /**
     * @return the sales
     */
    public Double getSales() {
        return sales;
    }

    /**
     * @param sales the sales to set
     */
    public void setSales(Double sales) {
        this.sales = sales;
    }

    /**
     * @return the freeDollar
     */
    public Double getFreeDollar() {
        return freeDollar;
    }

    /**
     * @param freeDollar the freeDollar to set
     */
    public void setFreeDollar(Double freeDollar) {
        this.freeDollar = freeDollar;
    }

    /**
     * @return the salesPayment
     */
    public Double getSalesPayment() {
        return salesPayment;
    }

    /**
     * @param salesPayment the salesPayment to set
     */
    public void setSalesPayment(Double salesPayment) {
        this.salesPayment = salesPayment;
    }

    /**
     * @return the quantity
     */
    public String getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the product
     */
    public String getProduct() {
        return product;
    }

    /**
     * @param product the product to set
     */
    public void setProduct(String product) {
        this.product = product;
    }
}
