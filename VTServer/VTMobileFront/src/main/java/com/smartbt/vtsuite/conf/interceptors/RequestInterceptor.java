/*
 ** File: RequestInterceptor.java
 **
 ** Date Created: November 2014
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
package com.smartbt.vtsuite.conf.interceptors;

import com.smartbt.girocheck.servercommon.dao.MobileClientDao;
import com.smartbt.girocheck.servercommon.utils.Utils;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import static com.smartbt.vtsuite.controller.v1.GeneralController.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author Roberto Rodriguez
 */
public class RequestInterceptor extends HandlerInterceptorAdapter {

    private static Map excludedURLs = new HashMap();

    static {
        excludedURLs.put("ping", "");
        excludedURLs.put("login", "");
        excludedURLs.put("register", "");
        excludedURLs.put("forgotPassword", "");
        excludedURLs.put("optOut", "");
        excludedURLs.put("updateMerchantCoordinates", "");
        
        //TODO remove this 
        excludedURLs.put("listMerchants", "");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("RequestInterceptor -> preHandle :: HibernateUtil.beginTransaction();");

        try {
            if (HibernateUtil.getSession().getTransaction() != null) {
                HibernateUtil.beginTransaction();
            } else {
                System.out.println("TRANSACTION STILL OPEN!!!");
            }
        } catch (Exception e) {
            System.out.println("It was going to throw a NestedTransactionException...   Thread.currentThread().sleep(1000);");
            e.printStackTrace();
            
           // HibernateUtil.rollbackTransaction();

            Thread.currentThread().sleep(1000);
           // HibernateUtil.beginTransaction();
        }

        String uri = request.getRequestURI();
        System.out.println("URL = " + uri);

        String lang = request.getHeader("LANG");
        System.out.println("LANG = " + lang);

        if (lang == null || 
                lang.startsWith("es") || 
                lang.equalsIgnoreCase("defaultLocale")) {
            lang = "es";
        } else {
            lang = "en";
        }
        request.getSession().setAttribute(LANG, lang);
 
        String token = "";
  
        boolean isValid = true;
        
        if (isExcludedURL(uri)) { 
            token = Utils.generateToken();
            System.out.println("Excluded URL, generated token = " + token);  
        }else{
            token = request.getHeader("TOKEN");
            System.out.println("Regular URL, Token in header = " + token); 
            
            isValid = token != null && MobileClientDao.get().validateToken(token);
            
            System.out.println("is Token Valid = " + isValid);
        }
        
        request.getSession().setAttribute(TOKEN, token);
        
        if(!isValid){
            terminateTransaction();
        }
 
        return isValid;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {

        response.addHeader("Access-Control-Allow-Origin", "*");
        terminateTransaction();
    }
    
    private void terminateTransaction(){
          try {
            System.out.println("RequestInterceptor -> postHandle :: terminateTransaction");
            HibernateUtil.commitTransaction();
        } catch (Exception ex) {
            ex.printStackTrace();
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.getSession().close();
        } 
    }

    private static boolean isExcludedURL(String uri) {
        return uri != null && excludedURLs.containsKey(uri.substring(uri.lastIndexOf("/") + 1));
    }
}
