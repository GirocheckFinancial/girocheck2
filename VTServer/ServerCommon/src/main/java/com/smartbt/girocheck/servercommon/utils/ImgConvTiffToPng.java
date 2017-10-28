package com.smartbt.girocheck.servercommon.utils;

import com.google.common.io.ByteStreams;
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
import javaxt.io.Image;
import org.aioobe.cloudconvert.CloudConvertService;
import org.aioobe.cloudconvert.ConvertProcess;
import org.aioobe.cloudconvert.ProcessStatus;

public class ImgConvTiffToPng {
    //This key is granted for this vendor
    //https://cloudconvert.com
    private static String IMAGE_CONVERTION_KEY = System.getProperty("IMAGE_CONVERTION_KEY");
    
    public static void main(String[] args){
        System.out.println("start");
         CloudConvertService service = new CloudConvertService("CMWetpcKyxRMEeqJfuSbxrJSUkMNKUXlO7iVHp7dImlRdtVOXSNMYO1Hmd7TorrCpu4iuCPND_zEEHPjgwxi3w");
        System.out.println("end");
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

    public static byte[] convertGrayScaleImages(Blob blob, String imageName) throws Exception {
        System.out.println("convertGrayScaleImages :: imageName = " + imageName + "...");
        
        if (blob == null) {
            return null;
        }
        
        System.out.println("IMAGE_CONVERTION_KEY = " + IMAGE_CONVERTION_KEY);
 
        if(IMAGE_CONVERTION_KEY == null){
//            IMAGE_CONVERTION_KEY = "oRkArXKrHfasWtL2VusjRWcqtyvYHd_F3AJvD44BROOajAo_iLWbhzu94wU2pV6qzV09jDQVhJvfH1iYsoid7g";
            IMAGE_CONVERTION_KEY = "elGTzBSfOT8J3mOKO2r9cHPtiLTf58IJOenMN5FE0qSF0F6lkhU4avZzej4EKqCYV4B1sgQx3xsjLOrCTFPtIw";
        }
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

    public byte[] rotate180(byte[] imagee) {
        Image image = new Image(imagee);
        image.rotate(180);
        image.rotateCounterClockwise();
        image.rotateClockwise();
        return image.getByteArray();
    }

    public static byte[] convert(byte[] tiff) throws Exception {
        System.out.println("ImgConvTiffToPng.convert -> tiff.length = " + tiff.length);
//        byte[] out = new byte[0];
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

        return out;

    }
    
    public static byte[] getImage(Blob blob) throws Exception {
        if (blob == null) {
            return null;
        } 
       return blob.getBytes(1, (int) blob.length());
    }

//    private BufferedImage createImageFromBytes(byte[] imageData) {
//        ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
//        try {
//            return ImageIO.read(bais);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private byte[] image_byte_data(BufferedImage image) {
//        WritableRaster raster = image.getRaster();
//        DataBufferByte buffer = (DataBufferByte) raster.getDataBuffer();
//        return buffer.getData();
//    }
//    public byte[] read(String imageName) throws IOException {
//
//        File file = new File(imageName+".png"); 
//        byte[] fileContent = Files.readAllBytes(file.toPath());
//        file.delete();
//        
//        return fileContent;
//    }
//
}
