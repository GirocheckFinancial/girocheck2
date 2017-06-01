package com.smartbt.vtsuite.vtams.client.gui.window.transaction;

import com.smartbt.vtsuite.vtams.client.gui.component.BaseImageContent;
import com.smartbt.vtsuite.vtams.client.utils.Utils;
import com.smartgwt.client.data.Record;

/**
 *
 * @author rrodriguez
 */
public class TransactionImage extends BaseImageContent {

    private String imageName;

    public TransactionImage(String imageName, String title) {
        super(title, 350, 650);
        this.imageName = imageName;
    }

    public void displayImage(Record record) {
        if (record == null || record.getAttributeAsString(imageName) == null) {
            Utils.debug("record doesn't have " + imageName);

            getLogo().setSrc("http://www.karenika.com/down_digi/empty_overlay_small.png");
        } else {
            Utils.debug("record has " + imageName);
            getLogo().setSrc(record.getAttributeAsString(imageName));
        }
        getLogo().redraw();
    }
}
