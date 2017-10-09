Ext.define('Admin.cmp.gridFilter.custom.FilterOperationSelectField', {
    extend: 'Admin.cmp.gridFilter.FilterSelectField',
    xtype: 'filterOperationSelectField',
    prefix: '(S)',
    store:Ext.create('Ext.data.Store', {
        fields: ['id', 'name'],
        data : [
            {id:0, name:"All"}, 
            {id:'01', name:"Check"}, 
            {id:'02', name:"Cash"} 
        ]
    }) 
});