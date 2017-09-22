Ext.define('Admin.view.cardSections.card.CardSubPanel', {
    extend: 'Admin.base.BaseSubPanel',
    xtype: 'cardSubPanel',
    config: {
        subPanels: [
            {
                xtype: 'transactionGridTab'
            }
        ]
    }
});
