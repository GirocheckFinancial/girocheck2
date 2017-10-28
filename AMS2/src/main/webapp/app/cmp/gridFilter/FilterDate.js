Ext.define('Admin.cmp.gridFilter.FilterDate', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.filterDate',
    xtype: 'filterDate',
    width: '100%',
    hidden: true, 
    prefix: '(D)', 
    startDate: null,
    endDate: null,
    style: {
        'padding': '0px',
        'margin': '0px',
        'border-width': '1px',
        'border-style': 'solid',
        'border-color': '#d0d0d0',
        'height': '100%'
    },
    layout: 'hbox', 
    getValue: function () {
        var me = this,
                startDate = me.startDate,
                endDate = me.endDate;
      if(startDate || endDate){
          return startDate + ',' + endDate;
      }
    },
    items: [
        {
            xtype: 'button',
            width: '100%',
            cls:'disabled-button',
            text: 'Select Dates',
            listeners: {
                click: function (cmp, e) {
                    var filterCmp = cmp.up(); 
                    var win = Ext.create('Ext.window.Window', {
                        id: 'dateRangeWindow',
                        header: false,
                        bodyBorder: false,
                        closable: true, 
                        floating:true, 
                        width: 200,
                        height:172,
                        triggerButton:cmp,
                        defaults: {
                            xtype: 'datefield',
                            style: {
                                margin: '10px'
                            },
                            listeners:{
                                change: {
                                        fn: function (component, e, eOpts) { 
                                            var me = this,
                                                    w = me.up(),
                                                    startDateValue = w.items.items[0].getValue(),
                                                    endDateValue = w.items.items[1].getValue(),
                                                    button = w.config.triggerButton;
                                            
                                            if(startDateValue || endDateValue){
                                                button.removeCls('disabled-button');
                                            }else{
                                                button.addCls('disabled-button');
                                            }
                                            
                                            button.up().startDate = startDateValue;
                                            button.up().endDate = endDateValue; 
                                        }
                                    }
                            }  
                        },
                        items: [
                            { 
                                value: filterCmp.startDate
                            },
                            { 
                                value: filterCmp.endDate
                            },
                            {
                                xtype: 'button',
                                text: 'Search',
                                height: 30,
                                width: 170,
                                style: {
                                    margin: '0px 10px 0px 10px' 
                                },
                               listeners: {
                                    click: {
                                        fn: function (component, e, eOpts) { 
                                            this.up().config.triggerButton.up().up().up().up().getStore().loadPage(1); 
                                            this.up().destroy();
                                        }
                                    }
                                }
                            },
                            {
                                xtype: 'button',
                                text: 'Cancel',
                                cls:'disabled-button',
                                height: 30,
                                width: 170,
                                style: {
                                    margin: '0px 10px 0px 10px' 
                                },
                               listeners: {
                                    click: {
                                        fn: function (component, e, eOpts) { 
                                             this.up().destroy();
                                        }
                                    }
                                }
                            }
                        ] 
                    });

                    win.showAt(cmp.getX(), cmp.getY() + cmp.getHeight()); 
                }
            }
        }
    ]
});