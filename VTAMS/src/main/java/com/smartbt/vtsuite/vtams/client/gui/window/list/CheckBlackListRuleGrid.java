/*
 ** File: ClientListGrid.java
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
package com.smartbt.vtsuite.vtams.client.gui.window.list;

import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseListGrid;
import com.smartbt.vtsuite.vtams.client.gui.component.TextListGridField;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.CheckBlacklistRuleDS;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.ClientDS;
import com.smartbt.vtsuite.vtcommon.enums.EntityType; 
import com.smartgwt.client.types.GroupStartOpen; 

/**
 * The Transaction ListGrid
 *
 * @author Ariel Saavedra
 */
public class CheckBlackListRuleGrid extends BaseListGrid {

    private TextListGridField ddaField = new TextListGridField("dda", "DDA", false);
    private TextListGridField abaField = new TextListGridField("aba", "ABA", false);
    private TextListGridField makerField = new TextListGridField("maker", "Maker Name", false);

    /**
     * Constructor
     *
     * @param entityType
     */
    public CheckBlackListRuleGrid() {
        super();

        setEmptyMessage("No check rules to show...");

        setGroupStartOpen(GroupStartOpen.ALL);

        setDataSource(new CheckBlacklistRuleDS());
        setFields(ddaField,
                abaField,
                makerField);
    }
}
