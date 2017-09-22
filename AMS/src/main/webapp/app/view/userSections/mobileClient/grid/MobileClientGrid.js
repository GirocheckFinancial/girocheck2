Ext.define('Admin.view.clientSections.client.grid.MobileClientGrid', {
    extend: 'Admin.base.BasePaginatedGrid',
    alias: 'widget.mobileClientGrid',
    columns: {
        defaults: {
            width: '11%',
            align: 'center'
        },
        items: [
            {
                xtype: 'idGridColumn'
            },
            {
                text: "First Name",
                dataIndex: 'firstName' 
            },
            {
                text: "Last Name",
                dataIndex: 'lastName' 
            },
            {
                text: "Phone",
                dataIndex: 'telephone'
            },
            {
                text: "Email",
                dataIndex: 'email'
            },
            {
                xtype: 'creationDateGridColumn',
                dataIndex: 'registrationDate' 
            },
            {
                text: "Version",
                dataIndex: 'version',
                width: '10%'
            },
            { 
                text: "Lang",
                dataIndex: 'lang'
            }, 
            { 
                text: "Device",
                dataIndex: 'deviceType'
            },
            {
                xtype: 'creationDateGridColumn',
                dataIndex: 'lastLogin',
                text: "Last Login"
            } 
        ]
    }
});