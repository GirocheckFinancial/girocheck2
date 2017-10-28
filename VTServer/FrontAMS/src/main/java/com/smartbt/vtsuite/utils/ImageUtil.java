/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.utils;

import com.google.common.io.ByteStreams;
import com.smartbt.girocheck.servercommon.dao.IDImagePngDAO;
import com.smartbt.girocheck.servercommon.display.TransactionImagesDisplay;
import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageDecoder;
import com.sun.media.jai.codec.ImageEncoder;
import com.sun.media.jai.codec.PNGEncodeParam;
import com.sun.media.jai.codec.TIFFDecodeParam;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import javax.xml.bind.DatatypeConverter;
import org.aioobe.cloudconvert.CloudConvertService;
import org.aioobe.cloudconvert.ConvertProcess;
import org.aioobe.cloudconvert.ProcessStatus;

/**
 *
 * @author rrodriguez
 */
public class ImageUtil {

    private static String IMAGE_CONVERTION_KEY = System.getProperty("IMAGE_CONVERTION_KEY");

    public static void buildImages(TransactionImagesDisplay dto) throws Exception {
        dto.setCheckFrontImage(convertBlackAndWhiteImages(dto.getCheckFront()));
        dto.setCheckBackImage(convertBlackAndWhiteImages(dto.getCheckBack()));

        byte[] idFront = null, idBack = null;

        System.out.println("TransactionManager.buildImages -> dto.isImagesConverted() = " + dto.isImagesConverted());
        System.out.println("TransactionManager.buildImages -> dto.getShowIdImages() = " + dto.getShowIdImages());

        if (dto.getShowIdImages()) {
            if (dto.isImagesConverted()) {
                idFront = getImage(dto.getIdFront());
                idBack = getImage(dto.getIdBack());
            } else {
                idFront = convertGrayScaleImages(dto.getIdFront(), "idfront_" + dto.getClientId());
                idBack = convertGrayScaleImages(dto.getIdBack(), "idback_" + dto.getClientId());

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

    public static String convertBlackAndWhiteImages(Blob blob) throws Exception {
        if (blob == null) {
            return "";
        }

        byte[] tiff = getImage(blob);

        InputStream inputStream = new ByteArrayInputStream(tiff);

        TIFFDecodeParam param = null;

        ImageDecoder dec = ImageCodec.createImageDecoder("tiff", inputStream, param);
        RenderedImage op = dec.decodeAsRenderedImage(0);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        PNGEncodeParam jpgparam = null;
        ImageEncoder en = ImageCodec.createImageEncoder("png", outputStream, jpgparam);

        en.encode(op);
        outputStream = (ByteArrayOutputStream) en.getOutputStream();
        byte[] out = outputStream.toByteArray();
        outputStream.flush();
        outputStream.close();

        return "data:image/png;base64," + DatatypeConverter.printBase64Binary(out);

    }

    public static void main(String[] args) {
        System.out.println("Start");
        String IMAGE_CONVERTION_KEY = "CMWetpcKyxRMEeqJfuSbxrJSUkMNKUXlO7iVHp7dImlRdtVOXSNMYO1Hmd7TorrCpu4iuCPND_zEEHPjgwxi3w";
        CloudConvertService service = new CloudConvertService(IMAGE_CONVERTION_KEY);
        System.out.println("OKKK");
    }

    public static byte[] convertGrayScaleImages(Blob blob, String imageName) throws Exception {
        System.out.println("convertGrayScaleImages :: imageName = " + imageName + "...");

        if (blob == null) {
            return null;
        }

        System.out.println("IMAGE_CONVERTION_KEY = " + IMAGE_CONVERTION_KEY);

        if (IMAGE_CONVERTION_KEY == null) {
//            IMAGE_CONVERTION_KEY = "oRkArXKrHfasWtL2VusjRWcqtyvYHd_F3AJvD44BROOajAo_iLWbhzu94wU2pV6qzV09jDQVhJvfH1iYsoid7g";
            IMAGE_CONVERTION_KEY = "elGTzBSfOT8J3mOKO2r9cHPtiLTf58IJOenMN5FE0qSF0F6lkhU4avZzej4EKqCYV4B1sgQx3xsjLOrCTFPtIw";
        }
        // String apiKey = "oRkArXKrHfasWtL2VusjRWcqtyvYHd_F3AJvD44BROOajAo_iLWbhzu94wU2pV6qzV09jDQVhJvfH1iYsoid7g";
        CloudConvertService service = new CloudConvertService(IMAGE_CONVERTION_KEY);

// Create conversion process
        ConvertProcess process = service.startProcess("tif", "png");

// Perform conversion 
        ByteArrayInputStream bis = new ByteArrayInputStream(getImage(blob));
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

    public static byte[] getImage(Blob blob) throws Exception {
        if (blob == null) {
            return null;
        }
        return blob.getBytes(1, (int) blob.length());
    }
}
