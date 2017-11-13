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

import com.smartbt.girocheck.servercommon.dao.ClientDAO;
import com.smartbt.girocheck.servercommon.dao.IdeologyResultDAO;
import com.smartbt.girocheck.servercommon.model.Client;
import com.smartbt.girocheck.servercommon.model.IdeologyResult;
import com.smartbt.girocheck.servercommon.model.IdeologyResultInfo;
import java.util.Arrays;

/**
 *
 * @author Roberto Rodriguez :: <roberto.rodriguez@smartbt.com>
 */
public class IdeologyResultManager {

    public static IdeologyResultManager INSTANCE;

    public static IdeologyResultManager get() {
        if (INSTANCE == null) {
            INSTANCE = new IdeologyResultManager();
        }
        return INSTANCE;
    }

    public Integer save(int clientId, IdeologyResult ideologyResult) {
//        IdeologyResultDAO.get().deleteAll();
        
        Client client = ClientDAO.get().findById(clientId);
        
        ideologyResult.setClient(client);
        
        System.out.println("222 IdeologyResultManager.save:: ideologyResult.getQualifiers().size() = " + ideologyResult.getQualifiers().size());
        
        return IdeologyResultDAO.get().save(ideologyResult);
    }
}
