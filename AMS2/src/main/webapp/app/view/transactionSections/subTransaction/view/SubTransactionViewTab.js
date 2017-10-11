Ext.define('Admin.view.transactionSections.subTransaction.view.subTransactionViewTab', {
    extend:'Admin.base.BaseViewTab',
    xtype: 'subTransactionViewTab', 
    staticTabTitle:'Transaction Fee Allocation', 
    entity:'subTransaction',
    items: [
        {
            xtype: 'subTransactionDetails'
        } 
    ]
});
