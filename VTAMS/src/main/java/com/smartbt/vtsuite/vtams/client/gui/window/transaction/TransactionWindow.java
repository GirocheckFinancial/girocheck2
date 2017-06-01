/*
 ** File: BaseEntityDetailWindow.java
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
package com.smartbt.vtsuite.vtams.client.gui.window.transaction;

import com.google.gwt.user.client.Window;
import com.smartbt.vtsuite.vtams.client.classes.Properties;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseWindow;
import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.ReportDS;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.TransactionImagesDS;
import com.smartbt.vtsuite.vtams.client.gui.listener.FilterListenerImp;
import com.smartbt.vtsuite.vtams.client.gui.listener.ListListener;
import com.smartbt.vtsuite.vtams.client.gui.listener.PaginationListener;
import com.smartbt.vtsuite.vtams.client.gui.listener.SimpleListener;
import com.smartbt.vtsuite.vtams.client.utils.Utils;
import com.smartbt.vtsuite.vtcommon.Constants;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.events.SectionHeaderClickEvent;
import com.smartgwt.client.widgets.layout.events.SectionHeaderClickHandler;

/**
 * The Base Entity Detail Window
 *
 * @author Ariamnet Lopez, Ariel Saavedra
 */
public class TransactionWindow extends BaseWindow {

    // Search Section Stack
    protected HLayout hMainLayout = new HLayout();
    private int aux = 0;

//    protected VLayout vLeftLayout = new VLayout();
    protected SectionStack leftSectionStack = new SectionStack();
    protected SectionStackSection leftSectionStackSection = new SectionStackSection("Details");
    protected DynamicForm leftHeaderForm = new DynamicForm();
    // Detail Section Stack
    protected SectionStack rightSectionStack = new SectionStack();
    // protected SectionStackSection rightSection = new SectionStackSection( "Transactions" );
    // Data Section Stack
    //protected SectionStack dataSectionStack = new SectionStack();
    protected SectionStackSection rightSectionStackSection = new SectionStackSection(" Transactions");
    protected SectionStackSection checkImagesSectionStackSection = new SectionStackSection("Check Images");
    protected SectionStackSection idImagesSectionStackSection = new SectionStackSection("ID Images");
//    private TransactionImagesForm transactionImagesForm;
    protected TransactionImage checkFrontImage;
    protected TransactionImage checkBackImage;
    protected TransactionImage idFrontImage;
    protected TransactionImage idBackImage;
//    protected BaseImageContent checkFrontImage;
//    protected BaseImageContent checkBackImage;
// Detail Section Components
    // protected VLayout vDetailLayout = new VLayout();
    //   protected BaseDetailLayout detailWindow;
    // Management Section Components
    protected TransactionSection transactionPanel;
    private Boolean showIDImages = false;

    protected SubTransactionSection subTransactionPanel;
    //protected BaseManagementWindow managementWindow = new BaseManagementWindow();

    /**
     * Constructor
     *
     */
    public TransactionWindow() {
        super();

        setHeight100();
        setWidth100();
        setShowHeader(false);
        setShowEdges(false);
        setStyleName("base-entity-window");
        setBodyStyle("base-entity-window");

        // Sub Transaction Section
        subTransactionPanel = new SubTransactionSection();

        leftSectionStackSection.addItem(subTransactionPanel);

        leftSectionStackSection.setExpanded(true);

        leftSectionStack.setVisibilityMode(VisibilityMode.MULTIPLE);
        leftSectionStack.setWidth("250");
        leftSectionStack.setHeight100();
        leftSectionStack.addSection(leftSectionStackSection);

        // Transaction Section
        rightSectionStackSection.setExpanded(true);
        rightSectionStackSection.setCanCollapse(true);
        // rightSectionStackSection.setShowHeader( false );

        transactionPanel = new TransactionSection();
        transactionPanel.setHeight100();

        rightSectionStackSection.addItem(transactionPanel);

        checkFrontImage = new TransactionImage("checkFrontImage", "Check Front");
        checkBackImage = new TransactionImage("checkBackImage", "Check Back");

        idFrontImage = new TransactionImage("idFrontImage", "ID Front");
        idBackImage = new TransactionImage("idBackImage", "ID Back");

        HLayout checkImagesLayout = new HLayout();
        checkImagesLayout.setAutoDraw(true);
        checkImagesLayout.setAutoHeight();
        checkImagesLayout.setMaxHeight(370);
        checkImagesLayout.addMembers(checkFrontImage, checkBackImage);

        HLayout idImagesLayout = new HLayout();
        idImagesLayout.setAutoDraw(true);
        idImagesLayout.setAutoHeight();
        idImagesLayout.setMaxHeight(370);
        idImagesLayout.addMembers(idFrontImage, idBackImage);

        checkImagesSectionStackSection.setExpanded(false);
        checkImagesSectionStackSection.setCanCollapse(true);
        checkImagesSectionStackSection.addItem(checkImagesLayout);
        checkImagesSectionStackSection.setCanReorder(true);
        checkImagesSectionStackSection.setCanDropBefore(true);
        checkImagesSectionStackSection.setResizeable(true);

        idImagesSectionStackSection.setExpanded(true);
        idImagesSectionStackSection.setCanCollapse(true);
        idImagesSectionStackSection.setCanReorder(true);
        idImagesSectionStackSection.setCanDropBefore(true);
        idImagesSectionStackSection.addItem(idImagesLayout);
        idImagesSectionStackSection.setResizeable(true);
        idImagesSectionStackSection.setHidden(true);
//        imagesSectionStackSection.addItem( checkFrontImage ); 

        rightSectionStack.addSection(rightSectionStackSection);
        rightSectionStack.addSection(checkImagesSectionStackSection);
        rightSectionStack.addSection(idImagesSectionStackSection);
        rightSectionStack.setVisibilityMode(VisibilityMode.MULTIPLE);

        // Right Section                
        hMainLayout.setMargin(1);
        hMainLayout.setMembersMargin(4);
        hMainLayout.addMember(leftSectionStack);
        hMainLayout.addMember(rightSectionStack);

        rightSectionStack.addSectionHeaderClickHandler(new SectionHeaderClickHandler() {
            public void onSectionHeaderClick(SectionHeaderClickEvent event) {
                boolean expanded = rightSectionStackSection.getAttributeAsBoolean("expanded");
                rightSectionStackSection.setExpanded(!expanded);
                rightSectionStackSection.setAttribute("expanded", !expanded);
            }
        });
        addItem(hMainLayout);

        transactionPanel.getFilterForm().addListener(new FilterListenerImp() {
            @Override
            public void FilterActionExecuted() {
                Filter(2);
            }

            public void ImportActionExecuted() {
                report();
            }
        });

        transactionPanel.getPaginationForm().addListener(new PaginationListener() {
            @Override
            public void PreviousActionExecuted() {
                Filter(3);
            }

            @Override
            public void NextActionExecuted() {
                Filter(3);
            }
        });

        transactionPanel.getListGrid().addListener(new ListListener() {
            /**
             * Method to execute when a Select event is fired.
             *
             */
            public void SelectActionExecuted(Record record) {
                record.setAttribute("showIDImages", showIDImages);
                SelectActionExcecuted(record);
            }

            /**
             * Method to execute when a Selection Change event is fired.
             *
             */
            public void SelectionChangeActionExecuted(Record record) {

                if (aux != record.getAttributeAsInt("id")) {
                    record.setAttribute("showIDImages", showIDImages);
                    SelectActionExcecuted(record);
                }
            }

            /**
             * Method to execute when a Data Arrived event is fired.
             *
             */
            public void DataArrivedHandlerExecuted() {
                // Do Nothing
            }
        });

        transactionPanel.addOnShowIDsEventListener(new SimpleListener() {

            @Override
            public void ActionExecuted(Object obj) {
                Utils.debug("Transaction Window -> ActionExecuted");
                OnShowIDs(obj);
            }
        });

        Filter(1);
    }

    public TransactionSection getTransactionSection() {
        return transactionPanel;
    }

    /*
     OPTION:
     1 - onLoad
     2 - onSearch
     3 - onPageChange
     */
    public void Filter(int option) {
        Criteria formCriteria = new Criteria();

        if (option != 1) {
            formCriteria = transactionPanel.getFilterForm().getCriteria();
        }

        formCriteria.addCriteria(transactionPanel.getPaginationForm().getCriteria());

        transactionPanel.getFilterForm().setDisabled(true);

        transactionPanel.getListGrid().invalidateCache();
        transactionPanel.getListGrid().setData(new RecordList());//ISSUE (The call back is not called if the Criteria is the same)
        transactionPanel.getListGrid().fetchData(formCriteria, new DSCallback() {
            public void execute(DSResponse response, Object rawData, DSRequest request) {
                transactionPanel.getPaginationForm().updatePage(response.getAttributeAsInt("totalPages"));
                transactionPanel.getFilterForm().setDisabled(false);
            }
        }, null);
    }

    public void SelectActionExcecuted(final Record record) {
        Utils.debug("SelectActionExcecuted");
        final int id = record.getAttributeAsInt("id");
        final Boolean showIDImages = record.getAttributeAsBoolean("showIDImages");
        Utils.debug("showIDImages = " + showIDImages);
        aux = id;
        Criteria criteria = new Criteria();
        criteria.setAttribute("idTransaction", id);

        subTransactionPanel.getListGrid().invalidateCache();
        subTransactionPanel.getListGrid().setData(new RecordList());//ISSUE (The call back is not called if the Criteria is the same)
        subTransactionPanel.getListGrid().fetchData(criteria, new DSCallback() {
            public void execute(DSResponse response, Object rawData, DSRequest request) {
                String merchant = record.getAttribute("merchant");
                String terminal = record.getAttribute("terminal");
                String clientFirstName = record.getAttribute("clientFirstName");
                if (clientFirstName == null || clientFirstName.equalsIgnoreCase("null")) {
                    clientFirstName = "";
                }
                String clientLastName = record.getAttribute("clientLastName");
                if (clientLastName == null || clientLastName.equalsIgnoreCase("null")) {
                    clientLastName = "";
                }
                String resultMessage = record.getAttribute("resultMessage");

                Record newRecord = new Record();

                newRecord.setAttribute("merchant", merchant);
                newRecord.setAttribute("terminal", terminal);
                newRecord.setAttribute("customer", clientFirstName + " " + clientLastName);
                newRecord.setAttribute("resultMessage", resultMessage);
                subTransactionPanel.getSubForm().loadRecord(newRecord);

            }
        }, null);

        Utils.debug("Before call images...");

        TransactionImagesDS imageDS = new TransactionImagesDS();
        criteria.setAttribute("showIdImages", showIDImages);
        checkImagesSectionStackSection.setTitle("Check Images  (loading)");
        idImagesSectionStackSection.setTitle("ID Images  (loading)");
  
        imageDS.fetchData(criteria, new DSCallback() {
            public void execute(DSResponse response, Object rawData, DSRequest request) {
                Utils.debug("Images -> callback");
                checkImagesSectionStackSection.setTitle("Check Images");
                idImagesSectionStackSection.setTitle("ID Images");
                
                Record record = response.getData()[0];

                Utils.debug("record = " + (record == null ? "NULL" : "HAS VALUE"));

                checkFrontImage.displayImage(record);
                checkBackImage.displayImage(record);

                if (showIDImages) {
                     idFrontImage.displayImage(record);
                     idBackImage.displayImage(record);
 
                     if(record != null){
                         String remainingConvertions = record.getAttributeAsString("remainingConvertions");
                         idImagesSectionStackSection.setTitle("ID Images   ( Remaining ID convertions: " + remainingConvertions + " )");
                     }
                } else {
                    idFrontImage.getLogo().setSrc("http://www.karenika.com/down_digi/empty_overlay_small.png");
                    idBackImage.getLogo().setSrc("http://www.karenika.com/down_digi/empty_overlay_small.png");
                }

                Utils.debug("Images -> end");
            }
        }, null);

    }

    public void report() {
        Criteria formCriteria = transactionPanel.getFilterForm().getCriteria();

        ReportDS ds = new ReportDS();

        ds.setCustomeFetchDataUrl(Properties.TRANSACTIONREPORTS_WS);

        ds.fetchData(formCriteria, new DSCallback() {

            /**
             * Callback to invoke on completion
             *
             * @param response Response sent by the server in response to a
             * DataSource request.
             * @param rawData data
             * @param request Request sent to the server to initiate a
             * DataSource operation.
             */
            public void execute(DSResponse response, Object rawData, DSRequest request) {
                sendURL(response);
            }
        });

    }

    public void sendURL(DSResponse response) {
        if (response.getStatus() == Constants.CODE_SUCCESS) {
            Record responseMap = response.getData()[0];

            String urlSimple = responseMap.getAttributeAsString("url");
            String url = urlSimple;
            Utils.debug("sendURL " + url);
            Window.open(url, "", "");
            Utils.debug("After Window.open...");
        } else {
            SC.warn(I18N.GET.MESSAGE_ERROR_ACTION(), I18N.GET.MESSAGE_ERROR_ACTION());
        }
    }

    public void OnShowIDs(Object obj) {
        Utils.debug("TransactionWindow -> onShowIDs");
        Utils.debug("obj = " + obj);
        Boolean selected = (Boolean) obj;
        Utils.debug("selected = " + selected);
        showIDImages = selected;

        idImagesSectionStackSection.setHidden(!selected);
        Utils.debug("TransactionWindow -> onShowIDs (end)");
    }
}
