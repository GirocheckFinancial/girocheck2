Ext.define('Admin.view.dashboard.Dashboard', {
    extend: 'Ext.panel.Panel',
    xtype: 'admindashboard',
    title: 'Daily Operations of October',
    tools: [ ], 
    requires: [
        'Ext.ux.layout.ResponsiveColumn',
        'Admin.view.dashboard.TransactionsChart'
    ],
    tbar: [
        {
            xtype: 'combobox',
            fieldLabel: 'Transaction Type',
            valueField: 'val',
            displayField: 'name',
            value: 0,
            labelWidth: 125,
            style: {
                'margin-left': '30px'
            },
            store: Ext.create('Ext.data.Store', {
                fields: ['val', 'name'],
                data: [
                    {val: 0, name: "All"},
                    {val: 1, name: "Check"},
                    {val: 2, name: "Cash"},
                    {val: 3, name: "Card to Bank"}
                ]
            }),
            listeners: {
                change: 'onTransactionTypeChange'
            }
        }
        , '->',
        {
            xtype: 'leftButton',
            value: -1,
            listeners: {
                click: 'onScrollTime'
            }
        },
        {
            xtype: 'button',
            text: 'Daily',
            id:'timeButton0',
            time:0,
            cls: 'active-button',
            listeners: {
                click: 'onTimeChange'
            }
        },
        {
            id:'timeButton1',
            time:1,
            xtype: 'button',
            text: 'Monthly',
            listeners: {
                click: 'onTimeChange'
            }
        },
        {
            id:'timeButton2',
            time:2,
            xtype: 'button',
            text: 'Yearly', 
            listeners: {
                click: 'onTimeChange'
            }
        },
        {
            xtype: 'rightButton',
            value: 1,
            listeners: {
                click: 'onScrollTime'
            }
        }
        , '->',
         {
            id:'checkButton', 
            xtype: 'button',
            text: 'Check', 
            isactive:true,
            activeCls:'check-active',
            cls:'check-active',
            listeners: {
                click: 'onOperationChange'
            }
        },
        {
            id:'cashButton', 
            xtype: 'button',
            text: 'Cash', 
            isactive:true,
            activeCls:'cash-active',
            cls:'cash-active', 
            listeners: {
                click: 'onOperationChange'
            }
        },
         {
            id:'cardToBankButton', 
            xtype: 'button',
            isactive:true,
            text: 'Card to Bank',
            activeCls:'card2bank-active',
            cls:'card2bank-active',
            listeners: {
                click: 'onOperationChange'
            }
        },
        {
            id:'commissionButton', 
            xtype: 'button',
            isactive:true,
            text: 'Commission', 
            activeCls:'commission-active',
            cls:'commission-active',
            listeners: {
                click: 'onOperationChange'
            },
            style:{
                'margin-right':'25px'
            }
        } 
    ],
    controller: 'dashboard',
//     viewModel: {
//        type: 'charts'
//    }, 
    viewModel: {
        type: 'dashboard'
    },
    layout: 'responsivecolumn',
    listeners: {
        hide: 'onHideView'
    },
    items: [
        {
            xtype: 'transactionsChart',
            // 60% width when viewport is big enough,
            // 100% when viewport is small
            userCls: 'big-50 small-50'
        }, 
        {
            xtype: 'earnings',
            userCls: 'big-50 small-50'
        }, 
//        {
//            xtype: 'weather',
//            cls: 'weather-panel shadow',
//            userCls: 'big-40 small-100'
//        },
        {
            xtype: 'todo',
            userCls: 'big-50 small-100'
        },
        {
            xtype: 'servicesPieChart',
            userCls:'big-50 small-100'
        }
    ]
});
