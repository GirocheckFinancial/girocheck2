Ext.define('Admin.cmp.gridFilter.FilterDate', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.filterDate',
    xtype: 'filterDate',
    width: '100%',
    hidden: true,
    disabled:true,
    prefix: '(S)',
    style: {
        'padding': '0px',
        'margin': '0px',
        'border-width': '1px',
        'border-style': 'solid',
        'border-color': '#d0d0d0',
        'height': '100%'
    },
    layout: 'hbox',
    defaults: {
    },
    items: [
//        {
//            xtype: 'button',
//            width: '100%',
//            text: 'Select Dates',
//            listeners: {
//                click: function (cmp, e) {
//                    var win = Ext.create('Ext.window.Window', {
//                        id: 'dateRangeWindow',
//                        header: false,
//                        bodyBorder: false,
//                        closable: false,
//                        width: cmp.getWidth(),
//                        height: 100,
//                        defaults: {
//                            width: '100%'
//                        },
//                        items: [
//                            {
//                                xtype: 'datefield'
//                            },
//                            {
//                                xtype: 'datefield'
//                            }
//                        ],
//                        listeners: {
//                            activate: {
//                                fn: function (component, e, eOpts) {
//                                    var me = this;
//                                    Ext.fly(document.body).on("click", function (e) {
//                                        if (!Ext.fly(e.getTarget()).up('#dateRangeWindow') && !(e.getTarget().innerHTML && e.getTarget().innerHTML === "Select Dates")) {
//                                            var w = Ext.getCmp('dateRangeWindow');
//                                            if (w) {
//                                                w.destroy();
//                                            }
//                                        }
//                                    });
//                                }
//                            }
//                        }
//                    });
//
//                    win.showAt(cmp.getX(), cmp.getY() + cmp.getHeight());
//                }
//            }
//        }
    ],
    getValue: function () {
        return '';
    }
});