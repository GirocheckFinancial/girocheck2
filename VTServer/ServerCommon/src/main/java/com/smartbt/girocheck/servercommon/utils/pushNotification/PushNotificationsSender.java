/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.girocheck.servercommon.utils.pushNotification;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;
//import org.apache.cxf.jaxrs.client.WebClient;

/**
 *
 * @author rrodriguez
 */
public class PushNotificationsSender {

    private static final HttpClient httpClient = HttpClientBuilder.create().build();
    private static final ObjectMapper mapper = new ObjectMapper();
//    private static final String PUSH_NOTIFICATIONS_API_KEY = "AAAAWqRTdVU:APA91bHKDtwRE6SyamGf1qIkR_KOhMUDlwdCoRf-RCr2bbwHGR8x-TZ3N2fF0XYoerHUlOH4OSqAQQgatk2g9QcqKyBgDtKzesdyPaNv0F-HB26PAb8oPgIZl1le8vpnVsJFcqhgRcWZ";
    //Girocheck Account
    private static final String PUSH_NOTIFICATIONS_API_KEY = "AAAAS9mowbw:APA91bGDixHTqkBszOOD44eliAzhEsv16LeRJ2pwxgD2cI8cKag1DVn_1iDhU4n7zBSox9ZjSb732zeQEMTwp4iDCjwJBzsbhQoyiDMxyKo88bzc9vGIdL5WhWaXMJh5MAzdz53PADa4";
 
    public static void main(String[] args) {
        String pushToken = "f3uHwT6SMxs:APA91bHexGxp-0S41tJ6UWKJRMk6mY5VtddcMdusu_be3GPDkP910tARJX2BZH9gi9MwNfFGPKBTz6uut6PNiCvkvb2Qq-I9yY7loaljxkik_nl_VRp_EF2tdjjFOOXu63V2pT4QlYW-";
        sendMessage("ios", pushToken, "Title", "Firebase android misses important feature of android notification like group, priority and etc. As a work around you can send data message (no notification payload at all) and this repo will build a local notification for you. If you pass custom_notification in the payload, the repo will treat the content as a local notification config and shows immediately.");
    }

    public static boolean sendMessage(String os, String pushToken, String title, String msg) {
        System.out.println("sending Push Notification Message...");
        System.out.println("os = " + os);
        System.out.println("title = " + title);
        System.out.println("msg = " + msg);
        System.out.println("pushToken = " + pushToken );
        
        if(pushToken == null || pushToken.isEmpty()){
            return false;
        }
        
        try {
            HttpPost postRequest = new HttpPost("https://fcm.googleapis.com/fcm/send");
            postRequest.addHeader("Content-type", "application/json");
            postRequest.addHeader("Authorization", "key=" + PUSH_NOTIFICATIONS_API_KEY);
  
            StringEntity input = new StringEntity(mapper.writeValueAsString( new PushNotification(os, pushToken, title, msg) ));
            input.setContentType("application/json");

            postRequest.setEntity(input);

            ResponseHandler<String> responseHandler=new BasicResponseHandler();
            String responseBody = httpClient.execute(postRequest, responseHandler);
            
            System.out.println("Message Sent:"); 
            System.out.println(responseBody); 
            JSONObject response=new JSONObject(responseBody);
            
            Integer result = (Integer)response.get("success");
            
            return result != null && result == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
