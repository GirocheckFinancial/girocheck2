Ext.define('Admin.view.transactionSections.subTansaction.grid.SubTransactionGrid', {
    extend: 'Admin.base.BasePaginatedGrid',
    alias: 'widget.subTransactionGrid',
    entity: 'subTransaction',
    loadAdditionalInfoWhenDblClick: false,
    columns: {
        defaults: {
            width: '20%',
            align: 'center'
        },
        items: [
            {
                xtype: 'idGridColumn'
            },
            {
                text: "Type",
                dataIndex: 'type' 
            },
            {
                text: "Result Code",
                dataIndex: 'resultCode' 
            },
            {
                text: "Host Code",
                dataIndex: 'hostCode' 
            },
            {
                text: "result Message", 
                dataIndex: 'resultMessage',
                 width: '39%'
            } 
        ]
    }
});