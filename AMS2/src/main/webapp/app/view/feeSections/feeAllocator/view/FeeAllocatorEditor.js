Ext.define('Admin.view.feeSections.feeAllocator.view.FeeAllocatorEditor', {
    extend: 'Admin.base.BaseEditor',
    alias: 'widget.feeAllocatorEditor', 
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
                } 
            ]
        }, 
        {
            items: [ 
                {
                    xtype: 'baseCheckboxField',
                    fieldLabel: 'Platform Level',
                    name: 'isPlatformLevel', 
                    value:false
                },
                {
                    xtype: 'idField' 
                }
            ]
        }
    ]
});
