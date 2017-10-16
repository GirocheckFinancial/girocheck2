Ext.define('Admin.view.dashboard.TransactionsChart', {
    extend: 'Admin.view.charts.ChartBase',
    xtype: 'transactionsChart',
    requires: [
        'Ext.chart.CartesianChart',
        'Ext.chart.axis.Category',
        'Ext.chart.axis.Numeric',
        'Ext.chart.series.Bar',
        'Ext.chart.interactions.PanZoom'
    ],
    title: 'Transactions',
    iconCls: 'x-fa fa-bar-chart',
    items: [{
            id: 'transactionsChart',
            xtype: 'cartesian',
            colors: [
                '#034f84',
                '#ee929c',
                
            ], 
            bind: '{transactionChartData}',
            axes: [{
                    type: 'category',
                    fields: [
                        'dateLabel'
                    ],
                    // hidden: true,
                    position: 'bottom'
                }, {
                    type: 'numeric',
                    fields: [ 
                        'successful',
                        'failed'
                    ],
                    grid: {
                        odd: {
                            fill: '#ADD8E6'
                        }
                    },
                    // hidden: true,
                    position: 'left'
                }],
            series: [{
                    type: 'bar',
                    xField: 'dateLabel',
                    yField: [
                        'successful',
                        'failed'
                    ],
                    tooltip: {
                        trackMouse: true,
                        renderer: function (tip, item) {
                            tip.update('Total: ' + (item.get('successful: ') + item.get('failed: ')) + ' Successful:' + item.get('successful') + ', Failed:' + item.get('failed'));
                        }
                    }
                }],
            platformConfig: {
                phone: {
                    // On a phone the whole view becomes a vertical strip of charts,
                    // which makes it impossible to scroll the view if touch action
                    // started on a chart. So we use a custom touchAction config.
                    touchAction: {
                        panX: true,
                        panY: true
                    }
                },
                '!phone': {
                    interactions: {
                        type: 'panzoom',
                        zoomOnPanGesture: false// true
                    }
                }
            }
        }]
});
