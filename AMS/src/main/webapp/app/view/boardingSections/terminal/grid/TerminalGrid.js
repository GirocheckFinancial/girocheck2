Ext.define('Admin.view.boardingSections.terminal.grid.TerminalGrid', {
    extend: 'Admin.base.BasePaginatedGrid',
    alias: 'widget.terminalGrid',
    columns: {
        defaults: {
            width: '25%',
            align: 'center'
        },
        items: [
            {
                xtype: 'idGridColumn'
            },
            {
                text: "Serial Number",
                dataIndex: 'serialNumber'
            },
            {
                text: "Username",
                dataIndex: 'username'
            },
            {
                text: "Merchant",
                dataIndex: 'merchant'
            },
            {
                xtype: 'creationDateGridColumn',
                width: '24%'
            }
        ]
    }
});


