Ext.define('Admin.view.feeSections.feeAllocationConfig.FeeAllocationConfigContainer', {
    extend: 'Admin.base.BaseContainer', 
    xtype: 'feeAllocationConfigs', 
    filters:'cardProgram.id',
    items: [
        {
          xtype:'feeAllocationConfigGridTab' 
        } 
    ]
});
