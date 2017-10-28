/*
 @Autor: Roberto Rodriguez
 Email: robertoSoftwareEngineer@gmail.com

 @Copyright 2016 
 */
package com.girocheck.frontams.common.enums;

/**
 *
 * @author rrodriguez
 */
public enum APP{ 
    AMS("ams");
 
    private String name;

    private APP(String name) { 
        this.name = name;
    }
 
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

}
