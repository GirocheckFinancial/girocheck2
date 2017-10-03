Ext.define('Admin.base.BasePaginatedGrid', {
    extend: 'Ext.grid.Panel',
    xtype: 'basePaginatedGrid',
    alias: 'widget.basePaginatedGrid',
    frame: false,
    header: false,
    height: 415,
    width: '100%',
    titleAlign: 'center',
    closable: true,
    showigOnTop: true,
    loadAdditionalInfoWhenDblClick: null, //If true, we consume the /load Web Service when DblClick
    openView: null,
    openViewFilters: null,
    openViewTab: null, //Tab to open when doubleclick the grid 
    //(if not specified, it will be deduced from the grid's name)
    requires: [
        'Admin.cmp.gridFilter.FilterDate'
    ],
    style: {
        borderColor: '#808080',
        borderStyle: 'solid',
        borderWidth: '1px'
    },
//    selModel: {
//        selType: 'checkboxmodel'
//    },
    constructor: function (config) {
        var me = this;
        Ext.apply(me, config);
        me.callParent(arguments);
    },
    listeners: {
        cellclick: 'onGridItemClick',
        itemdblclick: function (row, record) {
            this.fireEvent('doGridItemdblclick', record.data, this);
        },
        doGridItemdblclick: 'onGridItemDblClick'
    },
    initComponent: function () {
        var me = this;
        Ext.apply(this,
                {
                    bbar: {
                        xtype: 'pagingtoolbar',
                        store: me.getStore(),
                        displayInfo: true,
                        displayMsg: 'View {0} - {1} of {2}',
                        emptyMsg: "No records to display&nbsp;",
                        style: {
                            color: '#555555',
                            align: 'center'
                        },
                        listeners: {
                            single: true,
                            render: function () {
                                var items = this.items;
                                items.insert(0, Ext.create('Ext.toolbar.Fill'));
                                items.add(Ext.create('Ext.toolbar.Fill'));
                            }
                        }
                    }
                });
        me.columns.items.forEach(function (column) {
            column.items = [{
                    xtype: column.filterType || 'filterText'
                }];
        });
        me.callParent(arguments); //important call this before set store and Model

        //_______________ Setting Store and Model _______________ 

        var fields = [];
        Ext.each(me.columns, function (item) {
            fields.push({name: item.dataIndex});
        }, this);
        var model = Ext.create('Admin.base.BaseModel', {
            fields: fields
        });
        var container = me.up().isShowingOnBottom() ? me.up().up().up().up() : me.up().up(),
                pageId = container.xtype;
        var store = Ext.create('Admin.base.BaseStore', {
            model: model,
            proxy: {// Need to declare the proxy here to make the Store singlenton
                type: 'ajax',
                api:
                        {
                            read: WS.HOST + pageId + '/' + me.up().entity + '/list'
                        },
                reader:
                        {
                            type: 'json',
                            rootProperty: 'List',
                            totalProperty: 'TotalCount'
                        },
                headers: {
                    'TOKEN': Session.Principal.token
                }
            }
        });
        me.setStore(store);
    }, 
    plugins: [
        {ptype: "gridFilter"},
//        {ptype: "gridMultipageSelection"}
    ],
    getParams: function () {
        var me = this,
                searchParams = me.up().getFilters();
        me.columns.forEach(function (column) {
            var cmp = column.items.items[0];
            var val = cmp.getValue();
            if (val) {
                searchParams += '&' + (column.filter || column.dataIndex) + "=" + cmp.prefix + val;
            }
        });
        return searchParams;
    }
});