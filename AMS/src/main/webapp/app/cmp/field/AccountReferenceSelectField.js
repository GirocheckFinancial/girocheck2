Ext.define('Admin.cmp.field.AccountReferenceSelectField', {
    extend: 'Admin.cmp.BaseSelectField',
    xtype: 'accountReferenceSelectField',
    fieldLabel: 'Account Reference',
    name: 'accountReferenceId', //name of the merchant and id of the merchant Program
    url: 'accountReference',
    applyFilter: 'accountReferenceConfig.id=(I)',
    filterPropertyName: 'data.accountReferenceConfigId',
    loadDetailViewFieldAfterInit: true,
    disabledOnCreate:true  //When creating, there is not account references to pick from.
});


