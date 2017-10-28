Ext.define('Admin.view.securitySections.rolePageAccess.view.RolePageAccessDetails', {
    extend:'Admin.base.BaseDetails',
    xtype: 'rolePageAccessDetails', 
    items: [
        {
            xtype: 'component', 
            flex: 1,
            cls: 'single-mail-email-subject',
            data: {  
            },
            tpl: new Ext.XTemplate([
                '<div style="padding:20px">', 
                '<table cellpadding="4" style="width:100%;cellspacing">',
                    '<tr>',
                        '<td><b>Role:</b> {role}</td>',
                        '<td><b>Page Access:</b> {pageAccess}</td>',
                    '</tr>', 
                '</table>',
                '<div>'
            ].join('') 
            )
        }
    ]
});


