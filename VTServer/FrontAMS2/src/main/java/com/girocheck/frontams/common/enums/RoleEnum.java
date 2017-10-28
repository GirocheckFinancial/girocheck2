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
public enum RoleEnum {
    SUPER_ADMIN(1L),
    ADMIN(2L),
    MANAGER(3L),
    PSU(4L),     //Partner Super User
    MERCHANT_MANAGER(5L),
    DISTRIBUTOR(6L),
    USER(7L);
    
    private Long id;

    private RoleEnum(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    
    
}
