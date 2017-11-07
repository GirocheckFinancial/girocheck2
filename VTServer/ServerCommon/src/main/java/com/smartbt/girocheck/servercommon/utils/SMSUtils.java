package com.smartbt.girocheck.servercommon.utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.util.EntityUtils;

/**
 * Go4Clients SMS utils
 *
 */
public class SMSUtils {

//    static String url = "http://go4clients.com:3011/TelintelSms/api/sms/sendmessage"; //http://go4clients.com:3011/TelintelSms/api/sms/sendmessage
//    static String API_KEY = "9d71d77a7e1c4a069a9fa51fa28a3244";
//    static String API_SECRET = "7600138823729";

    public static boolean sendSMS(String phone, String message) {
        System.out.println("...Sending SMS to phone " + phone);

        String url = System.getProperty("SMS_URL");
        String API_KEY = System.getProperty("SMS_API_KEY");
        String API_SECRET = System.getProperty("SMS_API_SECRET");
        
        
        System.out.println("url = " + url);
        System.out.println("API_KEY = " + API_KEY);
        System.out.println("API_SECRET = " + API_SECRET);

        HttpPost postRequest = new HttpPost(url);

        try {
            String request = "{\"toList\" :[\""
                    + phone
                    + "\"], \"message\":\""
                    + message
                    + "\"}";
            
            HttpEntity entity = new ByteArrayEntity(request.getBytes("UTF-8"));
            
            HttpClient httpClient = HttpClientBuilder.create().build();

            postRequest.addHeader("Content-type", "application/json");
            postRequest.addHeader("Apikey", API_KEY);
            postRequest.addHeader("Apisecret", API_SECRET);

          //  StringEntity input = new StringEntity(request);
         //   input.setContentType("application/json");
             
            
            postRequest.setEntity(entity); 
//            postRequest.setEntity(input); 

            HttpResponse response = httpClient.execute(postRequest);
            HttpEntity responseEntity = response.getEntity();

            if (responseEntity != null) {
                String resp = EntityUtils.toString(responseEntity);
                System.out.println("********* SMS SEND SUCCESSFULLY*******************");// mapper.readValue(response.getEntity().getContent(), Response.class);
                System.out.println(resp);
            }
            return true;

            // System.out.println(entityResponse.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (postRequest != null) {
                postRequest.releaseConnection();
            }
        }
    }

    public static void main(String s[]) {
        sendSMS("17864540209", "Thank you for using VoltCash--------");
    }
}
