Ext.define('Admin.view.cardSections.card.view.CardViewTab', {
    extend:'Admin.base.BaseViewTab',
    xtype: 'cardViewTab', 
    viewName:'Card', 
    entity:'cardCard', 
    staticFilters:'card.id',
    items: [
        {
            xtype: 'cardDetails'
        },
        {
            xtype: 'cardEditor' 
        },
        {
            xtype: 'cardSubPanel'
        }
    ],
    setTabTitle: function (newData) {   //overriding the setTabTitle function.
        var me = this,
                data = newData || me.config.data,
                title = '';

        if (data && data.maskedNumber && data.maskedNumber.length >=4) {
            var length = data.maskedNumber.length;
            var title = data.maskedNumber.substring(length - 4, length);
         }

        me.setTitle('Card: ' + title);
    }
});
