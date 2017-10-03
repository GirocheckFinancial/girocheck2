Ext.define('Admin.cmp.gridFilter.FilterSelectField', {
    extend: 'Ext.form.ComboBox',
    xtype: 'filterSelectField',
    label: '',
    width: '100%',
    hidden: true,
    prefix: '(S)',
    autoSelect:true,
    style: {
        'padding': '0px',
        'margin': '0px',
        'text-align': 'center'
    },
    store:['android','ios'],
    listeners: {
//        specialkey: function (cmp, e) { 
//            if (e.getKey() == e.ENTER) {
//                 cmp.up().up().up().getStore().loadPage(1); 
//            }
//        } 
    }
});