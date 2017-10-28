Ext.define('Admin.view.clientSections.client.view.MobileClientViewTab', {
    extend:'Admin.base.BaseViewTab',
    xtype: 'mobileClientViewTab', 
    viewName:'MobileClient', 
    entity:'user',
    items: [
        {
            xtype: 'mobileClientDetails'
        },
        {
            xtype: 'mobileClientEditor' 
        } 
    ]
});
