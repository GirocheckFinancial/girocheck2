Ext.define('Admin.view.boardingSections.merchant.grid.MerchantGridTab', {
    extend:'Admin.base.BaseGridTab',
    xtype: 'merchantGridTab', 
    title: 'Merchants',    
    entity:'merchant',
    filters:'merchant.id', 
    items: [ 
        {
            xtype: 'merchantGrid' 
        }, 
        {
            xtype: 'merchantSubPanel'
        }
    ]
});
