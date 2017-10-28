Ext.define('Admin.view.feeSections.feeAllocation.grid.FeeAllocationGridTab', {
    extend:'Admin.base.BaseGridTab',
    xtype: 'feeAllocationGridTab', 
    title: 'Fee Buckets', 
    entity:'feeAllocation', 
    items: [ 
        {
            xtype: 'feeAllocationGrid' 
        } 
    ]
});
