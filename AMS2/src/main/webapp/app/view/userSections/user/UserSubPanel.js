Ext.define('Admin.view.userSections.user.UserSubPanel', {
    extend: 'Admin.base.BaseSubPanel',
    xtype: 'userSubPanel',
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
