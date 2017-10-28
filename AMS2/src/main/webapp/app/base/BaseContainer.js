Ext.define('Admin.base.BaseContainer', {
    extend: 'Ext.tab.Panel', 
    xtype: 'baseContainer', 
    margin: '20',  
    controller: 'baseController', 
    bodyStyle: {
        'background-color': 'white'
    } ,
    layout: {
        type: 'vbox',
        align: 'stretch'
    }
});
