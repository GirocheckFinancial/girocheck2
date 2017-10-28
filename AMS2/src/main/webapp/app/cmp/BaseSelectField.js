Ext.define('Admin.cmp.BaseSelectField', {
    extend: 'Ext.form.ComboBox',
    xtype: 'baseSelectField',
    requires: [],
    displayField: 'name',
    valueField: 'id',
    url: '',
    prefix: '(L)',
    applyFilter: null, //if true, apply the parent tab's filter
    //  applyFilter can have the filter to apply
    filterPropertyName: null, // Ej:  data.propertyName  o  superData.propertyName
    ////poner aqui el nombre de la propiedad en el 'data' o el 'superData' object
    //que sera usada en el filtro
    //SINO se espercifica se tomara viewTab.superEntityId
    noFilterOnCreate: null, //No apply any filters when creatingnew entity
    loadDetailViewFieldAfterInit: null,
    //Usar cuando queremos que se refresque en el campo equivalente en la vista Details
    //cuando el combo se cargue y tome su valor
    //(Se usa cuando el nombre no viene en la data, solo el id)
    defaultPropertyValue: null, // Ej:  data.propertyName  o  superData.propertyName
    //This is the propertyName from data or superData that will be taken
    //for the default value
    style: 'background-color: #fff;',
    disabledOnCreate: null,
    disabledOnEdit: null,
    disabledIfHasValue: null,
    disableIfOtherComponentIsEmpty: null, //put the name of the other component
    initComponent: function () {
        var me = this,
                container = me.up().up().up().up(),
                principal = Session.Principal,
                token = principal ? principal.token : '';
        this.callParent();

        var store = Ext.create('Admin.cmp.BaseSelectFieldStore', {});

        store.proxy.url = WS.HOST + container.xtype + '/' + me.url + '/nomenclator';
         
        store.proxy.headers = {'TOKEN': token};
        
        this.setStore(store);
    },
    listeners: {
        select: function (ele, rec, idx) {

            var me = this,
                    triggerTarget = me.getTriggerTarget();

            if (triggerTarget) {
                triggerTarget.enable();
                triggerTarget.doReset();
                triggerTarget.setParams(me.getParamFromUrl() + rec.id);
                triggerTarget.getStore().reload();
            }
        }
    },
    init: function (data) {
        var me = this,
                viewTab = me.up().up().up(),
                filterValue;

        if (me.applyFilter && !(me.noFilterOnCreate && (!data || !data.id))) {
            var filter;

            if (me.applyFilter === true) { //if true, apply the parent tab's filter
                filter = viewTab.getFilters(true);
            } else {// if applyFilter has the filter to apply  

                if (me.filterPropertyName) {  // Ej:  data.propertyName  o  superData.propertyName
                    filterValue = me.getValueFromSource(me.filterPropertyName, data, viewTab, true);

                    if (!filterValue) { //If not found valuem not apply any filter
                        me.applyFilter = '';
                    }
                } else {
                    filterValue = viewTab.superEntityId;
                }

                filter = me.applyFilter + filterValue;
            }
            me.setParams(filter);
        }


        if (data && (data[me.name] || filterValue)) {  //inestable yet.. test a lot!!
            me.getStore().load({
                scope: me,
                callback: function (records, operation, success, test) {

                    if (this.loadDetailViewFieldAfterInit) {
                        //Esto se hace porque el combo box no recibe el valor hasta que no se carga
                        //Entonces tenemos que esperar a que se cargue, para tomar el nombre del
                        //campo seleccionado y pasarselo al campo correspondiente en el 'Details' view.
                        //OJO
                        //Solo se usa cuando el nombre no viene en la Data y necesitamos tomarlo de
                        //lo que muestre el combo.
                        var me = this,
                                baseDetails = me.up().up().up().down('baseDetails');

                        if (baseDetails) {
                            var htmlContainer = baseDetails.items.items[0].getEl().dom,
                                    htmlElement = htmlContainer.getElementsByClassName(me.name);

                            if (htmlElement && htmlElement.length > 0) {
                                htmlElement = htmlElement[0];
                                htmlElement.innerHTML = me.rawValue;
                            }
                        }
                    }
                }
            });
            var val = data[me.name];
            me.setValue(val);

            if (me.trigger) {
                var nextField = me.up().up().getForm().findField(me.trigger);
                nextField.setParams(me.getParamFromUrl() + val);
            }
        }

        if (me.defaultPropertyValue) {
            var defaultPropertyValue = me.getValueFromSource(me.defaultPropertyValue, data, viewTab, false);
            if (defaultPropertyValue) {
                me.setValue(defaultPropertyValue);
            }
        }

        if (data && data.id) {
            if (me.disabledOnEdit) {
                me.disable();
            }
        } else {
            if (me.disabledOnCreate) {
                me.disable();
            }
        }

        if (me.disabledIfHasValue && me.getValue()) {
            me.disable();
        }

        if (me.disableIfOtherComponentIsEmpty) {
            var otherComponent = me.getAnotherComponent(me.disableIfOtherComponentIsEmpty);
            if (!otherComponent.getValue()) {
                me.disable();
            }
        }
    },
    doReset: function () {
        var me = this,
                triggerTarget = me.getTriggerTarget();
        me.reset();

        if (triggerTarget) {
            triggerTarget.doReset();
        }
    },
    getTriggerTarget: function () {
        var me = this;
        if (me.trigger) {
            return me.getAnotherComponent(me.trigger);
        }
    },
    getAnotherComponent: function (name) {
        return this.up().up().getForm().findField(name);
    },
    getParamFromUrl: function () {
        var me = this,
                url = me.url;
        return url + '.id=(L)';
    },
    setParams: function (params) {
        this.getStore().setParams(params);
    },
    getValueFromSource: function (sourcePropertyName, data, viewTab, disableIfNotSource) {
        var sourceName = sourcePropertyName.split('.')[0],
                propertyName = sourcePropertyName.split('.')[1],
                source,
                me = this;

        if (sourceName === 'data' && data[propertyName]) { //If the value is not in data, look in superData
            source = data;
        } else {
            source = viewTab.superData;
        }

        if (source) {
            return source[propertyName];
        } else {
            if (disableIfNotSource) {
                me.disable();
            }
        }
        return '';
    }
});

Ext.define('Admin.cmp.BaseSelectFieldStore', {
    extend: 'Ext.data.Store',
    fields: [
        {name: 'id'},
        {name: 'name'}
    ],
    proxy: {
        type: 'ajax',
        url: null,
        reader: {
            type: 'json',
            rootProperty: 'data'
        }
    },
    setParams: function (param) {
        var me = this,
                url = me.proxy.url;

        if (url.indexOf('?params=') > -1) {
            url = url.split('?params=')[0];
        }

        me.proxy.url = url + '?params=' + param;
    }
});


