Ext.define('Admin.feeSections.view.feeAllocationConfig.FeeAllocationConfigSubPanel', {
    extend: 'Admin.base.BaseSubPanel',
    xtype: 'feeAllocationConfigSubPanel',
    config: {
        subPanels: [
            {
                xtype: 'feeAllocationGridTab'
            }
        ]
    }
});
