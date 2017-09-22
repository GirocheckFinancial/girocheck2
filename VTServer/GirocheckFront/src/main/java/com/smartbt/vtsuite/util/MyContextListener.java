/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.util;
 
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
//        System.out.println("Creating ImageConvertionTask...");
//        Calendar today = Calendar.getInstance();
//        today.set(Calendar.HOUR_OF_DAY, 23);
//        today.set(Calendar.MINUTE, 0);
//        today.set(Calendar.SECOND, 0);
//        today.setTimeZone(TimeZone.getTimeZone("America/New_York"));
//
//        Timer timer = new Timer();
//        timer.schedule(new ImageConvertionTask(), today.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)); // 60*60*24*100 = 8640000ms
//
//        System.out.println("ImageConvertionTask created.");
    }
}

//class ImageConvertionTask extends TimerTask {
//
//    @Override
//    public void run() {
//         String prodProperty = System.getProperty("PROD");
//        Boolean isProd = prodProperty != null && prodProperty.equalsIgnoreCase("true");
//
////        if (!isProd) {
////            
////        }
//        System.out.println("isProd = " + isProd);
//
//        try {
//            System.out.println("Excecuting doGet...");
//            HibernateUtil.beginTransaction(); 
//             
//            List<Integer> usersToConvert = IDImagePngDAO.get().getListOfUsersToConvert();
//
//            System.out.println("usersToConvert.size = " + usersToConvert.size());
//
//            for (Integer u : usersToConvert) {
//                System.out.println(u);
//                try{
//                    IDImagePngManager.get().convertImage(u);
//                }catch(Exception e){
//                    System.out.println("Failed to convert image associated to client " + u);
//                }
//                
//            }
//
//            HibernateUtil.commitTransaction();
//            System.out.println("Excecution finish.");
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            HibernateUtil.rollbackTransaction();
//        }
//    }
//
//}
