 
package com.smartbt.girocheck.servercommon.enums;

/**
 *
 * @author rrodriguez
 */


public enum IdeologyResultInfoType {

    QUALIFIER(1),
    VELOCITY(2),
    ERROR(3);

    private Integer type;

    IdeologyResultInfoType(Integer type) {
        this.type = type;
    }

    public Integer get() {
        return type;
    }

    
}
