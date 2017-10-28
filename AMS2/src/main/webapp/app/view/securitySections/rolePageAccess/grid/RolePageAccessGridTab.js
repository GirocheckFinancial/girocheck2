Ext.define('Admin.view.securitySections.rolePageAccess.grid.RolePageAccessGridTab', {
    extend: 'Admin.base.BaseGridTab',
    xtype: 'rolePageAccessGridTab',
    title: 'Role - Page Access',
    entity: 'rolePageAccess',
    items: [
        {
            xtype: 'rolePageAccessGrid'
        }
    ]
});


