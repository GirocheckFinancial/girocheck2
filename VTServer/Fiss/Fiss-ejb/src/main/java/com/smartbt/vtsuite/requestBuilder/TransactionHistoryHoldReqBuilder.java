package com.smartbt.vtsuite.requestBuilder;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.vtsuite.util.FissParam;
import static com.smartbt.vtsuite.util.FissParam.APPLICATION_ID;
import static com.smartbt.vtsuite.util.FissParam.CARD_NUMBER;
import static com.smartbt.vtsuite.util.FissParam.FISS_SERVICE_VERSION;
import static com.smartbt.vtsuite.util.FissParam.HOLD_STATUS_FILTER;
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
import com.smartbt.vtsuite.util.RequestMapBuilder;
import com.smartbt.vtsuite.ws.history.hold.CBHoldListInqMtvnSvcReq;
import com.smartbt.vtsuite.ws.history.hold.CBHoldListInqReqData;
import java.util.Map;

/**
 *
 * @author rrodriguez
 */
public class TransactionHistoryHoldReqBuilder {

    public static CBHoldListInqMtvnSvcReq build(Map<FissParam, String> map) {

        if (LOG_REQUEST) {
            System.out.println("Printing TransactionHistoryHoldsRequest...");
        }

        CBHoldListInqMtvnSvcReq request = new CBHoldListInqMtvnSvcReq();

        startTag("", "REQUEST");
        request.setMtvnSvcVer(map.get(FISS_SERVICE_VERSION));
        request.setMsgUUID(map.get(MSG_UUID));

        print(null, map, "", FISS_SERVICE_VERSION, MSG_UUID);

        request.setPrcsParms(buildPrcsParams("", map));
        request.getSvc().add(buildSVC("", map));

        closeTag("", "REQUEST");
        return request;
    }

    private static CBHoldListInqMtvnSvcReq.PrcsParms buildPrcsParams(String space, Map<FissParam, String> map) {
        CBHoldListInqMtvnSvcReq.PrcsParms params = new CBHoldListInqMtvnSvcReq.PrcsParms();
        params.setSrcID(map.get(SOURCE_ID));
        params.setTestInd(map.get(TEST_INDICATOR));

        print("PRCS_POARAMS", map, space, SOURCE_ID, TEST_INDICATOR);
        return params;
    }

    private static CBHoldListInqMtvnSvcReq.Svc buildSVC(String space, Map<FissParam, String> map) {
        space = "   " + space;
        CBHoldListInqMtvnSvcReq.Svc svcItem = new CBHoldListInqMtvnSvcReq.Svc();
        startTag(space, "SVC");
        svcItem.setSvcParms(buildSvcParams(space, map));
        svcItem.setSecurity(buildSecurity(space, map));
        svcItem.setMsgData(buildMsgData(space, map));
        closeTag(space, "SVC");
        return svcItem;
    }

    private static CBHoldListInqMtvnSvcReq.Svc.SvcParms buildSvcParams(String space, Map<FissParam, String> map) {
        CBHoldListInqMtvnSvcReq.Svc.SvcParms params = new CBHoldListInqMtvnSvcReq.Svc.SvcParms();
        params.setApplID(map.get(APPLICATION_ID));
        params.setSvcID(map.get(SERVICE_ID));
        params.setSvcVer(map.get(SERVICE_VERSION));
        params.setRqstUUID(map.get(REQUEST_ID));
        params.setRoutingID(map.get(ROUTING_ID));

        print("SVC_POARAMS", map, space, APPLICATION_ID, SERVICE_ID, SERVICE_VERSION, REQUEST_ID, ROUTING_ID);

        return params;
    }

    private static CBHoldListInqMtvnSvcReq.Svc.Security buildSecurity(String space, Map<FissParam, String> map) {
        CBHoldListInqMtvnSvcReq.Svc.Security security = new CBHoldListInqMtvnSvcReq.Svc.Security();

        CBHoldListInqMtvnSvcReq.Svc.Security.BasicAuth basicAuth = new CBHoldListInqMtvnSvcReq.Svc.Security.BasicAuth();
        basicAuth.setUsrID(map.get(USER));
        basicAuth.setPwd(map.get(PASSWORD));

        security.setBasicAuth(basicAuth);

        print("SECURITY/BASIC_AUTH", map, space, USER, PASSWORD);

        return security;
    }

    private static CBHoldListInqMtvnSvcReq.Svc.MsgData buildMsgData(String space, Map<FissParam, String> map) {
        CBHoldListInqMtvnSvcReq.Svc.MsgData data = new CBHoldListInqMtvnSvcReq.Svc.MsgData();

        CBHoldListInqReqData reqData = new CBHoldListInqReqData();
        reqData.setE130013(map.get(CARD_NUMBER));
        reqData.setE130679(map.get(HOLD_STATUS_FILTER));

        data.setCBHoldListInqReqData(reqData);

        print("MSG_DATA/REQUEST_DATA", map, space, CARD_NUMBER, HOLD_STATUS_FILTER);

        return data;
    }

}
