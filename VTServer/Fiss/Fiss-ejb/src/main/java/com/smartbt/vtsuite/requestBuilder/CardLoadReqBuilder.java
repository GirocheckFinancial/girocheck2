package com.smartbt.vtsuite.requestBuilder;
 
import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.vtsuite.util.CardLoadType;
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
import static com.smartbt.vtsuite.util.FissParam.TEST_INDICATOR;
import static com.smartbt.vtsuite.util.FissParam.USER; 
import com.smartbt.vtsuite.util.FissPrintUtil;
import static com.smartbt.vtsuite.util.FissPrintUtil.buildXML;
import static com.smartbt.vtsuite.util.FissPrintUtil.endTag;
import static com.smartbt.vtsuite.util.FissPrintUtil.startTag;
import com.smartbt.vtsuite.ws.cardLoad.CBPrpdLdUnldMtvnSvcReq;
import com.smartbt.vtsuite.ws.cardLoad.CBPrpdLdUnldReqData;
import java.util.Map;

/**
 *
 * @author rrodriguez
 */
public class CardLoadReqBuilder {

    public static CBPrpdLdUnldMtvnSvcReq build(Map<FissParam, String> map) {
 

        CBPrpdLdUnldMtvnSvcReq request = new CBPrpdLdUnldMtvnSvcReq();

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

    private static CBPrpdLdUnldMtvnSvcReq.PrcsParms buildPrcsParams(String space, Map<FissParam, String> map, StringBuilder sb) {
        CBPrpdLdUnldMtvnSvcReq.PrcsParms params = new CBPrpdLdUnldMtvnSvcReq.PrcsParms();
        params.setSrcID(map.get(SOURCE_ID));
        params.setTestInd(map.get(TEST_INDICATOR));

        sb.append(buildXML("PRCS_POARAMS", map, space, SOURCE_ID, TEST_INDICATOR));
        return params;
    }

    private static CBPrpdLdUnldMtvnSvcReq.Svc buildSVC(String space, Map<FissParam, String> map, StringBuilder sb) {
        space = "   " + space;
        CBPrpdLdUnldMtvnSvcReq.Svc svcItem = new CBPrpdLdUnldMtvnSvcReq.Svc();
        sb.append(startTag(space, "SVC"));
        svcItem.setSvcParms(buildSvcParams(space, map, sb));
        svcItem.setSecurity(buildSecurity(space, map, sb));
        svcItem.setMsgData(buildMsgData(space, map, sb));
        sb.append(endTag(space, "SVC"));
        return svcItem;
    }

    private static CBPrpdLdUnldMtvnSvcReq.Svc.SvcParms buildSvcParams(String space, Map<FissParam, String> map, StringBuilder sb) {
        CBPrpdLdUnldMtvnSvcReq.Svc.SvcParms params = new CBPrpdLdUnldMtvnSvcReq.Svc.SvcParms();
        params.setApplID(map.get(APPLICATION_ID));
        params.setSvcID(map.get(SERVICE_ID));
        params.setSvcVer(map.get(SERVICE_VERSION));
        params.setRqstUUID(map.get(REQUEST_ID));
        params.setRoutingID(map.get(ROUTING_ID));

        sb.append(buildXML("SVC_POARAMS", map, space, APPLICATION_ID, SERVICE_ID, SERVICE_VERSION, REQUEST_ID, ROUTING_ID));

        return params;
    }

    private static CBPrpdLdUnldMtvnSvcReq.Svc.Security buildSecurity(String space, Map<FissParam, String> map, StringBuilder sb) {
        CBPrpdLdUnldMtvnSvcReq.Svc.Security security = new CBPrpdLdUnldMtvnSvcReq.Svc.Security();

        CBPrpdLdUnldMtvnSvcReq.Svc.Security.BasicAuth basicAuth = new CBPrpdLdUnldMtvnSvcReq.Svc.Security.BasicAuth();
        basicAuth.setUsrID(map.get(USER));
        basicAuth.setPwd(map.get(PASSWORD));

        security.setBasicAuth(basicAuth);

        sb.append(buildXML("SECURITY/BASIC_AUTH", map, space, USER, PASSWORD));

        return security;
    }

    private static CBPrpdLdUnldMtvnSvcReq.Svc.MsgData buildMsgData(String space, Map<FissParam, String> map, StringBuilder sb) {
        CBPrpdLdUnldMtvnSvcReq.Svc.MsgData data = new CBPrpdLdUnldMtvnSvcReq.Svc.MsgData();

        CBPrpdLdUnldReqData reqData = new CBPrpdLdUnldReqData();
        reqData.setE130013(map.get(CARD_NUMBER));

        CardLoadType cardLoadType = CardLoadType.valueOf(map.get(FissParam.CARD_LOAD_TYPE));

        String detailedTransactionDescriptionId = "";
        FissParam amountFieldToPrint = null;
        switch (cardLoadType) {
            case CHECK:
                amountFieldToPrint = FissParam.AMOUNT;
                reqData.setE130484(map.get(FissParam.AMOUNT));
                detailedTransactionDescriptionId = "G1";
                break;
            case CHECK_FEE:
                amountFieldToPrint = FissParam.FEE_AMOUNT;
                reqData.setE130485(map.get(FissParam.FEE_AMOUNT));
                detailedTransactionDescriptionId = "G4";
                break;
            case CASH_FEE:
                amountFieldToPrint = FissParam.FEE_AMOUNT;
                reqData.setE130485(map.get(FissParam.FEE_AMOUNT));
                detailedTransactionDescriptionId = "G3";
                break;
        }
        map.put(DETAILED_TRANSACTION_DESCRIPTION, detailedTransactionDescriptionId);
        reqData.setE131150(detailedTransactionDescriptionId);

        data.setCBPrpdLdUnldReqData(reqData);

        sb.append(buildXML("MSG_DATA/REQUEST_DATA", map, space, CARD_NUMBER, amountFieldToPrint, DETAILED_TRANSACTION_DESCRIPTION));

        return data;
    }

}
