Ext.define('Admin.view.securitySections.role.RoleSubPanel', {
    extend: 'Admin.base.BaseSubPanel',
    xtype: 'roleSubPanel',
    config: {
        subPanels: [
            {
                xtype: 'rolePageAccessGridTab',
                openViewFilters: 'rolePageAccess.id'
            }
        ]
    }
});


