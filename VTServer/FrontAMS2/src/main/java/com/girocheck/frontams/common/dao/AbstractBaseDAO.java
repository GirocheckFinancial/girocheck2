/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.girocheck.frontams.common.dao;

import com.smartbt.girocheck.servercommon.model.BaseEntity;
import com.girocheck.frontams.common.dto.ListRequestDTO;
import com.girocheck.frontams.common.dto.NomenclatorDTO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Criterion;
import org.hibernate.transform.Transformers;

/**
 *
 * @author rrodriguez
 */
public class AbstractBaseDAO<T extends BaseEntity, D> extends BaseDAO<T> {

    public Criteria buildCriteria() {
        return getCriteria();
    }

    public Map pageList(ListRequestDTO request) {
        Criteria criteria = buildCriteria(request.getExpressions())
                .setFirstResult(request.getStart());

        if (request.getLimit() != 0) {
            criteria.setMaxResults(request.getLimit());
        }

        applyListProjection(criteria);

        addOrder(criteria);

        Map result = new HashMap();
        result.put("List", criteria.list());
        
        if (request.getLimit() != 0) {
            result.put("page", request.getPage());
            Long total = (Long) buildCriteria(request.getExpressions()).setProjection(Projections.rowCount()).uniqueResult();
            result.put("TotalCount", total);
        }
        return result;
    }

    //The Criteria should already contain the ProjectionList and the transformer DTO class
    public List<D> list(Criteria criteria, List<Criterion> expressions) {
        return buildCriteriaWithParams(criteria, expressions).list();
    }

    public List<NomenclatorDTO> nomenclatorList(List<Criterion> expressions) {
        Criteria criteria = buildCriteria(expressions);

        applyNomenclatorProjection(criteria);

        return criteria.list();
    }

    protected Criteria buildCriteria(List<Criterion> expressions) {
        return buildCriteriaWithParams(buildCriteria(), expressions);
    }

    protected Criteria buildCriteriaWithParams(Criteria criteria, List<Criterion> expressions) {
        System.out.println("AbstractBaseDAO:: buildCriteriaWithParams");
        for (Criterion expression : expressions) {
            System.out.println("" + expression.toString());
            criteria.add(expression);
        }

        return criteria;
    }

    public static void main(String[] args) {
        System.out.println("[abc]itstarthere".substring("[abc]".indexOf("]") + 1));
    }

    public D load(List<Criterion> expressions) {
        Criteria critera = buildCriteria(expressions);

        applyLoadProjection(critera);

        D dto = (D) critera.uniqueResult();

        return dto;
    }

    public D load(Long id) {
        Criteria criteria = buildCriteria().add(Restrictions.eq("id", id));

        applyLoadProjection(criteria);

        D dto = (D) criteria.uniqueResult();

        return dto;
    }

    protected void applyLoadProjection(Criteria criteria) {
        applyListProjection(criteria); // override this method if need different projection
    }

    protected void applyListProjection(Criteria criteria) {
    } // override this method if need different projection

    protected void applyReportProjection(Criteria criteria, String reportType) {
    } // override this method if need different projection

    public void addOrder(Criteria criteria) {
        //Override this if need to add Order to the list
    }

    public NomenclatorDTO getNomenclatorById(Long id) {
        Criteria criteria = getCriteria();
        criteria.add(Restrictions.eq("id", id));
        applyNomenclatorProjection(criteria);
        return (NomenclatorDTO) criteria.uniqueResult();
    }

    protected void applyNomenclatorProjection(Criteria criteria) {
        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("id").as("id"))
                .add(Projections.property("name").as("name"));

        criteria.setProjection(projectionList)
                .setResultTransformer(Transformers.aliasToBean(NomenclatorDTO.class));

    }

    public Object getPropertyValueFromEntityId(Long entityId, String propertyName) {
        return getCriteria().add(Restrictions.eq("id", entityId))
                .setProjection(Projections.property(propertyName))
                .setMaxResults(1)
                .uniqueResult();
    }

    //Use this when the property needed is in the entity's dependence.
    public Object advancedGetPropertyValueFromEntityId(Long entityId, String propertyName) {
        return buildCriteria().add(Restrictions.eq("id", entityId))
                .setProjection(Projections.property(propertyName))
                .setMaxResults(1)
                .uniqueResult();
    }
}
