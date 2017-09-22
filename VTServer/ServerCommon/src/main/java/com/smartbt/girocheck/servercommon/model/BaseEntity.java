/*
 @Autor: Roberto Rodriguez
 Email: robertoSoftwareEngineer@gmail.com

 @Copyright 2016 
 */
package com.smartbt.girocheck.servercommon.model;

import java.io.Serializable; 


 
public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public abstract int getId();

    
}
