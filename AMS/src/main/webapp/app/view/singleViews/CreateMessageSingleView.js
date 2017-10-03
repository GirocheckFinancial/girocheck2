Ext.define('Admin.view.singleViews.CreateMessageSingleView', {
    extend: 'Admin.base.BaseTab',
    xtype: 'createMessageSingleView',
    title: 'Create Message', 
    items: [
        { 
          html:'', 
          style:{
              'padding-left':'30px',
              'padding-top':'20px'
          }
        }, 
         {
            xtype: 'component',  
            hidden:true,
            data: {
            },
            tpl: new Ext.XTemplate([
                '<div style="padding-left:20px">', 
                '<tpl if="messagesNotSent &gt; 0"><p style="color:red; margin-left:4px">Failed to send message to: {unsentClientNames}</p></tpl>',
                '<table cellpadding="10" style="width:100%;cellspacing;">',
                    '<tr>',
                        '<td><b>Mobile Clients</b></td>',
                        '<td><b>Clients</b> </td>', 
                    '</tr>',
                    '<tr>',
                        '<td>Push Notifications Sent: {pushNotificationsSent}</td>',
                        '<td>Messages Sent: {messagesSent}</td>', 
                    '</tr>',
                   '<tr>',
                        '<td>Push Notifications No Sent: {pushNotificationsNotSent}</td>',
                        '<td>Messages No Sent: {messagesNotSent}</td>', 
                    '</tr>', 
                '</table>',
                '<div>'
            ].join('')
                    )
        },
        {
            xtype: 'panel',
            width: '100%',
            height: 320,
            layout: {
                type: 'table',
                columns: 2,
                tableAttrs: {
                    style: {
                        width: '100%'
                    }
                }
            },
            defaults: {
                xtype: 'singleViewMsgTab'
            },
            items: [
                {
                    title: 'English'
                },
                {
                    title: 'Spanish'
                }
            ],
            bbar: [
                '->',
                {
                    text: 'Cancel',
                    ui: 'soft-green',
                    handler: function () {
                        var me = this,
                                view = me.up().up().up(),
                                tabPanel = view.up();

                        tabPanel.remove(view);
                    }
                },
                {
                    text: 'Send',
                    ui: 'green',
                    handler: function () { 
                        var panel = this.up().up(),
                                wrapper = panel.up(),
                                viewTab = panel.up().up(),
                                firstTab = viewTab.items.items[0],
                                grid = firstTab.items.items[0],
                                totalCount = grid.getStore().totalCount,
                                params = grid.getParams();
                        
                        wrapper.items.items[0].setHtml('Target Audience: ' + totalCount);

                        var msg = {
                            params: params,
                            enTitle: panel.items.items[0].down('baseTextField').value,
                            enText: panel.items.items[0].down('baseTextAreaField').value,
                            esTitle: panel.items.items[1].down('baseTextField').value,
                            esText: panel.items.items[1].down('baseTextAreaField').value
                        }

                        Request.load({
                            url: viewTab.up().xtype + '/' + firstTab.entity + '/sendMessage',
                            method: 'POST',
                            jsonData: msg,
                            success: function (response) {  
                                if(response && response.data){
                                    var metricsView = wrapper.items.items[1];
                                    metricsView.show();
                                    metricsView.setData(response.data);
                                }
                            }
                        });
                    }
                },
                {//This is a separator (need time to research on a better way to do it)
                    text: '',
                    width: 60,
                    style: {
                        'background-color': 'white',
                        'border-width': '0px'
                    }
                }
            ]
        }
    ],
    listeners: {
        render: function () {
        },
        activate: function () {
        }
    }
});


Ext.define('Admin.view.singleViews.MsgTab', {
    extend: 'Ext.panel.Panel',
    xtype: 'singleViewMsgTab',
    title: 'Create Message',
    cls: 'innerPanel',
    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    style: {
        margin: '30px',
        border: '1px solid grey'
    },
    bodyStyle: {
        padding: '20px'
    },
    items: [
        {
            xtype: 'baseTextField',
            fieldLabel: 'Title',
            name: 'title',
//            value: 'Title here'
        },
        {
            xtype: 'baseTextAreaField',
            fieldLabel: 'Text',
            name: 'text',
//            value:'Text here'
        }
    ]
});
