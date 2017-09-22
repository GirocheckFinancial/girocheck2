Ext.define('Admin.view.securitySections.role.grid.RoleGrid', {
    extend: 'Admin.base.BasePaginatedGrid',
    alias: 'widget.roleGrid',
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
                xtype: 'nameGridColumn',
                width: '20%'
            },
            {
                text: "Access All",
                dataIndex: 'accessAll',
                width: '10%'
            },
            {
                text: "Read only",
                dataIndex: 'readOnly',
                width: '10%'
            },
            {
                xtype: 'descriptionGridColumn',
                width: '59%'
            }
        ]
    }
});


