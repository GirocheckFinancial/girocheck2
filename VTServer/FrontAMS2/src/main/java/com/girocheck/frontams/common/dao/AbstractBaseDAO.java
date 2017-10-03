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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.hibernate.Criteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
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
        Criteria criteria = buildCriteria(request.getParams())
                .setFirstResult(request.getStart());

        if (request.getLimit() != 0) {
            criteria.setMaxResults(request.getLimit());
        }

        addOrder(criteria);

        applyListProjection(criteria);

        Map result = new HashMap();
        result.put("List", criteria.list());
        result.put("page", request.getPage());
        Long total = (Long) buildCriteria(request.getParams()).setProjection(Projections.rowCount()).uniqueResult();
        result.put("TotalCount", total);
        return result;
    }
     
    //The Criteria should already contain the ProjectionList and the transformer DTO class
    public List<D> list(Criteria criteria, Map<String, Object> params) {
       return buildCriteriaWithParams( criteria, params).list(); 
    }

    public List<NomenclatorDTO> nomenclatorList(Map<String, Object> params) {
        Criteria criteria = buildCriteria(params);

        applyNomenclatorProjection(criteria);

        return criteria.list();
    }

    protected Criteria buildCriteria(Map<String, Object> params) { 
        return buildCriteriaWithParams( buildCriteria(), params);
    }
     
    protected Criteria buildCriteriaWithParams(Criteria criteria, Map<String, Object> params) { 
        Iterator<Entry<String, Object>> entries = params.entrySet().iterator();

        while (entries.hasNext()) {
            Entry<String, Object> entry = entries.next();
            System.out.println("Adding Restriction:: " + entry.getKey() + " = " + entry.getValue());
            criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
        }

        return criteria;
    }

    public D load(Map<String, Object> params) {
        Criteria critera = buildCriteria(params);

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

    protected void applyListProjection(Criteria criteria) { } // override this method if need different projection

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
