Ext.define('Admin.view.boardingSections.merchant.MerchantContainer', {
    extend: 'Admin.base.BaseContainer', 
    xtype: 'merchant',  
    items: [
        {
          xtype:'merchantViewTab', 
          filters:'merchant.id',
          loadFilter:'id',          //load merchants where id
          loadFilterValue:'entityId'//be equal to the Principal's entityId
        } 
    ]
});
