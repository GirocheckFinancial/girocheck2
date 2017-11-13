package com.smartbt.vtsuite.requestBuilder;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.vtsuite.util.FissParam;
import static com.smartbt.vtsuite.util.FissParam.APPLICATION_ID;
import static com.smartbt.vtsuite.util.FissParam.CARD_NUMBER;
import static com.smartbt.vtsuite.util.FissParam.FISS_SERVICE_VERSION;
import static com.smartbt.vtsuite.util.FissParam.HOLD_STATUS_FILTER;
import static com.smartbt.vtsuite.util.FissParam.INSTITUTION_NUMBER;
import static com.smartbt.vtsuite.util.FissParam.MSG_UUID;
import static com.smartbt.vtsuite.util.FissParam.PASSWORD;
import static com.smartbt.vtsuite.util.FissParam.PIN;
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
import com.smartbt.vtsuite.ws.setPin.CBPinOffsetChgMtvnSvcReq;
import com.smartbt.vtsuite.ws.setPin.CBPinOffsetChgReqData;
import java.util.Map;

/**
 *
 * @author rrodriguez
 */
public class SetPinReqBuilder {

    public static CBPinOffsetChgMtvnSvcReq build(Map<FissParam, String> map) {

        if (LOG_REQUEST) {
            System.out.println("Printing SetPinRequest...");
        }

        CBPinOffsetChgMtvnSvcReq request = new CBPinOffsetChgMtvnSvcReq();

        startTag("", "REQUEST");
        request.setMtvnSvcVer(map.get(FISS_SERVICE_VERSION));
        request.setMsgUUID(map.get(MSG_UUID));

        print(null, map, "", FISS_SERVICE_VERSION, MSG_UUID);

        request.setPrcsParms(buildPrcsParams("", map));
        request.getSvc().add(buildSVC("", map));

        closeTag("", "REQUEST");
        return request;
    }

    private static CBPinOffsetChgMtvnSvcReq.PrcsParms buildPrcsParams(String space, Map<FissParam, String> map) {
        CBPinOffsetChgMtvnSvcReq.PrcsParms params = new CBPinOffsetChgMtvnSvcReq.PrcsParms();
        params.setSrcID(map.get(SOURCE_ID));
        params.setTestInd(map.get(TEST_INDICATOR));

        print("PRCS_POARAMS", map, space, SOURCE_ID, TEST_INDICATOR);
        return params;
    }

    private static CBPinOffsetChgMtvnSvcReq.Svc buildSVC(String space, Map<FissParam, String> map) {
        space = "   " + space;
        CBPinOffsetChgMtvnSvcReq.Svc svcItem = new CBPinOffsetChgMtvnSvcReq.Svc();
        startTag(space, "SVC");
        svcItem.setSvcParms(buildSvcParams(space, map));
        svcItem.setSecurity(buildSecurity(space, map));
        svcItem.setMsgData(buildMsgData(space, map));
        closeTag(space, "SVC");
        return svcItem;
    }

    private static CBPinOffsetChgMtvnSvcReq.Svc.SvcParms buildSvcParams(String space, Map<FissParam, String> map) {
        CBPinOffsetChgMtvnSvcReq.Svc.SvcParms params = new CBPinOffsetChgMtvnSvcReq.Svc.SvcParms();
        params.setApplID(map.get(APPLICATION_ID));
        params.setSvcID(map.get(SERVICE_ID));
        params.setSvcVer(map.get(SERVICE_VERSION));
        params.setRqstUUID(map.get(REQUEST_ID));
        params.setRoutingID(map.get(ROUTING_ID));

        print("SVC_POARAMS", map, space, APPLICATION_ID, SERVICE_ID, SERVICE_VERSION, REQUEST_ID, ROUTING_ID);

        return params;
    }

    private static CBPinOffsetChgMtvnSvcReq.Svc.Security buildSecurity(String space, Map<FissParam, String> map) {
        CBPinOffsetChgMtvnSvcReq.Svc.Security security = new CBPinOffsetChgMtvnSvcReq.Svc.Security();

        CBPinOffsetChgMtvnSvcReq.Svc.Security.BasicAuth basicAuth = new CBPinOffsetChgMtvnSvcReq.Svc.Security.BasicAuth();
        basicAuth.setUsrID(map.get(USER));
        basicAuth.setPwd(map.get(PASSWORD));

        security.setBasicAuth(basicAuth);

        print("SECURITY/BASIC_AUTH", map, space, USER, PASSWORD);

        return security;
    }

    private static CBPinOffsetChgMtvnSvcReq.Svc.MsgData buildMsgData(String space, Map<FissParam, String> map) {
        CBPinOffsetChgMtvnSvcReq.Svc.MsgData data = new CBPinOffsetChgMtvnSvcReq.Svc.MsgData();

        CBPinOffsetChgReqData reqData = new CBPinOffsetChgReqData();
        reqData.setE130013(map.get(CARD_NUMBER));
        reqData.setE130015(map.get(INSTITUTION_NUMBER));
        reqData.setE130305(map.get(PIN));

        data.setCBPinOffsetChgReqData(null);

        print("MSG_DATA/REQUEST_DATA", map, space, CARD_NUMBER,INSTITUTION_NUMBER, PIN);

        return data;
    }

}
