Ext.define('Admin.view.securitySections.role.view.RoleEditor', {
    extend: 'Admin.base.BaseEditor',
    alias: 'widget.roleEditor',
    items: [
        {
            items: [
                {
                    xtype: 'idField'
                },
                {
                    fieldLabel: 'Name',
                    name: 'name'
                },
                {
                    xtype: 'baseTextAreaField',
                    fieldLabel: 'Description',
                    name: 'description'
                }
            ]
        },
        {
            items: [
                {
                    xtype: 'baseCheckboxField',
                    fieldLabel: 'Access All',
                    name: 'accessAll',
                    value: false
                }
            ]
        },
        {
            items: [
                {
                    xtype: 'baseCheckboxField',
                    fieldLabel: 'Read Only',
                    name: 'readOnly',
                    value: true
                }
            ]
        }
    ]
});


