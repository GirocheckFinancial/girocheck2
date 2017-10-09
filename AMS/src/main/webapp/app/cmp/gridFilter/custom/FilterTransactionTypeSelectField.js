Ext.define('Admin.cmp.gridFilter.custom.FilterTransactionTypeSelectField', {
    extend: 'Admin.cmp.gridFilter.FilterSelectField',
    xtype: 'filterTransactionTypeSelectField',
    store:Ext.create('Ext.data.Store', {
        fields: ['id', 'name'],
        data : [
            {id:0, name:"All"}, 
            {id:1, name:"New Card Load"}, 
            {id:2, name:"Card Reload"},  
            {id: 19, name:"Card to Bank"}
        ]
    }) 
});