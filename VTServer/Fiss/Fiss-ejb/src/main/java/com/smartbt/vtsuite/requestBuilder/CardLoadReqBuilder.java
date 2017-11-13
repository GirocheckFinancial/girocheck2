package com.smartbt.vtsuite.requestBuilder;

import com.smartbt.vtsuite.util.FissParam;
import static com.smartbt.vtsuite.util.FissParam.AMOUNT;
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
import static com.smartbt.vtsuite.util.FissParam.TEST_INDICATOR;
import static com.smartbt.vtsuite.util.FissParam.USER;
import static com.smartbt.vtsuite.util.FissUtil.LOG_REQUEST;
import static com.smartbt.vtsuite.util.FissUtil.closeTag;
import static com.smartbt.vtsuite.util.FissUtil.print;
import static com.smartbt.vtsuite.util.FissUtil.startTag; 
import com.smartbt.vtsuite.ws.balanceInquiry.CBAcctInqReqData;
import com.smartbt.vtsuite.ws.cardLoad.CBPrpdLdUnldMtvnSvcReq;
import com.smartbt.vtsuite.ws.cardLoad.CBPrpdLdUnldReqData;
import java.util.Map;

/**
 *
 * @author rrodriguez
 */
public class CardLoadReqBuilder {

    public static CBPrpdLdUnldMtvnSvcReq build(Map<FissParam, String> map) {

        if (LOG_REQUEST) {
            System.out.println("Printing CardLoadRequest...");
        }

        CBPrpdLdUnldMtvnSvcReq request = new CBPrpdLdUnldMtvnSvcReq();

        startTag("", "REQUEST");
        request.setMtvnSvcVer(map.get(FISS_SERVICE_VERSION));
        request.setMsgUUID(map.get(MSG_UUID));

        print(null, map, "", FISS_SERVICE_VERSION, MSG_UUID);

        request.setPrcsParms(buildPrcsParams("", map));
        request.getSvc().add(buildSVC("", map));

        closeTag("", "REQUEST");
        return request;
    }

    private static CBPrpdLdUnldMtvnSvcReq.PrcsParms buildPrcsParams(String space, Map<FissParam, String> map) {
        CBPrpdLdUnldMtvnSvcReq.PrcsParms params = new CBPrpdLdUnldMtvnSvcReq.PrcsParms();
        params.setSrcID(map.get(SOURCE_ID));
        params.setTestInd(map.get(TEST_INDICATOR));

        print("PRCS_POARAMS", map, space, SOURCE_ID, TEST_INDICATOR);
        return params;
    }

    private static CBPrpdLdUnldMtvnSvcReq.Svc buildSVC(String space, Map<FissParam, String> map) {
        space = "   " + space;
        CBPrpdLdUnldMtvnSvcReq.Svc svcItem = new CBPrpdLdUnldMtvnSvcReq.Svc();
        startTag(space, "SVC");
        svcItem.setSvcParms(buildSvcParams(space, map));
        svcItem.setSecurity(buildSecurity(space, map));
        svcItem.setMsgData(buildMsgData(space, map));
        closeTag(space, "SVC");
        return svcItem;
    }

    private static CBPrpdLdUnldMtvnSvcReq.Svc.SvcParms buildSvcParams(String space, Map<FissParam, String> map) {
        CBPrpdLdUnldMtvnSvcReq.Svc.SvcParms params = new CBPrpdLdUnldMtvnSvcReq.Svc.SvcParms();
        params.setApplID(map.get(APPLICATION_ID));
        params.setSvcID(map.get(SERVICE_ID));
        params.setSvcVer(map.get(SERVICE_VERSION));
        params.setRqstUUID(map.get(REQUEST_ID));
        params.setRoutingID(map.get(ROUTING_ID));

        print("SVC_POARAMS", map, space, APPLICATION_ID, SERVICE_ID, SERVICE_VERSION, REQUEST_ID, ROUTING_ID);

        return params;
    }

    private static CBPrpdLdUnldMtvnSvcReq.Svc.Security buildSecurity(String space, Map<FissParam, String> map) {
        CBPrpdLdUnldMtvnSvcReq.Svc.Security security = new CBPrpdLdUnldMtvnSvcReq.Svc.Security();

        CBPrpdLdUnldMtvnSvcReq.Svc.Security.BasicAuth basicAuth = new CBPrpdLdUnldMtvnSvcReq.Svc.Security.BasicAuth();
        basicAuth.setUsrID(map.get(USER));
        basicAuth.setPwd(map.get(PASSWORD));

        security.setBasicAuth(basicAuth);

        print("SECURITY/BASIC_AUTH", map, space, USER, PASSWORD);

        return security;
    }

    private static CBPrpdLdUnldMtvnSvcReq.Svc.MsgData buildMsgData(String space, Map<FissParam, String> map) {
        CBPrpdLdUnldMtvnSvcReq.Svc.MsgData data = new CBPrpdLdUnldMtvnSvcReq.Svc.MsgData();

        CBPrpdLdUnldReqData reqData = new CBPrpdLdUnldReqData();
        reqData.setE130013(map.get(CARD_NUMBER));
        reqData.setE130484(map.get(AMOUNT));

        data.setCBPrpdLdUnldReqData(reqData);

        print("MSG_DATA/REQUEST_DATA", map, space, CARD_NUMBER, AMOUNT);

        return data;
    }

}
