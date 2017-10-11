Ext.define('Admin.view.feeSections.feeAllocationConfig.view.FeeAllocationConfigEditor', {
    extend: 'Admin.base.BaseEditor',
    alias: 'widget.feeAllocationConfigEditor', 
    items: [
        {
            items: [
                {
                    fieldLabel: 'Name',
                    name: 'name'
                }  
            ]
        }, 
        {
            items: [ 
                {
                    xtype: 'lastUpdateField' 
                },
                {
                    xtype: 'idField' 
                }
            ]
        }
    ]
});
