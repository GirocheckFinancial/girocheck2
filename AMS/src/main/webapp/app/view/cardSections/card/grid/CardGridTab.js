Ext.define('Admin.view.cardSections.card.grid.CardGridTab', {
    extend:'Admin.base.BaseGridTab',
    xtype: 'cardGridTab', 
    title: 'Credit Cards',   
    entity:'card',
    filters:'card.id', 
    items: [ 
        {
            xtype: 'cardGrid' 
        }, 
        {
            xtype: 'cardSubPanel'
        }
    ]
});
