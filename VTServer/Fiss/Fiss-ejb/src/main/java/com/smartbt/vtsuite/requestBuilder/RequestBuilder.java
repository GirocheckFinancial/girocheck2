package com.smartbt.vtsuite.requestBuilder;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.vtsuite.util.CardLoadType;
import com.smartbt.vtsuite.util.FissParam;
import com.smartbt.vtsuite.util.FissUtil;
import static com.smartbt.vtsuite.util.RequestMapBuilder.buildParamsMap;
import com.smartbt.vtsuite.ws.balanceInquiry.CBAcctInqMtvnSvcReq;
import com.smartbt.vtsuite.ws.cardActivation.CBNegFleMaintMtvnSvcReq;
import com.smartbt.vtsuite.ws.cardCashing.CBPrpdAdjMtvnSvcReq;
import com.smartbt.vtsuite.ws.cardLoad.CBPrpdLdUnldMtvnSvcReq;
import com.smartbt.vtsuite.ws.cardPersonalization.CBNmeAddrChgMtvnSvcReq;
import com.smartbt.vtsuite.ws.changePassword.SZChgPwdMtvnSvcReq;
import com.smartbt.vtsuite.ws.history.details.CBHistTxnDtlInqMtvnSvcReq;
import com.smartbt.vtsuite.ws.history.general.CBHistTxnInqMtvnSvcReq;
import com.smartbt.vtsuite.ws.history.hold.CBHoldListInqMtvnSvcReq;
import com.smartbt.vtsuite.ws.history.pending.CBPndTxnInqMtvnSvcReq;
import com.smartbt.vtsuite.ws.setPin.CBPinOffsetChgMtvnSvcReq;
import com.smartbt.vtsuite.ws.setProductId.CBProdIDMaintMtvnSvcReq;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author rrodriguez
 */
public class RequestBuilder {

    private static DateFormat DOB_DATE_FORMAT = new SimpleDateFormat("YYMMdd");

    public static CBPrpdAdjMtvnSvcReq buildCardCashingRequest(Map<ParameterName, Object> params) {
        Map<FissParam, String> fissParams = buildParamsMap(params);
        fissParams.put(FissParam.APPLICATION_ID, "CB");
        fissParams.put(FissParam.SERVICE_ID, "CBPrpdAdj");
        fissParams.put(FissParam.SERVICE_VERSION, "5.0");
        fissParams.put(FissParam.DETAILED_TRANSACTION_DESCRIPTION, "G5");
        fissParams.put(FissParam.REASON_CODE, "99");
        fissParams.put(FissParam.MERCHANT_NAME, (String) params.get(ParameterName.MERCHANT_NAME));
        fissParams.put(FissParam.MERCHANT_CITY_STATE, (String) params.get(ParameterName.MERCHANT_CITY_STATE));
        fissParams.put(FissParam.CARD_NUMBER, (String) params.get(ParameterName.CARD_NUMBER));
        fissParams.put(FissParam.AMOUNT, params.get(ParameterName.AMOUNT) + "");
        return CardCashingReqBuilder.build(fissParams);
    }

    public static CBAcctInqMtvnSvcReq buildBalanceInquiryRequest(Map<ParameterName, Object> params) {
        Map<FissParam, String> fissParams = buildParamsMap(params);
        fissParams.put(FissParam.APPLICATION_ID, "CB");
        fissParams.put(FissParam.SERVICE_ID, "CBAcctInq");
        fissParams.put(FissParam.SERVICE_VERSION, "15.0");
        fissParams.put(FissParam.CARD_NUMBER, (String) params.get(ParameterName.CARD_NUMBER));
        return BalanceInquiryReqBuilder.build(fissParams);
    }

    public static CBNmeAddrChgMtvnSvcReq buildCardPersonalizationRequest(Map<ParameterName, Object> params) {
        Map<FissParam, String> fissParams = buildParamsMap(params);
        fissParams.put(FissParam.APPLICATION_ID, "CB");
        fissParams.put(FissParam.SERVICE_ID, "CBNmeAddrChg");
        fissParams.put(FissParam.SERVICE_VERSION, "13.0");

        fissParams.put(FissParam.CARD_NUMBER, (String) params.get(ParameterName.CARD_NUMBER));
        fissParams.put(FissParam.FIRST_NAME, (String) params.get(ParameterName.FIRST_NAME));
        fissParams.put(FissParam.LAST_NAME, (String) params.get(ParameterName.LAST_NAME));
        fissParams.put(FissParam.STREET, (String) params.get(ParameterName.ADDRESS));
        fissParams.put(FissParam.STREET2, " ");
        fissParams.put(FissParam.CITY, (String) params.get(ParameterName.CITY));
        fissParams.put(FissParam.STATE, (String) params.get(ParameterName.STATE_ABBREVIATION));
        fissParams.put(FissParam.COUNTRY, "USA");
        fissParams.put(FissParam.SSN, (String) params.get(ParameterName.SSN));
        fissParams.put(FissParam.ZIPCODE, (String) params.get(ParameterName.ZIPCODE));
        fissParams.put(FissParam.TELEPHONE, (String) params.get(ParameterName.TELEPHONE));

        if (params.containsKey(ParameterName.BORNDATE_AS_DATE)) {
            Date dob = (Date) params.get(ParameterName.BORNDATE_AS_DATE);

            if (dob != null) {
                fissParams.put(FissParam.DOB, "21" + DOB_DATE_FORMAT.format(dob)); //start with century 21
            }
        } else {
            //this is for restore card
            fissParams.put(FissParam.DOB, (String) params.get(ParameterName.BORNDATE));
        }

        return CardPersonalizationReqBuilder.build(fissParams);
    }

    public static CBNegFleMaintMtvnSvcReq buildCardActivationRequest(Map<ParameterName, Object> params) {
        Map<FissParam, String> fissParams = buildParamsMap(params);
        fissParams.put(FissParam.APPLICATION_ID, "CB");
        fissParams.put(FissParam.SERVICE_ID, "CBNegFleMaint");
        fissParams.put(FissParam.SERVICE_VERSION, "1.0");
        fissParams.put(FissParam.CARD_NUMBER, (String) params.get(ParameterName.CARD_NUMBER));
        fissParams.put(FissParam.RESULT_CODE, (String) params.get(ParameterName.STATUS_CODE));

        return CardActivationReqBuilder.build(fissParams);
    }

    public static CBProdIDMaintMtvnSvcReq buildSetProductIdRequest(Map<ParameterName, Object> params) {
        Map<FissParam, String> fissParams = buildParamsMap(params);
        fissParams.put(FissParam.APPLICATION_ID, "CB");
        fissParams.put(FissParam.SERVICE_ID, "CBProdIDMaint");
        fissParams.put(FissParam.SERVICE_VERSION, "1.0");
        fissParams.put(FissParam.CARD_NUMBER, (String) params.get(ParameterName.CARD_NUMBER));
        fissParams.put(FissParam.PRODUCT_ID, FissUtil.PRODUCT_ID);

        return SetProductIdReqBuilder.build(fissParams);
    }

    public static CBPrpdLdUnldMtvnSvcReq buildCardLoadRequest(Map<ParameterName, Object> params) {
        Map<FissParam, String> fissParams = buildParamsMap(params);
        fissParams.put(FissParam.APPLICATION_ID, "CB");
        fissParams.put(FissParam.SERVICE_ID, "CBPrpdLdUnld");
        fissParams.put(FissParam.SERVICE_VERSION, "3.0");
        fissParams.put(FissParam.CARD_NUMBER, (String) params.get(ParameterName.CARD_NUMBER));
        fissParams.put(FissParam.AMOUNT, params.get(ParameterName.AMOUNT) + "");
        fissParams.put(FissParam.FEE_AMOUNT, params.get(ParameterName.FEE_AMOUNT) + "");

        ParameterName requestType = (ParameterName) params.get(ParameterName.REQUEST_TYPE);

        String operation = (String) params.get(ParameterName.OPERATION);
        Boolean isCheck = operation.equals("01");

        CardLoadType cardLoadType = null;
        if (requestType == ParameterName.AMOUNT) {
            cardLoadType = isCheck ? CardLoadType.CHECK : CardLoadType.CASH;
        } else {
            cardLoadType = isCheck ? CardLoadType.CHECK_FEE : CardLoadType.CASH_FEE;
        }
        fissParams.put(FissParam.CARD_LOAD_TYPE, cardLoadType.toString());

        return CardLoadReqBuilder.build(fissParams);
    }

    public static CBPrpdAdjMtvnSvcReq buildCardLoadCashRequest(Map<ParameterName, Object> params) {
        Map<FissParam, String> fissParams = buildParamsMap(params);
        fissParams.put(FissParam.APPLICATION_ID, "CB");
        fissParams.put(FissParam.SERVICE_ID, "CBPrpdAdj");
        fissParams.put(FissParam.SERVICE_VERSION, "5.0");
        fissParams.put(FissParam.DETAILED_TRANSACTION_DESCRIPTION, "G2");
        fissParams.put(FissParam.REASON_CODE, "04");
        fissParams.put(FissParam.CARD_NUMBER, (String) params.get(ParameterName.CARD_NUMBER));
        fissParams.put(FissParam.AMOUNT, params.get(ParameterName.AMOUNT) + "");
        fissParams.put(FissParam.MERCHANT_NAME, (String) params.get(ParameterName.MERCHANT_NAME));
        fissParams.put(FissParam.MERCHANT_CITY_STATE, (String) params.get(ParameterName.MERCHANT_CITY_STATE));

        return CardLoadCashReqBuilder.build(fissParams);
    }

    public static SZChgPwdMtvnSvcReq buildChangePasswordRequest(Map<ParameterName, Object> params) {
        Map<FissParam, String> fissParams = buildParamsMap(params);
        fissParams.put(FissParam.APPLICATION_ID, "SZ");
        fissParams.put(FissParam.SERVICE_ID, "SZChgPwd");
        fissParams.put(FissParam.SERVICE_VERSION, "1.0");
        fissParams.put(FissParam.NEW_PASSWORD, params.get(ParameterName.PASSWORD) + "");

        return ChangePasswordReqBuilder.build(fissParams);
    }

    public static CBPinOffsetChgMtvnSvcReq buildSetPinRequest(Map<ParameterName, Object> params) {
        Map<FissParam, String> fissParams = buildParamsMap(params);
        fissParams.put(FissParam.APPLICATION_ID, "CB");
        fissParams.put(FissParam.SERVICE_ID, "CBPinOffsetChg");
        fissParams.put(FissParam.SERVICE_VERSION, "1.0");

        String ssn = (String) params.get(ParameterName.SSN);
        String pin = "";

        if (ssn != null && ssn.length() >= 4) {
            pin = ssn.substring(ssn.length() - 4);
        }

        fissParams.put(FissParam.PIN, pin);
        fissParams.put(FissParam.CARD_NUMBER, (String) params.get(ParameterName.CARD_NUMBER));
        fissParams.put(FissParam.INSTITUTION_NUMBER, FissUtil.INSTITUTION_NUMBER);
        return SetPinReqBuilder.build(fissParams);
    }

    public static CBHoldListInqMtvnSvcReq buildTransactionHistoryHoldRequest(Map<ParameterName, Object> params) {
        Map<FissParam, String> fissParams = buildParamsMap(params);
        fissParams.put(FissParam.APPLICATION_ID, "CB");
        fissParams.put(FissParam.SERVICE_ID, "CBHoldListInq");
        fissParams.put(FissParam.SERVICE_VERSION, "2.0");
        fissParams.put(FissParam.HOLD_STATUS_FILTER, FissUtil.HOLD_STATUS_FILTER);
        fissParams.put(FissParam.CARD_NUMBER, (String) params.get(ParameterName.CARD_NUMBER));
        return TransactionHistoryHoldReqBuilder.build(fissParams);
    }

    public static CBPndTxnInqMtvnSvcReq buildTransactionHistoryPendingRequest(Map<ParameterName, Object> params) {
        Map<FissParam, String> fissParams = buildParamsMap(params);
        fissParams.put(FissParam.APPLICATION_ID, "CB");
        fissParams.put(FissParam.SERVICE_ID, "CBPndTxnInq");
        fissParams.put(FissParam.SERVICE_VERSION, "7.0");
        fissParams.put(FissParam.CARD_NUMBER, (String) params.get(ParameterName.CARD_NUMBER));
        fissParams.put(FissParam.PENDING_TRANSACTION_FILTER, FissUtil.PENDING_TRANSACTION_FILTER);
        return TransactionHistoryPendingReqBuilder.build(fissParams);
    }

    public static CBHistTxnInqMtvnSvcReq buildTransactionHistoryGeneralRequest(Map<ParameterName, Object> params) {
        Map<FissParam, String> fissParams = buildParamsMap(params);
        fissParams.put(FissParam.APPLICATION_ID, "CB");
        fissParams.put(FissParam.SERVICE_ID, "CBHistTxnInq");
        fissParams.put(FissParam.SERVICE_VERSION, "7.0");
        fissParams.put(FissParam.CARD_NUMBER, (String) params.get(ParameterName.CARD_NUMBER));
        fissParams.put(FissParam.PENDING_TRANSACTION_FILTER, FissUtil.PENDING_TRANSACTION_FILTER);
        fissParams.put(FissParam.NUMBER_OF_OCCURS, FissUtil.NUMBER_OF_OCCURS);
        return TransactionHistoryGeneralReqBuilder.build(fissParams);
    }

    public static CBHistTxnDtlInqMtvnSvcReq buildTransactionHistoryDetailsRequest(Map<ParameterName, Object> params) {
        Map<FissParam, String> fissParams = buildParamsMap(params);
        fissParams.put(FissParam.APPLICATION_ID, "CB");
        fissParams.put(FissParam.SERVICE_ID, "CBHistTxnDtlInq");
        fissParams.put(FissParam.SERVICE_VERSION, "6.0");
        fissParams.put(FissParam.CARD_NUMBER, (String) params.get(ParameterName.CARD_NUMBER));
        fissParams.put(FissParam.PENDING_TRANSACTION_FILTER, FissUtil.PENDING_TRANSACTION_FILTER);
        return TransactionHistoryDetailsReqBuilder.build(fissParams);
    }

}
