Ext.define('Admin.view.boardingSections.merchant.view.MerchantViewTab', {
    extend:'Admin.base.BaseViewTab',
    xtype: 'merchantViewTab', 
    viewName:'Merchant', 
    entity:'merchant',
    items: [
        {
            xtype: 'merchantDetails'
        },
        {
            xtype: 'merchantEditor' 
        }, 
        {
            xtype: 'merchantSubPanel'
        }
    ]
});
