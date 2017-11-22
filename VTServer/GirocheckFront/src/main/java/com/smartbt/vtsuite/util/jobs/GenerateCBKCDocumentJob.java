package com.smartbt.vtsuite.util.jobs;

import com.smartbt.girocheck.servercommon.display.CBKCDisplay;
import com.smartbt.girocheck.servercommon.manager.IdeologyResultManager;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

/**
 * @author @Roberto Rodriguez :: <RobertoSoftwareEngineer@gmail.com>
 */
public class GenerateCBKCDocumentJob extends TimerTask {

    @Override
    public void run() {
        System.out.println("Running task at " + (new Date()));
        try {
            List<CBKCDisplay> list = getCBKCInfo();
            createFile(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<CBKCDisplay> getCBKCInfo() throws Exception {
        List<CBKCDisplay> list = new ArrayList<>();

        try {
            HibernateUtil.beginTransaction();

            list = IdeologyResultManager.get().getYesterdayIdeologyResult();

            HibernateUtil.commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            HibernateUtil.rollbackTransaction();
        }
        return list;
    }

    public static void createFile(List<CBKCDisplay> list) throws UnsupportedEncodingException, FileNotFoundException, IOException {
        String folderName = "CBKC_Files";
        String fileName = getFileName();

        System.out.println("fileName = " + fileName);

        File folder = new File("C:\\", folderName);

        if (folder.exists() || folder.mkdirs()) {
            System.out.println("Folder " + folderName + " created.");
            File newFile = new File(folder, fileName);
            if (!newFile.exists()) {
                if (newFile.createNewFile()) {
                    System.out.println("File " + fileName + " created..");
                } else {
                    //TODO
                    //Handle Exception here
                }
            }

            System.out.println("Writting in file " + fileName);

            try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(
                    new FileOutputStream(newFile), "utf-8"))) {
                writer.println("Header");
                writer.println("LineCount|" + list.size());
                writer.println("DataSource|Application");
                writer.println("Sender|Girocheck");
                for (CBKCDisplay cbkc : list) {
                    writer.println(cbkc.toString());
                }
                writer.println("Footer");
            }

        }

    }

    private static String getFileName() {
        return new StringBuilder()
                .append((new SimpleDateFormat("yyyyMMdd")).format(getYesterdayDate()))
                .append("_Girocheck_CBKC__Application_File")
                .append(getIndex())
                .append(".txt")
                .toString();

    }

    private static String getIndex() {
        String cbkcIndex = "";

        try {
            HibernateUtil.beginTransaction();
            cbkcIndex = IdeologyResultManager.get().getCBKCIndex();
            HibernateUtil.commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            HibernateUtil.rollbackTransaction();
        }

        System.out.println("cbkcIndex = " + cbkcIndex);
        return cbkcIndex;
    }

    private static Date getYesterdayDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return calendar.getTime();
    }
}
 
