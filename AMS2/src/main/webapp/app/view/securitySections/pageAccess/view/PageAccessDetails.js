Ext.define('Admin.view.securitySections.pageAccess.view.PageAccessDetails', {
    extend: 'Admin.base.BaseDetails',
    xtype: 'pageAccessDetails',
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
                            '<td><b>Name:</b> {name}</td>',
                            '<td><b>Id Page:</b> {idPage}</td>',
                        '</tr>',
                    '</table>',
                '<div>'
            ].join('') 
            )
        }
    ]
});

