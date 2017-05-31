/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.girocheck.servercommon.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smartbt.girocheck.servercommon.model.Transaction;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import org.hibernate.criterion.Restrictions;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartbt.girocheck.servercommon.display.TransactionDisplay;
import com.smartbt.girocheck.servercommon.model.FiltersReport;
import com.smartbt.girocheck.servercommon.utils.bd.TransformerComplexBeans;
import com.smartbt.girocheck.servercommon.display.report.TransactionDetailReportDisplay;
import com.smartbt.vtsuite.servercommon.utils.DateUtils;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.sql.JoinType;

/**
 *
 * @author Roberto Rodriguez
 */
public class ReportDAO extends BaseDAO<Transaction> {

    protected static ReportDAO dao;
    ObjectMapper objectMapper = new ObjectMapper();

    public ReportDAO() {
    }

    public static ReportDAO get() {
        if (dao == null) {
            dao = new ReportDAO();
        }
        return dao;
    }

    public String detailListingTransactionReport(FiltersReport filters) throws JsonProcessingException {
        Criteria cri = HibernateUtil.getSession().createCriteria(Transaction.class)
                .createAlias("terminal", "terminal", JoinType.LEFT_OUTER_JOIN)
                .createAlias("merchant", "merchant", JoinType.LEFT_OUTER_JOIN)
                .createAlias("client", "client", JoinType.LEFT_OUTER_JOIN)
                .createAlias("client.address", "address", JoinType.LEFT_OUTER_JOIN)
                .createAlias("address.state", "state", JoinType.LEFT_OUTER_JOIN)
                .createAlias("check", "check", JoinType.LEFT_OUTER_JOIN)
                .createAlias("data_sc1", "data_sc1", JoinType.LEFT_OUTER_JOIN)
                .addOrder(Order.desc("dateTime"));

        Date startRangeDate = filters.getStartRangeDate();
        Date endRangeDate = filters.getEndRangeDate();
        String operation = filters.getOperation();
        Integer transactionType = filters.getTransactionType();
        Boolean pending = filters.getPending();
        Boolean filterAmmount = filters.getFilterAmmount();
        String ammountString = filters.getAmmountString();
        Integer ammountType = filters.getAmmountType();
        Integer opType = filters.getOpType();
        String searchFilter = filters.getSearchFilter();

        System.out.println("startRangeDate + " + startRangeDate);
        System.out.println("endRangeDate + " + endRangeDate);

        setDateRange(startRangeDate, endRangeDate, cri);

        if (startRangeDate != null) {
            startRangeDate.setHours(0);
            startRangeDate.setMinutes(0);
            startRangeDate.setSeconds(0);
            cri.add(Restrictions.ge("dateTime", startRangeDate));
        }

        if (endRangeDate != null) {
            endRangeDate.setHours(23);
            endRangeDate.setMinutes(59);
            endRangeDate.setSeconds(59);
            cri.add(Restrictions.le("dateTime", endRangeDate));
        }

        if (operation != null && (operation.contains("01") || operation.contains("02"))) {
            cri.add(Restrictions.like("operation", operation, MatchMode.ANYWHERE).ignoreCase());
        }

        if (transactionType != 0) {
            cri.add(Restrictions.eq("transactionType", transactionType));
        } else {
            cri.add(Restrictions.ne("transactionType", 5));
        }

        if (pending) {
            //if pending is checked gonna bring unfinished transactions
            cri.add(Restrictions.eq("transactionFinished", false));
        }

        try {
            if (filterAmmount) {
                Double ammount = Double.parseDouble(ammountString);

                String field;

                switch (ammountType) {
                    case 1:
                        field = "ammount";
                        break;
                    case 2:
                        field = "feeAmmount";
                        break;
                    case 3:
                        field = "payoutAmmount";
                        break;
                    default:
                        field = "ammount";
                }

                switch (opType) {
                    case 1:
                        cri.add(Restrictions.gt(field, ammount));
                        break;
                    case 2:
                        cri.add(Restrictions.eq(field, ammount));
                        break;
                    case 3:
                        cri.add(Restrictions.lt(field, ammount));
                        break;
                    default:
                        field = "ammount";
                }
            }
        } catch (NumberFormatException e) {
//            log.debug( "[TransactionDAO] NumberFormatException" );
            e.printStackTrace();
        }

        if (searchFilter != null && !searchFilter.isEmpty()) {
            Disjunction disjunction = (Disjunction) Restrictions.disjunction()
                    .add(Restrictions.like("resultMessage", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("account", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("errorCode", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("errorCode", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("merchant.legalName", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("terminal.serialNumber", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("client.firstName", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("client.ssn", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("client.lastName", searchFilter, MatchMode.ANYWHERE).ignoreCase());

            Criterion dateRestriction = DateUtils.getRestrictionForDateFilter(searchFilter, "dateTime");
            if (dateRestriction != null) {
//                log.debug( "[TransactionDAO] ( dateRestriction != null )" );
                disjunction.add(dateRestriction);
            }
            cri.add(disjunction);

        }

        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("id").as("id"))
                .add(Projections.property("transactionType").as("transactionType"))
                .add(Projections.property("dateTime").as("dateTime"))
                .add(Projections.property("operation").as("operation"))
                .add(Projections.property("data_sc1.maskCardNumber").as("maskCardNumber"))
                .add(Projections.property("ammount").as("amount"))
                .add(Projections.property("feeAmmount").as("feeAmount"))
                .add(Projections.property("payoutAmmount").as("payoutAmount"))
                .add(Projections.property("id").as("requestId"))
                .add(Projections.property("resultCode").as("resultCode"))
                .add(Projections.property("merchant.legalName").as("merchantName")) 
                .add(Projections.property("certegyApprovalNumber").as("certegyApprovalNumber"))
                .add(Projections.property("terminal.serialNumber").as("terminalSerial"))
                .add(Projections.property("merchant.idPosOrderExp").as("idPosOrderExp"))
                .add(Projections.property("client.firstName").as("clientFirstName"))
                .add(Projections.property("client.lastName").as("clientLastName"))
                .add(Projections.property("client.telephone").as("clientPhone"))
                .add(Projections.property("check.paymentCheck").as("checkNumber"))
                .add(Projections.property("resultMessage").as("resultMessage"))
                .add(Projections.property("address.address").as("clientStreet"))
                .add(Projections.property("address.city").as("clientCity"))
                .add(Projections.property("address.zipcode").as("clientZipCode"))
                .add(Projections.property("state.abbreviation").as("clientState"))
                .add(Projections.property("check.makerName").as("makerName"));
        cri.setProjection(projectionList); 
        cri.setResultTransformer(new TransformerComplexBeans(TransactionDetailReportDisplay.class));

        String str = objectMapper.writeValueAsString(cri.list());
        System.out.println("str = " + objectMapper.writeValueAsString(cri.list()));
        return str;
    }

    public String detailTransactionReport(FiltersReport filters) throws JsonProcessingException {
        Criteria cri = HibernateUtil.getSession().createCriteria(Transaction.class)
                .createAlias("terminal", "terminal", JoinType.LEFT_OUTER_JOIN)
                .createAlias("merchant", "merchant", JoinType.LEFT_OUTER_JOIN)
                .createAlias("data_sc1", "data_sc1", JoinType.LEFT_OUTER_JOIN)
                .createAlias("client", "client", JoinType.LEFT_OUTER_JOIN)
                .addOrder(Order.desc("dateTime"));

        Date startRangeDate = filters.getStartRangeDate();
        Date endRangeDate = filters.getEndRangeDate();
        String operation = filters.getOperation();
        Integer transactionType = filters.getTransactionType();
        Boolean pending = filters.getPending();
        Boolean filterAmmount = filters.getFilterAmmount();
        String ammountString = filters.getAmmountString();
        Integer ammountType = filters.getAmmountType();
        Integer opType = filters.getOpType();
        String searchFilter = filters.getSearchFilter();

        System.out.println("startRangeDate + " + startRangeDate);
        System.out.println("endRangeDate + " + endRangeDate);

        setDateRange(startRangeDate, endRangeDate, cri);
        
        if (operation != null && (operation.contains("01") || operation.contains("02"))) {
            cri.add(Restrictions.like("operation", operation, MatchMode.ANYWHERE).ignoreCase());
        }

        if (transactionType != 0) {
            cri.add(Restrictions.eq("transactionType", transactionType));
        } else {
            cri.add(Restrictions.ne("transactionType", 5));
        }

        if (pending) {
            //if pending is checked gonna bring unfinished transactions
            cri.add(Restrictions.eq("transactionFinished", false));
        }

        try {
            if (filterAmmount) {
                Double ammount = Double.parseDouble(ammountString);

                String field;

                switch (ammountType) {
                    case 1:
                        field = "ammount";
                        break;
                    case 2:
                        field = "feeAmmount";
                        break;
                    case 3:
                        field = "payoutAmmount";
                        break;
                    default:
                        field = "ammount";
                }

                switch (opType) {
                    case 1:
                        cri.add(Restrictions.gt(field, ammount));
                        break;
                    case 2:
                        cri.add(Restrictions.eq(field, ammount));
                        break;
                    case 3:
                        cri.add(Restrictions.lt(field, ammount));
                        break;
                    default:
                        field = "ammount";
                }
            }
        } catch (NumberFormatException e) { 
            e.printStackTrace();
        }

        if (searchFilter != null && !searchFilter.isEmpty()) {
            Disjunction disjunction = (Disjunction) Restrictions.disjunction()
                    .add(Restrictions.like("resultMessage", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("account", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("errorCode", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("errorCode", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("merchant.legalName", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("terminal.serialNumber", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("client.firstName", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("client.ssn", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("client.lastName", searchFilter, MatchMode.ANYWHERE).ignoreCase());

            Criterion dateRestriction = DateUtils.getRestrictionForDateFilter(searchFilter, "dateTime");
            if (dateRestriction != null) {
//                log.debug( "[TransactionDAO] ( dateRestriction != null )" );
                disjunction.add(dateRestriction);
            }
            cri.add(disjunction);

        }

        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("id").as("id"))
                .add(Projections.property("transactionType").as("transactionType"))
                .add(Projections.property("dateTime").as("dateTime"))
                .add(Projections.property("operation").as("operation"))
                .add(Projections.property("data_sc1.maskCardNumber").as("maskCardNumber"))
                .add(Projections.property("ammount").as("amount"))
                .add(Projections.property("feeAmmount").as("feeAmount"))
                .add(Projections.property("payoutAmmount").as("payoutAmount"))
                .add(Projections.property("id").as("requestId"))
                .add(Projections.property("resultCode").as("resultCode"))
                .add(Projections.property("client.firstName").as("clientFirstName"))
                .add(Projections.property("client.lastName").as("clientLastName"));
        cri.setProjection(projectionList);
        cri.setResultTransformer(new TransformerComplexBeans(TransactionDetailReportDisplay.class));
        ;
        List<TransactionDetailReportDisplay> list = cri.list();

        return objectMapper.writeValueAsString(list);
    }

    public int saveFiltersReport(FiltersReport filters) {
        System.out.println("ReportDAO -> saveFiltersReport()");
        Session session = HibernateUtil.getSession();
        session.saveOrUpdate(filters);
        session.flush();

        return filters.getId();
    }

    public FiltersReport getFiltersReport(int id) {
        FiltersReport filter = (FiltersReport) HibernateUtil.getSession().createCriteria(FiltersReport.class)
                .add(Restrictions.eq("id", id)).uniqueResult();

        if (filter != null) {
            HibernateUtil.getSession().delete(filter);
        }

        return filter;
    }

    private void setDateRange(Date dateStart, Date dateEnd, Criteria criteria) {
        if (dateStart == null && dateEnd == null) {
            System.out.println("Both dates are NULL"); 
            dateEnd = new Date();
            dateStart =  getDateWithMonthDifference(dateEnd, -2); 
        } else {
            if (dateEnd == null) {
                System.out.println("dateEnd is NULL");
                dateEnd = getDateWithMonthDifference(dateStart, 2);
                System.out.println("Setting dateEnd to " + dateEnd);
            } else {
                if (dateStart == null) {
                    System.out.println("dateStart is NULL");
                    dateStart = getDateWithMonthDifference(dateEnd, -2);
                    System.out.println("Setting dateStart to " + dateStart);
                } else {
                    if ((dateEnd.getTime() - dateStart.getTime()) / (1000 * 60 * 60 * 24) > 60) {
                        System.out.println("DateEnd < dateStart > 30 days");
                        dateStart = getDateWithMonthDifference(dateEnd, -2);
                        System.out.println("Setting dateStart to " + dateStart);
                    }
                }
            }
        }

        dateStart.setHours(0);
        dateStart.setMinutes(0);
        dateStart.setSeconds(0);

        dateEnd.setHours(23);
        dateEnd.setMinutes(59);
        dateEnd.setSeconds(59);

        criteria.add(Restrictions.ge("dateTime", dateStart));
        criteria.add(Restrictions.le("dateTime", dateEnd));
    }

    private static Date getDateWithMonthDifference(Date date, Integer difference) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, difference);
        return c.getTime();
    }
}
