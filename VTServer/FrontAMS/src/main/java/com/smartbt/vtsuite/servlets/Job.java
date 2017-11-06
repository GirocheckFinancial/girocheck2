/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.servlets;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
//import org.aioobe.cloudconvert.CloudConvertService;
//import org.aioobe.cloudconvert.ConvertProcess;
//import org.aioobe.cloudconvert.ProcessStatus;
import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.apache.http.*;
import org.apache.http.auth.*;
import org.apache.http.client.*;
import org.apache.http.client.methods.*;
import org.apache.http.entity.*;
import org.apache.http.entity.mime.*;
import org.apache.http.entity.mime.content.*;
import org.apache.http.impl.client.*;
import org.apache.http.util.*;

/**
 *
 * @author rrodriguez
 */
public class Job {
//
//    public static void main2(String[] args) throws Exception {
//        int fileId = 19211186;
//        String apiKey = "7f107f31561ec381c4012f4650008d8aeb7b960f";
//        String endpoint = "https://sandbox.zamzar.com/v1/files/" + fileId + "/content";
//        String localFilename = "C:\\Glassfish\\ids\\converted.png";
//
//        // Create HTTP client and request object
//        CloseableHttpClient httpClient = getHttpClient(apiKey);
//        HttpGet request = new HttpGet(endpoint);
//
//        // Make request
//        CloseableHttpResponse response = httpClient.execute(request);
//
//        // Extract body from response
//        HttpEntity responseContent = response.getEntity();
//
//        // Save response content to file on local disk
//        BufferedInputStream bis = new BufferedInputStream(responseContent.getContent());
//        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(localFilename));
//        int inByte;
//        while ((inByte = bis.read()) != -1) {
//            bos.write(inByte);
//        }
//
//        // Print success message
//        System.out.println("File downloaded");
//
//        // Finalise response, client and streams
//        response.close();
//        httpClient.close();
//        bos.close();
//        bis.close();
//    }
//
//    public static void main(String[] args) throws Exception {
//        String apiKey = "oRkArXKrHfasWtL2VusjRWcqtyvYHd_F3AJvD44BROOajAo_iLWbhzu94wU2pV6qzV09jDQVhJvfH1iYsoid7g";
//        String endpoint = "https://sandbox.zamzar.com/v1/jobs";
//        String sourceFile = "C:\\Glassfish\\ids\\id2.tif";
//        CloudConvertService service = new CloudConvertService(apiKey);
//
//// Create conversion process
//        ConvertProcess process = service.startProcess("tif", "png");
//
//// Perform conversion
//        process.startConversion(new File("C:\\Glassfish\\ids\\idfront_4058.tif"));
//
//// Wait for result
//        ProcessStatus status;
//        waitLoop:
//        while (true) {
//            status = process.getStatus();
//
//            switch (status.step) {
//                case FINISHED:
//                    break waitLoop;
//                case ERROR:
//                    throw new RuntimeException(status.message);
//            }
//
//            // Be gentle
//            Thread.sleep(200);
//        }
//        
//// Download result
//        System.out.println("status.output.url = " + status.output.url );
//        service.download(status.output.url, new File("C:\\Glassfish\\ids\\rober2.png"));
//
//// Clean up
//        process.delete();
//    }
//
//    public static void main(String[] args) throws Exception {
//        String apiKey = "7f107f31561ec381c4012f4650008d8aeb7b960f";
//        String endpoint = "https://sandbox.zamzar.com/v1/jobs";
//        String sourceFile = "C:\\Glassfish\\ids\\id2.tif";
//        String targetFormat = "png";
//
//        // Create HTTP client and request object
//        CloseableHttpClient httpClient = getHttpClient(apiKey);
//        HttpEntity requestContent = MultipartEntityBuilder.create()
//            .addPart("source_file", new FileBody(new File(sourceFile)))
//            .addPart("target_format", new StringBody(targetFormat, ContentType.TEXT_PLAIN))
//            .build();
//        HttpPost request = new HttpPost(endpoint);
//        request.setEntity(requestContent);
//
//        // Make request
//        CloseableHttpResponse response = httpClient.execute(request);
//
//        // Extract body from response
//        HttpEntity responseContent = response.getEntity();
//        String result = EntityUtils.toString(responseContent, "UTF-8");
//
//        // Parse result as JSON
//        JSONObject json = new JSONObject(result);
//
//        // Print result
//        System.out.println(json);
//
//        // Finalise response and client
//        response.close();
//        httpClient.close();
//    }

    // Creates a HTTP client object that always makes requests
    // that are signed with the specified API key via Basic Auth
    private static CloseableHttpClient getHttpClient(String apiKey) {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(apiKey, ""));

        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultCredentialsProvider(credentialsProvider)
                .build();

        return httpClient;
    }
}
