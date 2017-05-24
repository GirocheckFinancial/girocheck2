/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.util;

import com.smartbt.girocheck.servercommon.dao.IDImagePngDAO;
import com.smartbt.girocheck.servercommon.enums.EnumApplicationParameter;
import com.smartbt.girocheck.servercommon.manager.ApplicationParameterManager;
import com.smartbt.girocheck.servercommon.manager.IDImagePngManager;
import com.smartbt.girocheck.servercommon.utils.IDScanner;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author rrodriguez
 */
public class MyContextListener
        implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        //do stuff
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        System.out.println("Creating ImageConvertionTask...");
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 11);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);

        Timer timer = new Timer();
        timer.schedule(new ImageConvertionTask(), today.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)); // 60*60*24*100 = 8640000ms

        System.out.println("ImageConvertionTask created.");
    }
}

class ImageConvertionTask extends TimerTask {

    @Override
    public void run() {
         String prodProperty = System.getProperty("PROD");
        Boolean isProd = prodProperty != null && prodProperty.equalsIgnoreCase("true");

//        if (!isProd) {
//            
//        }
        System.out.println("isProd = " + isProd);

        try {
            System.out.println("Excecuting doGet...");
            HibernateUtil.beginTransaction(); 
             
            List<Integer> usersToConvert = IDImagePngDAO.get().getListOfUsersToConvert();

            System.out.println("usersToConvert.size = " + usersToConvert.size());

            for (Integer u : usersToConvert) {
                System.out.println(u);
                try{
                    IDImagePngManager.get().convertImage(u);
                }catch(Exception e){
                    System.out.println("Failed to convert image associated to client " + u);
                }
                
            }

            HibernateUtil.commitTransaction();
            System.out.println("Excecution finish.");
        } catch (Exception ex) {
            ex.printStackTrace();
            HibernateUtil.rollbackTransaction();
        }
    }

}
