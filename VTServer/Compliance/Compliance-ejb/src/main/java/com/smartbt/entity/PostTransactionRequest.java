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
public class PostTransactionRequest {

    private String transactionpayload;
    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    public PostTransactionRequest() {
    }

    public PostTransactionRequest(Map map) {
        String payload = createPayload(map);
        System.out.println("---- PostTransactionRequest:: payload => " + payload);
        setTransactionpayload(payload);
    }
     

    public String createPayload(Map map) {
        String zipCode = (String)map.get(ParameterName.ZIPCODE);
        if(zipCode != null && zipCode.length() > 5){
            zipCode = zipCode.substring(0, 5);
        } 
        
        StringBuilder sb = new StringBuilder("{");

        sb.append("\"ID_BRANCH\" : \"" + map.get(ParameterName.IDMERCHANT) + "\",");  //TODO Which ID company should Girocheck use?
        sb.append("\"NAME_SENDER\" : \"" + map.get(ParameterName.FIRST_NAME) + "\",");
        sb.append("\"ADDRES_SENDER\" : \"" + map.get(ParameterName.ADDRESS) + "\",");
        sb.append("\"PHONE1_SENDER\" : \"" + map.get(ParameterName.PHONE) + "\",");
        sb.append("\"PHONE2_SENDER\" : \"\",");
        sb.append("\"ZIP_SENDER\" : \"" + zipCode + "\",");
        sb.append("\"ID_CITY\" : \"" + zipCode + "\",");
        sb.append("\"ID_STATE\" : \"" + map.get(ParameterName.STATE_ABBREVIATION) + "\",");
        sb.append("\"ID_COUNTRY\" : \"USA\",");
        sb.append("\"ID_TYPE_ID_SENDER\" : \"2\",");
        sb.append("\"NUMBER_ID_SENDER\" : \"" + map.get(ParameterName.ID) + "\",");
        sb.append("\"birthday_sender\" : \"" + formatDate( (Date)map.get(ParameterName.BORNDATE_AS_DATE))  + "\",");
        sb.append("\"ssn_number\" : \"" + map.get(ParameterName.SSN) + "\",");
        sb.append("\"ocupation_sender\" : \"NA\",");
        sb.append("\"id_expiration_sender\" : \"" + formatDate( (Date)map.get(ParameterName.EXPIRATION_DATE_AS_DATE)) + "\",");
        
        String gender =  map.containsKey(ParameterName.GENDER) ?  (String)map.get(ParameterName.GENDER) : "M"; 
        sb.append("\"sexo_sender\" : \"" + gender + "\",");
       
        sb.append("\"ID_PAYMENT\" : \"1\",");
        sb.append("\"ID_FLAG_RECEIVER\" : \"1\",");
        sb.append("\"ID_CITY_RECEIVER\" : \"2\",");
        sb.append("\"ID_STATE_RECEIVER\" : \"2\",");
        sb.append("\"ID_COUNTRY_RECEIVER\" : \"2\",");
        sb.append("\"BRANCH_PAY_RECEIVER\" : \"1\",");
        sb.append("\"ID_CASHIER\" : \"1\",");
        sb.append("\"NAME_RECEIVER\" : \"\",");
        sb.append("\"ADDRESS_RECEIVER\" : \"\",");
        sb.append("\"PHONE1_RECEIVER\" : \"\",");
        sb.append("\"PHONE2_RECEIVER\" : \"\",");
        sb.append("\"ZIP_RECEIVER\" : \"\",");
        sb.append("\"NOTES_RECEIVER\" : \"\",");
        sb.append("\"DATE_RECEIVER\" : \"\",");
        sb.append("\"TIME_RECEIVER\" : \"\",");
        sb.append("\"NET_AMOUNT_RECEIVER\" : \"" + map.get(ParameterName.AMOUNT) + "\",");
        sb.append("\"RATE_CHANGE_RECEIVER\" : \"2961\",");
        sb.append("\"TELEX_RECEIVER\" : \"12\",");
        sb.append("\"URGENCY_RECEIVER\" : \"0\",");
        sb.append("\"RECOMEND_RECEIVER\" : \"1\",");
        sb.append("\"MODE_PAY_RECEIVER\" : \"1\",");
        sb.append("\"ACC_RECEIVER\" : \"\",");
        sb.append("\"EXCHANGE_RECEIVER\" : \"0\",");
        sb.append("\"HANDLING_RECEIVER\" : \"0\",");
        sb.append("\"TOTAL_RECEIVER\" : \"\",");
        sb.append("\"MOD_PAY_CURRENCY\" : \"1\",");
        sb.append("\"ref_receiver\" : \"\",");
        sb.append("\"ID_CURRENY\" : \"3\",");
        sb.append("\"bank_receiver\" : \"\""); 
        sb.append("}");
        return sb.toString();
    }

    public String formatDate(Date date){ 
        Date d = new Date();
        DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        
        if(date != null){
            return df.format(d);
        } 
        return "null";
    }
    
    /**
     * @return the transactionpayload
     */
    public String getTransactionpayload() {
        return transactionpayload;
    }

    /**
     * @param transactionpayload the transactionpayload to set
     */
    public void setTransactionpayload(String transactionpayload) {
        this.transactionpayload = transactionpayload;
    }
 
}
