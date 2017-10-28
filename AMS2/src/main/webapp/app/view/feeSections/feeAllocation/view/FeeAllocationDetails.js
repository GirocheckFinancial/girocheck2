Ext.define('Admin.view.feeSections.feeAllocation.view.FeeAllocationDetails', {
    extend: 'Admin.base.BaseDetails',
    xtype: 'feeAllocationDetails',
    items: [
        {
            xtype: 'component', 
            width:'100%',
            data: { 
            },
            tpl: new Ext.XTemplate([
                '<div style="padding:20px">',
                '<table cellpadding="10" style="width:100%">',
                '<tr>',
                '<td><b>Fee Schedule:</b> {feeAllocator}</td>',
                '<td><b>Percent:</b> {percent}</td>',
                '<td><b>Fixed Rate:</b> {fixedRate}</td>',
                '</tr>',
                '<tr>',
                '<td><b>Last Updated:</b> {[Util.formatDate(values.lastUpdate)]}</td>', 
                '</tr>',
                '</table>',
                '<div>'
            ].join('') 
            )
        }
    ]
});
