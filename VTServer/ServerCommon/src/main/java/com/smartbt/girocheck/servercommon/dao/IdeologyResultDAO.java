package com.smartbt.girocheck.servercommon.dao;

import com.smartbt.girocheck.servercommon.display.CBKCDisplay;
import com.smartbt.girocheck.servercommon.model.IdeologyResult;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.girocheck.servercommon.utils.bd.TransformerComplexBeans;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
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
                .add(Projections.property("merchant").as("source"))
                .add(Projections.property("idNumber").as("cipid"))
                .add(Projections.property("disposition").as("applicationDisposition"))
                .add(Projections.property("summaryResultKey").as("ideologyResult"))
                .add(Projections.property("summaryResultMessage").as("idNotes"))
                .add(Projections.property("creationDate").as("enrollmentDate"));

        return HibernateUtil.getSession().createCriteria(IdeologyResult.class)
                .add(Restrictions.between("creationDate", startDate, endDate))
                .add(Restrictions.isNotNull("summaryResultKey"))
                .add(Restrictions.isNotNull("summaryResultMessage"))
                .setProjection(projectionList)
                .setResultTransformer(new TransformerComplexBeans(CBKCDisplay.class))
                .list();
    }

}
