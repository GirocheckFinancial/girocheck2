/*
 @Autor: Roberto Rodriguez
 Email: robertoSoftwareEngineer@gmail.com

 @Copyright 2016 
 */
package com.girocheck.frontams.common.manager;

import com.girocheck.frontams.common.dao.AbstractBaseDAO;
import com.smartbt.girocheck.servercommon.model.BaseEntity;
import com.girocheck.frontams.common.dto.ListRequestDTO;
import com.girocheck.frontams.common.dto.NomenclatorDTO;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rrodriguez
 */
@Transactional
public abstract class AbstractManager<T extends BaseEntity, D> {

    public Map pageList(ListRequestDTO request) {
        return dao().pageList(request);
    }

    public List<NomenclatorDTO> nomenclatorList(Map<String, Object> params) { 
        return dao().nomenclatorList(params);
    }

    public D load(Map<String, Object> params) {
        D entity = (D) dao().load(params);
        completeLoad(entity);
        return entity;
    }

    public D load(Long id) {
        D entity = (D) dao().load(id);
        completeLoad(entity);
        return entity;
    }

    protected void completeLoad(D dto) {
        // override this method if need add more things to the DTO
    }

    public abstract AbstractBaseDAO dao();

    protected abstract T create(Map<String, Object> data) throws Exception;

    protected void update(T entity, Map<String, Object> data) throws Exception{}

    public int save(Map<String, Object> data) throws Exception {
        System.out.println("AbstractManager -> creating...");
        T entity;
        Boolean creating = (data == null || !data.containsKey("id"));
        System.out.println("AbstractManager -> creating = " + creating);
        if (creating) {
            entity = create(data);
        } else {
            Long id = (Long) data.get("id");
            entity = (T) dao().findById(id);

            if (entity != null) {
                update(entity, data);
            }
        }

        entity = (T) dao().saveOrUpdate(entity);

        if (creating) {
            afterCreate(entity);
        }

        return entity.getId();
    }

    public T findById(Long id) {
        return (T) dao().findById(id);
    }

    public Object getPropertyValueFromEntityId(Long entityId, String propertyName) {
        return dao().getPropertyValueFromEntityId(entityId, propertyName);
    }

    protected void afterCreate(T entity) {
    } //Redefine if need to do something after save
}
