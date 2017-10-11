Ext.define('Admin.view.clientSections.client.grid.ClientGrid', {
    extend: 'Admin.base.BasePaginatedGrid',
    alias: 'widget.clientGrid',
    columns: {
        defaults: {
            width: '9%',
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
                text: "Phone",
                dataIndex: 'telephone'
            },
            {
                text: "Email",
                dataIndex: 'email' 
            },
            {
                xtype: 'creationDateGridColumn',
                dataIndex: 'createdAt', 
                filterType: 'filterDate'
            },
            {
                text: "Loads",
                dataIndex: 'successfulLoads',
                filterType:'filterInteger'
            },
            {
                xtype: 'maskGridColumn',
                text: "SSN",
                dataIndex: 'maskSSN'
            },
            {  
                text: "Block C2B",
                dataIndex: 'blacklistCard2bank',
                filterType:'filterBoolean'
            },
            { 
                text: "Block All",
                dataIndex: 'blackListAll',
                filterType:'filterBoolean'
            },
            { 
                text: 'Mobile', 
                dataIndex: 'isMobileClient',
                filterType:'filterBoolean'
            },
            { 
                text: 'Active', 
                dataIndex: 'active',
                filterType:'filterBoolean'
            }
        ]
    }
});