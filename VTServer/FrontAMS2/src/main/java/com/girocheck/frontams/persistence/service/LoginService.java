/*
 @Autor: Roberto Rodriguez
 Email: robertoSoftwareEngineer@gmail.com

 @Copyright 2016 
 */
package com.girocheck.frontams.persistence.service;
  
import com.girocheck.frontams.persistence.dto.Principal;
import com.girocheck.frontams.persistence.dao.UserDAO;
import com.smartbt.girocheck.servercommon.utils.CryptoUtils;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author rrodriguez
 */
@Service
@Transactional
public class LoginService {

    @Autowired
    private UserDAO userDAO; 

    public Principal login(String username, String password) throws NoSuchAlgorithmException { 
        Principal principal = userDAO.getPrincipal(username, CryptoUtils.encryptPassword(password));
 
        if (principal != null) {
//            List<String> pageAccess = rolePageAccessDAO.getPageAccessByRole(principal.getRoleId());
//            principal.setPageAccess(pageAccess);
//            principal.setEntityId(getEntityId(principal));
            principal.setToken(UUID.randomUUID().toString());
        }

        return principal;
    }
    
//    private Long getEntityId(Principal principal){
//        Long entityId = 0L;
//        System.out.println("getEntityId:: principal.getId() = " + principal.getId());
//        switch(principal.getRoleId().intValue()){
//            case 4:  //PSU
//                entityId = partnerUserDAO.getPartnerIdByUser(principal.getId()); 
//                break;
//            case 5:  //MERCHANT_MANAGER
//                entityId = merchantUserDAO.getMerchantIdByUser(principal.getId()); 
//                break;
//        }
//        
//        System.out.println("entityId = " + entityId);
//        return entityId;
//    }
}
