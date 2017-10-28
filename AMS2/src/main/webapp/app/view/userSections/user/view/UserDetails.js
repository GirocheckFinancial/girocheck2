Ext.define('Admin.view.userSections.user.view.UserDetails', {
    extend: 'Admin.base.BaseDetails',
    xtype: 'userDetails',
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
                        '<td><b>Creation Date:</b> {[Util.formatDate(values.creationDate)]}</td>',
                    '</tr>',
                    '<tr>', 
                        '<td><b>Address:</b> {address}</td>',
                        '<td><b>Role:</b><span class="roleId">{role}<span></td>',
                    '</tr>',
                '</table>',
                '<div>'
            ].join('')
                    )
        }
    ]
});
