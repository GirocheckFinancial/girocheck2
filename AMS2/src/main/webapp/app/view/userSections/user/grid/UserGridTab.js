Ext.define('Admin.view.userSections.user.grid.UserGridTab', {
    extend:'Admin.base.BaseGridTab',
    xtype: 'userGridTab', 
    title: 'Users',  
    entity:'user',
    filters:'user.id',
    items: [ 
        {
            xtype: 'userGrid' 
        }
    ]
});
