/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.girocheck.servercommon.utils.pushNotification;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
//import org.apache.cxf.jaxrs.client.WebClient;

/**
 *
 * @author rrodriguez
 */
public class PushNotificationsSender {

    private static final HttpClient httpClient = HttpClientBuilder.create().build();
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final String PUSH_NOTIFICATIONS_API_KEY = "AAAAWqRTdVU:APA91bHKDtwRE6SyamGf1qIkR_KOhMUDlwdCoRf-RCr2bbwHGR8x-TZ3N2fF0XYoerHUlOH4OSqAQQgatk2g9QcqKyBgDtKzesdyPaNv0F-HB26PAb8oPgIZl1le8vpnVsJFcqhgRcWZ";
 
    public static void main(String[] args) {
        sendMessage("android", "dL9CaJEWY7M:APA91bEKEnC_5zz6f9q_xpg6MxmCHWpwshrzBLMpChTsSYimRzqjBEmy62tG2n1CEDsJD3pvU5OgcwnMRyb3E15ZZzxTSKQVVeDSZkGFTcMm2lor7ITVf1lOkpjgjEDPAX_-sVGolg7D", "Title", "Firebase android misses important feature of android notification like group, priority and etc. As a work around you can send data message (no notification payload at all) and this repo will build a local notification for you. If you pass custom_notification in the payload, the repo will treat the content as a local notification config and shows immediately.");
    }

    public static void sendMessage(String os, String pushToken, String title, String msg) {

        try {
            HttpPost postRequest = new HttpPost("https://fcm.googleapis.com/fcm/send");
            postRequest.addHeader("Content-type", "application/json");
            postRequest.addHeader("Authorization", "key=" + PUSH_NOTIFICATIONS_API_KEY);
  
            StringEntity input = new StringEntity(mapper.writeValueAsString( new PushNotification(os, pushToken, title, msg) ));
            input.setContentType("application/json");

            postRequest.setEntity(input);

            httpClient.execute(postRequest);
             
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
