Ext.define('Admin.view.securitySections.rolePageAccess.view.RolePageAccessEditor', {
    extend: 'Admin.base.BaseEditor',
    alias: 'widget.rolePageAccessEditor',
    items: [
        {
            items: [
                {
                    xtype: 'idField'
                },
                {
                    fieldLabel: 'Role',
                    xtype: 'baseSelectField',
                    name: 'roleId',
                    url: 'role',
                    applyFilter: 'id=(I)',
                    filterPropertyName: 'superData.id',
                    disabledIfHasValue:true,
                    defaultPropertyValue:'superData.id'
                },
                {
                    fieldLabel: 'Page Access',
                    xtype: 'baseSelectField',
                    name: 'pageAccessId',
                    url: 'pageAccess'
                }
            ]
        }
    ]
});



