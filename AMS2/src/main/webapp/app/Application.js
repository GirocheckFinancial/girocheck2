Ext.define('Admin.Application', {
    extend: 'Ext.app.Application',
    
    name: 'Admin',  
    stores: [
        'NavigationTree' 
    ], 
    requires: [  
       'Admin.util.WS',
       'Admin.util.Constants',
       'Admin.util.Request',
       'Admin.util.Util',
       'Admin.util.Session'
    ],
    defaultToken : 'dashboard',

    // The name of the initial view to create. This class will gain a "viewport" plugin
    // if it does not extend Ext.Viewport.
    //
    mainView:
    // 'Admin.view.authentication.Login',
     'Admin.view.main.Main',

    onAppUpdate: function () {
        Ext.Msg.confirm('Application Update', 'This application has an update, reload?',
            function (choice) {
                if (choice === 'yes') {
                    window.location.reload();
                }
            }
        );
    }
});
