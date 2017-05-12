/*
 * Copyright @ 2004-2014 Smart Business Technology, Inc.
 *
 * All rights reserved. No part of this software may be 
 * reproduced, transmitted, transcribed, stored in a retrieval
 * system, or translated into any language or computer language, 
 * in any form or by any means, electronic, mechanical, magnetic,  
 * optical, chemical, manual or otherwise, without the prior  
 * written permission of Smart Business Technology, Inc.  
 *
 *
 */
package com.smartbt.girocheck.servercommon.manager;

import com.smartbt.girocheck.servercommon.dao.BalanceInquiryLogDAO;  
import com.smartbt.girocheck.servercommon.model.BalanceInquiryLog;
/**
 *
 * @author Roberto Rodriguez :: <roberto.rodriguez@smartbt.com>
 */
public class BalanceInquiryLogManager {

    protected static BalanceInquiryLogManager INSTANCE;

    private BalanceInquiryLogDAO dao = BalanceInquiryLogDAO.get();

    public static BalanceInquiryLogManager get() {
        if (INSTANCE == null) {
            INSTANCE = new BalanceInquiryLogManager();
        }
        return INSTANCE;
    }
    
    public void save(BalanceInquiryLog log){
        dao.saveOrUpdate(log);
    }
}
