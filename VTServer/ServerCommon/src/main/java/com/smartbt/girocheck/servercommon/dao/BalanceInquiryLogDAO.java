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
package com.smartbt.girocheck.servercommon.dao;

import com.smartbt.girocheck.servercommon.model.BalanceInquiryLog;
 
/**
 *
 * @author Roberto Rodriguez :: <roberto.rodriguez@smartbt.com>, Alejo
 */
public class BalanceInquiryLogDAO extends BaseDAO<BalanceInquiryLog> {
//    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(MerchantDAO.class);

    protected static BalanceInquiryLogDAO dao;

    public BalanceInquiryLogDAO() {
    }

    public static BalanceInquiryLogDAO get() {
        if ( dao == null ) {
            dao = new BalanceInquiryLogDAO();
        }
        return dao;
    }
}
