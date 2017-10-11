Ext.define('Admin.view.securitySections.rolePageAccess.view.RolePageAccessViewTab', {
    extend: 'Admin.base.BaseViewTab',
    xtype: 'rolePageAccessViewTab',
    viewName: 'Role - Page Access',
    entity: 'rolePageAccess',
    items: [
        {
            xtype: 'rolePageAccessDetails'
        },
        {
            xtype: 'rolePageAccessEditor'
        }
    ],
    setTabTitle: function (newData) {
        var me = this,
                data = newData || me.config.data,
                title = '';

        if (data && data.id) {
            title = 'Role: ' + data.role + ' - Page Access:' + data.pageAccess;
        } else {
            title = 'New ' + me.viewName;
        }
        me.setTitle(title);
    }
});


