/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.servlets;

import com.smartbt.girocheck.servercommon.dao.IDImagePngDAO;
import com.smartbt.girocheck.servercommon.manager.IDImagePngManager;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author rrodriguez
 *
 * https://cloudconvert.com
 */
public class ImageServlet extends HttpServlet {

//    public void doGet(HttpServletRequest req, HttpServletResponse resp){
//        System.out.println("before call..");
//        try {
//            Job.main(null);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        System.out.println("after call..");
//    }
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        try {
            System.out.println("Excecuting doGet...");
            HibernateUtil.beginTransaction();

            String q = readFile();//"select c.id from client c inner join identification i on c.id = i.client where c.id not in (select client from idimages_png) order by c.created_at desc limit ";

            System.out.println("q = " + q);

            List<Integer> usersToConvert = IDImagePngDAO.get().testGetListOfUsersToConvert(q);

            System.out.println("usersToConvert.size = " + usersToConvert.size());

            for (Integer u : usersToConvert) {
                System.out.println(u);
                IDImagePngManager.get().convertImage(u);
            }

            HibernateUtil.commitTransaction();
            System.out.println("Excecution finish.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String readFile() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("C:\\query.txt"));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            return sb.toString();
        } finally {
            br.close();
        }
    }

}
