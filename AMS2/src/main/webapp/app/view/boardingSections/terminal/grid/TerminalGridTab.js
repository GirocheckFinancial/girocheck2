Ext.define('Admin.view.boardingSections.terminal.grid.TerminalGridTab', {
    extend:'Admin.base.BaseGridTab',
    xtype: 'terminalGridTab', 
    title: 'Terminals',    
    entity:'terminal',
    filters:'terminal.id', 
    items: [ 
        {
            xtype: 'terminalGrid' 
        },
        {
            xtype:'terminalSubPanel'
        }
    ]
});


