package com.smartbt.girocheck.servercommon.dao;

import com.smartbt.girocheck.servercommon.model.IDImagePng;
import com.smartbt.girocheck.servercommon.utils.bd.HibernateUtil;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.sql.rowset.serial.SerialBlob; 
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
/*
 * @Roberto
 */

public class IDImagePngDAO extends BaseDAO<IDImagePng> {

    protected static IDImagePngDAO dao;
 

    public static IDImagePngDAO get() {
        if (dao == null) {
            dao = new IDImagePngDAO();
        }
        return dao;
    }

    public void save(byte[] idFront, byte[] idBack, int clientId) throws SQLException {
        IDImagePng image = new IDImagePng();
        if (idFront != null) {
            image.setIdFront(new SerialBlob(idFront));
        }
        if (idBack != null) {
            image.setIdBack(new SerialBlob(idBack));
        }
        image.setClient(clientId);
        Date today = new Date(); 
        image.setCreatedAt(today);
        save(image);
    }
    
    

    public Long getRemainingConvertions() {
        Date today = new Date();
        today.setHours(0);
        today.setMinutes(0);
        today.setSeconds(0);

        Long cant = (Long) HibernateUtil.getSession().createCriteria(IDImagePng.class)
                .add(Restrictions.ge("createdAt", today))
                .setProjection(Projections.rowCount())
                .uniqueResult();

        return cant == null ? 25 : 25 - (cant * 2);
    }
    
    public List<Integer> getListOfUsersToConvert(){
        String queryString = "select c.id from client c inner join identification i on c.id = i.client where c.id not in (select client from idimages_png) order by c.created_at desc  limit " + getRemainingConvertions();
        
        System.out.println("*queryString = " + queryString);
        List<Integer> list = HibernateUtil.getSession().createSQLQuery(queryString).list();
        return list;
    }
    
    public List<Integer> testGetListOfUsersToConvert(String q){
        String queryString = q ;// + getRemainingConvertions();
        
        System.out.println("queryString = " + queryString);
        List<Integer> list = HibernateUtil.getSession().createSQLQuery(queryString).list();
        return list;
    }
}
