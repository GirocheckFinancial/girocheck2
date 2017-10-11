Ext.define('Admin.view.boardingSections.merchant.view.MerchantDetails', {
    extend: 'Admin.base.BaseDetails',
    xtype: 'merchantDetails',
    items: [
        {
            xtype: 'component',
            flex: 1,
            cls: 'single-mail-email-subject',
            data: { 
            },
            tpl: new Ext.XTemplate([
                '<div style="padding:20px">',
                '<table cellpadding="10" style="width:100%;cellspacing">',
                    '<tr>',
                        '<td><b>Name:</b> {name}</td>',
                        '<td><b>Address:</b>{address}</td>',
                        '<td><b>Account:</b><span class="depositAccountId">{depositAccount}<span></td>',
                    '</tr>',
                    '<tr>',
                        '<td><b>Creation Date:</b> {[Util.formatDate(values.creationDate)]}</td>',
                        '<td><b>Phone:</b> {phone}</td>', 
                    '</tr>', 
                '</table>',
                '<div>'
            ].join('') 

            )
        }
    ]
});
