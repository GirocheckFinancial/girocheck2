/*
 @Autor: Roberto Rodriguez
 Email: robertoSoftwareEngineer@gmail.com

 @Copyright 2016 
 */
package com.girocheck.frontams.persistence.manager;

import com.girocheck.frontams.common.dao.AbstractBaseDAO;
import com.girocheck.frontams.persistence.dao.UserDAO;
import com.smartbt.girocheck.servercommon.model.User;
import com.girocheck.frontams.common.manager.AbstractManager; 
import com.girocheck.frontams.persistence.dto.UserDTO;
import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rrodriguez
 */
@Service
@Transactional
public class UserManager extends AbstractManager<User, UserDTO> {

    @Autowired
    private UserDAO userDAO;
      
    @Override
    public AbstractBaseDAO dao() {
        return userDAO;
    }

    @Override
    protected User create(Map<String, Object> data) throws Exception {
        User user = new User(); 
        update(user, data);

        return user;
    }

    @Override
    protected void update(User entity, Map<String, Object> data) {
        //Ignore for this version
        
//        entity.setName((String) data.get("name"));
//        entity.setLastName((String) data.get("lastName"));
//        entity.setUsername((String) data.get("username"));
//        entity.setPassword((String) data.get("password"));
//        entity.setAddress((String) data.get("address"));
//        entity.setPhone((String) data.get("phone"));
//        
//        Long roleId = (Long) data.get("roleId");
//        if(roleId != null){
//            entity.setRole(roleDAO.findById(roleId));
//        }
    }

}
