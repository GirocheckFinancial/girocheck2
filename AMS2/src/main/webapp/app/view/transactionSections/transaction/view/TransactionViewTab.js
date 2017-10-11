Ext.define('Admin.view.transactionSections.transaction.view.TransactionViewTab', {
    extend:'Admin.base.BaseViewTab',
    xtype: 'transactionViewTab', 
    viewName:'Transaction', 
    propertyTitleName:'transactionType',
    entity:'transaction',
    staticFilters:'transaction.id',
    items: [
        {
            xtype: 'transactionDetails'
        }, 
        {
            xtype: 'transactionSubPanel'
        }
    ]
});
