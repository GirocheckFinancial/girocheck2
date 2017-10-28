Ext.define('Admin.view.cardSections.card.view.CardDetails', {
    extend: 'Admin.base.BaseDetails',
    xtype: 'cardDetails',
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
                        '<td><b>Card Number:</b> {maskedNumber}</td>',
                        '<td><b>Merchant:</b> {merchant}</td>', 
                    '</tr>',
                    '<tr>',
                        '<td><b>User:</b>{user}</td>', 
                        '<td><b>Expiration Date:</b> {[Util.formatDate(values.expDate)]}</td>',
                    '</tr>',
                '</table>',
                '<div>'
            ].join('') 

            )
        }
    ]
});
