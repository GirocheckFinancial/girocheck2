Ext.define('Admin.cmp.gridFilter.FilterBoolean', {
    extend: 'Ext.form.Text',
    xtype: 'filterBoolean',
    width: '100%',
    hidden: true,
    prefix:'(B)',
    style: {
        'padding': '0px',
        'margin': '0px',
        'text-align':'center'
    },
    listeners: {
        specialkey: function (cmp, e) { 
            if (e.getKey() == e.ENTER) {
                 cmp.up().up().up().getStore().loadPage(1); 
            }
        } 
    }
});