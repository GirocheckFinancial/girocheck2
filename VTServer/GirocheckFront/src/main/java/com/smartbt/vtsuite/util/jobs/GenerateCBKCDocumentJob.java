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

            StringBuilder sb = new StringBuilder();

            for (CBKCDisplay cbkc : list) {
                sb.append(cbkc.toString() + "|");
            }

            System.out.println(sb.toString());

            createFile(sb.toString());
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

    public static void createFile(String text) throws UnsupportedEncodingException, FileNotFoundException, IOException {
        String folderName = "CBKC_Files";

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date yesterday = calendar.getTime();

        String fileName = "CBKC_" + (new SimpleDateFormat("MM_dd_yyyy")).format(yesterday) + ".txt";
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
            
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(newFile), "utf-8"))) {
                writer.write(text);
            }

        }

    }
}

class CBKC {

    private Date enrollmentDate;
    private String source;
    private String cipId;
    private String ideologyResult;
    private String idNotes;
    private String applicationDisposition;

    public CBKC() {
    }

    public static CBKC sample1() {
        return new CBKC(new Date(), "Merchant A", "cipId-1", "ideologyResult-1", "idNotes-1", "Approved");
    }

    public static CBKC sample2() {
        return new CBKC(new Date(), "Merchant B", "cipId-2", "ideologyResult-2", "idNotes-2", "Declined");
    }

    public CBKC(Date enrollmentDate, String source, String cipId, String ideologyResult, String idNotes, String applicationDisposition) {
        this.enrollmentDate = enrollmentDate;
        this.source = source;
        this.cipId = cipId;
        this.ideologyResult = ideologyResult;
        this.idNotes = idNotes;
        this.applicationDisposition = applicationDisposition;
    }

    private static final DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (enrollmentDate != null) {
            sb.append("enrollmentDate=" + df.format(enrollmentDate) + ",");
        }
        sb.append("source=" + source + ",");
        sb.append("cipId=" + cipId + ",");
        sb.append("ideologyResult=" + ideologyResult + ",");
        sb.append("idNotes=" + idNotes + ",");
        sb.append("applicationDisposition=" + applicationDisposition);

        return sb.toString();
    }

    /**
     * @return the enrollmentDate
     */
    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    /**
     * @param enrollmentDate the enrollmentDate to set
     */
    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    /**
     * @return the source
     */
    public String getSource() {
        return source;
    }

    /**
     * @param source the source to set
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * @return the cipId
     */
    public String getCipId() {
        return cipId;
    }

    /**
     * @param cipId the cipId to set
     */
    public void setCipId(String cipId) {
        this.cipId = cipId;
    }

    /**
     * @return the ideologyResult
     */
    public String getIdeologyResult() {
        return ideologyResult;
    }

    /**
     * @param ideologyResult the ideologyResult to set
     */
    public void setIdeologyResult(String ideologyResult) {
        this.ideologyResult = ideologyResult;
    }

    /**
     * @return the idNotes
     */
    public String getIdNotes() {
        return idNotes;
    }

    /**
     * @param idNotes the idNotes to set
     */
    public void setIdNotes(String idNotes) {
        this.idNotes = idNotes;
    }

    /**
     * @return the applicationDisposition
     */
    public String getApplicationDisposition() {
        return applicationDisposition;
    }

    /**
     * @param applicationDisposition the applicationDisposition to set
     */
    public void setApplicationDisposition(String applicationDisposition) {
        this.applicationDisposition = applicationDisposition;
    }

}
