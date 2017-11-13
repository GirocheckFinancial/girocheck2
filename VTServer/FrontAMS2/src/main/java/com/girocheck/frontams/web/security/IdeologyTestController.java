/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.girocheck.frontams.web.security;

import com.smartbt.vtsuite.manager.IdeologyBusinessLogic;
import com.smartbt.vtsuite.test.IdeologyTest;
import com.smartbt.vtsuite.test.IdeologyTest2;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Roberto Rodriguez
 */
@RestController
@RequestMapping(value = "/ideology", method = RequestMethod.GET)
public class IdeologyTestController {

    @RequestMapping(value = "/test/{index}")
    public String test(@PathVariable("index") Integer index) throws Exception {
        System.out.println("Ideology Test");

        return IdeologyTest.get().sendSuccessTest(index);
    }

    @RequestMapping(value = "/test2/{index}")
    public String test2(@PathVariable("index") Integer index) throws Exception {
        System.out.println("Ideology Test");

        return IdeologyTest2.get().sendSuccessTest(index);
    }

}
