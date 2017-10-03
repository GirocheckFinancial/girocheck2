/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.girocheck.frontams.web.api;
 
import com.girocheck.frontams.common.controller.AbstractController;
import com.girocheck.frontams.persistence.dto.MessageDTO;
import com.girocheck.frontams.common.manager.AbstractManager;  
import com.girocheck.frontams.common.util.GRUtil;
import com.girocheck.frontams.common.util.response.WebResponse;
import com.girocheck.frontams.common.util.response.WebResponseData;
import com.girocheck.frontams.persistence.manager.MobileClientManager;
import com.girocheck.frontams.persistence.service.MessageService;
import java.text.ParseException;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Roberto Rodriguez
 */
@RestController
@RequestMapping(value = "/{pageId}/mobileClients")
public class MobileClientController extends AbstractController{
    
    @Autowired
    protected MobileClientManager mobileClientManager;
     
    @Autowired
    protected MessageService messageService;
     

    @Override
    public AbstractManager getAbstractManager() {
       return mobileClientManager;
    }
    
     @RequestMapping(value = "/sendMessage", method = RequestMethod.POST, consumes = "application/json")
    public WebResponseData sendMessage(@PathVariable("pageId") String pageId,
            @RequestBody MessageDTO messageDTO, HttpSession session) throws ParseException {
            System.out.println("MobileClientController -> sendMessage...");
        try { 
            Map<String, Object> data = GRUtil.parseParams(messageDTO.getParams());
            messageDTO.setParamsMap(data); 
                    
            return new WebResponseData( messageService.sendMessage(messageDTO, false) );
        } catch (Exception e) {
            e.printStackTrace();
            return WebResponseData.forException(e);
        }
    }
  
    
  
}
 
   
