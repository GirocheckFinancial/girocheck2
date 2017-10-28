Ext.define('Admin.view.transactionSections.transaction.view.TransactionDetails', {
    extend: 'Admin.base.BaseDetails',
    xtype: 'transactionDetails',
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
                        '<td><b>User:</b> {user}&nbsp;{userLastName}</td>',
                        '<td><b>Type:</b> {transactionType}</td>',
                        '<td><b>Merchant:</b>{merchant}</td>',
                        '<td><b>Card:</b>{maskedCard}</td>',
                    '</tr>',
                     '<tr>',
                        '<td><b>Amount:</b> {[Util.formatAmount(values.amount)]}</td>',
                        '<td><b>Payout Amount:</b> {[Util.formatAmount(values.payoutAmount)]}</td>',
                        '<td><b>Fee:</b>{[Util.formatAmount(values.fee)]}</td>',
                     '</tr>',
                    '<tr>',
                        '<td><b>Creation Date:</b> {[Util.formatDateTime(values.creationDate)]}</td>',       
                        '<td><b>Result Code:</b> {resultCode} </td>',
                        '<td><b>resultMessage:</b> {resultMessage} </td>',
                    '</tr>', 
                '</table>',
                '<div>'
            ].join('') 

            )
        }
    ]
});
