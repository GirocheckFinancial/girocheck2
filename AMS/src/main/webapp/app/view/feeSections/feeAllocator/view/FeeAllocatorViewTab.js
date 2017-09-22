Ext.define('Admin.view.feeSections.feeAllocator.view.FeeAllocatorViewTab', {
    extend:'Admin.base.BaseViewTab',
    xtype: 'feeAllocatorViewTab', 
    viewName:'Fee Buckets', 
    entity:'feeAllocator',
    items: [
        {
            xtype: 'feeAllocatorDetails'
        },
        {
            xtype: 'feeAllocatorEditor' 
        } 
    ]
});
