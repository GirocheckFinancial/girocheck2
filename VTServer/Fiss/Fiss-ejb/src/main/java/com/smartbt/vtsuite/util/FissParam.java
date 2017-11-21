package com.smartbt.vtsuite.util;

/**
 *
 * @author rrodriguez
 */
public enum FissParam {
    TRANSACTION_TYPE(null),
    FISS_SERVICE_VERSION(null),
    MSG_UUID(null),
    SOURCE_ID(null),
    TEST_INDICATOR(null),
    APPLICATION_ID(null),
    SERVICE_ID(null),
    SERVICE_VERSION(null),
    REQUEST_ID(null),
    ROUTING_ID(null),
    USER(null),
    PASSWORD(null),
    CARD_NUMBER("130013"),
    HOLD_STATUS_FILTER("130679"),
    //card personalization
    INSTITUTION_NUMBER(null),
    FIRST_NAME("130025"),
    LAST_NAME("30027"),
    STREET("130029"),
    STREET2("130030"),
    CITY("130031"),
    STATE("130032"),
    COUNTRY("130033"),
    ZIPCODE("130034"),
    SSN("130145"),
    DOB("130147"), //CCYYMMDD
    TELEPHONE("130138"),
    // activation
    CARD_STATUS(null),
    //setProductId
    PRODUCT_ID("130579"),
    //card load
    AMOUNT("130484"), 
    FEE_AMOUNT("130485"),
    CARD_LOAD_TYPE(null),
    DETAILED_TRANSACTION_DESCRIPTION("131150"),
    //change_password
    NEW_PASSWORD("300205"),
    //set_pin
    PIN("130305"),
    //history_pending
    PENDING_TRANSACTION_FILTER("130551"),
    //history_general
    NUMBER_OF_OCCURS("304"), 
    
    //Responses
    SUCCESS(null),
    RESULT_CODE(null),
    RESULT_MESSAGE(null),
    FISS_PERSONAL_INFO_DATA(null),
    BALANCE(null);
    
    private String field;

    private FissParam(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }
    
    
}
