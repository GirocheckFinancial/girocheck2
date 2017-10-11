Ext.define('Admin.cmp.toolbar.ExcelReportButton', {
    extend: 'Ext.button.Button',
    xtype: 'excelReportButton',
    iconCls: 'x-fa fa-file-excel-o',
    tooltip: 'Excel Report',
    listeners: {
        click: function (button) {
            var grid = button.up().up().items.items[0],
                    gridTab = grid.up(),
                    params = grid.getParams(), 
                    entity = gridTab.entity,
                    url = "/AMS2/report/" + entity + ".htm?report=excel&params=" + params;
            console.log(url);
            window.open(url , 'window', 'HEIGHT=660,resizable=yes,scrollbars=yes,WIDTH=800,target="blank_"');
        }
    }
});