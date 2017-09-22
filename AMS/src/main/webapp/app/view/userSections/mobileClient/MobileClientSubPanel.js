Ext.define('Admin.view.clientSections.client.MobileClientSubPanel', {
    extend: 'Admin.base.BaseSubPanel',
    xtype: 'mobileClientSubPanel',
    config: {
        subPanels: [
            {
                xtype: 'cardGridTab'
            },
            {
                xtype: 'transactionGridTab'
            }
        ]
    }
});
