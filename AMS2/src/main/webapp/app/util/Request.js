/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
Ext.define('Admin.util.Request', {
    extend: 'Ext.data.Connection',
    alternateClassName: 'Request',
    singleton: true,
    autoAbort: false,
    constructor: function (config) {
        var me = this;
        me.callParent(arguments);
        me.on(Ext.apply(me.submitEvents, {scope: me}));
    },
    submitEvents: {
        beforerequest: "onBeforerequest",
        requestcomplete: "onRequestcomplete",
        requestexception: "onRequestexception"
    },
    load: function (config) {
        var me = this,
                token = Session.Principal ? Session.Principal.token : '';
        
        var obj = {
            headers: {
               'TOKEN': token
            },
            method: config.method || 'GET', //'POST',
            url: config.url,
            params: config.params,
            jsonData: config.jsonData,
            callback: config.callback,
            customSuccess: config.success,
            failure: config.failure,
            scope: config.scope,
            loadingMask: config.loadingMask || false,
            loadingText: config.loadingText,
            processResponse: config.processResponse || false,
            ignoreError: config.ignoreError
        };

        if (config.params) {
            obj['headers']['Content-Type'] = 'application/x-www-form-urlencoded';
        } else {
            obj['headers']['Content-Type'] = 'application/json; charset=utf-8';
        }

        me.request(obj);
    },
    onBeforerequest: function (conn, options, eOpts) {

//        if (options.url.indexOf('http') !== 0) {
            options.url = WS.HOST + options.url;
//        }

//        if (options.loadingMask) {
//            Loading.start({showloading: 1});
//        }
    },
    onRequestcomplete: function (conn, response, options, eOpts) {
        if(!response.responseText || response.responseText.length === 0){
            Ext.GlobalEvents.fireEvent('logOut');
            return;
        } 
         
        var responseAsJson = Ext.decode(response.responseText);
        
        if(responseAsJson.status && responseAsJson.status != 100 && !options.ignoreError){
            alert(responseAsJson.statusMessage);
            return;
        }
        
        if (responseAsJson.error) {
           alert('Error');
        } else { 
            options.customSuccess(responseAsJson);
        } 
    },
    onRequestexception: function (conn, response, options, eOpts) {
        
        alert('Exception');
    }
});