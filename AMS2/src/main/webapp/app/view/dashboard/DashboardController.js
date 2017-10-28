Ext.define('Admin.view.dashboard.DashboardController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.dashboard',
    monthNames: ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"],
    currentMonth: (new Date()).getMonth(),
    currentYear: (new Date()).getYear() + 1900,
    time: 0, //0-daily  1-monthly  2-yearly
    transactionType: 0,
    operationPattern: '1111',
    charts: ['transactionsChart', 'earnings', 'servicesPieChart'],
    operationButtons: ['checkButton', 'cashButton', 'cardToBankButton', 'commissionButton'],
    requires: [
        'Ext.util.TaskRunner'
    ],
    init: function () {
        var me = this,
                view = me.getView();

        this.setTitle();
    },
    refreshCharts: function (transactionType) {
        var me = this;

        this.charts.forEach(function (chartType) {
            var chart = me.getView().down('#' + chartType),
                    store = chart.getStore();

            store.proxy.url = me.buildUrl(chartType);
            store.load();
        })
    },
    buildUrl: function (chartType) {
        var url = WS.HOST + 'dashboard/transaction/chart';
        url += '?chartType=' + chartType;
        url += '&timeType=' + this.time;
        url += '&month=' + this.currentMonth;
        url += '&year=' + this.currentYear;
        url += '&transactionType=' + this.transactionType;
        url += '&operationPattern=' + this.operationPattern;
        return url;
    },
    onOperationChange: function (btn) {
        var me = this,
                dashboard = me.getView();

        if (btn.isactive) {
            btn.removeCls(btn.activeCls);
        } else {
            btn.addCls(btn.activeCls);
        }

        btn.isactive = !btn.isactive;
        var pattern = '';

        this.operationButtons.forEach(function (buttonId) {
            pattern += dashboard.down('#' + buttonId).isactive ? 1 : 0
        });

        this.operationPattern = pattern;

        this.charts.forEach(function (chartType, index) {
            if (index > 0) {
                var chart = me.getView().down('#' + chartType),
                        store = chart.getStore();

                store.proxy.url = me.buildUrl(chartType);
                store.load();
            } 
        })

    },
    onTransactionTypeChange: function (a, transactionType, c) {
        var me = this,
                dashboard = me.getView();

        this.transactionType = transactionType;

        var chart = dashboard.down('transactionsChart').items.items[0],
                store = chart.getStore();
        store.proxy.url = me.buildUrl('transactionsChart');
        store.load();
    },
    onScrollTime: function (btn, b, c) {
        var scroll = btn.value;

        switch (this.time) {
            case 0:
                if (this.currentYear == (new Date()).getYear() + 1900 && (this.currentMonth + scroll > (new Date()).getMonth())) {
                    return;
                }

                this.currentMonth += scroll;

                if (this.currentMonth < 0) {
                    this.currentYear--;
                    this.currentMonth = 11;
                }

                if (this.currentMonth > 11) {
                    this.currentYear++;
                    this.currentMonth = this.currentMonth % 12;
                }
                break;
            case 1:
                if (this.currentYear + scroll > (new Date()).getYear() + 1900) {
                    return;
                }
                this.currentYear += scroll;
                break;
        }

        this.setTitle();
        this.refreshCharts();
    },
    onTimeChange: function (btn, b, c) {
        var toolbar = btn.up();
        for (var i = 0; i < 3; i++) {
            toolbar.down('#timeButton' + i).removeCls('active-button');
        }

        btn.addCls('active-button');
        this.time = btn.time;
        this.setTitle();
        this.refreshCharts();
    },
    setTitle: function () {
        var timeStr = '';
        var dateStr = '';

        switch (this.time) {
            case 0:
                timeStr = 'Daily';
                break;
            case 1:
                timeStr = 'Monthly';
                break;
            case 2:
                timeStr = 'Yearly';
                break;
        }

        switch (this.time) {
            case 0:
                dateStr = 'of ' + this.monthNames[this.currentMonth ] + ', ' + this.currentYear;
                break;
            case 1:
                dateStr = 'of ' + this.currentYear;
                break;
            case 2:
                dateStr = '';
                break;
        }

        this.getView().setTitle(timeStr + ' Operations ' + dateStr);
    },
    onRefreshToggle: function (tool, e, owner) {
    },
    clearChartUpdates: function () {
    },
    destroy: function () {
        this.clearChartUpdates();
        this.callParent();
    },
    onHideView: function () {
        this.clearChartUpdates();
    }
});
