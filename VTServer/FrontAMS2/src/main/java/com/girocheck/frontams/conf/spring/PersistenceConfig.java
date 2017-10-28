/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.girocheck.frontams.conf.spring;

import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration; 
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor; 
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author rrodriguez2
 */
@Configuration
@EnableTransactionManagement
@ComponentScan({
    "com.girocheck.frontams.persistence"})
@PropertySource({"classpath:hibernate.properties"})
public class PersistenceConfig {

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() throws Exception {
        JndiObjectFactoryBean jndiFactory = new JndiObjectFactoryBean();
        jndiFactory.setJndiName("jdbc/__default");
        jndiFactory.afterPropertiesSet();
        return (DataSource) jndiFactory.getObject();
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() throws Exception {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setHibernateProperties(additionalProperties());
        sessionFactoryBean.setMappingResources(
                "ormmapping/com/smartbt/girocheck/servercommon/model/Address.hbm.xml",
                "ormmapping/com/smartbt/girocheck/servercommon/model/Agrupation.hbm.xml",
                "ormmapping/com/smartbt/girocheck/servercommon/model/AgrupationParameter.hbm.xml",
                "ormmapping/com/smartbt/girocheck/servercommon/model/AgrupationParameterValue.hbm.xml",
                "ormmapping/com/smartbt/girocheck/servercommon/model/ApplicationParameter.hbm.xml",
                "ormmapping/com/smartbt/girocheck/servercommon/model/CardProgram.hbm.xml",
                "ormmapping/com/smartbt/girocheck/servercommon/model/CardType.hbm.xml",
                "ormmapping/com/smartbt/girocheck/servercommon/model/Check.hbm.xml",
                "ormmapping/com/smartbt/girocheck/servercommon/model/Client.hbm.xml",
                "ormmapping/com/smartbt/girocheck/servercommon/model/Country.hbm.xml",
                "ormmapping/com/smartbt/girocheck/servercommon/model/CreditCard.hbm.xml",
                "ormmapping/com/smartbt/girocheck/servercommon/model/Merchant.hbm.xml",
                "ormmapping/com/smartbt/girocheck/servercommon/model/MerchantParameter.hbm.xml",
                "ormmapping/com/smartbt/girocheck/servercommon/model/MerchantParameterValue.hbm.xml",
                "ormmapping/com/smartbt/girocheck/servercommon/model/PersonalIdentification.hbm.xml",
                "ormmapping/com/smartbt/girocheck/servercommon/model/Privilege.hbm.xml",
                "ormmapping/com/smartbt/girocheck/servercommon/model/Role.hbm.xml",
                "ormmapping/com/smartbt/girocheck/servercommon/model/RolePrivilege.hbm.xml",
                "ormmapping/com/smartbt/girocheck/servercommon/model/State.hbm.xml",
                "ormmapping/com/smartbt/girocheck/servercommon/model/SubTransaction.hbm.xml",
                "ormmapping/com/smartbt/girocheck/servercommon/model/Terminal.hbm.xml",
                "ormmapping/com/smartbt/girocheck/servercommon/model/TerminalParameter.hbm.xml",
                "ormmapping/com/smartbt/girocheck/servercommon/model/TerminalParameterValue.hbm.xml",
                "ormmapping/com/smartbt/girocheck/servercommon/model/Transaction.hbm.xml",
                "ormmapping/com/smartbt/girocheck/servercommon/model/User.hbm.xml",
                "ormmapping/com/smartbt/girocheck/servercommon/model/VTSession.hbm.xml",
                "ormmapping/com/smartbt/girocheck/servercommon/model/AchCard.hbm.xml",
                "ormmapping/com/smartbt/girocheck/servercommon/model/Host.hbm.xml",
                "ormmapping/com/smartbt/girocheck/servercommon/model/ReportFilters.hbm.xml",
                "ormmapping/com/smartbt/girocheck/servercommon/model/TransactionMethod.hbm.xml",
                "ormmapping/com/smartbt/girocheck/servercommon/model/FeeSchedules.hbm.xml",
                "ormmapping/com/smartbt/girocheck/servercommon/model/FeeBuckets.hbm.xml",
                "ormmapping/com/smartbt/girocheck/servercommon/model/Email.hbm.xml",
                "ormmapping/com/smartbt/girocheck/servercommon/model/FiltersReport.hbm.xml",
                "ormmapping/com/smartbt/girocheck/servercommon/model/MobileClient.hbm.xml",
                "ormmapping/com/smartbt/girocheck/servercommon/model/BalanceInquiryLog.hbm.xml",
                "ormmapping/com/smartbt/girocheck/servercommon/model/IDImagePng.hbm.xml",
                "ormmapping/com/smartbt/girocheck/servercommon/model/CheckBlacklistRule.hbm.xml",
                "ormmapping/com/smartbt/girocheck/servercommon/model/MobileNotification.hbm.xml");

        return sessionFactoryBean;
    }

    @Bean
    public HibernateTransactionManager hibernateTransactionManager() throws Exception {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    final Properties additionalProperties() {
        return new Properties() {
            {
                setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
                setProperty("hibernate.connection.driver_class", env.getProperty("hibernate.connection.driver_class"));
            }
        };
    }
}
