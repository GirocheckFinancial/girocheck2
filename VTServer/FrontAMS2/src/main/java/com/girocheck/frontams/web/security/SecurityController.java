/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.girocheck.frontams.web.security;

import com.girocheck.frontams.common.util.response.WebResponseData;
import com.girocheck.frontams.persistence.dto.Principal;
import com.girocheck.frontams.persistence.manager.TransactionImageManager;
import com.girocheck.frontams.persistence.service.AccessService;
import com.girocheck.frontams.persistence.service.LoginService;
import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
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
@RequestMapping(value = "/{pageId}/security", method = RequestMethod.GET)
public class SecurityController {

    @Autowired
    protected LoginService loginService;
    @Autowired
    protected AccessService accessService;

    @RequestMapping(value = "/ping")
    public ResponseData checkAccess(@PathVariable("pageId") String pageId) throws Exception {
        System.out.println("222 Calling PING...");
        ResponseData response = null;
        try {
            HibernateUtil.beginTransaction();
            response = TransactionImageManager.get().getTransactionImage(370, true);
            HibernateUtil.commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            HibernateUtil.rollbackTransaction();
        }

        return response;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
    public WebResponseData login(@PathVariable("pageId") String pageId, @RequestBody LoginRequest data, HttpSession session) {

        String username = data.getUser();
        String password = data.getPassword();

        System.out.println("username = " + username);
        System.out.println("password = " + password);

        Principal principal = null;

        try {
            principal = loginService.login(username, password);

            if (principal != null) {
                session.putValue(Principal.PRINCIPAL, principal);
                session.putValue(Principal.TOKEN, principal.getToken());
                return new WebResponseData(principal);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return WebResponseData.toLoginFail();
    }

    @RequestMapping(value = "/checkAccess")
    public WebResponseData checkAccess(@PathVariable("pageId") String pageId, HttpSession session) {

        Principal principal = (Principal) session.getAttribute(Principal.PRINCIPAL);

        return new WebResponseData(accessService.checkAccess(principal, pageId));
    }

}

class LoginRequest {

    private String user;
    private String password;

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
