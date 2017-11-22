package com.smartbt.vtsuite.requestBuilder;

import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.vtsuite.util.FissParam;
import static com.smartbt.vtsuite.util.FissParam.APPLICATION_ID;
import static com.smartbt.vtsuite.util.FissParam.CARD_NUMBER;
import static com.smartbt.vtsuite.util.FissParam.FISS_SERVICE_VERSION;
import static com.smartbt.vtsuite.util.FissParam.MSG_UUID;
import static com.smartbt.vtsuite.util.FissParam.PASSWORD;
import static com.smartbt.vtsuite.util.FissParam.REQUEST_ID;
import static com.smartbt.vtsuite.util.FissParam.ROUTING_ID;
import static com.smartbt.vtsuite.util.FissParam.SERVICE_ID;
import static com.smartbt.vtsuite.util.FissParam.SERVICE_VERSION;
import static com.smartbt.vtsuite.util.FissParam.SOURCE_ID;

import static com.smartbt.vtsuite.util.FissParam.USER;
import com.smartbt.vtsuite.util.FissPrintUtil;
import static com.smartbt.vtsuite.util.FissPrintUtil.buildXML;
import static com.smartbt.vtsuite.util.FissPrintUtil.endTag;
import static com.smartbt.vtsuite.util.FissPrintUtil.startTag;

import com.smartbt.vtsuite.ws.cardPersonalization.CBNmeAddrChgMtvnSvcReq;
import com.smartbt.vtsuite.ws.cardPersonalization.CBNmeAddrChgReqData;
import java.util.Map;

/**
 *
 * @author rrodriguez
 */
public class CardPersonalizationReqBuilder {

    public static CBNmeAddrChgMtvnSvcReq build(Map<FissParam, String> map) {

        CBNmeAddrChgMtvnSvcReq request = new CBNmeAddrChgMtvnSvcReq();

        TransactionType transactionType = TransactionType.valueOf(map.get(FissParam.TRANSACTION_TYPE));
        StringBuilder sb = new StringBuilder("\n\n--------- Printing " + transactionType + "_REQUEST --------\n");

        sb.append(startTag("", transactionType + "_REQUEST"));
        request.setMtvnSvcVer(map.get(FISS_SERVICE_VERSION));
        request.setMsgUUID(map.get(MSG_UUID));

        sb.append(buildXML(null, map, "", FISS_SERVICE_VERSION, MSG_UUID));

        request.setPrcsParms(buildPrcsParams("", map, sb));
        request.getSvc().add(buildSVC("", map, sb));

        sb.append(endTag("", transactionType + "_REQUEST"));

        if (FissPrintUtil.ACTIVE_FISS_LOGS) {
            System.out.println(sb.toString());
        }
        return request;
    }

    private static CBNmeAddrChgMtvnSvcReq.PrcsParms buildPrcsParams(String space, Map<FissParam, String> map, StringBuilder sb) {
        CBNmeAddrChgMtvnSvcReq.PrcsParms params = new CBNmeAddrChgMtvnSvcReq.PrcsParms();
        params.setSrcID(map.get(SOURCE_ID));

        sb.append(buildXML("PRCS_POARAMS", map, space, SOURCE_ID));
        return params;
    }

    private static CBNmeAddrChgMtvnSvcReq.Svc buildSVC(String space, Map<FissParam, String> map, StringBuilder sb) {
        space = "   " + space;
        CBNmeAddrChgMtvnSvcReq.Svc svcItem = new CBNmeAddrChgMtvnSvcReq.Svc();
        sb.append(startTag(space, "SVC"));
        svcItem.setSvcParms(buildSvcParams(space, map, sb));
        svcItem.setSecurity(buildSecurity(space, map, sb));
        svcItem.setMsgData(buildMsgData(space, map, sb));
        sb.append(endTag(space, "SVC"));
        return svcItem;
    }

    private static CBNmeAddrChgMtvnSvcReq.Svc.SvcParms buildSvcParams(String space, Map<FissParam, String> map, StringBuilder sb) {
        CBNmeAddrChgMtvnSvcReq.Svc.SvcParms params = new CBNmeAddrChgMtvnSvcReq.Svc.SvcParms();
        params.setApplID(map.get(APPLICATION_ID));
        params.setSvcID(map.get(SERVICE_ID));
        params.setSvcVer(map.get(SERVICE_VERSION));
        params.setRqstUUID(map.get(REQUEST_ID));
        params.setRoutingID(map.get(ROUTING_ID));

        sb.append(buildXML("SVC_PARAMS", map, space, APPLICATION_ID, SERVICE_ID, SERVICE_VERSION, REQUEST_ID, ROUTING_ID));

        return params;
    }

    private static CBNmeAddrChgMtvnSvcReq.Svc.Security buildSecurity(String space, Map<FissParam, String> map, StringBuilder sb) {
        CBNmeAddrChgMtvnSvcReq.Svc.Security security = new CBNmeAddrChgMtvnSvcReq.Svc.Security();

        CBNmeAddrChgMtvnSvcReq.Svc.Security.BasicAuth basicAuth = new CBNmeAddrChgMtvnSvcReq.Svc.Security.BasicAuth();
        basicAuth.setUsrID(map.get(USER));
        basicAuth.setPwd(map.get(PASSWORD));

        security.setBasicAuth(basicAuth);

        sb.append(buildXML("SECURITY/BASIC_AUTH", map, space, USER, PASSWORD));

        return security;
    }

    private static CBNmeAddrChgMtvnSvcReq.Svc.MsgData buildMsgData(String space, Map<FissParam, String> map, StringBuilder sb) {
        CBNmeAddrChgMtvnSvcReq.Svc.MsgData data = new CBNmeAddrChgMtvnSvcReq.Svc.MsgData();

        CBNmeAddrChgReqData reqData = new CBNmeAddrChgReqData();
        reqData.setE130013(map.get(CARD_NUMBER));

        String fullName = map.get(FissParam.FIRST_NAME) + " " + map.get(FissParam.LAST_NAME);

        fullName = trim(fullName, 26);

        reqData.setE130025(fullName);
        reqData.setE130024(trim(map, FissParam.FIRST_NAME, 10));
        reqData.setE130023(trim(map, FissParam.LAST_NAME, 15));
        reqData.setE130029(map.get(FissParam.STREET));
        reqData.setE130030(map.get(FissParam.STREET2));
        reqData.setE130031(map.get(FissParam.CITY));
        reqData.setE130032(map.get(FissParam.STATE));
        reqData.setE130033(map.get(FissParam.COUNTRY));
        reqData.setE130034(map.get(FissParam.ZIPCODE));
        reqData.setE130138(map.get(FissParam.TELEPHONE));
        reqData.setE130145(map.get(FissParam.SSN));
        reqData.setE130146(map.get(FissParam.TELEPHONE));
        reqData.setE130147(map.get(FissParam.DOB));

        data.setCBNmeAddrChgReqData(reqData);

        sb.append(buildXML("MSG_DATA/REQUEST_DATA", map, space, CARD_NUMBER,
                FissParam.FIRST_NAME,
                FissParam.LAST_NAME,
                FissParam.STREET,
                FissParam.STREET2,
                FissParam.CITY,
                FissParam.STATE,
                FissParam.COUNTRY,
                FissParam.ZIPCODE,
                FissParam.SSN,
                FissParam.DOB, //CCYYMMDD
                FissParam.TELEPHONE));

        return data;
    }

    public static String trim(Map<FissParam, String> map, FissParam param, int length) {
        return trim(map.get(param), length);
    }

    public static String trim(String str, int length) {
        if (str != null && str.length() > length) {
            return str.substring(0, length);
        }
        return str;
    }
}
