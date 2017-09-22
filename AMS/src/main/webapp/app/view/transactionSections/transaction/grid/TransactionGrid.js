Ext.define('Admin.view.transactionSections.tansaction.grid.TransactionGrid', {
    extend: 'Admin.base.BasePaginatedGrid',
    alias: 'widget.transactionGrid',
    columns: {
        defaults: {
            width: '12%',
            align: 'center'
        },
        items: [
            {
                xtype: 'idGridColumn'
            },
            {
                text: "Type",
                dataIndex: 'transactionType'
            },
            {
                xtype: 'creationDateGridColumn',
                text: "Date",
                format: 'm/d/Y' 
            },
            {
                xtype: 'creationDateGridColumn',
                text: "Time",
                format: 'H:i:s' 
            },
            {
                text: "Merchant",
                dataIndex: 'merchant', 
                filter: 'merchant.name' 
            }, 
            {
                text: "Client",
                dataIndex: 'user',
                filter: 'user.name'
            },
            {
                text: "Card",
                dataIndex: 'maskedCard',
                width: '14%'
            },  
            {
                text: "Amount",
                dataIndex: 'amount',
                xtype:'amountGridColumn',
                width: '8%'
            },
            {
                text: "Fee",
                dataIndex: 'fee',
                xtype:'amountGridColumn',
                width: '8%'
            },
            {
                text: "Result",
                dataIndex: 'resultMessage',
                width: '9%'
            }
        ]
    }
});