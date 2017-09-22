Ext.define('Admin.view.transactionSections.subTransaction.grid.subTransactionGridTab', {
    extend:'Admin.base.BaseGridTab',
    xtype: 'subTransactionGridTab', 
    title: 'Sub-Transactions',   
    entity:'subTransaction',
    items: [ 
        {
            xtype: 'subTransactionGrid' 
        }
    ]
});
