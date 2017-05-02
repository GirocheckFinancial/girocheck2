/*
 ** File: BasePasswordItem.java
 **
 ** Date Created: February 2014
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
package com.smartbt.vtsuite.vtams.client.gui.component;

import com.smartgwt.client.types.FormErrorOrientation;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.form.fields.PasswordItem;

/**
 * The Base Password Item
 *
 * @author Ariel Saavedra
 */
public class BasePasswordItem extends PasswordItem {

    /**
     * Constructor
     *
     */
    public BasePasswordItem() {
        setWrapTitle(false);
        setErrorOrientation(FormErrorOrientation.RIGHT);
        //setWidth("100%");
        setValidateOnExit(true);
        //setValidateOnChange(true);
    }

    /**
     * Constructor
     *
     * @param name the item name
     * @param isRequired Whether a non-empty value is required for this field to
     * pass validation
     */
    public BasePasswordItem(String name, Boolean isRequired) {
        this();
        setName(name);
        setRequired(isRequired);
    }

    /**
     * Constructor
     *
     * @param name the item name
     * @param title the item title
     * @param isRequired Whether a non-empty value is required for this field to
     * pass validation
     */
    public BasePasswordItem(String name, String title, Boolean isRequired) {
        this(name, isRequired);
        setTitle(isRequired ? title + "<b> *</b>" : title);
    }

    /**
     * Constructor
     *
     * @param tabIndex the item tabIndex
     * @param name the item name
     * @param title the item title
     * @param tooltip
     * @param isRequired Whether a non-empty value is required for this field to
     * pass validation
     */
    public BasePasswordItem(int tabIndex, String name, String title, String tooltip, boolean isRequired) {
        this(name, title, isRequired);
        setVAlign(VerticalAlignment.BOTTOM);
        setTabIndex(tabIndex);
        setTooltip(tooltip);
        setWidth("100%");
    }
}
