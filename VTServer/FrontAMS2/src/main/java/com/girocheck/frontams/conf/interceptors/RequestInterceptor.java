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
package com.girocheck.frontams.conf.interceptors;

import com.girocheck.frontams.persistence.dto.Principal; 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author Roberto
 */
public class RequestInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String uri = request.getRequestURI();
//        System.out.println("Intercepting Request: " + uri);
//        
//        if(uri.contains("login")){
//            return true;
//        }
//        
//        String token = request.getHeader(Principal.TOKEN); 
//        String tokenInSession = (String)request.getSession().getAttribute(Principal.TOKEN);
//        
//        System.out.println("Request TOKEN = " + token);
//        System.out.println("Session TOKEN = " + tokenInSession);
//        
//        
//         return tokenInSession != null && token != null && tokenInSession.equals(token);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {

    }

}