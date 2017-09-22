Ext.define('Admin.view.feeSections.feeAllocationConfig.grid.FeeAllocationConfigGrid', {
    extend: 'Admin.base.BasePaginatedGrid',
    alias: 'widget.feeAllocationConfigGrid',
    loadAdditionalInfoWhenDblClick: true,
    columns: {
        defaults: {
            width: '50%',
            align: 'center'
        },
        items: [
            {
                xtype:'idGridColumn' 
            },
            {
                text: "Name",
                dataIndex: 'name'
            },
            {
                xtype: 'lastUpdateGridColumn',
                width: '49%'
            }
        ]
    }
});