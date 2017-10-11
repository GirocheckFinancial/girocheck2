Ext.define('Admin.cmp.gridFilter.FilterNumber', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.filterNumber',
    xtype: 'filterNumber',
    width: '100%',
    hidden: true,
    prefix: '(d)', //Override this if needed
    operator: null,
    value: null,
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
                operator = me.operator,
                value = me.value;
        if (operator && value) {
            return '[' + operator + ']' + value;
        }
    },
    defaults: {
        width: '50%'
    },
    items: [
        {
            xtype: 'button',
            width: '100%',
            cls: 'disabled-button',
            text: '',
            listeners: {
                click: function (cmp, e) {
                    var filterCmp = cmp.up();
                    var win = Ext.create('Ext.window.Window', {
                        id: filterCmp.id + 'dateRangeWindow',
                        header: false,
                        bodyBorder: false,
                        closable: true,
                        floating: true,
                        width: 200,
                        height: 130,
                        triggerButton: cmp,
                        defaults: {
                            style: {
                                margin: '10px'
                            }
                        },
                        items: [
                            {
                                layout: 'hbox',
                                defaults: {
                                    width: '48%'
                                },
                                items: [
                                    {
                                        xtype: 'combobox',
                                        valueField: 'id',
                                        displayField: 'name',
                                        value: filterCmp.operator,
                                        store: Ext.create('Ext.data.Store', {
                                            fields: ['id', 'name'],
                                            data: [
                                                {id: '', name: ""},
                                                {id: 'eq', name: " = "}, 
                                                {id: 'ne', name: " != "},
                                                {id: 'gt', name: " > "},
                                                {id: 'lt', name: " < "},
                                                {id: 'ge', name: " >= "},
                                                {id: 'le', name: " <= "},
                                            ]
                                        }),
                                    },
                                    {
                                        xtype: 'textfield',
                                        value: filterCmp.value
                                    }
                                ]
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
                                            var me = this,
                                                    w = me.up(),
                                                    fields = w.items.items[0].items.items,
                                                    operatorField = fields[0],
                                                    valueField = fields[1],
                                                    operator = operatorField.getValue(),
                                                    value = valueField.getValue(),
                                                    button = w.config.triggerButton;;

                                            if (operator && value) {
                                                button.removeCls('disabled-button');
                                                button.setText(operatorField.getDisplayValue() + ' ' + value);
                                                button.up().operator = operator;
                                                button.up().value = value;
                                            } else {
                                                button.up().operator = '';
                                                button.up().value = '';
                                                button.setText('');
                                                button.addCls('disabled-button');
                                            }
                                            button.up().up().up().up().getStore().loadPage(1);
                                            w.destroy();
                                        }
                                    }
                                }
                            },
                            {
                                xtype: 'button',
                                text: 'Cancel',
                                cls: 'disabled-button',
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