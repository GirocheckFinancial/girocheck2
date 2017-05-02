/*
 ** File: DashboardWindow.java
 **
 ** Date Created: December 2013
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
package com.smartbt.vtsuite.vtams.client.gui.window;

import com.smartbt.vtsuite.vtams.client.gui.base.BaseEntityTabSetWindow;
import com.smartbt.vtsuite.vtams.client.gui.window.tab.ClientBlackListTab;
import com.smartbt.vtsuite.vtams.client.gui.window.tab.DashboardEnviromentalTab;

/**
 * The Dashboard Window
 *
 * @author Ariel Saavedra
 */
public class ClientBlackListWindow extends BaseEntityTabSetWindow {

    /**
     * Constructor
     *
     */
    public ClientBlackListWindow() {
        super();
        ClientBlackListTab clientBlackListTab = new ClientBlackListTab();

        managementWindow.addTab(clientBlackListTab);
    }
}
