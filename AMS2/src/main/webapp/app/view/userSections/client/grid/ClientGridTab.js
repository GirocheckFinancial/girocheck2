Ext.define('Admin.view.clientSections.client.grid.ClientGridTab', {
    extend: 'Admin.base.BaseGridTab',
    xtype: 'clientGridTab',
    title: 'clients',
    entity: 'client',
    filters: 'client.id',
    tbar: [
        '->',
        {
            xtype: 'msgButton'
        },
        {
            xtype: 'pdfReportButton'
        },
        {
            xtype: 'showFilters'
        }
    ],
    items: [
        {
            xtype: 'clientGrid'
        },
        {
            xtype: 'clientSubPanel'
        }
    ]
});
