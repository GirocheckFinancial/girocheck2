/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.girocheck.frontams.persistence.manager;
 
import com.google.common.io.ByteStreams;
import com.smartbt.girocheck.servercommon.dao.IDImagePngDAO;
import com.smartbt.girocheck.servercommon.dao.TransactionDAO;
import com.smartbt.girocheck.servercommon.display.TransactionImagesDisplay;
import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import com.smartbt.girocheck.servercommon.utils.ImgConvTiffToPng;
import com.smartbt.vtsuite.common.VTSuiteMessages;
import com.smartbt.vtsuite.vtcommon.Constants;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Blob;
import javax.xml.bind.DatatypeConverter;
import org.aioobe.cloudconvert.CloudConvertService;
import org.aioobe.cloudconvert.ConvertProcess;
import org.aioobe.cloudconvert.ProcessStatus;

/**
 *
 * @author Alejo
 */
public class TransactionImageManager {
    private static String IMAGE_CONVERTION_KEY = "oRkArXKrHfasWtL2VusjRWcqtyvYHd_F3AJvD44BROOajAo_iLWbhzu94wU2pV6qzV09jDQVhJvfH1iYsoid7g";
       
    public static TransactionImageManager INSTANCE;

    public static TransactionImageManager get() {
        if (INSTANCE == null) {
            INSTANCE = new TransactionImageManager();
        }
        return INSTANCE;
    }

    public ResponseData getTransactionImage(int idTransaction, boolean showIdImages) throws Exception {

        ResponseData response = new ResponseData();

        try {
            TransactionImagesDisplay result = TransactionDAO.get().getTransactionImage(idTransaction, showIdImages);

            buildImages(result);

            response.setData(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        return response;
    }

    private void buildImages(TransactionImagesDisplay dto) throws Exception {
        dto.setCheckFrontImage(ImgConvTiffToPng.convertBlackAndWhiteImages(dto.getCheckFront()));
        dto.setCheckBackImage(ImgConvTiffToPng.convertBlackAndWhiteImages(dto.getCheckBack()));

        byte[] idFront = null, idBack = null;

        System.out.println("TransactionManager.buildImages -> dto.isImagesConverted() = " + dto.isImagesConverted());
        System.out.println("TransactionManager.buildImages -> dto.getShowIdImages() = " + dto.getShowIdImages());

        if (dto.getShowIdImages()) {
            if (dto.isImagesConverted()) {
                idFront = getImage(dto.getIdFront());
                idBack = getImage(dto.getIdBack());
            } else {
                idFront = convert(dto.getIdFront(), "idfront_" + dto.getClientId());
                idBack = convert(dto.getIdBack(), "idback_" + dto.getClientId());

                IDImagePngDAO.get().save(idFront, idBack, dto.getClientId());
            }

            Long remainingConvertions = IDImagePngDAO.get().getRemainingConvertions();
            System.out.println("remainingConvertions = " + remainingConvertions);
            dto.setRemainingConvertions(remainingConvertions);

            dto.setIdFrontImage(getImageAsString(idFront));
            dto.setIdBackImage(getImageAsString(idBack));
        }
    }

    public static String getImageAsString(byte[] image) {
        if (image != null) {
            return "data:image/png;base64," + DatatypeConverter.printBase64Binary(image);
        } else {
            return "";
        }

    }
    
      public static byte[] getImage(Blob blob) throws Exception {
        if (blob == null) {
            return null;
        } 
        int length = (int)blob.length();
        
         System.out.println("CloudConvertClient -> length " + length);
       return blob.getBytes(1, length);
    }
      
      
       public static byte[] convert(Blob blob, String imageName) throws Exception {
        System.out.println("convertGrayScaleImages :: imageName = " + imageName + "...");
        
        if (blob == null) {
            return null;
        }
        
       String IMAGE_CONVERTION_KEY = "oRkArXKrHfasWtL2VusjRWcqtyvYHd_F3AJvD44BROOajAo_iLWbhzu94wU2pV6qzV09jDQVhJvfH1iYsoid7g";
       
         System.out.println("**IMAGE_CONVERTION_KEY = " + IMAGE_CONVERTION_KEY);
 
       // String apiKey = "oRkArXKrHfasWtL2VusjRWcqtyvYHd_F3AJvD44BROOajAo_iLWbhzu94wU2pV6qzV09jDQVhJvfH1iYsoid7g";
        CloudConvertService service = new CloudConvertService(IMAGE_CONVERTION_KEY);

// Create conversion process
        ConvertProcess process = service.startProcess("tif", "png");

// Perform conversion 
        ByteArrayInputStream bis = new ByteArrayInputStream( getImage(blob));
        process.startConversion(bis, imageName + ".tif");

// Wait for result
        ProcessStatus status;
        waitLoop:
        while (true) {
            status = process.getStatus();

            switch (status.step) {
                case FINISHED:
                    break waitLoop;
                case ERROR:
                    throw new RuntimeException(status.message);
            }

            // Be gentle
            Thread.sleep(200);
        }

        InputStream is = service.download(status.output.url);
       
// Clean up
        process.delete();
       System.out.println("convertGrayScaleImages :: imageName = " + imageName + ". Convertion end.");
        return ByteStreams.toByteArray(is);
    }
}
