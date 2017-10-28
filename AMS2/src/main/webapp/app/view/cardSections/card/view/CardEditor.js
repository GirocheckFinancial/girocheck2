Ext.define('Admin.view.cardSections.card.view.CardEditor', {
    extend: 'Admin.base.BaseEditor',
    alias: 'widget.cardEditor',
    items: [
        {
            items: [
                {
                    fieldLabel: 'Card Number',
                    name: 'maskedNumber',
                    disabled: true
                },
                {
                    fieldLabel: 'User',
                    xtype: 'baseSelectField',
                    name: 'userId', //name of the merchant and id of the merchant Card
                    url: 'user'
                }
            ]
        },
        {
            items: [
                {
                    fieldLabel: 'Merchant',
                    xtype: 'baseSelectField',
                    name: 'merchantId',
                    url: 'merchant'
                } 
            ]
        },
        {
            items: [ 
                {
                     fieldLabel: 'Expiration Date',
                    xtype: 'baseDateField',
                    name: 'expDate'
                },
                {
                    xtype: 'idField'
                }
            ]
        }
    ] 
});
