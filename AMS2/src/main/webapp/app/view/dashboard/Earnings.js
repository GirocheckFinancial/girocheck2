Ext.define('Admin.view.dashboard.Earnings', {
    extend: 'Ext.Panel',
    xtype: 'earnings',
    requires: [
        'Ext.chart.CartesianChart',
        'Ext.chart.axis.Category',
        'Ext.chart.axis.Numeric',
        'Ext.chart.series.Line',
        'Ext.chart.interactions.PanZoom'
    ],
    title: 'Earnings',
    ui: 'light',
    iconCls: 'x-fa fa-dollar',
    headerPosition: 'bottom',
    cls: 'quick-graph-panel shadow',
    height: 300,
    layout: 'fit',
    items: [
        {
            id: 'earnings',
            xtype: 'cartesian',
            animation: !Ext.isIE9m && Ext.os.is.Desktop,
            background: 'white', 
            colors: [
                '#483D8B',
                '#94ae0a',
                '#a61120',
                '#ff8809',
                '#ffd13e',
                '#a61187',
                '#24ad9a',
                '#7c7474',
                '#a66111'
            ],
            bind: {
                store: '{earnings}'
            },
            axes: [
                {
                    type: 'category',
                    fields: [
                        'dateLabel'
                    ],
                    // hidden: true,
                    position: 'bottom'
                },
                {
                    type: 'numeric',
                    fields: [
                        'check', 'cash','card2bank', 'commission'
                    ],
                    grid: {
                        odd: {
                            fill: '#eff0f1'
//                            fill: '#ADD8E6'
                        }
                    },
                    //  hidden: true,
                    position: 'left'
                } 
            ],
            series: [
                {
                    type: 'line',
                    style: {
                        stroke: '#3b5999',
                        'stroke-width': '15px'
                    },
                    xField: 'dateLabel',
                    yField: [
                        'check'
                    ],
                    tooltip: {
                        trackMouse: true,
                        renderer: function (tip, item) {
                            tip.update('Check Amount: ' + item.get('check'));
                        }
                    }
                },
                {
                    type: 'line',
                    style: {
                        stroke: 'red',
                        'stroke-width': '15px'
                    },
                    xField: 'dateLabel',
                    yField: [
                        'cash'
                    ],
                    tooltip: {
                        trackMouse: true,
                        renderer: function (tip, item) {
                            tip.update('Cash Amount: ' + item.get('cash'));
                        }
                    }
                },
                {
                    type: 'line',
                    style: {
                        stroke: 'green',
                        'stroke-width': '15px'
                    },
                    xField: 'dateLabel',
                    yField: [
                        'card2bank'
                    ],
                    tooltip: {
                        trackMouse: true,
                        renderer: function (tip, item) {
                            tip.update('Card to Bank Amount: ' + item.get('card2bank'));
                        }
                    }
                },
                {
                    type: 'line',
                    style: {
                        stroke: 'orange',
                        'stroke-width': '15px'
                    },
                    xField: 'dateLabel',
                    yField: [
                        'commission'
                    ],
                    tooltip: {
                        trackMouse: true,
                        renderer: function (tip, item) {
                            tip.update('Commission Amount: ' + item.get('commission'));
                        }
                    }
                }
            ],
            interactions: [
                {
                    type: 'panzoom'
                }
            ]
        }
    ],
    tools: [
        {
            xtype: 'tool',
            cls: 'quick-graph-panel-tool x-fa fa-ellipsis-v'
        }
    ]
});
