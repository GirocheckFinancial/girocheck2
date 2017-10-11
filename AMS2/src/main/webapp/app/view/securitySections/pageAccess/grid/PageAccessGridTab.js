Ext.define('Admin.view.securitySections.pageAccess.grid.PageAccessGridTab', {
    extend:'Admin.base.BaseGridTab',
    xtype: 'pageAccessGridTab', 
    title: 'Page Access',    
    entity:'pageAccess',
    filters:'pageAccess.id', 
    items: [ 
        {
            xtype: 'pageAccessGrid' 
        }
    ]
});


