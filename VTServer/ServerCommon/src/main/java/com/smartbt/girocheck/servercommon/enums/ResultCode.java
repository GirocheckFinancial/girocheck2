package com.smartbt.girocheck.servercommon.enums;

import com.smartbt.vtsuite.vtcommon.nomenclators.NomHost;

/**
 *
 * @author Roberto
 */
public enum ResultCode {

    SUCCESS(0),
    FAILED(1),
    RESPONSE_TIME_EXCEEDED(2),
    CARD_RELOAD_DATA_CANCELED(3),
    MISSING_FIELDS(4),
    INVALID_AMOUNT(5),
    CORE_ERROR(100),
    CORE_RECEIVED_NULL(101),
    CORE_RECEIVED_NULL_FROM_HOST(102),
    CORE_RECEIVED_NULL_FROM_HOST_ISTREAM(103),
    CORE_RECEIVED_NULL_FROM_HOST_ORDER_EXPRESS(104),
    CORE_RECEIVED_NULL_FROM_HOST_TECNICARD(105),
    CORE_FEE_CALCULATION_ERROR(106),
    ISTREAM_HOST_ERROR(200),
    ISTREAM_HOST_RECEIVED_NULL(201),
    ISTREAM_RETURN_CHECK_ID_NULL(202),
    ISTREAM_RESPONSE_TIME_EXCEEDED(203),
    ISTREAM_LOGIN_FAILED(204),
    ORDER_EXPRESS_HOST_ERROR(300),
    ORDER_EXPRESS_HOST_RECEIVED_NULL(301),
    ORDER_EXPRESS_FAILED(302),
    ORDER_EXPRESS_RESPONSE_TIME_EXCEEDED(303),
    TECNICARD_HOST_ERROR(400),
    TECNICARD_HOST_RECEIVED_NULL(401),
    TECNICARD_HOST_UNEXPECTED_RESULT_CODE(402),
    TECNICARD_RESPONSE_TIME_EXCEEDED(403),
    TECNICARD_HOST_RETURN_PROCESSING_FALSE(404),
    CHOICE_HOST_ERROR(410),
    CHOICE_HOST_RECEIVED_NULL(411),
    CHOICE_HOST_UNEXPECTED_RESULT_CODE(412),
    CHOICE_RESPONSE_TIME_EXCEEDED(413),
    CHOICE_HOST_RETURN_PROCESSING_FALSE(414),
    WESTECH_HOST_ERROR(420),
    WESTECH_HOST_RECEIVED_NULL(421),
    WESTECH_HOST_UNEXPECTED_RESULT_CODE(422),
    WESTECH_RESPONSE_TIME_EXCEEDED(423),
    WESTECH_HOST_RETURN_PROCESSING_FALSE(424),
    WESTECH_DECLINED(425),
    CERTEGY_HOST_ERROR(430),
    CERTEGY_HOST_RECEIVED_NULL(431),
    CERTEGY_HOST_UNEXPECTED_RESULT_CODE(432),
    CERTEGY_RESPONSE_TIME_EXCEEDED(433),
    CERTEGY_HOST_RETURN_PROCESSING_FALSE(434),
    ISTREAM2_HOST_ERROR(440),
    ISTREAM2_HOST_RECEIVED_NULL(441),
    ISTREAM2_HOST_UNEXPECTED_RESULT_CODE(442),
    ISTREAM2_RESPONSE_TIME_EXCEEDED(443),
    ISTREAM2_HOST_RETURN_PROCESSING_FALSE(444),
    REQUEST_ID_REQUIRED(450),
    TERMINAL_ID_REQUIRED(451),
    USER_REQUIRED(452),
    PASSWORD_REQUIRED(453),
    SSN_REQUIRED(454),
    GIROCHECK_FRONT_ERROR(500),
    FUZE_HOST_FAILED(550),
    FUZE_HOST_UNEXPECTED_RESULT_CODE(551),
    FUZE_HOST_ERROR(552),
    ISTREAM_FRONT_ERROR(600),
    ISTREAM_FRONT_PERSONAL_INFO_NOT_RECEIVED(601),
    ISTREAM_FRONT_PERSONAL_INFO_RECEIVED_AS_NULL(602),
    ISTREAM_FRONT_CERTEGY_INFO_NOT_RECEIVED(603),
    ISTREAM_FRONT_VALIDATION_ERROR(604),
    CERTEGY_DENY(700),
    LOGIN_FAILED(800),
    TERMINAL_CONFIRMATION_TIME_EXCEED(801),
    TERMINAL_WRONG_AMMOUNT_FORMAT(802),
    TERMINAL_ID_NOT_EXIST(803),
    CREDIT_CARD_NOT_EXIST(804),
    TRANSACTION_NOT_FOUND(805),
    TERMINAL_WRONG_AMMOUNT(806),
    CHECK_IN_BLACK_LIST(807),
    CARD_UNAUTHORIZED_BY_MIDDLEWARE(900),
    CARD_ALREADY_PERSONALIZED(901),
    CARD_NOT_PERSONALIZED(902),
    ISTREAM_CANCELLED_TRANSACTION(903),
    CLIENT_IN_BLACKLIST(950);
     
    private int code;

    private ResultCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static ResultCode getFromHost(NomHost host) {
        switch (host) {
            case ISTREAM:
                return ISTREAM_HOST_ERROR;
            case TECNICARD:
                return TECNICARD_HOST_ERROR;
            case ORDER_EXPRESS:
                return ORDER_EXPRESS_HOST_ERROR;
            case CHOICE:
                return CHOICE_HOST_ERROR;
            default:
                return CORE_ERROR;
        }
    }

}
