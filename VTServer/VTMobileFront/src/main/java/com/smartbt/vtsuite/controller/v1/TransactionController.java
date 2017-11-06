/*
 ** File: TransactionController.java
 **
 ** Date Created: October 2014
 **
 ** Copyright @ 2004-2014 Smart Business Technology, Inc.
 **
 ** All rights reserved. No part of this software may be 
 ** reproduced, transmitted, transcribed, stored in a retrieval 
 ** system, or translated into any language or computer language, 
 ** in any form or by any means, electronic, mechanical, magnetic, 
 ** optical, chemical, manual or otherwise, without the prior 
 ** written permission of Smart Business Technology, Inc.
 **
 */
package com.smartbt.vtsuite.controller.v1;

import static com.smartbt.vtsuite.controller.v1.GeneralController.TOKEN;
import com.smartbt.vtsuite.manager.TransactionManager;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Roberto Rodriguez
 */
@RestController
@RequestMapping("/v1/tx")
public class TransactionController {

    @Autowired
    TransactionManager transactionManager;

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public Map listTansactions(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "start", defaultValue = "0") Integer start,
            @RequestParam(value = "limit", defaultValue = "0") Integer limit,
            @RequestParam(value = "startDate", defaultValue = "") String startDate,
            @RequestParam(value = "endDate", defaultValue = "") String endDate,
            @RequestParam(value = "clientId") Integer clientId,
            HttpSession session) throws Exception {

        System.out.println("page = " + page + " \n start = " + start + "\n limit = " + limit + "\n startDate = " + startDate + "\n endDate = " + endDate + " \n clientId = " + clientId);
        String token = (String) session.getAttribute(TOKEN);
        return transactionManager.transactionHistory(clientId, page, start, limit, startDate, endDate, token);
    }

    public static void main(String[] args) throws Exception{
        System.out.println( ideology() );
    }
    
   // @RequestMapping(value = "/ideology", method = RequestMethod.GET)
    public static String ideology() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Host", "web.ideologylive.com");

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("username", "GiroCheck.API.GPR");
        map.add("password", "G!roCk@CbKc2~7Qr");
        map.add("firstName", "Roberto");
        map.add("lastName", "Greenberg");
        map.add("address", "81 Flat Rock Hill");
        map.add("city", "San Francisco");
        map.add("state", "CA");
        map.add("zip", "91119");
        map.add("dobMonth", "3");
        map.add("dobDay", "1");
        map.add("dobYear", "1954");
        map.add("ssn", "499091234");
  
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity("https://web.idologylive.com/api/idiq.svc", request, String.class);

        return response.getBody(); 
    }
}
