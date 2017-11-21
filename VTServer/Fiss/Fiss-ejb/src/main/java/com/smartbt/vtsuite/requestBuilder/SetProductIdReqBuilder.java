package com.smartbt.vtsuite.requestBuilder;

import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.vtsuite.util.FissParam;
import static com.smartbt.vtsuite.util.FissParam.APPLICATION_ID;
import static com.smartbt.vtsuite.util.FissParam.CARD_NUMBER;
import static com.smartbt.vtsuite.util.FissParam.FISS_SERVICE_VERSION;
import static com.smartbt.vtsuite.util.FissParam.MSG_UUID;
import static com.smartbt.vtsuite.util.FissParam.PASSWORD;
import static com.smartbt.vtsuite.util.FissParam.PRODUCT_ID;
import static com.smartbt.vtsuite.util.FissParam.REQUEST_ID;
import static com.smartbt.vtsuite.util.FissParam.ROUTING_ID;
import static com.smartbt.vtsuite.util.FissParam.SERVICE_ID;
import static com.smartbt.vtsuite.util.FissParam.SERVICE_VERSION;
import static com.smartbt.vtsuite.util.FissParam.SOURCE_ID;
import static com.smartbt.vtsuite.util.FissParam.TEST_INDICATOR;
import static com.smartbt.vtsuite.util.FissParam.USER; 
import com.smartbt.vtsuite.util.FissPrintUtil;
import static com.smartbt.vtsuite.util.FissPrintUtil.buildXML;
import static com.smartbt.vtsuite.util.FissPrintUtil.buildXML;
import static com.smartbt.vtsuite.util.FissPrintUtil.endTag;
import static com.smartbt.vtsuite.util.FissPrintUtil.startTag;
import com.smartbt.vtsuite.ws.setProductId.CBProdIDMaintMtvnSvcReq;
import com.smartbt.vtsuite.ws.setProductId.CBProdIDMaintReqData;
import java.util.Map;

/**
 *
 * @author rrodriguez
 */
public class SetProductIdReqBuilder {

    public static CBProdIDMaintMtvnSvcReq build(Map<FissParam, String> map) {
 
        CBProdIDMaintMtvnSvcReq request = new CBProdIDMaintMtvnSvcReq();
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

    private static CBProdIDMaintMtvnSvcReq.PrcsParms buildPrcsParams(String space, Map<FissParam, String> map, StringBuilder sb) {
        CBProdIDMaintMtvnSvcReq.PrcsParms params = new CBProdIDMaintMtvnSvcReq.PrcsParms();
        params.setSrcID(map.get(SOURCE_ID));
        params.setTestInd(map.get(TEST_INDICATOR));

        sb.append(buildXML("PRCS_PARAMS", map, space, SOURCE_ID, TEST_INDICATOR));
        return params;
    }

    private static CBProdIDMaintMtvnSvcReq.Svc buildSVC(String space, Map<FissParam, String> map, StringBuilder sb) {
        space = "   " + space;
        CBProdIDMaintMtvnSvcReq.Svc svcItem = new CBProdIDMaintMtvnSvcReq.Svc();
        sb.append(startTag(space, "SVC"));
        svcItem.setSvcParms(buildSvcParams(space, map, sb));
        svcItem.setSecurity(buildSecurity(space, map, sb));
        svcItem.setMsgData(buildMsgData(space, map, sb));
        sb.append(endTag(space, "SVC"));
        return svcItem;
    }

    private static CBProdIDMaintMtvnSvcReq.Svc.SvcParms buildSvcParams(String space, Map<FissParam, String> map, StringBuilder sb) {
        CBProdIDMaintMtvnSvcReq.Svc.SvcParms params = new CBProdIDMaintMtvnSvcReq.Svc.SvcParms();
        params.setApplID(map.get(APPLICATION_ID));
        params.setSvcID(map.get(SERVICE_ID));
        params.setSvcVer(map.get(SERVICE_VERSION));
        params.setRqstUUID(map.get(REQUEST_ID));
        params.setRoutingID(map.get(ROUTING_ID));

        sb.append(buildXML("SVC_PARAMS", map, space, APPLICATION_ID, SERVICE_ID, SERVICE_VERSION, REQUEST_ID, ROUTING_ID));

        return params;
    }

    private static CBProdIDMaintMtvnSvcReq.Svc.Security buildSecurity(String space, Map<FissParam, String> map, StringBuilder sb) {
        CBProdIDMaintMtvnSvcReq.Svc.Security security = new CBProdIDMaintMtvnSvcReq.Svc.Security();

        CBProdIDMaintMtvnSvcReq.Svc.Security.BasicAuth basicAuth = new CBProdIDMaintMtvnSvcReq.Svc.Security.BasicAuth();
        basicAuth.setUsrID(map.get(USER));
        basicAuth.setPwd(map.get(PASSWORD));

        security.setBasicAuth(basicAuth);

        sb.append(buildXML("SECURITY/BASIC_AUTH", map, space, USER, PASSWORD));

        return security;
    }

    private static CBProdIDMaintMtvnSvcReq.Svc.MsgData buildMsgData(String space, Map<FissParam, String> map, StringBuilder sb) {
        CBProdIDMaintMtvnSvcReq.Svc.MsgData data = new CBProdIDMaintMtvnSvcReq.Svc.MsgData();

        CBProdIDMaintReqData reqData = new CBProdIDMaintReqData();
        reqData.setE130013(map.get(CARD_NUMBER));
        reqData.setE130579(map.get(PRODUCT_ID));

        data.setCBProdIDMaintReqData(reqData);

        sb.append(buildXML("MSG_DATA/REQUEST_DATA", map, space, CARD_NUMBER, PRODUCT_ID));

        return data;
    }

}
