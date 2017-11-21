/*
 ** File: NomHost.java
 **
 ** Date Created: April 2013
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
package com.smartbt.vtsuite.vtcommon.nomenclators;

/**
 *
 * Nomenclature class
 */
public enum NomHost {
 
    /**
     * HOST - TECNICARD
     */
    TECNICARD(2),
      
    WESTECH(6),
    
    CERTEGY(7),
    
    ISTREAM2(8),
     
    COMPLIANCE(9),
    
    FISS(10),
    
    IDEOLOGY(11),
    
    GENERIC_HOST(12);
 

    private NomHost(int id) {
        this.id = id;
    }
    private int id;
 
    /**
     * Get id
     *
     * @return the id
     */
    public int getId() {
        return id;
    }
    
   
}
