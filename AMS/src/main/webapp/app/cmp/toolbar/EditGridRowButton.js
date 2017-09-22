Ext.define('Admin.cmp.toolbar.EditGridRowButton', {
    extend: 'Ext.button.Button',
    xtype: 'editGridRowButton',
     iconCls: 'x-fa fa-folder-open-o',
    listeners: {
        click: 'editGridRow'
    }
});