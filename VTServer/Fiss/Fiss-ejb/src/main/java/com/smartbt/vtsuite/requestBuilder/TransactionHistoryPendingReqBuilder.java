package com.smartbt.vtsuite.requestBuilder;

import com.smartbt.vtsuite.util.FissParam;
import static com.smartbt.vtsuite.util.FissParam.APPLICATION_ID;
import static com.smartbt.vtsuite.util.FissParam.CARD_NUMBER;
import static com.smartbt.vtsuite.util.FissParam.FISS_SERVICE_VERSION;
import static com.smartbt.vtsuite.util.FissParam.MSG_UUID;
import static com.smartbt.vtsuite.util.FissParam.PASSWORD;
import static com.smartbt.vtsuite.util.FissParam.PENDING_TRANSACTION_FILTER;
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
import com.smartbt.vtsuite.ws.history.pending.CBPndTxnInqMtvnSvcReq;
import com.smartbt.vtsuite.ws.history.pending.CBPndTxnInqReqData;
import java.util.Map;

/**
 *
 * @author rrodriguez
 */
public class TransactionHistoryPendingReqBuilder {

    public static CBPndTxnInqMtvnSvcReq build(Map<FissParam, String> map) {

        if (LOG_REQUEST) {
            System.out.println("Printing TransactionHistoryPendingRequest...");
        }

        CBPndTxnInqMtvnSvcReq request = new CBPndTxnInqMtvnSvcReq();

        startTag("", "REQUEST");
        request.setMtvnSvcVer(map.get(FISS_SERVICE_VERSION));
        request.setMsgUUID(map.get(MSG_UUID));

        print(null, map, "", FISS_SERVICE_VERSION, MSG_UUID);

        request.setPrcsParms(buildPrcsParams("", map));
        request.getSvc().add(buildSVC("", map));

        closeTag("", "REQUEST");
        return request;
    }

    private static CBPndTxnInqMtvnSvcReq.PrcsParms buildPrcsParams(String space, Map<FissParam, String> map) {
        CBPndTxnInqMtvnSvcReq.PrcsParms params = new CBPndTxnInqMtvnSvcReq.PrcsParms();
        params.setSrcID(map.get(SOURCE_ID));
        params.setTestInd(map.get(TEST_INDICATOR));

        print("PRCS_POARAMS", map, space, SOURCE_ID, TEST_INDICATOR);
        return params;
    }

    private static CBPndTxnInqMtvnSvcReq.Svc buildSVC(String space, Map<FissParam, String> map) {
        space = "   " + space;
        CBPndTxnInqMtvnSvcReq.Svc svcItem = new CBPndTxnInqMtvnSvcReq.Svc();
        startTag(space, "SVC");
        svcItem.setSvcParms(buildSvcParams(space, map));
        svcItem.setSecurity(buildSecurity(space, map));
        svcItem.setMsgData(buildMsgData(space, map));
        closeTag(space, "SVC");
        return svcItem;
    }

    private static CBPndTxnInqMtvnSvcReq.Svc.SvcParms buildSvcParams(String space, Map<FissParam, String> map) {
        CBPndTxnInqMtvnSvcReq.Svc.SvcParms params = new CBPndTxnInqMtvnSvcReq.Svc.SvcParms();
        params.setApplID(map.get(APPLICATION_ID));
        params.setSvcID(map.get(SERVICE_ID));
        params.setSvcVer(map.get(SERVICE_VERSION));
        params.setRqstUUID(map.get(REQUEST_ID));
        params.setRoutingID(map.get(ROUTING_ID));

        print("SVC_POARAMS", map, space, APPLICATION_ID, SERVICE_ID, SERVICE_VERSION, REQUEST_ID, ROUTING_ID);

        return params;
    }

    private static CBPndTxnInqMtvnSvcReq.Svc.Security buildSecurity(String space, Map<FissParam, String> map) {
        CBPndTxnInqMtvnSvcReq.Svc.Security security = new CBPndTxnInqMtvnSvcReq.Svc.Security();

        CBPndTxnInqMtvnSvcReq.Svc.Security.BasicAuth basicAuth = new CBPndTxnInqMtvnSvcReq.Svc.Security.BasicAuth();
        basicAuth.setUsrID(map.get(USER));
        basicAuth.setPwd(map.get(PASSWORD));

        security.setBasicAuth(basicAuth);

        print("SECURITY/BASIC_AUTH", map, space, USER, PASSWORD);

        return security;
    }

    private static CBPndTxnInqMtvnSvcReq.Svc.MsgData buildMsgData(String space, Map<FissParam, String> map) {
        CBPndTxnInqMtvnSvcReq.Svc.MsgData data = new CBPndTxnInqMtvnSvcReq.Svc.MsgData();

        CBPndTxnInqReqData reqData = new CBPndTxnInqReqData();
        reqData.setE130013(map.get(CARD_NUMBER));
        reqData.setE130551(map.get(PENDING_TRANSACTION_FILTER));

        data.setCBPndTxnInqReqData(null);

        print("MSG_DATA/REQUEST_DATA", map, space, CARD_NUMBER, PENDING_TRANSACTION_FILTER);

        return data;
    }

}
