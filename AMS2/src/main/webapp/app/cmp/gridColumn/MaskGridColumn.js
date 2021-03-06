Ext.define('Admin.cmp.gridColumn.MaskGridColumn', {
    extend: 'Ext.grid.column.Column',
    xtype: 'maskGridColumn',  
    sortable: true,
    mask: '*****',
    falseVal: 'false',
    renderer: function (value, metaData) {  
        return value && ( metaData.column.mask + value);
    }
});
 