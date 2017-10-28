Ext.define('Admin.view.boardingSections.terminal.view.TerminalViewTab', {
    extend: 'Admin.base.BaseViewTab',
    xtype: 'terminalViewTab',
    viewName: 'Terminal',
    propertyTitleName: 'serialNumber',
    entity: 'terminal',
    items: [
        {
            xtype: 'terminalDetails'
        },
        {
            xtype: 'terminalEditor'
        }
    ]
});


