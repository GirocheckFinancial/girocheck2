Ext.define('Admin.view.feeSections.feeAllocation.view.FeeAllocationEditor', {
    extend: 'Admin.base.BaseEditor',
    alias: 'widget.feeAllocationEditor',
    items: [
        {
            items: [
                {
                    fieldLabel: 'Allocator',
                    xtype: 'baseSelectField',
                    name: 'feeAllocatorId',
                    url: 'feeAllocator'
                },
                {
                    xtype: 'lastUpdateField' 
                }
            ]
        },
          {
            items: [ 
                {
                    fieldLabel: 'Percent',
                    name: 'percent',
                    xtype: 'baseDoubleField'
                } 
            ]
        },
        {
            items: [
                {
                    fieldLabel: 'Fixed Rate',
                    name: 'fixedRate',
                    xtype: 'baseDoubleField',
                    value:'0'
                },
                {
                    xtype: 'idField'
                },
                {
                    xtype: 'idField',
                    name: 'feeAllocationConfigId'
                }
            ]
        }
    ],
    initData: function () {
        return {
            feeAllocationConfigId: this.up().superEntityId
        };
    } 
});
