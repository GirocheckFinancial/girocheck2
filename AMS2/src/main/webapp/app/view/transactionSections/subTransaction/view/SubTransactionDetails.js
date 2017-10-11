Ext.define('Admin.view.transactionSections.subTransaction.view.subTransactionDetails', {
    extend: 'Admin.base.BaseDetails',
    xtype: 'subTransactionDetails',
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
                        '<td><b>Type:</b> {type} </td>',
                        '<td><b>Result Code:</b>{resultCode}</td>', 
                        '<td><b>Host Code:</b> {hostCode}</td>',
                    '</tr>',
                    '<tr>',
                         '<td colspan="3"><b>Result Message:</b> {resultMessage}</td>',
                    '</tr>',  
                '</table>',
                '<div>'
            ].join('') 
            )
        }
    ]
});
