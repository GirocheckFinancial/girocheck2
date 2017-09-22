Ext.define('Admin.view.boardingSections.terminal.TerminalSubPanel', {
    extend: 'Admin.base.BaseSubPanel',
    xtype: 'terminalSubPanel',
    config: {
        subPanels: [
            {
                xtype: 'transactionGridTab',
                openViewFilters: 'transaction.id'
            } 
        ]
    }
});
