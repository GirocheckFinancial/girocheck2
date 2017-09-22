/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.girocheck.frontams.conf.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author rrodriguez2
 */
@Configuration
@EnableTransactionManagement
@ComponentScan({
    "com.girocheck.frontams.businessLogic"})
public class ServiceConfig {

}
