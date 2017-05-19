package com.smartbt.vtsuite.vtams.client.gui.component.datasource;

import com.smartbt.vtsuite.vtams.client.classes.Properties;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseDatasource;
import com.smartgwt.client.data.fields.DataSourceImageField;
import com.smartgwt.client.data.fields.DataSourceTextField;

/**
 *
 * @author Alejo
 */
public class TransactionImagesDS extends BaseDatasource {

    public TransactionImagesDS() {

        DataSourceImageField checkFrontImageField = new DataSourceImageField("checkFrontImage");
        DataSourceImageField checkBackImageField = new DataSourceImageField("checkBackImage");

        DataSourceImageField idFrontImageField = new DataSourceImageField("idFrontImage");
        DataSourceImageField idBackImageField = new DataSourceImageField("idBackImage");
        DataSourceTextField remainingConvertionsField = new DataSourceTextField("remainingConvertions");

        setFetchDataURL(Properties.GET_TRANSACTION_IMAGES_WS);
        setFields(checkFrontImageField,
                checkBackImageField,
                idFrontImageField,
                idBackImageField,
                remainingConvertionsField);
    }

}
