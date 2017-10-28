Ext.define('Admin.view.boardingSections.merchant.view.MerchantEditor', {
    extend: 'Admin.base.BaseEditor',
    alias: 'widget.merchantEditor',
    items: [
        {
            items: [
                {
                    xtype: 'idField'
                },
                {
                    fieldLabel: 'Merchant Name',
                    name: 'name'
                },
                {
                    xtype: 'creationDateField'
                },
                {
                    fieldLabel: 'Phone',
                    name: 'phone'
                }
            ]
        },
        {
            items: [
                {
                    fieldLabel: 'Address',
                    name: 'address'
                },
                {
                    fieldLabel: 'Agrupation',
                    xtype: 'baseSelectField',
                    name: 'agrupationId',
                    url: 'agrupation',
                    defaultPropertyValue: 'data.agrupationId'
                }
            ]
        },
//        {
//            items: [
//                {
//                    fieldLabel: 'Deposit Account',
//                    xtype: 'accountReferenceSelectField',
//                    name: 'depositAccountId'
//                }, {
//                    fieldLabel: 'Distribution Account',
//                    xtype: 'accountReferenceSelectField',
//                    name: 'distributionAccountId'
//                }
//            ]
//        }
    ],
    initData: function () {
        var data = null,
                superData = this.up().superData,
                agrupationId;

        if (!superData)
            return null;
        if (superData.agrupationId) {
            agrupationId = superData.agrupationId; // When is in Program Tab
        } else {
            agrupationId = superData.id;  //When is in affiliate Tab
        }
        return {
            agrupationId: agrupationId
        };
    }
});