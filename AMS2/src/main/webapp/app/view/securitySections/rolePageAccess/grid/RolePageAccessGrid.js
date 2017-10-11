Ext.define('Admin.view.securitySections.rolePageAccess.grid.RolePageAccessGrid', {
    extend: 'Admin.base.BasePaginatedGrid',
    alias: 'widget.rolePageAccessGrid',
    columns: {
        defaults: {
            width: '50%',
            align: 'center'
        },
        items: [
            {
                xtype: 'idGridColumn'
            },
            {
                text: "Role",
                dataIndex: 'role',
                filter: 'role.name'
            },
            {
                text: "Page Access",
                dataIndex: 'pageAccess',
                filter: 'pageAccess.name',
                width: '49%'
            }
        ]
    }
});


