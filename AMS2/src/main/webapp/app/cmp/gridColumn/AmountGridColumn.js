Ext.define('Admin.cmp.gridColumn.AmountGridColumn', {
    extend: 'Ext.grid.column.Column',
    xtype: 'amountGridColumn',  
    sortable: true, 
    renderer: function (value, metaData) { 
        return Ext.util.Format.number(value, '0,000.00');
    }
});
 