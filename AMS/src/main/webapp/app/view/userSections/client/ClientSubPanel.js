Ext.define('Admin.view.clientSections.client.ClientSubPanel', {
    extend: 'Admin.base.BaseSubPanel',
    xtype: 'clientSubPanel',
    config: {
        subPanels: [ 
            {
                xtype: 'transactionGridTab'
            },
            {
                xtype: 'cardGridTab'
            }
        ]
    }
});
