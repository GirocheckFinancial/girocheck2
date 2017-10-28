Ext.define('Admin.view.feeSections.feeAllocationConfig.view.FeeAllocationConfigViewTab', {
    extend:'Admin.base.BaseViewTab',
    xtype: 'feeAllocationConfigViewTab', 
    viewName:'Fee Schedules', 
    entity:'feeAllocationConfig',
    items: [
        {
            xtype: 'feeAllocationConfigDetails'
        },
        {
            xtype: 'feeAllocationConfigEditor' 
        },
        {
            xtype: 'feeAllocationConfigSubPanel'
        }
    ]
});
