Ext.define('Admin.view.feeSections.feeAllocation.grid.FeeAllocationGrid', {
    extend: 'Admin.base.BasePaginatedGrid',
    alias: 'widget.feeAllocationGrid',   
    columns: {
        defaults: {
            width: '33%',
            align: 'center'
        },
        items: [{
                xtype:'idGridColumn' 
            },
            {
                text: "Allocator",
                dataIndex: 'feeAllocator',
                filter:'feeAllocator.name'
            },
            {
                text: "Percent",
                dataIndex: 'percent' 
            },
            {
                text: "Fixed Rate",
                dataIndex: 'fixedRate' 
            },  
            {
                dataIndex:'feeAllocationConfigId',
                hidden: true
            }
        ]
    }
});