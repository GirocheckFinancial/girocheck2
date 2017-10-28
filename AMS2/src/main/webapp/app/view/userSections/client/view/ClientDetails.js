Ext.define('Admin.view.clientSections.client.view.ClientDetails', {
    extend: 'Admin.base.BaseDetails',
    xtype: 'clientDetails',
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
                        '<td><b>First Name:</b> {name}</td>',
                        '<td><b>Last Name:</b> {lastName}</td>', 
                    '</tr>',
                    '<tr>',
                        '<td><b>Phone:</b> {phone}</td>',
                        '<td><b>Address:</b> {address}</td>', 
                    '</tr>',
                '</table>',
                '<div>'
            ].join('')
                    )
        }
    ]
});
