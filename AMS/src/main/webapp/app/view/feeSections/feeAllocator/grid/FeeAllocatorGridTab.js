Ext.define('Admin.view.feeSections.feeAllocator.grid.FeeAllocatorGridTab', {
    extend:'Admin.base.BaseGridTab',
    xtype: 'feeAllocatorGridTab', 
    title: 'Fee Allocators', 
    filters:'feeAllocator.id',  
    entity:'feeAllocator',
    items: [ 
        {
            xtype: 'feeAllocatorGrid',
            width:'100%'
        }
    ]
});
