Ext.define('Admin.view.authentication.AuthenticationController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.authentication',
    onLoginButton: function () {
        var me = this;

        var credentials = {
            user: me.getView().getReferences()['userNameField'].getValue(),
            password: me.getView().getReferences()['passwordField'].getValue()
        };

        Request.load({
            url: 'loginPage/security/login',
            method: 'POST',
            jsonData: credentials,
            ignoreError: true,
            success: function (response) { 
                if (response && response.status && response.status === 100 && response.data && response.data.token) {
                    Session.Principal = response.data;
                    
                    var navTree = Ext.getStore('NavigationTree');
                    navTree.custom();
                    Ext.ComponentQuery.query('#navigationTreeList')[0].setStore( navTree );
                   
                    var node = navTree.data.items[0],
                            firstItem = node.get('routeId') || node.get('viewType') || node.get('children')[0].viewType;

                    // Ext.ComponentQuery.query('#principalName')[0].setText(Session.Principal.name + ' ' + Session.Principal.lastName);
                    Ext.ComponentQuery.query('#principalName')[0].setText('Kenneth Erdberg');
                    Ext.GlobalEvents.fireEvent('logIn', firstItem);
                  //  me.redirectTo(firstItem, true);
                } else {
                    me.getView().getReferences()['userNameField'].el.dom.getElementsByClassName('x-form-trigger-wrap-default')[0].classList.add('x-form-trigger-wrap-invalid')
                    me.getView().getReferences()['passwordField'].el.dom.getElementsByClassName('x-form-trigger-wrap-default')[0].classList.add('x-form-trigger-wrap-invalid')
                }

            }
        });



    },
    onLoginAsButton: function () {
        alert('OK');
        //   this.redirectTo('login', true);
    },
    onNewAccount: function () {
        this.redirectTo('register', true);
    },
    onSignupClick: function () {
        this.redirectTo('dashboard', true);
    },
    onResetClick: function () {
        this.redirectTo('dashboard', true);
    }
});