package com.smartbt.girocheck.servercommon.enums;

import com.smartbt.vtsuite.vtcommon.nomenclators.NomHost;

/**
 *
 * @author Roberto
 */
public enum TransactionType { 
    TRANSACTION_TYPE( null, 0 ),
     
    NEW_CARD_LOAD(null, 1 ),
    CARD_RELOAD(null, 2 ),
    CHECK_AUTH(null, 6 ),
    CARD_RELOAD_WITH_DATA(null, 35 ),
    
    PERSONAL_INFO(null, 3 ), 
    CHECK_INFO( NomHost.WESTECH, 43),
    
    CHECK_AUTH_LOCATION_CONFIG( null, 5 ),
     
     
    CARD_ACTIVATION( NomHost.GENERIC_HOST, 14 ),
    CARD_PERSONALIZATION( NomHost.GENERIC_HOST, 15 ),
    CARD_VALIDATION( NomHost.GENERIC_HOST, 16 ),
    CARD_LOAD( NomHost.GENERIC_HOST, 17 ),
    BALANCE_INQUIRY( NomHost.GENERIC_HOST, 18 ),
    CARD_TO_BANK( NomHost.GENERIC_HOST, 19 ),
    CARD_TO_BANK_CONFIRMATION( NomHost.GENERIC_HOST, 25 ),
    TECNICARD_CARD_HOLDER_VALIDATION( NomHost.TECNICARD, 20 ), 
    TRANSACTION_HISTORY( NomHost.GENERIC_HOST, 22 ),
    
    TECNICARD_CASH_TO_CARD( NomHost.TECNICARD, 23 ), //This is just Tecnicard
    RESTORE_CARD(NomHost.GENERIC_HOST, 44),
    TERMINAL_CONFIRMATION( null, 24 ),
     
    ACTIVITY_REPORT(null, 35 ), //no transactional
     
    WESTECH_CHECK_PROCESS(NomHost.WESTECH, 39 ),
    
    CERTEGY_AUTHENTICATION(NomHost.CERTEGY, 40),
    CERTEGY_REVERSE_REQUEST(NomHost.CERTEGY, 41),
    
    ISTREAM2_SEND_SINGLE_ICL(NomHost.ISTREAM2, 42),
     
    COMPLIANCE_NEW_BRANCH(NomHost.COMPLIANCE, 45 ),
    COMPLIANCE_POST_TRANSACTION(NomHost.COMPLIANCE, 46 ),
  
    IDEOLOGY_VERYFY_CLIENT(NomHost.IDEOLOGY, 53),
    
    FISS_SET_PRODUCT_ID(NomHost.FISS, 54),
    FISS_CHANGE_PASSWORD(NomHost.FISS, 55),
    FISS_SET_PIN(NomHost.FISS, 56);
 
    private NomHost host;

    private int code;

    private TransactionType( NomHost host, int code ) {
        this.host = host;
        this.code = code;
    }

    public NomHost getHost() {
        return host;
    }

    public int getCode() {
        return code;
    }

    public static TransactionType get( int code ) {
        switch ( code ) {
            case 0:
                return TransactionType.TRANSACTION_TYPE;
            case 1:
                return TransactionType.NEW_CARD_LOAD;
            case 2:
                return TransactionType.CARD_RELOAD;
            case 3:
                return TransactionType.PERSONAL_INFO; 
            case 5:
                return TransactionType.CHECK_AUTH_LOCATION_CONFIG;
            case 6:
                return TransactionType.CHECK_AUTH;  
            case 14:
                return TransactionType.CARD_ACTIVATION;
            case 15:
                return TransactionType.CARD_PERSONALIZATION;
            case 16:
                return TransactionType.CARD_VALIDATION;
            case 17:
                return TransactionType.CARD_LOAD;
            case 18:
                return TransactionType.BALANCE_INQUIRY;
            case 19:
                return TransactionType.CARD_TO_BANK;            
            case 20:
                return TransactionType.TECNICARD_CARD_HOLDER_VALIDATION; 
            case 22:
                return TransactionType.TRANSACTION_HISTORY;
            case 23:
                return TransactionType.TECNICARD_CASH_TO_CARD;
            case 24:
                return TransactionType.TERMINAL_CONFIRMATION;
            case 25:
                return TransactionType.CARD_TO_BANK_CONFIRMATION; 
            case 35: 
                return TransactionType.CARD_RELOAD_WITH_DATA; 
            case 39: 
                return TransactionType.WESTECH_CHECK_PROCESS;
            case 40: 
                return TransactionType.CERTEGY_AUTHENTICATION;
            case 41: 
                return TransactionType.CERTEGY_REVERSE_REQUEST;
            case 42: 
                return TransactionType.ISTREAM2_SEND_SINGLE_ICL;
            case 43: 
                return TransactionType.CHECK_INFO;
            case 44: 
                return TransactionType.RESTORE_CARD;
            case 45: 
                return TransactionType.COMPLIANCE_NEW_BRANCH;
            case 46: 
                return TransactionType.COMPLIANCE_POST_TRANSACTION; 
            case 53: 
                return TransactionType.IDEOLOGY_VERYFY_CLIENT;
            case 54: 
                return TransactionType.FISS_SET_PRODUCT_ID;
            case 55: 
                return TransactionType.FISS_CHANGE_PASSWORD;
            case 56: 
                return TransactionType.FISS_SET_PIN;
            default:
                  return TransactionType.TRANSACTION_TYPE;
        }
    }

    }   