Ext.define('Admin.view.dashboard.model.TransactionChartModel', {
    extend: 'Admin.model.Base',

    fields: [
        {
            name: 'dateLabel'
        }, 
        {
            name: 'successful'
        },
        {
            name: 'failed'
        } 
    ]
});
