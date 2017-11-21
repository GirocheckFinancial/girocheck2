package com.smartbt.vtsuite.dao;

import com.smartbt.girocheck.servercommon.dao.BaseDAO; 
import com.smartbt.girocheck.servercommon.model.TerminalParameterValue;

import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil; 

/**
 *
 * @author Roberto Rodriguez
 */
public class TerminalParameterValueDAO extends BaseDAO<TerminalParameterValue> {

    protected static TerminalParameterValueDAO dao;

    public static TerminalParameterValueDAO get() {
        if (dao == null) {
            dao = new TerminalParameterValueDAO();
        }
        return dao;
    }

    public void removeTerminalParameterValueByIdTerminal(int idTerminal) {
//        String query = "delete from terminal_parameter_value  where id_terminal = " + idTerminal;
//        HibernateUtil.getSession().createSQLQuery(query).executeUpdate();

        String hql = "delete from " + TerminalParameterValue.class.getName() + " where terminal.id = :idTerminal";
        HibernateUtil.getSession().createQuery(hql)
                .setInteger("idTerminal", idTerminal)
                .executeUpdate();

    }

}
