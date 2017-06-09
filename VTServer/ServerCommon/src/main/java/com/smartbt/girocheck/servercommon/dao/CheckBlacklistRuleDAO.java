package com.smartbt.girocheck.servercommon.dao;

import com.smartbt.girocheck.common.VTSuiteMessages;
import com.smartbt.girocheck.servercommon.display.ClientDisplay;
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.girocheck.servercommon.model.CheckBlacklistRule;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import com.smartbt.vtsuite.vtcommon.Constants;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;



public class CheckBlacklistRuleDAO extends BaseDAO<CheckBlacklistRule>{
     
    protected static CheckBlacklistRuleDAO dao;
 

    public static CheckBlacklistRuleDAO get() {
        if ( dao == null ) {
            dao = new CheckBlacklistRuleDAO();
        }
        return dao;
    }
    
    
    
    public ResponseDataList searchRules(String searchFilter, int firstResult, int maxResult ) {
        List<ClientDisplay> list;

        Criteria criteria = getSearchCriteria(searchFilter );

        if (firstResult >= 0) {
            criteria.setFirstResult(firstResult);
            criteria.setMaxResults(maxResult);
        }
 
        list = criteria.list();

        Criteria countCriteria = getSearchCriteria(searchFilter);
        countCriteria.setProjection(Projections.rowCount());
        Long total = (Long) countCriteria.uniqueResult();

        ResponseDataList response = new ResponseDataList();

        response.setData(list);
        response.setTotalPages((int) Math.ceil((float) total / (float) maxResult));

        response.setStatus(Constants.CODE_SUCCESS);
        response.setStatusMessage(VTSuiteMessages.SUCCESS);
        return response;
    }

    private Criteria getSearchCriteria(String searchFilter) {
        Criteria criteria = HibernateUtil.getSession().createCriteria(CheckBlacklistRule.class);
 
        if (searchFilter != null && !searchFilter.isEmpty()) { 
            Disjunction disjunction = (Disjunction) Restrictions.disjunction()
                    .add(Restrictions.like("dda", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("aba", searchFilter, MatchMode.ANYWHERE).ignoreCase())
                    .add(Restrictions.like("maker", searchFilter, MatchMode.ANYWHERE).ignoreCase());

            criteria.add(disjunction);
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        } 
        return criteria; 
    }
     
    
    public boolean validateCheck(String makerName, String routingNumber, String accountNumber){
         Criteria criteria = HibernateUtil.getSession().createCriteria(CheckBlacklistRule.class);
 
         Conjunction conjunction = (Conjunction)Restrictions.conjunction()
                 .add(Restrictions.like("aba", routingNumber))
                 .add(Restrictions.like("dda", accountNumber));
         
           Disjunction disjunction = (Disjunction) Restrictions.disjunction()
                    .add(conjunction)
                    .add(Restrictions.like("maker", makerName));
           
           criteria.add(disjunction);
           criteria.setProjection(Projections.rowCount());
           
          Long cant = (Long)criteria.uniqueResult();
          
          System.out.println("cant = " + cant);
        return cant == 0;
    }
}
