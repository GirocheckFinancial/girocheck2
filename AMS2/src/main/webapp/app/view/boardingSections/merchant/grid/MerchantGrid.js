Ext.define('Admin.view.boardingSections.merchant.grid.MerchantGrid', {
    extend: 'Admin.base.BasePaginatedGrid',
    alias: 'widget.merchantGrid',
    columns: {
        defaults: {
            width: '33%',
            align: 'center'
        },
        items: [
            {
                xtype: 'idGridColumn'
            },
            {
                xtype: 'nameGridColumn'
            },
            {
                xtype: 'creationDateGridColumn'
            },
            {
                text: "Address",
                dataIndex: 'address'
            }
        ]
    }
});