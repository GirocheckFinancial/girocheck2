/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.girocheck.ams.web.mvc.controller;

import com.girocheck.ams.manager.ReportManager;  
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.PathParam;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Ariel
 */
@Controller
@RequestMapping(value = "/report", method = RequestMethod.GET)
public class ReportController {

    @Autowired
    private ReportManager reportManager;

    @RequestMapping(value = "/{entity}", method = RequestMethod.GET)
    public ModelAndView viewReport(HttpServletRequest request, @PathVariable("entity") String entity) {
        System.out.println("viewReport... entity = " + entity);

        String url = request.getRequestURL().toString();
        String params = request.getQueryString();

        System.out.println("%url = " + url);
        System.out.println("%params = " + params);

        if (url.contains("/AMS/")) {
            url = url.split("/AMS/")[0];
            System.out.println("after refactor: url = " + url);
        }

        if (params != null && !params.isEmpty() && params.contains("params=") && params.split("params=").length > 1) {
            params = params.split("params=")[1];
            if (!params.isEmpty() && (params.charAt(0) == '?') || params.charAt(0) == '&') {
                params = "params=" + params.substring(1);
            }
            System.out.println("after refactor: params = " + params);
        }
 
        List list = reportManager.getReport(url,entity, params);

        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("datasource", new JRBeanCollectionDataSource(list));
        return new ModelAndView(entity + "PDFView", parameterMap);
    }

//    public List<TransactionDTO> generateList() {
//        List<TransactionDTO> list = new ArrayList<>();
//
//        for (int i = 0; i < 10; i++) {
//            list.add(new TransactionDTO(100.0, 2.75, (new Date()), "134567890123456", "Roberto", "Merchant A", "Card Load", "Success"));
//        }
//
//        return list;
//    }

}
