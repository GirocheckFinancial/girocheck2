Ext.define('Admin.view.feeSections.feeAllocationConfig.grid.FeeAllocationConfigGridTab', {
    extend:'Admin.base.BaseGridTab',
    xtype: 'feeAllocationConfigGridTab', 
    title: 'Fee Schedules', 
    filters:'feeAllocationConfig.id',  
    entity:'feeAllocationConfig',
    items: [ 
        {
            xtype: 'feeAllocationConfigGrid',
            width:'100%'
        }, 
        {
            xtype: 'feeAllocationConfigSubPanel'
        }
    ]
});
