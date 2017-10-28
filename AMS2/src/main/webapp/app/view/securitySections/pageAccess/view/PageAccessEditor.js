Ext.define('Admin.view.securitySections.pageAccess.view.PageAccessEditor', {
    extend: 'Admin.base.BaseEditor',
    alias: 'widget.pageAccessEditor',
    items: [
        {
            items: [
                {
                    xtype: 'idField'
                },
                {
                    fieldLabel: 'Name',
                    name: 'name'
                },
                {
                    fieldLabel: 'Id Page',
                    name: 'idPage'
                }
            ]
        }
    ]
});

