package com.smartbt.vtsuite.requestBuilder;

import com.smartbt.vtsuite.util.FissParam;
import static com.smartbt.vtsuite.util.FissParam.APPLICATION_ID;
import static com.smartbt.vtsuite.util.FissParam.CARD_NUMBER;
import static com.smartbt.vtsuite.util.FissParam.FISS_SERVICE_VERSION;
import static com.smartbt.vtsuite.util.FissParam.MSG_UUID;
import static com.smartbt.vtsuite.util.FissParam.NEW_PASSWORD;
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
import com.smartbt.vtsuite.ws.changePassword.SZChgPwdMtvnSvcReq;
import com.smartbt.vtsuite.ws.changePassword.SZChgPwdReqData;
import java.util.Map;

/**
 *
 * @author rrodriguez
 */
public class ChangePasswordReqBuilder {

    public static SZChgPwdMtvnSvcReq build(Map<FissParam, String> map) {

        if (LOG_REQUEST) {
            System.out.println("Printing ChangePasswordRequest...");
        }

        SZChgPwdMtvnSvcReq request = new SZChgPwdMtvnSvcReq();

        startTag("", "REQUEST");
        request.setMtvnSvcVer(map.get(FISS_SERVICE_VERSION));
        request.setMsgUUID(map.get(MSG_UUID));

        print(null, map, "", FISS_SERVICE_VERSION, MSG_UUID);

        request.setPrcsParms(buildPrcsParams("", map));
        request.getSvc().add(buildSVC("", map));

        closeTag("", "REQUEST");
        return request;
    }

    private static SZChgPwdMtvnSvcReq.PrcsParms buildPrcsParams(String space, Map<FissParam, String> map) {
        SZChgPwdMtvnSvcReq.PrcsParms params = new SZChgPwdMtvnSvcReq.PrcsParms();
        params.setSrcID(map.get(SOURCE_ID));
        params.setTestInd(map.get(TEST_INDICATOR));

        print("PRCS_POARAMS", map, space, SOURCE_ID, TEST_INDICATOR);
        return params;
    }

    private static SZChgPwdMtvnSvcReq.Svc buildSVC(String space, Map<FissParam, String> map) {
        space = "   " + space;
        SZChgPwdMtvnSvcReq.Svc svcItem = new SZChgPwdMtvnSvcReq.Svc();
        startTag(space, "SVC");
        svcItem.setSvcParms(buildSvcParams(space, map));
        svcItem.setSecurity(buildSecurity(space, map));
        svcItem.setMsgData(buildMsgData(space, map));
        closeTag(space, "SVC");
        return svcItem;
    }

    private static SZChgPwdMtvnSvcReq.Svc.SvcParms buildSvcParams(String space, Map<FissParam, String> map) {
        SZChgPwdMtvnSvcReq.Svc.SvcParms params = new SZChgPwdMtvnSvcReq.Svc.SvcParms();
        params.setApplID(map.get(APPLICATION_ID));
        params.setSvcID(map.get(SERVICE_ID));
        params.setSvcVer(map.get(SERVICE_VERSION));
        params.setRqstUUID(map.get(REQUEST_ID));
        params.setRoutingID(map.get(ROUTING_ID));

        print("SVC_POARAMS", map, space, APPLICATION_ID, SERVICE_ID, SERVICE_VERSION, REQUEST_ID, ROUTING_ID);

        return params;
    }

    private static SZChgPwdMtvnSvcReq.Svc.Security buildSecurity(String space, Map<FissParam, String> map) {
        SZChgPwdMtvnSvcReq.Svc.Security security = new SZChgPwdMtvnSvcReq.Svc.Security();

        SZChgPwdMtvnSvcReq.Svc.Security.BasicAuth basicAuth = new SZChgPwdMtvnSvcReq.Svc.Security.BasicAuth();
        basicAuth.setUsrID(map.get(USER));
        basicAuth.setPwd(map.get(PASSWORD));

        security.setBasicAuth(basicAuth);

        print("SECURITY/BASIC_AUTH", map, space, USER, PASSWORD);

        return security;
    }

    private static SZChgPwdMtvnSvcReq.Svc.MsgData buildMsgData(String space, Map<FissParam, String> map) {
        SZChgPwdMtvnSvcReq.Svc.MsgData data = new SZChgPwdMtvnSvcReq.Svc.MsgData();

        SZChgPwdReqData reqData = new SZChgPwdReqData();
        reqData.setE300205(map.get(NEW_PASSWORD));

        data.setSZChgPwdReqData(reqData);

        print("MSG_DATA/REQUEST_DATA", map, space, NEW_PASSWORD);

        return data;
    }

}
