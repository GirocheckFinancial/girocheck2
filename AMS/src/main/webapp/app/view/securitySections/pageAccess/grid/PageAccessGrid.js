Ext.define('Admin.view.securitySections.pageAccess.grid.PageAccessGrid', {
    extend: 'Admin.base.BasePaginatedGrid',
    alias: 'widget.pageAccessGrid',
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
                xtype: 'nameGridColumn'
            },
            {
                text: "Id Page",
                dataIndex: 'idPage',
                width: '49%'
            }
        ]
    }
});

