Ext.define('Admin.view.dashboard.DashboardModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.dashboard',

    requires: [
        'Ext.data.Store',
        'Ext.data.field.Integer',
        'Ext.data.field.String',
        'Ext.data.field.Boolean'
    ], 
    stores: {
//         stackedData: {
         transactionChartData: {
//            model: 'Admin.model.MultiDataXY',
            model: 'Admin.view.dashboard.model.TransactionChartModel',
            autoLoad: true,
            proxy: {
                type: 'ajax',
                url: '/Front/page/transaction/chart?chartType=transactionsChart',
                reader: {
                    type: 'json',
                    rootProperty: 'list'
                }
            }  
        },
//        hddusage: {
//            autoLoad: true,
//            model: 'Admin.model.DataXY', 
//            proxy: {
//                type: 'api',
//                url: '~api/qg/area'
//            } 
//        },
//        quarterlyGrowth: {
//            autoLoad: true,
//            model: 'Admin.model.DataXY',
//            proxy: {
//                type: 'api',
//                url: '~api/qg/bar'
//            }
//        },
        earnings: {
            autoLoad: true,
            model: 'Admin.model.DataXY',
            proxy: {
               type: 'ajax',
               url: '/Front/page/transaction/chart?chartType=earnings',
               reader: {
                   type: 'json',
                   rootProperty: 'list'
               }
           } 
        }, 
        servicePerformance: {
              autoLoad: true,
            model: 'Admin.model.DataXY',
            proxy: {
               type: 'ajax',
               url: '/Front/page/transaction/chart?chartType=servicesPieChart',
               reader: {
                   type: 'json',
                   rootProperty: 'data'
               }
           },
           listeners:{
               load: function(a,data,c,d,e,f,g){
                  Ext.getCmp('servicesLegend').items.items.forEach(function(legentItem, i){
                      legentItem.setVal(data[i].data.value);
                  });
                  
//                  data && data.forEach(function(dataItem, i){
//                      legentItems[i].setVal(dataItem.data.value);
//                  });
               }
           }
        },
//        topMovies: {
//            autoLoad: true,
//            model: 'Admin.model.DataXY',
//            proxy: {
//                type: 'api',
//                url: '~api/dashboard/movies'
//            }
//        },
//        networkData: {
//            autoLoad: true,
//            model: 'Admin.model.MultiDataXY',
//            proxy: {
//                type: 'api',
//                url: '~api/dashboard/full'
//            }
//        },
//        visitors: {
//            autoLoad: true,
//            model: 'Admin.model.MultiDataXY',
//            proxy: {
//                type: 'api',
//                url: '~api/dashboard/visitor'
//            }
//        },
//        bounces: {
//            autoLoad: true,
//            model: 'Admin.model.MultiDataXY',
//            proxy: {
//                type: 'api',
//                url: '~api/dashboard/counce'
//            }
//        },
//        subscriptions: {
//            autoLoad: true,
//            model: 'Admin.model.Subscription',
//            proxy: {
//                type: 'api',
//                url: '~api/subscriptions'
//            }
//        },
        todos: {
            autoLoad: true,
            fields: [
                {
                    type: 'int',
                    name: 'id'
                },
                {
                    type: 'string',
                    name: 'task'
                },
                {
                    type: 'boolean',
                    name: 'done'
                }
            ],
            proxy: {
                type: 'api',
                url: '~api/dashboard/tasks'
            }            
        }
    }
});
