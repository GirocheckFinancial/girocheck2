/*
 ** File: ClientController.java
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
import com.smartbt.girocheck.servercommon.display.ClientDisplay;
import com.smartbt.girocheck.servercommon.manager.ClientManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType; 
import javax.ws.rs.core.SecurityContext; 
/**
 *
 * @author Ariel Saavedra
 */
@Path("VTAMS")
public class ClientController {

    @Context
    private HttpHeaders context;
    @Context
    private HttpServletResponse response;
    @Context
    private HttpServletRequest request;
    private ClientManager manager = new ClientManager();
//    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ClientController.class);
    @Context
    private SecurityContext securityContext;

    /**
     * Search all Clients by a given Merchant's id and a filter
     *
     * @param idEntity
     * @param entityType
     * @param searchFilter
     * @param activityFilter
     * @param pageNumber
     * @param rowsPerPage
     * @return
     * @throws java.lang.Exception
     */
    //@FormParam("idEntity") int idEntity,
    // @FormParam("entityType") EntityType entityType,
    @POST
    @Path("searchClients")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseDataList searchClients(
            @FormParam("searchFilter") String searchFilter,
            @FormParam("blackList") Integer blackListCode,
            @FormParam("optOut") Boolean optOut,
            @FormParam("pageNumber") int pageNumber, @FormParam("rowsPerPage") int rowsPerPage) throws Exception {

        return manager.searchClients(searchFilter, pageNumber, rowsPerPage, blackListCode, optOut);
    }

    @PUT
    @Path("updateClientBlackList")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseData updateClientBlackList(ClientDisplay clientDisplay) throws Exception {

        return manager.updateClientBlackList(clientDisplay);

    }

    @POST
    @Path("updateClientOptOut")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseData updateClientOptOut(@FormParam("id") int id) throws Exception {
        return manager.updateClientOptOut(id);
    }
    
}
