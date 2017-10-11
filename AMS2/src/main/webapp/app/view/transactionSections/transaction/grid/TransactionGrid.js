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
                filterType: 'filterTransactionTypeSelectField',
                xtype: 'customGridColumn',
                data: {1: "New Card Load",
                    2: "Card Reload",
                    3: "Card to Bank"
                },
                width: '8%'
            },
            {
                xtype: 'creationDateGridColumn',
                dataIndex: 'dateTime',
                text: "Date",
                format: 'm/d/Y H:i:s',
                width: '9%',
                filterType: 'filterDate'
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
                filter: 'client.firstName,client.lastName',
                width: '14%'
            },
            {
                text: "Operation",
                dataIndex: 'operation',
                xtype: 'customGridColumn',
                filterType: 'filterOperationSelectField',
                data: {'01': "Check",
                    '02': "Cash"
                },
                width: '7%'
            },
            {
                text: "Card",
                dataIndex: 'card',
                filter: 'card.maskCardNumber',
                width: '7%'
            },
            {
                text: "Amount",
                dataIndex: 'amount',
                xtype: 'amountGridColumn',
                filter: 'ammount',
                filterType: 'filterNumber'
            },
            {
                text: "Fee",
                dataIndex: 'feeAmmount',
                xtype: 'amountGridColumn',
                filterType: 'filterNumber'
            },
            {
                text: "Payout",
                dataIndex: 'payoutAmmount',
                xtype: 'amountGridColumn',
                filterType: 'filterNumber'
            },
            {
                text: "Completed",
                dataIndex: 'completed',
                filter: 'transactionFinished',
                filterType: 'filterBoolean'
            },
            {
                text: "R. Code",
                dataIndex: 'resultCode', 
                filterType: 'filterInteger'
            },
            {
                text: "Result",
                dataIndex: 'resultMessage',
                width: '17%'
            }
        ]
    }
});