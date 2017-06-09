/*
 ** File: RoleEditor.java
 **
 ** Date Created: April 2013
 **
 ** Copyright @ 2004-2014 Smart Business Technology, Inc.
 **
 ** All rights reserved. No part of this software may be 
 ** reproduced, transmitted, transcribed, stored in a retrieval 
 ** system, or translated into any language or computer language, 
 ** in any form or by any means, electronic, mechanical, magnetic, 
 ** optical, chemical, manual or otherwise, without the prior 
 ** written permission of Smart Business Technology, Inc.
 **
 */
package com.smartbt.vtsuite.vtams.client.gui.window.editor;
 
import com.smartbt.vtsuite.vtams.client.gui.base.BaseEditorWindow;
import com.smartbt.vtsuite.vtams.client.gui.component.BaseTextItem;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.CheckBlacklistRuleDS;
import com.smartgwt.client.data.Record;

/**
 * The Role Editor Window
 *
 * @author Roberto
 */
public final class CheckRuleEditor extends BaseEditorWindow { 
    private BaseTextItem dda;
    private BaseTextItem aba;
    private BaseTextItem maker;

    public CheckRuleEditor() {
        super("Check Black List Rule");

        dda = new BaseTextItem("dda", true);
        aba = new BaseTextItem("aba", true);
        maker = new BaseTextItem("maker", true);

        dataForm.setDataSource(new CheckBlacklistRuleDS());
        dataForm.setFields(dda, aba, maker);
    }

    @Override
    public void addRecord(Record record) {
        dda.setValue(record.getAttribute("dda"));
        aba.setValue(record.getAttribute("aba"));
        maker.setValue(record.getAttribute("maker"));
    }

    public Record getRecord() { 
        Record userRecord = new Record();

        userRecord.setAttribute("id", dataForm.getValuesAsRecord().getAttributeAsInt("id"));
        userRecord.setAttribute("dda", dda.getValueAsString());
        userRecord.setAttribute("aba", aba.getValueAsString());
        userRecord.setAttribute("maker", maker.getValueAsString());
        return userRecord;
    }

    public void Reset() {
        dda.setValue("");
        aba.setValue("");
        maker.setValue("");
    }
}
