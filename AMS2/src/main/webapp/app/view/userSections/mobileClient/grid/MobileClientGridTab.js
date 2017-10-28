Ext.define('Admin.view.clientSections.client.grid.MobileClientGridTab', {
    extend: 'Admin.base.BaseGridTab',
    xtype: 'mobileClientGridTab',
    title: 'Mobile Clients',
    entity: 'mobileClients',
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
            xtype: 'mobileClientGrid'
        },
        {
            xtype: 'mobileClientSubPanel'
        }
    ]
});
