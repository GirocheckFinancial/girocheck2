Ext.define('Admin.util.Util', {
    alternateClassName: 'Util',
    statics: {
        substractString: function (str, part) {
            var index;
            if ((index = str.indexOf(part)) > -1) {
                return str.substring(0, index);
            }
            return str;
        },
        formatDate: function (dateTime, format) {
            if (dateTime) {
                var date = new Date(parseInt(dateTime));
                return Ext.Date.format(date, (format || 'm/d/Y'));
            }
            return dateTime;
        },
        formatDateTime: function (dateTime) {
            return Util.formatDate(dateTime, 'm/d/Y H:i:s');
        },
        formatAmount: function (amount) {
            return Ext.util.Format.number(amount, '0,000.00');
        },
        formatBool: function (isPlatformLevel) {
                            if (isPlatformLevel) { 
                                return "Yes";
                            }
                            return "No";
                        }
    }
});