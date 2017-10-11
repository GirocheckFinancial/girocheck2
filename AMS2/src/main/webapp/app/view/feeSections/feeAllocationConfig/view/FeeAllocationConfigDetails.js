Ext.define('Admin.view.feeSections.feeAllocationConfig.view.FeeAllocationConfigDetails', {
    extend:'Admin.base.BaseDetails',
    xtype: 'feeAllocationConfigDetails', 
    items: [
        {
            xtype: 'component', 
            flex: 1,
            cls: 'single-mail-email-subject',
            data: {  
            },
            tpl: new Ext.XTemplate([
                '<div style="padding:20px">', 
                '<table cellpadding="4" style="width:100%;cellspacing">',
                    '<tr>',
                        '<td><b>Name:</b> {name}</td>', 
                        '<td><b>Last Update:</b> {[Util.formatDate(values.lastUpdate)]}</td>', 
                     '</tr>', 
                '</table>',
                '<div>'
            ].join('') 
            )
        }
    ]
});
