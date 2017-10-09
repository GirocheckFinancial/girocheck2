Ext.define('Admin.cmp.toolbar.PdfReportButton', {
    extend: 'Ext.button.Button',
    xtype: 'pdfReportButton',
    iconCls: 'x-fa fa-file-pdf-o',
    tooltip: 'Report',
    listeners: {
        click: function (button) {
            var grid = button.up().up().items.items[0],
                    gridTab = grid.up(),
                    params = grid.getParams(), 
                    entity = gridTab.entity,
                    url = "/AMS/report/" + entity + ".htm?params=";
            console.log('params before = ' + params);
            
//            if(params && params.indexOf('##') == 0){
//                params = params.substring(2).replace(new RegExp('=','g'), 'equal').replace(new RegExp('##','g'), '**');
//            }
            console.log('params after = ' + params);
            url += params;
            console.log(url);
            window.open(url , 'window', 'HEIGHT=660,resizable=yes,scrollbars=yes,WIDTH=800,target="blank_"');
        }
    }
});