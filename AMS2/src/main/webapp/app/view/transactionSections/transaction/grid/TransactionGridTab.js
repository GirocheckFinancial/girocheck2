Ext.define('Admin.view.transactionSections.transaction.grid.TransactionGridTab', {
    extend:'Admin.base.BaseGridTab',
    xtype: 'transactionGridTab', 
    title: 'Transactions',   
    entity:'transaction',
    filters:'transaction.id',
    items: [ 
        {
            xtype: 'transactionGrid' 
        },
        {
            xtype: 'transactionSubPanel'
        }
    ]
});
