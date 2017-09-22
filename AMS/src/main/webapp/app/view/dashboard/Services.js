Ext.define('Admin.view.dashboard.Services', {
    extend: 'Ext.Panel',
    xtype: 'services',
    requires: [
        'Ext.chart.series.Pie',
        'Ext.chart.series.sprite.PieSlice',
        'Ext.chart.interactions.Rotate'
    ],
    cls: 'service-type shadow',
    height: 250,
    bodyPadding: 15,
    title: 'Activity Report',
    layout: {
        type: 'vbox',
        align: 'top'
    },
    items: [{
        height: 150,
        layout: {
            type: 'hbox',
            align: 'stretch'
        },
        items: [
            {
                xtype: 'container',
                 width: 170, 
                defaults: {
                    height: 126,
                    insetPadding: '7.5 7.5 7.5 7.5',
                    background: 'rgba(255, 255, 255, 1)',
                    colors: [
                        '#6aa5dc',
                        '#fdbf00',
                        '#ee929d'
                    ],
                    bind: '{servicePerformance}',
                    series: [
                        {
                            type: 'pie',
                            label: {
                                field: 'xField',
                                display: 'rotate',
                                contrast: true,
                                font: '12px Arial'
                            },
                            useDarkerStrokeColor: false,
                            xField: 'yvalue',
                            donut: 50,
                            padding: 0
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
                        xtype: 'polar'
                    }
                ]
            }, 
            {
                xtype: 'container', 
                layout: {
                    type: 'vbox',
                    align: 'right'
                },
                items: [
                    {
                        xtype: 'component',
                        data: {
                            name: 'Cash to Cards',
                            value: '20%'
                        },
                        tpl: '<div class="left-aligned-div">{name}</div><div class="right-aligned-div">{value}</div>'
                    },
                    {
                        xtype: 'progressbar',
                        cls: 'bottom-indent service-finance',
                        height: 4,
                        minHeight: 4,
                        value: 0.2
                    },
                    {
                        xtype: 'component',
                        data: {
                            name: 'Check to Cards',
                            value: '68%'
                        },
                        tpl: '<div class="left-aligned-div">{name}</div><div class="right-aligned-div">{value}</div>'
                    },
                    {
                        xtype: 'progressbar',
                        cls: 'bottom-indent service-research',
                        height: 4,
                        minHeight: 4,
                        value: 0.68
                    },
                    {
                        xtype: 'component',
                        data: {
                            name: 'Card to Merchants',
                            value: '12%'
                        },
                        tpl: '<div class="left-aligned-div">{name}</div><div class="right-aligned-div">{value}</div>'
                    },
                    {
                        xtype: 'progressbar',
                        cls: 'bottom-indent service-marketing',
                        height: 4,
                        value: 0.12
                    }
                ]
            }
        ]
    },
    {
        layout: 'hbox',
        width:'100%',
         layout: {
            type: 'hbox',
          //  align: 'stretch'
        },
        defaults:{
           width:'32%'
        },
        items: [
            {
                html: '<div class="services-legend"><span  style="width:100%"><div class="legend-finance"></div>Cash to Card</span></div>'
            },
            {
                html: '<div class="services-legend"><span  style="width:100%"><div class="legend-research"></div>Check to Cards</span></div>'
            },
            {
                 width:'36%',
                html: '<div class="services-legend"><span  style="width:100%"><div class="legend-marketing"></div>Card to Merchants</span></div>'
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
