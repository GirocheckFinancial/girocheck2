Ext.define('Admin.view.userSections.user.view.UserEditor', {
    extend: 'Admin.base.BaseEditor',
    alias: 'widget.userEditor',
    items: [
        {
            items: [
                {
                    fieldLabel: 'First Name',
                    name: 'name'
                }, 
                {
                    fieldLabel: 'User Name',
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
