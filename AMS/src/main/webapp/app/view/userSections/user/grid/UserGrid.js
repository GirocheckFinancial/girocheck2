Ext.define('Admin.view.userSections.user.grid.UserGrid', {
    extend: 'Admin.base.BasePaginatedGrid',
    alias: 'widget.userGrid',
    columns: {
        defaults: {
            width: '25%',
            align: 'center'
        },
        items: [
            {
                xtype: 'idGridColumn'
            },
            {
                xtype: 'nameGridColumn'
            },
            {
                text: "Last Name",
                dataIndex: 'lastName'
            },
            {
                text: "Role",
                dataIndex: 'role'
            }, 
            {
                text: "Email",
                dataIndex: 'email',
                width: '24%'
            }
        ]
    }
});