Ext.define('Admin.view.clientSections.client.grid.MobileClientGridTab', {
    extend:'Admin.base.BaseGridTab',
    xtype: 'mobileClientGridTab', 
    title: 'Mobile Clients',  
    entity:'mobileClients',
    filters:'client.id',
    items: [ 
        {
            xtype: 'mobileClientGrid' 
        }, 
        {
            xtype: 'mobileClientSubPanel'
        }
    ]
});
