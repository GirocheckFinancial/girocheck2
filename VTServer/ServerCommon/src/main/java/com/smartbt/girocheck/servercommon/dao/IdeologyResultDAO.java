package com.smartbt.girocheck.servercommon.dao;
 
import com.smartbt.girocheck.servercommon.model.IdeologyResult;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
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
 
    
    
    public void deleteAll( ) {
        HibernateUtil.getSession().createSQLQuery("delete from ideology_result").executeUpdate();
    }
}
