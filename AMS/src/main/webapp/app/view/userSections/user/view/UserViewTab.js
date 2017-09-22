Ext.define('Admin.view.userSections.user.view.UserViewTab', {
    extend:'Admin.base.BaseViewTab',
    xtype: 'userViewTab', 
    viewName:'User', 
    entity:'user',
    items: [
        {
            xtype: 'userDetails'
        },
        {
            xtype: 'userEditor' 
        },
//        {
//            xtype: 'userSubPanel'
//        }
    ]
});
