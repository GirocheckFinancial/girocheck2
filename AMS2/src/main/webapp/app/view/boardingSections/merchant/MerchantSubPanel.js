Ext.define('Admin.view.boardingSections.merchant.MerchantSubPanel', {
    extend: 'Admin.base.BaseSubPanel',
    xtype: 'merchantSubPanel',
    config: {
        subPanels: [
            {
                xtype: 'transactionGridTab',
                openViewFilters: 'transaction.id'
            },
            {
                title:'Issued Cards',
                xtype: 'cardGridTab',
                openViewFilters: 'merchant.id'
            },
//            {
//                xtype: 'accountReferenceGridTab',
//                subPanelsToShow: ['transactionFeeAllocationGridTab']
//            },
            {
                xtype: 'userMerchantGridTab'
            },
            {
                xtype: 'terminalGridTab',
                openViewFilters: 'terminal.id'
            }
        ]
    }
});
