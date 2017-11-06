/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.girocheck.frontams.persistence.manager;

import com.smartbt.girocheck.cloudconvert.CloudConvertClient;
import com.smartbt.girocheck.servercommon.dao.IDImagePngDAO;
import com.smartbt.girocheck.servercommon.dao.TransactionDAO;
import com.smartbt.girocheck.servercommon.display.TransactionImagesDisplay;
import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.girocheck.servercommon.utils.ImgConvTiffToPng;
import com.smartbt.vtsuite.common.VTSuiteMessages;
import com.smartbt.vtsuite.vtcommon.Constants;
import java.io.InputStream;
import java.util.Date;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Alejo
 */
public class TransactionImageManager {

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
                idFront = CloudConvertClient.getImage(dto.getIdFront());
                idBack = CloudConvertClient.getImage(dto.getIdBack());
            } else {
                idFront = CloudConvertClient.convert(dto.getIdFront(), "idfront_" + dto.getClientId());
                idBack = CloudConvertClient.convert(dto.getIdBack(), "idback_" + dto.getClientId());

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
}
