package com.smartbt.vtsuite.requestBuilder;
 
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.vtsuite.util.FissParam;
import static com.smartbt.vtsuite.util.FissParam.APPLICATION_ID;
import static com.smartbt.vtsuite.util.FissParam.CARD_NUMBER;
import static com.smartbt.vtsuite.util.FissParam.DETAILED_TRANSACTION_DESCRIPTION;
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
import com.smartbt.vtsuite.ws.cardCashing.CBPrpdAdjMtvnSvcReq;
import com.smartbt.vtsuite.ws.cardCashing.CBPrpdAdjReqData;
import java.util.Map;

/**
 *
 * @author rrodriguez
 */
public class CardLoadCashReqBuilder {

    public static CBPrpdAdjMtvnSvcReq build(Map<FissParam, String> map) {
 

        CBPrpdAdjMtvnSvcReq request = new CBPrpdAdjMtvnSvcReq();

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

    private static CBPrpdAdjMtvnSvcReq.PrcsParms buildPrcsParams(String space, Map<FissParam, String> map, StringBuilder sb) {
        CBPrpdAdjMtvnSvcReq.PrcsParms params = new CBPrpdAdjMtvnSvcReq.PrcsParms();
        params.setSrcID(map.get(SOURCE_ID)); 

        sb.append(buildXML("PRCS_POARAMS", map, space, SOURCE_ID));
        return params;
    }

    private static CBPrpdAdjMtvnSvcReq.Svc buildSVC(String space, Map<FissParam, String> map, StringBuilder sb) {
        space = "   " + space;
        CBPrpdAdjMtvnSvcReq.Svc svcItem = new CBPrpdAdjMtvnSvcReq.Svc();
        sb.append(startTag(space, "SVC"));
        svcItem.setSvcParms(buildSvcParams(space, map, sb));
        svcItem.setSecurity(buildSecurity(space, map, sb));
        svcItem.setMsgData(buildMsgData(space, map, sb));
        sb.append(endTag(space, "SVC"));
        return svcItem;
    }

    private static CBPrpdAdjMtvnSvcReq.Svc.SvcParms buildSvcParams(String space, Map<FissParam, String> map, StringBuilder sb) {
        CBPrpdAdjMtvnSvcReq.Svc.SvcParms params = new CBPrpdAdjMtvnSvcReq.Svc.SvcParms();
        params.setApplID(map.get(APPLICATION_ID));
        params.setSvcID(map.get(SERVICE_ID));
        params.setSvcVer(map.get(SERVICE_VERSION));
        params.setRqstUUID(map.get(REQUEST_ID));
        params.setRoutingID(map.get(ROUTING_ID));

        sb.append(buildXML("SVC_POARAMS", map, space, APPLICATION_ID, SERVICE_ID, SERVICE_VERSION, REQUEST_ID, ROUTING_ID));

        return params;
    }

    private static CBPrpdAdjMtvnSvcReq.Svc.Security buildSecurity(String space, Map<FissParam, String> map, StringBuilder sb) {
        CBPrpdAdjMtvnSvcReq.Svc.Security security = new CBPrpdAdjMtvnSvcReq.Svc.Security();

        CBPrpdAdjMtvnSvcReq.Svc.Security.BasicAuth basicAuth = new CBPrpdAdjMtvnSvcReq.Svc.Security.BasicAuth();
        basicAuth.setUsrID(map.get(USER));
        basicAuth.setPwd(map.get(PASSWORD));

        security.setBasicAuth(basicAuth);

        sb.append(buildXML("SECURITY/BASIC_AUTH", map, space, USER, PASSWORD));

        return security;
    }

    private static CBPrpdAdjMtvnSvcReq.Svc.MsgData buildMsgData(String space, Map<FissParam, String> map, StringBuilder sb) {
        CBPrpdAdjMtvnSvcReq.Svc.MsgData data = new CBPrpdAdjMtvnSvcReq.Svc.MsgData();

        CBPrpdAdjReqData reqData = new CBPrpdAdjReqData(); 
        
        reqData.setE130013(map.get(CARD_NUMBER));  
        reqData.setE131150(map.get(DETAILED_TRANSACTION_DESCRIPTION));
        reqData.setE130486(map.get(FissParam.REASON_CODE));
        reqData.setE130487(map.get(FissParam.AMOUNT));
        reqData.setE202272(map.get(FissParam.MERCHANT_NAME));
        reqData.setE202273(map.get(FissParam.MERCHANT_CITY_STATE));

        data.setCBPrpdAdjReqData(reqData);

        sb.append(buildXML("MSG_DATA/REQUEST_DATA", map, space, FissParam.CARD_NUMBER,
                FissParam.DETAILED_TRANSACTION_DESCRIPTION, FissParam.REASON_CODE,
                FissParam.AMOUNT, FissParam.MERCHANT_NAME, FissParam.MERCHANT_CITY_STATE));

        return data;
    }

}
