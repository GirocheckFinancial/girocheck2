Ext.define('Admin.base.BaseGridTab', {
    extend:'Admin.base.BaseTab',
    xtype: 'baseGridTab',
    cls: 'shadow', 
    entity:null,  
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
