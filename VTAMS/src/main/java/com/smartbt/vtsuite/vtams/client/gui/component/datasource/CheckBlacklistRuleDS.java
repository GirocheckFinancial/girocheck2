/*
 ** File: ClientDS.java
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
package com.smartbt.vtsuite.vtams.client.gui.component.datasource;

import com.smartbt.vtsuite.vtams.client.classes.Properties;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseDatasource;
import com.smartgwt.client.data.fields.DataSourceTextField;

/**
 * The Client DataSource
 *
 * @author Roberto
 */
public class CheckBlacklistRuleDS extends BaseDatasource {

    /**
     * Constructor
     */
    public CheckBlacklistRuleDS() { 
        DataSourceTextField ddaField = new DataSourceTextField("dda");
        DataSourceTextField abaField = new DataSourceTextField("aba"); 
        DataSourceTextField makerField = new DataSourceTextField("maker");
        
        setFetchDataURL(Properties.SEARCH_CHECK_BLACKLIST_RULES_WS);
        setAddDataURL(Properties.UPDATE_CHECK_BLACKLIST_RULES_WS);
        setUpdateDataURL(Properties.UPDATE_CHECK_BLACKLIST_RULES_WS);
        setRemoveDataURL(Properties.DELETE_CHECK_BLACKLIST_RULES_WS);
 
        setFields(ddaField,
                abaField,
                makerField);
    }
}
