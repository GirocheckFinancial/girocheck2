Ext.define('Admin.cmp.toolbar.CreateButton', {
    extend: 'Ext.button.Button',
    xtype: 'createButton',
    iconCls: 'x-fa fa-folder-o',
    listeners: {
        click: 'editGridRow'
    }
});