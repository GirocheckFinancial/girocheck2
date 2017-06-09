/*
 ** File: ClerkController.java
 **
 ** Date Created: October 2013
 **
 ** Copyright @ 2004-2013 Smart Business Technology, Inc.
 **
 ** All rights reserved. No part of this software may be 
 ** reproduced, transmitted, transcribed, stored in a retrieval 
 ** system, or translated into any language or computer language, 
 ** in any form or by any means, electronic, mechanical, magnetic, 
 ** optical, chemical, manual or otherwise, without the prior 
 ** written permission of Smart Business Technology, Inc.
 **
 */
package com.smartbt.vtsuite.controller;

import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.girocheck.servercommon.manager.CheckBlacklistRuleManager;
import com.smartbt.girocheck.servercommon.model.CheckBlacklistRule;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Roberto
 */
@Path("VTAMS")
public class CheckController {
 
    private CheckBlacklistRuleManager manager = CheckBlacklistRuleManager.get();

    @POST
    @Path("searchCheckRules")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseDataList searchCheckRules(
            @FormParam("searchFilter") String searchFilter, 
            @FormParam("pageNumber") int pageNumber, 
            @FormParam("rowsPerPage") int rowsPerPage) throws Exception {

        return manager.searchRules(searchFilter, pageNumber, rowsPerPage);
    }

    @PUT
    @Path("updateCheckRule")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseData updateCheckRule(CheckBlacklistRule checkBlacklistRule) throws Exception { 
        return manager.saveOrUpdate(checkBlacklistRule); 
    }
    
    
    @PUT
    @Path("deleteCheckRules")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseData deleteCheckRules(CheckBlacklistRule checkBlacklistRule) throws Exception {
        int id = checkBlacklistRule.getId();
        return manager.deleteRule(id);
    }
     
}
