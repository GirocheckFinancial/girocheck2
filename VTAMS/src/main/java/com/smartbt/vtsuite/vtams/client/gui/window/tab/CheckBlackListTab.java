/*
 ** File: ClientTab.java
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
package com.smartbt.vtsuite.vtams.client.gui.window.tab;

import com.smartbt.vtsuite.vtams.client.classes.Properties;
import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseFilterForm;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseTab;
import com.smartbt.vtsuite.vtams.client.gui.component.BaseSelectItem;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.CheckBlacklistRuleDS;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.ClientDS;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.DataSourceBuilder;
import com.smartbt.vtsuite.vtams.client.gui.listener.EditorListener;
import com.smartbt.vtsuite.vtams.client.gui.listener.FilterListenerImp;
import com.smartbt.vtsuite.vtams.client.gui.listener.ListListener;
import com.smartbt.vtsuite.vtams.client.gui.listener.PaginationListener;
import com.smartbt.vtsuite.vtams.client.gui.window.editor.ChangePasswordEditor;
import com.smartbt.vtsuite.vtams.client.gui.window.editor.CheckRuleEditor;
import com.smartbt.vtsuite.vtams.client.gui.window.filter.ClientFilterForm;
import com.smartbt.vtsuite.vtams.client.gui.window.list.CheckBlackListRuleGrid;
import com.smartbt.vtsuite.vtams.client.gui.window.list.ClientListGrid;
import com.smartbt.vtsuite.vtams.client.utils.Utils;
import com.smartbt.vtsuite.vtcommon.Constants;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.SpacerItem;
import com.smartgwt.client.widgets.tab.events.TabSelectedEvent;
import com.smartgwt.client.widgets.tab.events.TabSelectedHandler;

/**
 * The Client Tab
 *
 * @author Roberto Rodriguez
 */
public class CheckBlackListTab extends BaseTab {

    private ClientFilterForm filterForm;
    private CheckBlackListRuleGrid listGrid;
    private Integer selectedId = 0;
    private Record selectedRecord = null;
    private CheckRuleEditor checkRuleEditor;

    /**
     * Constructor
     *
     * @param type
     * @param idEntity
     */
    public CheckBlackListTab() {
        super("Check Black List Rules");
        Utils.debug("CheckBlackListTab - 1");
        filterForm = new ClientFilterForm() {
            public FormItem[] getFormFields() {
                SpacerItem space = new SpacerItem();
                space.setWidth(40);
                return new FormItem[]{filterButton, searchText, space, addButton, updateButton, deleteButton};
            }
        };
        Utils.debug("CheckBlackListTab - 2");
        listGrid = new CheckBlackListRuleGrid();
        Utils.debug("CheckBlackListTab - 3");
        addTabSelectedHandler(new TabSelectedHandler() {
            /**
             * Method to execute when the tab is selected.
             *
             * @param event the event
             */
            public void onTabSelected(TabSelectedEvent event) {
                Filter();
            }
        });
        Utils.debug("CheckBlackListTab - 4");
        filterForm.addListener(new FilterListenerImp() {
            @Override
            public void FilterActionExecuted() {
                Filter();
            }

            @Override
            public void AddActionExecuted() {
                Add();
            }

            @Override
            public void UpdateActionExecuted() {
                Update(listGrid.getSelectedRecord());
            }

            @Override
            public void ImportActionExecuted() {
//                importClients();
            }

            @Override
            public void DeleteActionExecuted() {
                Delete();
            }

            @Override
            public void DeleteAllActionExecuted() {
//                DeleteAll();
            }
        });
        Utils.debug("CheckBlackListTab - 5");
        paginationForm.addListener(new PaginationListener() {
            public void PreviousActionExecuted() {
                Filter();
            }

            public void NextActionExecuted() {
                Filter();
            }
        });
        Utils.debug("CheckBlackListTab - 6");
        listGrid.addListener(new ListListener() {

            public void SelectActionExecuted(Record record) {

            }

            public void SelectionChangeActionExecuted(Record record) {
                //Update(record);
                Integer id = record.getAttributeAsInt("id");
                Utils.debug("id = " + id);
                selectedId = id;
                filterForm.getDeleteButton().setDisabled(false);
                filterForm.getUpdateButton().setDisabled(false);
            }

            public void DataArrivedHandlerExecuted() {
                filterForm.getDeleteButton().setDisabled(true);
                filterForm.getUpdateButton().setDisabled(true);
                selectedId = 0;
                selectedRecord = null;
            }
        });
        Utils.debug("CheckBlackListTab - 7");
        checkRuleEditor = new CheckRuleEditor();
        Utils.debug("CheckBlackListTab - 8");
        checkRuleEditor.addListener(new EditorListener() {
            /**
             * Method to execute when a Save event is fired.
             *
             */
            public void SaveActionExecuted() {
                Save();
            }

            /**
             * Method to execute when a Close event is fired.
             *
             */
            public void CloseActionExecuted() {
                checkRuleEditor.hide();
            }
        });
        Utils.debug("CheckBlackListTab - 9");
        filterLayout.addMember(filterForm);
        filterLayout.addMember(paginationForm);
        listLayout.addMember(listGrid);
        Utils.debug("CheckBlackListTab - 10");
    }

    /**
     * Filter method
     *
     */
    public void Filter() {
        Utils.debug("Filter");
        Criteria formCriteria = filterForm.getValuesAsCriteria();
        //paginationForm.setCriteria(formCriteria);

        formCriteria.addCriteria("pageNumber", paginationForm.getRequestPageNumber());
        formCriteria.addCriteria("rowsPerPage", paginationForm.getRowsPerPage());

        filterForm.setDisabled(true);

        listGrid.invalidateCache();
        listGrid.setData(new RecordList());//ISSUE (The call back is not called if the Criteria is the same)
        listGrid.fetchData(formCriteria, new DSCallback() {
            public void execute(DSResponse response, Object rawData, DSRequest request) {
                paginationForm.updatePage(response.getAttributeAsInt("totalPages"));
                filterForm.setDisabled(false);
            }
        }, null);
    }

    /**
     * Update method
     *
     * @param record the record to update
     */
    public void Update(Record record) {
        Utils.debug("Update record = " + (record == null));
        if (record == null) {
            return;
        }

        Integer id = record.getAttributeAsInt("id");
        Utils.debug("id = " + id);
        selectedId = id;
        selectedRecord = record;
        filterForm.getDeleteButton().setDisabled(false);
        filterForm.getUpdateButton().setDisabled(false);

        checkRuleEditor.addRecord(selectedRecord);
        checkRuleEditor.show();
    }

    public void Add() {
        Utils.debug("Add -> selectedRecord = " + (selectedRecord != null));
        checkRuleEditor.Reset();
        checkRuleEditor.show();
    }

    public void Delete() {
        Utils.debug("Delete -> selectedId = " + selectedId);
        CheckBlacklistRuleDS ds = new CheckBlacklistRuleDS();

        Record record = new Record();
        record.setAttribute("id", selectedId);

        ds.setAddDataURL(Properties.DELETE_CHECK_BLACKLIST_RULES_WS);

        ds.addData(record, new DSCallback() {
            public void execute(DSResponse response, Object rawData, DSRequest request) {

                if (response.getStatus() == Constants.CODE_SUCCESS) {
                    Filter();
                } else {
                    Utils.debug("Delete :: comeBack -> failed");
                }
            }
        });
    }

    public void Save() {
        Utils.debug("Save");
        Record recordToSave = checkRuleEditor.getRecord();

        recordToSave.setAttribute("id", selectedId);
        Utils.debug("selectedId = " + selectedId);

        checkRuleEditor.getDataForm().getDataSource().addData(recordToSave, new DSCallback() {

            public void execute(DSResponse response, Object rawData, DSRequest request) {
                if (response.getStatus() == Constants.CODE_SUCCESS) {
                    Filter();

                    checkRuleEditor.hide();
                } else {
                    SC.warn(I18N.GET.WINDOW_ERROR_TITLE(), I18N.GET.MESSAGE_ERROR_ACTION());
                }
            }
        });
    }
}
