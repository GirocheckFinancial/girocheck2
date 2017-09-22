Ext.define('Admin.view.clientSections.client.view.MobileClientEditor', {
    extend: 'Admin.base.BaseEditor',
    alias: 'widget.mobileClientEditor',
    items: [
        {
            items: [
                {
                    fieldLabel: 'First Name',
                    name: 'name'
                },
                {
                    fieldLabel: 'Phone',
                    name: 'phone'
                },
                {
                    fieldLabel: 'client Name',
                    name: 'username'
                }
            ]
        },
        {
            items: [
                {
                    fieldLabel: 'Last Name',
                    name: 'lastName'
                },
                {
                    fieldLabel: 'Address',
                    name: 'address'
                },
                {
                    fieldLabel: 'Password',
                    name: 'password'
                }
            ]
        },
        {
            items: [
                {
                    xtype: 'creationDateField'
                },
                 {
                    fieldLabel: 'Role',
                    xtype: 'baseSelectField',
                    name: 'roleId',
                    url: 'role' 
                },
                {
                    xtype: 'idField'
                }
            ]
        }
    ]
});
