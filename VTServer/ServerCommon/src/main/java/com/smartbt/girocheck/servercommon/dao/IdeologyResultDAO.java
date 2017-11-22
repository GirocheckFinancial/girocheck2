package com.smartbt.girocheck.servercommon.dao;

import com.smartbt.girocheck.servercommon.display.CBKCDisplay;
import com.smartbt.girocheck.servercommon.model.ApplicationParameter;
import com.smartbt.girocheck.servercommon.model.IdeologyResult;
import com.smartbt.girocheck.servercommon.utils.DateUtils;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.girocheck.servercommon.utils.bd.TransformerComplexBeans;
import com.smartbt.vtsuite.vtcommon.enums.ApplicationType;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
/*
 * @Roberto
 */

public class IdeologyResultDAO extends BaseDAO<IdeologyResult> {

    protected static IdeologyResultDAO dao;

    public static IdeologyResultDAO get() {
        if (dao == null) {
            dao = new IdeologyResultDAO();
        }
        return dao;
    }

    public List<CBKCDisplay> cbkcReport() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);

        Date startDate = calendar.getTime();
        startDate.setHours(0);
        startDate.setMinutes(0);
        startDate.setSeconds(0);
        Date endDate = calendar.getTime();
        endDate.setHours(23);
        endDate.setMinutes(59);
        endDate.setSeconds(59);

        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("address.address").as("address"))
                .add(Projections.property("address.city").as("city"))
                .add(Projections.property("state.abbreviation").as("state"))
                .add(Projections.property("address.zipcode").as("zipcode"))
                .add(Projections.property("client.telephone").as("phone"))
                .add(Projections.property("client.ssn").as("ssn"))
                .add(Projections.property("client.bornDate").as("dob"))
                .add(Projections.property("merchant").as("source"))
                .add(Projections.property("idNumber").as("cipid"))
                .add(Projections.property("disposition").as("applicationDisposition"))
                .add(Projections.property("summaryResultKey").as("ideologyResult"))
                .add(Projections.property("summaryResultMessage").as("idNotes"))
                .add(Projections.property("creationDate").as("enrollmentDate"));

        return HibernateUtil.getSession().createCriteria(IdeologyResult.class)
                .createAlias("client", "client", JoinType.LEFT_OUTER_JOIN)
                .createAlias("client.address", "address", JoinType.LEFT_OUTER_JOIN)
                .createAlias("address.state", "state", JoinType.LEFT_OUTER_JOIN)
                .add(Restrictions.between("creationDate", startDate, endDate))
                .add(Restrictions.isNotNull("summaryResultKey"))
                .add(Restrictions.isNotNull("summaryResultMessage"))
                .setProjection(projectionList)
                .setResultTransformer(new TransformerComplexBeans(CBKCDisplay.class))
                .list();
    }

    public String getCBKCIndex() {
        List<ApplicationParameter> cbkcParameters = ApplicationParameterDAO.get().listParameterByApplication(ApplicationType.CBKC);
        ApplicationParameter cbkcIndexParameter = null, cbkcLastDateParameter = null;
        Integer cbkcIndex;
        Date lastDate;
        String lastDateStr;
        Boolean lastDateWasToday = false;

        for (ApplicationParameter cbkcParameter : cbkcParameters) {
            if (cbkcParameter.getName() != null) {
                if (cbkcParameter.getName().equalsIgnoreCase("CBKC_index")) {
                    cbkcIndexParameter = cbkcParameter;
                } else {
                    if (cbkcParameter.getName().equalsIgnoreCase("CBKC_last_date")) {
                        cbkcLastDateParameter = cbkcParameter;
                    }
                }
            }
        }

        if (cbkcLastDateParameter == null) {
            cbkcIndex = 1;
            cbkcLastDateParameter = new ApplicationParameter();
            cbkcLastDateParameter.setApplication(ApplicationType.CBKC.getId());
            cbkcLastDateParameter.setDataType(1);
            cbkcLastDateParameter.setDescription("Last date a CBKC document was generated.");
            cbkcLastDateParameter.setName("CBKC_last_date");
            cbkcLastDateParameter.setReadOnly(Boolean.TRUE);
        } else {
            lastDateStr = cbkcLastDateParameter.getValue();

            if (lastDateStr != null) {
                try {
                    Long time = Long.parseLong(lastDateStr);
                    lastDate = new Date(time);
                    lastDateWasToday = DateUtils.isToday(lastDate);
                } catch (Exception e) {
                }
            } else {
                lastDate = new Date();
            }
        }

        if (cbkcIndexParameter == null) {
            cbkcIndex = 1;
            cbkcIndexParameter = new ApplicationParameter();
            cbkcIndexParameter.setApplication(ApplicationType.CBKC.getId());
            cbkcIndexParameter.setDataType(1);
            cbkcIndexParameter.setDescription("Index Used to generate CBKC document");
            cbkcIndexParameter.setName("CBKC_index");
            cbkcIndexParameter.setReadOnly(Boolean.TRUE);
            cbkcIndexParameter.setValue("2");
        } else {
            cbkcIndex = Integer.parseInt(cbkcIndexParameter.getValue());
        }

        if (!lastDateWasToday) {
            cbkcIndexParameter.setValue((cbkcIndex + 1) + "");
            ApplicationParameterDAO.get().saveOrUpdate(cbkcIndexParameter);

            cbkcLastDateParameter.setValue((new Date()).getTime() + "");
            ApplicationParameterDAO.get().saveOrUpdate(cbkcLastDateParameter);
        }

        return cbkcIndex >= 10 ? cbkcIndex + "" : "0" + cbkcIndex;
    }

}
