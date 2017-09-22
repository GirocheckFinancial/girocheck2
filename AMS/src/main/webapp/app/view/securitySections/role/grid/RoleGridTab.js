Ext.define('Admin.view.securitySections.role.grid.RoleGridTab', {
    extend: 'Admin.base.BaseGridTab',
    xtype: 'roleGridTab',
    title: 'Roles',
    filters: 'role.id',
    entity: 'role',
    items: [
        {
            xtype: 'roleGrid'
        },
        {
            xtype: 'roleSubPanel'
        }
    ]
});



