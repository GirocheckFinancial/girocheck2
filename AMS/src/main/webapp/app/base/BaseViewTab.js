Ext.define('Admin.base.BaseViewTab', {
    extend: 'Admin.base.BaseTab',
    xtype: 'baseViewTab',
    cls: 'shadow',
    title: '',
    view: '',
    superEntityId: null, //entityId if the super Tab of the view that oppened me 
    additionalValues: null, //additional values to send when submit the form,
    //this will be an array of objs with the format:
    //[{propertyName:'property_name', propertyValue:'name_of_the_value_parameter'}]
    loadFilter:null,      //Use this when loading single views (Ej: Distributor, Merchant, Partner)
    loadFilterValue:null, //This is the property in Principal that will determine the value.
    tbar: [
        '->',
        {
            xtype: 'editButton',
            handler: function () {
                var me = this;
                me.hide();
                me.up().up().editMode();
            }
        },
        {
            xtype: 'deleteButton'
        }
    ],
    listeners: {
        render: function () {
            var me = this,
                    container = me.up();
            
            if(container.items.items.length === 1){ //If get's here is because Is a single view
                   Request.load({
                    url: container.xtype + '/' + me.entity + '/load',
                    params: 'params=' + me.loadFilter + '=(I)' + Session.Principal[ me.loadFilterValue ],
                    success: function (response) {
                        me.superData = response;
                        me.config.data = response;//This will be used in setTabTitle
                        me.setTabTitle();
                        me.entityId = response.id;
                        me.init(response);
                    }
                });
            }
        } 
    },
    init: function (data) {
        var me = this,
                baseEditor = me.down('baseEditor'),
                baseDetails = me.down('baseDetails'),
                subPanel = me.down('baseSubPanel');

        if (baseEditor) {
            baseEditor.init(data);
        }

        if (baseDetails) {
            baseDetails.items.items[0].setData(data);
        }

        if (data && data.id) {
            if (subPanel) {
                me.down('baseSubPanel').show();
            }
        } else {
            if (baseEditor && baseDetails) {
                me.editMode();
            }
        }


    },
    editMode: function () {
        var me = this;
        me.items.items[0].hide();
        me.items.items[1].show();
    },
    setTabTitle: function (newData) {   //this function should be overriden when required
        var me = this,
                data = newData || me.config.data,
                title = '';

        if (me.staticTabTitle) {
            title = me.staticTabTitle;
        } else {
            if (data && data.id) {
                var titleName = data[ me.propertyTitleName || 'name']; //if data has not name, then specify 'propertyTitleName'
                title = me.viewName + ': ' + titleName;
            } else {
                title = 'New ' + me.viewName;
            }
        }
        me.setTitle(title);
    }
});
