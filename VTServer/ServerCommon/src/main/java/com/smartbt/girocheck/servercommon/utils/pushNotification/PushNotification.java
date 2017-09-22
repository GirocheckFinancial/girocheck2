/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.girocheck.servercommon.utils.pushNotification;

/**
 *
 * @author rrodriguez
 */
public class PushNotification {

    private Notification notification;
    private Data data;
    private String to;
    //= "dL9CaJEWY7M:APA91bEKEnC_5zz6f9q_xpg6MxmCHWpwshrzBLMpChTsSYimRzqjBEmy62tG2n1CEDsJD3pvU5OgcwnMRyb3E15ZZzxTSKQVVeDSZkGFTcMm2lor7ITVf1lOkpjgjEDPAX_-sVGolg7D";

    public PushNotification(String os, String pushToken, String title, String msg) {
        Notification notification = new Notification(os, title, msg);
        this.to = pushToken;//"cPkNDmQv85I:APA91bGloOGNOFA4zlClLoSrFrrXk8nH76YaEC4XuP1GbeQg3lpBARIZ1jQkA-N6brOA0RFURzwzunFNbNsKyZLG5PGEA7x_D9aCm4bZ4RXOoT36_LVTVwbQHt4GpQzQabPGRtPmCwPp";// pushToken;
           
        if(os != null && os.equalsIgnoreCase("android")){
           this.data = new Data(notification); 
        }else{
           this.notification = notification; 
        } 
    }

    /**
     * @return the data
     */
    public Data getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Data data) {
        this.data = data;
    }
    /**
     * @return the to
     */
    public String getTo() {
        return to;
    }

    /**
     * @param to the to to set
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     * @return the notification
     */
    public Notification getNotification() {
        return notification;
    }

    /**
     * @param notification the notification to set
     */
    public void setNotification(Notification notification) {
        this.notification = notification;
    }

}

class Notification {

    private String title = "";
    private String body = "";
    private String sub_text = "";
    private String color = "#58b45f";
    private boolean show_in_foreground = true;
    private String priority = "max";
    private String large_icon = "http://res.cloudinary.com/titorobe/image/upload/v1505605755/voltcash-icon.png";

    public Notification(String os, String title, String body) {
        this.title = title;

        if (os != null && (os.equalsIgnoreCase("ios") || body.length() <= 36)) {
            this.body = body;
        } else {
            if (body != null && body.length() > 36) {
                try {
                    int cutPoint = body.substring(0, 36).lastIndexOf(" ");
                    this.body = body.substring(0, cutPoint);
                    this.sub_text = body.substring(cutPoint + 1);
                } catch (Exception e) {
                    this.body = body;
                } 
            }
        }

    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the body
     */
    public String getBody() {
        return body;
    }

    /**
     * @param body the body to set
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * @return the show_in_foreground
     */
    public boolean isShow_in_foreground() {
        return show_in_foreground;
    }

    /**
     * @param show_in_foreground the show_in_foreground to set
     */
    public void setShow_in_foreground(boolean show_in_foreground) {
        this.show_in_foreground = show_in_foreground;
    }

    /**
     * @return the priority
     */
    public String getPriority() {
        return priority;
    }

    /**
     * @param priority the priority to set
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }

    /**
     * @return the large_icon
     */
    public String getLarge_icon() {
        return large_icon;
    }

    /**
     * @param large_icon the large_icon to set
     */
    public void setLarge_icon(String large_icon) {
        this.large_icon = large_icon;
    }

    /**
     * @return the sub_text
     */
    public String getSub_text() {
        return sub_text;
    }

    /**
     * @param sub_text the sub_text to set
     */
    public void setSub_text(String sub_text) {
        this.sub_text = sub_text;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

}

class Data {

    private Notification custom_notification;// = new Notification("Welcome to VoltCash", msg);

    public Data(Notification notification) {
        this.custom_notification = notification;
    }

    public Notification getCustom_notification() {
        return custom_notification;
    }

    public void setCustom_notification(Notification custom_notification) {
        this.custom_notification = custom_notification;
    }

}
