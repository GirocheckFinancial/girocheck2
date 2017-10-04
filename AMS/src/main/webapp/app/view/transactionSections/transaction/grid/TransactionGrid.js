Ext.define('Admin.view.transactionSections.tansaction.grid.TransactionGrid', {
    extend: 'Admin.base.BasePaginatedGrid',
    alias: 'widget.transactionGrid',
    columns: {
        defaults: {
            width: '5%',
            align: 'center'
        },
        items: [
            {
                xtype: 'idGridColumn'
            },
            {
                text: "Type", 
                dataIndex: 'transactionType',
                filterType:'filterSelectField',
                xtype: 'customGridColumn',
                data:{ 1: "New Card Load",
                       2:"Card Reload",  
                       3:"Card to Bank"
                   },
                width: '8%'
            },
            {
                xtype: 'creationDateGridColumn',
                dataIndex: 'dateTime',
                text: "Date",
                format: 'm/d/Y H:i:s',
                width: '9%' 
            }, 
            {
                text: "Merchant",
                dataIndex: 'merchant', 
                filter: 'merchant.legalName',
                width: '12%'  
            }, 
            {
                text: "Client",
                dataIndex: 'clientFullName',
                filter: 'client.firstName,client.firstName',
                width: '14%'
            },
            {
                text: "Operation",
                dataIndex: 'operation',
                xtype: 'customGridColumn',
                data:{ '01': "Check",
                       '02':"Cash" 
                   }, 
                width: '7%'
            },
            {
                text: "Card",
                dataIndex: 'card',
                width: '7%'
            },  
            {
                text: "Amount",
                dataIndex: 'amount',
                xtype:'amountGridColumn' 
            },
            {
                text: "Fee",
                dataIndex: 'feeAmmount',
                xtype:'amountGridColumn' 
            },
            {
                text: "Payout",
                dataIndex: 'payoutAmmount',
                xtype:'amountGridColumn' 
            },
            {
                text: "Completed",
                dataIndex: 'completed' 
            },
            {
                text: "R. Code",
                dataIndex: 'resultCode' 
            },
            {
                text: "Result",
                dataIndex: 'resultMessage',
                width: '17%'
            }
        ]
    }
});