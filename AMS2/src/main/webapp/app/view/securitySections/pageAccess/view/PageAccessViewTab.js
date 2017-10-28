Ext.define('Admin.view.securitySections.pageAccess.view.PageAccessViewTab', {
    extend: 'Admin.base.BaseViewTab',
    xtype: 'pageAccessViewTab',
    viewName: 'Page Access',
    entity: 'pageAccess',
    items: [
        {
            xtype: 'pageAccessDetails'
        },
        {
            xtype: 'pageAccessEditor'
        }
    ]
});


