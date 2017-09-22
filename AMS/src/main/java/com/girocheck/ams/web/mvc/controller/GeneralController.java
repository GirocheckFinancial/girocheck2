/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.girocheck.ams.web.mvc.controller;
 
import java.net.URISyntaxException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Roberto Rodriguez
 */
@Controller
@RequestMapping(value = "/", method = RequestMethod.GET)
public class GeneralController {

  
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request, @RequestParam(required = false) Integer type){
        System.out.println("index..."); 
        return new ModelAndView("index");
//         return new ModelAndView("login");
    }
 
    
//    @RequestMapping(value = "portal",method = RequestMethod.GET)
//    public String login(HttpServletRequest request, @RequestParam(required = false) Integer type){
//        System.out.println("login...");
//
//         return "index";
//    }
    
//    @RequestMapping(value = "/portal",method = RequestMethod.GET)
//    private ModelAndView dashboard(HttpServletRequest request, @RequestParam(required = false) Integer type){
//        System.out.println("dashboard...");
//
//         return new ModelAndView("index");
//    }
}
