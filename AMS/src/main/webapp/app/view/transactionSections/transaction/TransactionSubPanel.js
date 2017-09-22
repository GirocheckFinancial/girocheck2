Ext.define('Admin.view.transactionSections.transaction.TransactionSubPanel', {
    extend: 'Admin.base.BaseSubPanel',
    xtype: 'transactionSubPanel',
     config: {
        subPanels: [
            {
                xtype: 'subTransactionGridTab'
            } 
        ]
    }
});
