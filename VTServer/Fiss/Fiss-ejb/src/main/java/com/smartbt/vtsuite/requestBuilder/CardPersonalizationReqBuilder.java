package com.smartbt.vtsuite.requestBuilder;

import com.smartbt.vtsuite.util.FissParam;
import static com.smartbt.vtsuite.util.FissParam.APPLICATION_ID;
import static com.smartbt.vtsuite.util.FissParam.CARD_NUMBER;
import static com.smartbt.vtsuite.util.FissParam.FISS_SERVICE_VERSION;
import static com.smartbt.vtsuite.util.FissParam.INSTITUTION_NUMBER;
import static com.smartbt.vtsuite.util.FissParam.MSG_UUID;
import static com.smartbt.vtsuite.util.FissParam.PASSWORD;
import static com.smartbt.vtsuite.util.FissParam.REQUEST_ID;
import static com.smartbt.vtsuite.util.FissParam.ROUTING_ID;
import static com.smartbt.vtsuite.util.FissParam.SERVICE_ID;
import static com.smartbt.vtsuite.util.FissParam.SERVICE_VERSION;
import static com.smartbt.vtsuite.util.FissParam.SOURCE_ID;
import static com.smartbt.vtsuite.util.FissParam.TEST_INDICATOR;
import static com.smartbt.vtsuite.util.FissParam.USER;
import static com.smartbt.vtsuite.util.FissUtil.LOG_REQUEST;
import static com.smartbt.vtsuite.util.FissUtil.closeTag;
import static com.smartbt.vtsuite.util.FissUtil.print;
import static com.smartbt.vtsuite.util.FissUtil.startTag;
import com.smartbt.vtsuite.ws.cardPersonalization.CBNmeAddrChgMtvnSvcReq;
import com.smartbt.vtsuite.ws.cardPersonalization.CBNmeAddrChgReqData;
import java.util.Map;

/**
 *
 * @author rrodriguez
 */
public class CardPersonalizationReqBuilder {

    public static CBNmeAddrChgMtvnSvcReq build(Map<FissParam, String> map) {

        if (LOG_REQUEST) {
            System.out.println("Printing CardPersonalizationRequest...");
        }

        CBNmeAddrChgMtvnSvcReq request = new CBNmeAddrChgMtvnSvcReq();

        startTag("", "REQUEST");
        request.setMtvnSvcVer(map.get(FISS_SERVICE_VERSION));
        request.setMsgUUID(map.get(MSG_UUID));

        print(null, map, "", FISS_SERVICE_VERSION, MSG_UUID);

        request.setPrcsParms(buildPrcsParams("", map));
        request.getSvc().add(buildSVC("", map));

        closeTag("", "REQUEST");
        return request;
    }

    private static CBNmeAddrChgMtvnSvcReq.PrcsParms buildPrcsParams(String space, Map<FissParam, String> map) {
        CBNmeAddrChgMtvnSvcReq.PrcsParms params = new CBNmeAddrChgMtvnSvcReq.PrcsParms();
        params.setSrcID(map.get(SOURCE_ID));
        params.setTestInd(map.get(TEST_INDICATOR));

        print("PRCS_POARAMS", map, space, SOURCE_ID, TEST_INDICATOR);
        return params;
    }

    private static CBNmeAddrChgMtvnSvcReq.Svc buildSVC(String space, Map<FissParam, String> map) {
        space = "   " + space;
        CBNmeAddrChgMtvnSvcReq.Svc svcItem = new CBNmeAddrChgMtvnSvcReq.Svc();
        startTag(space, "SVC");
        svcItem.setSvcParms(buildSvcParams(space, map));
        svcItem.setSecurity(buildSecurity(space, map));
        svcItem.setMsgData(buildMsgData(space, map));
        closeTag(space, "SVC");
        return svcItem;
    }

    private static CBNmeAddrChgMtvnSvcReq.Svc.SvcParms buildSvcParams(String space, Map<FissParam, String> map) {
        CBNmeAddrChgMtvnSvcReq.Svc.SvcParms params = new CBNmeAddrChgMtvnSvcReq.Svc.SvcParms();
        params.setApplID(map.get(APPLICATION_ID));
        params.setSvcID(map.get(SERVICE_ID));
        params.setSvcVer(map.get(SERVICE_VERSION));
        params.setRqstUUID(map.get(REQUEST_ID));
        params.setRoutingID(map.get(ROUTING_ID));

        print("SVC_POARAMS", map, space, APPLICATION_ID, SERVICE_ID, SERVICE_VERSION, REQUEST_ID, ROUTING_ID);

        return params;
    }

    private static CBNmeAddrChgMtvnSvcReq.Svc.Security buildSecurity(String space, Map<FissParam, String> map) {
        CBNmeAddrChgMtvnSvcReq.Svc.Security security = new CBNmeAddrChgMtvnSvcReq.Svc.Security();

        CBNmeAddrChgMtvnSvcReq.Svc.Security.BasicAuth basicAuth = new CBNmeAddrChgMtvnSvcReq.Svc.Security.BasicAuth();
        basicAuth.setUsrID(map.get(USER));
        basicAuth.setPwd(map.get(PASSWORD));

        security.setBasicAuth(basicAuth);

        print("SECURITY/BASIC_AUTH", map, space, USER, PASSWORD);

        return security;
    }

    private static CBNmeAddrChgMtvnSvcReq.Svc.MsgData buildMsgData(String space, Map<FissParam, String> map) {
        CBNmeAddrChgMtvnSvcReq.Svc.MsgData data = new CBNmeAddrChgMtvnSvcReq.Svc.MsgData();

        CBNmeAddrChgReqData reqData = new CBNmeAddrChgReqData();
        reqData.setE130013(map.get(CARD_NUMBER));
        reqData.setE130015(map.get(INSTITUTION_NUMBER));
        reqData.setE130025(map.get(FissParam.FIRST_NAME));
        reqData.setE130027(map.get(FissParam.LAST_NAME));
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

        print("MSG_DATA/REQUEST_DATA", map, space, CARD_NUMBER, INSTITUTION_NUMBER,
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
                FissParam.TELEPHONE);

        return data;
    }

}
