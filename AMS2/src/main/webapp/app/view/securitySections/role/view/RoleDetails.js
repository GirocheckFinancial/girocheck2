Ext.define('Admin.view.securitySections.role.view.RoleDetails', {
    extend: 'Admin.base.BaseDetails',
    xtype: 'roleDetails',
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
                            '<td><b>Access All:</b> {[Util.formatBool(values.accessAll)]}</td>',
                            '<td><b>Read Only:</b> {[Util.formatBool(values.readOnly)]}</td>',
                        '</tr>',
                        '<tr>',
                            '<td colspan="3"><b>Description:</b> {description}</td>',
                        '</tr>',
                    '</table>',
                '<div>'
            ].join('') 
            )
        }
    ]
});


