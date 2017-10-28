package com.smartbt.entity;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author rrodriguez
 */
public class NewBranchRequest {

    private String branchpayload;
    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    public NewBranchRequest() {
    }

    public NewBranchRequest(Map map) {
        String payload = createPayload(map);
        System.out.println("NewBranchRequest:: payload => " + payload);
        setBranchpayload(payload);
    }

    public String createPayload(Map map) {
        StringBuilder sb = new StringBuilder("{");

        sb.append("\"ID_COMPANY\" : \"1\",");  //TODO Which ID company should Girocheck use?
        sb.append("\"ID_BRANCH\" : \"" + map.get(ParameterName.MERCHANT_ID) + "\",");
        sb.append("\"NAME_BRANCH\" : \"" + map.get(ParameterName.MERCHANT_NAME) + "\",");
        sb.append("\"ADDRESS_BRANCH\" : \"" + map.get(ParameterName.ADDRESS) + "\",");
        sb.append("\"ID_CITY\" : \"" + map.get(ParameterName.ZIPCODE) + "\",");
        sb.append("\"ID_STATE\" : \"" + map.get(ParameterName.STATE_ABBREVIATION) + "\",");
        sb.append("\"ID_COUNTRY\" : \"USA\",");
        sb.append("\"ID_MAIN_BRANCH\" : \"GCK01\",");
        sb.append("\"ID_FLAG_BRANCH\" : \"A\",");
        sb.append("\"ID_TYPE_BRANCH\" : \"A\",");
        sb.append("\"PHONE1_BRANCH\" : \"" + map.get(ParameterName.PHONE) + "\",");
        sb.append("\"PHONE2_BRANCH\" : \"\",");
        sb.append("\"FAX_BRANCH\" : \"\",");
        sb.append("\"MODEM_BRANCH\" : \"\",");
        sb.append("\"DATE_CRE_BRANCH\" : \"" + df.format(new Date()) + "\",");
        sb.append("\"ZIP_BRANCH\" : \"" + map.get(ParameterName.ZIPCODE) + "\",");
        sb.append("\"CURRENCY_PAY_BRANCH\" : \"3\",");
        sb.append("\"SUB_GRUPO_PAGADOR\" : \"\",");
        sb.append("\"consecutivo_branch\" : \"" + map.get(ParameterName.MERCHANT_ID) + "\",");
        sb.append("\"TITULO_RECIBO\" : \"\",");
        sb.append("\"encabezado\" : \"\"");

        sb.append("}");
        return sb.toString();
    }

    /**
     * @return the branchpayload
     */
    public String getBranchpayload() {
        return branchpayload;
    }

    /**
     * @param branchpayload the branchpayload to set
     */
    public void setBranchpayload(String branchpayload) {
        this.branchpayload = branchpayload;
    }

}
