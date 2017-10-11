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
                xtype:'nameGridColumn',
                filter:'client.firstName'
            },
            {
                text: "Last Name",
                dataIndex: 'lastName',
                filter:'client.lastName' 
            },
            {
                text: "Phone",
                dataIndex: 'telephone',
                filter:'client.telephone'
            },
            {
                text: "Email",
                dataIndex: 'email',
                filter:'client.email'
            },
            {
                xtype: 'creationDateGridColumn',
                dataIndex: 'registrationDate',
                filterType:'noFilter'
            },
            {
                text: "Version",
                dataIndex: 'version',
                filterType:'filterInteger'
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
                text: "Last Login", 
                filterType: 'filterDate'
            } 
        ]
    }
});