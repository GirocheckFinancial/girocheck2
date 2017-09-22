Ext.define('Admin.view.clientSections.client.grid.ClientGrid', {
    extend: 'Admin.base.BasePaginatedGrid',
    alias: 'widget.clientGrid',
    columns: {
        defaults: {
            width: '10%',
            align: 'center'
        },
        items: [
            {
                xtype: 'idGridColumn'
            },
            {
                text: "First Name",
                dataIndex: 'firstName',
                width: '12%'
            },
            {
                text: "Last Name",
                dataIndex: 'lastName',
                width: '12%'
            },
            {
                text: "Phone",
                dataIndex: 'telephone'
            },
            {
                text: "Email",
                dataIndex: 'email',
                width: '12%'
            },
            {
                xtype: 'creationDateGridColumn',
                dataIndex: 'createdAt',
                width: '12%'
            },
            {
                text: "Loads",
                dataIndex: 'successfulLoads'
            },
            {
                xtype:'maskGridColumn',
                text: "SSN",
                dataIndex: 'maskSSN'
            },
            {
                xtype: 'booleanGridColumn',
                trueVal: 'Blocked',
                falseVal: '-',
                text: "C2Bank BL",
                dataIndex: 'blacklistCard2bank'
            },
            {
                xtype: 'booleanGridColumn',
                trueVal: 'Blocked',
                falseVal: '-',
                text: "All BL",
                dataIndex: 'blackListAll'
            }
        ]
    }
});