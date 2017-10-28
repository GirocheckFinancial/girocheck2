Ext.define('Admin.view.boardingSections.terminal.view.TerminalDetails', {
    extend: 'Admin.base.BaseDetails',
    xtype: 'terminalDetails',
    items: [
        {
            xtype: 'component',
            flex: 1,
            cls: 'single-mail-email-subject',
            data: {
            },
            tpl: new Ext.XTemplate([
                '<div style="padding:20px">',
                    '<table cellpadding="10" style="width:100%;cellspacing">',
                        '<tr>',
                            '<td><b>Serial Number:</b> {serialNumber}</td>',
                            '<td><b>Merchant:</b> {merchant}</td>',
                        '</tr>',
                        '<tr>',
                            '<td><b>Username:</b> {username}</td>',
                            '<td><b>Creation Date:</b> {[Util.formatDate(values.creationDate)]}</td>',
                        '</tr>',
                    '</table>',
                '<div>'
            ].join('')
            )
        }
    ]
});
