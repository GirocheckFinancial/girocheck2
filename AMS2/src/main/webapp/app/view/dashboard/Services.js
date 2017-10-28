Ext.define('Admin.view.dashboard.Services', {
    extend: 'Ext.Panel',
    xtype: 'servicesPieChart',
    id: 'services',
    requires: [
        'Ext.chart.series.Pie3D',
        'Ext.chart.series.sprite.PieSlice',
        'Ext.chart.interactions.Rotate'
    ],
    cls: 'service-type shadow',
    height: 400,  
    title: 'Activity Report',
    layout: {
        type: 'vbox',
        align: 'top'
    },
    items: [{
        height: 270,
        width:'100%', 
        layout: {
            type: 'hbox',
            pack  : 'center',
            align: 'middle'
        },
        items: [
            { 
                xtype: 'container',
                 width: 400, 
                defaults: {
                    height: 270,
                    insetPadding: '7.5 7.5 7.5 7.5',
                    background: 'rgba(255, 255, 255, 1)',
                    colors: [
                        '#3b5999',
                        'red',
                        'green',
                        'grey'
                    ],
                    bind: '{servicePerformance}',
                    series: [
                        {
                            type: 'pie3d',
//                            label: {
//                                field: 'xvalue',
//                                display: 'rotate',
//                                contrast: true,
//                                font: '12px Arial'
//                            },
                            useDarkerStrokeColor: false,
                            xField: 'value',
                            donut: 50,
                            padding: 0, 
                            tooltip: {
                                trackMouse: true,
                                renderer: function (tip, item) {
                                    tip.update(item.get('label') + ': $'+ item.get('value'));
                                }
                            }
                        }
                    ],
                    interactions: [
                        {
                            type: 'rotate'
                        }
                    ]
                },
                items: [
                    {
                        xtype: 'polar',
                        id:'servicesPieChart'
                    }
                ]
            } 
        ]
    },
    {
        layout: 'hbox',
        width:'100%',
        id:'servicesLegend',
         layout: {
            type: 'hbox',
          //  align: 'stretch'
        },
        defaults:{
           width:'32%',
           setVal:function(val, percent){
               this.setHtml('<div class="services-legend"><span  style="width:100%"><div class="' + this.squareClass + '"></div>' + this.label + ': $' + val+ '</span></div>');
           }
        },
        items: [
            {
                label:'Check',
                squareClass:'check-active',
                html: '<div class="services-legend"><span  style="width:100%"><div class="check-active"></div>Check</span></div>'
            },
            {
                label:'Cash',
                squareClass:'cash-active',
                html: '<div class="services-legend"><span  style="width:100%"><div class="cash-active"></div>Cash</span></div>'
            }, 
            {
                width:'36%',
                label:'Card to Bank',
                squareClass:'card2bank-active',
                html: '<div class="services-legend"><span  style="width:100%"><div class="card2bank-active"></div><p class="val">Card to Bank</p></span></div>'
            }
        ]
    }
        // {
        //     xtype: 'component',
        //     width: '100%',
        //     html:
        //             '<br><br><div class="services-legend">' +
        //             '<span><div class="legend-finance"></div>Cash to Card</span>' +
        //             '<span><div class="legend-research"></div>Check to Cards</span>' +
        //             '<span><div class="legend-marketing"></div>Card to Merchants</span>' +
        //             '<div>'
        // }
    ]

});
