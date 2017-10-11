Ext.define('Admin.view.feeSections.feeAllocation.view.FeeAllocationViewTab', {
    extend: 'Admin.base.BaseViewTab',
    xtype: 'feeAllocationViewTab',
    viewName: 'Fee Bucket',
    entity: 'feeAllocation',
    items: [
        {
            xtype: 'feeAllocationDetails'
        },
        {
            xtype: 'feeAllocationEditor'
        }
    ],
    setTabTitle: function (newData) {   //this function should be overriden when required
        var me = this,
                data = newData || me.config.data,
                title = '';

        if (data && data.id) {
            title = 'Fee Range';
        } else {
            title = 'New ' + me.viewName;
        }

        me.setTitle(title);
    }
});
