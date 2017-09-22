package com.smartbt.vtsuite.manager;

import com.smartbt.girocheck.servercommon.dao.IDImagePngDAO;
import com.smartbt.girocheck.servercommon.dao.PersonalIdentificationDAO;
import com.smartbt.girocheck.servercommon.model.PersonalIdentification; 
import com.smartbt.vtsuite.utils.ImageUtil;

/**
 *
 * @author rrodriguez
 */
public class IDImagePngManager {

    protected static IDImagePngManager INSTANCE;

    public static IDImagePngManager get() {
        if (INSTANCE == null) {
            INSTANCE = new IDImagePngManager();
        }
        return INSTANCE;
    }

    private PersonalIdentificationDAO personalIdentificationDAO = PersonalIdentificationDAO.get();
    private IDImagePngDAO idimagePngDAO = IDImagePngDAO.get();

    public void convertImage(int idClient) throws Exception {
        System.out.println("IDImagePngManager.convertImage :: idClient = " + idClient);
        PersonalIdentification identification = personalIdentificationDAO.getByClientId(idClient);

        if (identification != null) {
            byte[] idFront = ImageUtil.convertGrayScaleImages(identification.getIdFront(), "idfront_" + idClient);
            byte[] idBack = ImageUtil.convertGrayScaleImages(identification.getIdBack(), "idback_" + idClient);

            idimagePngDAO.save(idFront, idBack, idClient);
        }
    }
}
