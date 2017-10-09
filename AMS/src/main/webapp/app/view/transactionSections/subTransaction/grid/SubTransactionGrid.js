Ext.define('Admin.view.transactionSections.subTansaction.grid.SubTransactionGrid', {
    extend: 'Admin.base.BasePaginatedGrid',
    alias: 'widget.subTransactionGrid',
    entity: 'subTransaction',
    loadAdditionalInfoWhenDblClick: false,
    columns: {
        defaults: {
            width: '16%',
            align: 'center'
        },
        items: [
            {
                xtype: 'idGridColumn'
            },
            {
                text: "Type",
                dataIndex: 'type',
                width: '19%', 
                xtype: 'customGridColumn',
                data: {
                    3: "Personal Info",
                    42: "Send Single ICL",
                    14: "Card Activation",
                    15: "Card Personalization",
                    17: "Card Load",
                    18: "Balance Inquiry",
                    19: "Card to Bank",
                    16: "Card Validation",
                    20: "Card Holder Validation",
                    23: "Cash to Card",
                    44: "Restore Card",
                    39: "Check Process",
                    40: "Authentication",
                    41: "Reverse Request",
                    43: "Check Info",
                    43: "New Branch",
                    43: "Post Transaction",
                }
            }, 
            {
                text: "Host",
                dataIndex: 'host', 
                xtype: 'customGridColumn',
                data: {
                    1: "iStream",
                    8: "iStream",
                    2: "Tecnicard",
                    3: "Order Express",
                    6: "Westech",
                    7: "Certegy",
                    9: "Compliance" 
                } 
            },
            {
                text: "Error Code",
                dataIndex: 'errorCode' 
            },
            {
                text: "Result Code",
                dataIndex: 'resultCode',
                filterType: 'filterInteger'
            }, 
            {
                text: "result Message", 
                dataIndex: 'resultMessage',
                 width: '32%'
            } 
        ]
    }
});