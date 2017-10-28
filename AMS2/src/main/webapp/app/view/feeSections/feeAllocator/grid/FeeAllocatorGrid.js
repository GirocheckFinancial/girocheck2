Ext.define('Admin.view.feeSections.feeAllocator.grid.FeeAllocatorGrid', {
    extend: 'Admin.base.BasePaginatedGrid',
    alias: 'widget.feeAllocatorGrid',
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
               xtype:'nameGridColumn'
            },
            {
                xtype: 'lastUpdateGridColumn',
                width: '49%'
            }
        ]
    }
});