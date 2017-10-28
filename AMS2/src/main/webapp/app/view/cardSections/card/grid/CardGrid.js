Ext.define('Admin.view.cardSections.card.grid.CardGrid', {
    extend: 'Admin.base.BasePaginatedGrid',
    alias: 'widget.cardGrid', 
    columns: {
        defaults: {
            width: '25%',
            align: 'center'
        },
        items: [
            {
                xtype:'idGridColumn' 
            },
            {  
                dataIndex: 'cvv',
                hidden:true
            },
            { 
                text: "Masked Number",
                dataIndex: 'maskedNumber'
            },
            {
                text: "User",
                dataIndex: 'user'
            },
             {
                text: "Expiration Date",
                dataIndex: 'expDate',
                xtype: 'dateGridColumn'
            }, 
//            {
//                text: "Program",
//                dataIndex: 'program' 
//            },
            {
                text: "Merchant",
                dataIndex: 'merchant',
                width: '24%'
            }
        ]
    }
});