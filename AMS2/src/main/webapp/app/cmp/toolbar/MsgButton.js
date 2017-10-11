Ext.define('Admin.cmp.toolbar.MsgButton', {
    extend: 'Ext.button.Button',
    xtype: 'msgButton',
    iconCls: 'x-fa fa-envelope-o',
    openView:'createMessageSingleView',
    listeners: {
        click: 'openSingleView'
    }
});