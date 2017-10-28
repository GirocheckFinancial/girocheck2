Ext.define('Admin.view.clientSections.client.view.ClientViewTab', {
    extend:'Admin.base.BaseViewTab',
    xtype: 'clientViewTab', 
    viewName:'Client', 
    entity:'client',
    items: [
        {
            xtype: 'clientDetails'
        },
        {
            xtype: 'clientEditor' 
        } 
    ]
});
