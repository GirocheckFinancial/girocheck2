Ext.define('Admin.view.boardingSections.terminal.view.TerminalEditor', {
    extend: 'Admin.base.BaseEditor',
    alias: 'widget.terminalEditor',
    items: [
        {
            items: [
                {
                    xtype: 'idField'
                },
                {
                    fieldLabel: 'Serial Number',
                    name: 'serialNumber'
                },
                {
                    fieldLabel: 'Merchant',
                    xtype: 'baseSelectField',
                    name: 'merchantId',
                    url: 'merchant',
                    defaultPropertyValue: 'data.merchantId'
                }
            ]
        },
        {
            items: [
                {
                    fieldLabel: 'Username',
                    name: 'username'
                },
                {
                    fieldLabel: 'Password',
                    name: 'password',
                    inputType: 'password'
                }
            ]
        },
        {
            items: [
                {
                    xtype: 'creationDateField'
                }
            ]
        }
    ],
    initData: function () {
        var data = null,
                superData = this.up().superData,
                merchantId;

        if (!superData)
            return null;
        if (superData.merchantId) {
            merchantId = superData.merchantId; // When is in Terminal Tab
        } else {
            merchantId = superData.id; // When is in Merchant Tab
        }
        return {
            merchantId: merchantId
        };
    }
});
