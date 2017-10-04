Ext.define('Admin.cmp.gridFilter.FilterSelectField', {
    extend: 'Ext.form.ComboBox',
    xtype: 'filterSelectField',
    label: '',
    width: '100%',
    hidden: true,
    prefix: '(I)',
    autoSelect:true,
    valueField: 'id',
    style: {
        'padding': '0px',
        'margin': '0px',
        'text-align': 'center'
    },
    valueField:'id',
    displayField:'name',
    value:0,
    store:Ext.create('Ext.data.Store', {
        fields: ['id', 'name'],
        data : [
            {id:0, name:"All"}, 
            {id:1, name:"New Card Load"}, 
            {id:2, name:"Card Reload"},  
            {id: 19, name:"Card to Bank"}
        ]
    }), 
    listeners: { 
        select: function (cmp, rec, idx) {
             cmp.up().up().up().getStore().loadPage(1); 
        }  
    }
});