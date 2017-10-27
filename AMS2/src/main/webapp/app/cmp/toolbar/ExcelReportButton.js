Ext.define('Admin.cmp.toolbar.ExcelReportButton', {
    extend: 'Ext.button.Button',
    xtype: 'excelReportButton',
    iconCls: 'x-fa fa-file-excel-o',
    tooltip: 'Excel Report',
    listeners: {
        click: function (button) {
            var grid = button.up().up().items.items[0],
                    gridTab = grid.up(),
                    grid = gridTab.items.items[0],
                    params = grid.getParams(),
                    entity = gridTab.entity,
                    url = "/AMS2/report/" + entity + ".htm?report=excel&params=" + params;
            console.log(url);

            var total = grid.getStore().totalCount;
            if (total > 10) {
                if (confirm('You are about generate a report of of ' + total + ' records. Do you want to proceed?')) {
                    window.open(url, 'window', 'HEIGHT=660,resizable=yes,scrollbars=yes,WIDTH=800,target="blank_"');
                }
            }else{
                window.open(url , 'window', 'HEIGHT=660,resizable=yes,scrollbars=yes,WIDTH=800,target="blank_"');
            }


        }
    }
});