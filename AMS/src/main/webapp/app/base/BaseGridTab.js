Ext.define('Admin.base.BaseGridTab', {
    extend:'Admin.base.BaseTab',
    xtype: 'baseGridTab',
    cls: 'shadow', 
    entity:null,
    requires: [ 
        'Admin.cmp.toolbar.ShowFiltersButton',
        'Admin.cmp.toolbar.CreateButton',
        'Admin.cmp.toolbar.EditGridRowButton',
        'Admin.cmp.toolbar.PdfReportButton',
        'Admin.cmp.toolbar.DeleteButton' 
    ],   
    tbar: [ 
        '->',
        {
            xtype: 'createButton'
        },
        {
            xtype: 'editGridRowButton' 
        },
        {
            xtype: 'deleteButton'
        },
        {
            xtype: 'pdfReportButton'
        },
        {
            xtype: 'showFilters' 
        }
    ] 
});
