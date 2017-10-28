 
package com.girocheck.frontams.conf.interceptors;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 *
 * @author Ariel Saavedra
 */
@ControllerAdvice
public class ExceptionInterceptor {

    static Logger log = Logger.getLogger(ExceptionInterceptor.class);

//    @ResponseBody
//    @ExceptionHandler(Exception.class)
//    public WebResponse handleGeneralError(Exception ex) {
//        exceptionCommon(ex);
//        return new WebResponse(Constants.CODE_ERROR_GENERAL, ex.getMessage());
//    }
//
//    @ResponseBody
//    @ExceptionHandler(javax.ws.rs.ForbiddenException.class)
//    public WebResponse handleForbiddenException(javax.ws.rs.ForbiddenException ex) {
//        exceptionCommon(ex);
//        return new WebResponse(Constants.CODE_NOT_PRIVILEGE, ex.getMessage());
//    }
//
//    @ResponseBody
//    @ExceptionHandler(javax.xml.bind.ValidationException.class)
//    public WebResponse handleMethodArgumentNotValidException(javax.xml.bind.ValidationException ex) {
//        exceptionCommon(ex);
//        return new WebResponse(Constants.CODE_INVALID_ENTRY_DATA, ex.getMessage());
//    }
//    
//    @ResponseBody
//    @ExceptionHandler(CustomException.class)
//    public WebResponse handleGeneralError(CustomException ex) {
//        exceptionCommon(ex);
//        return new WebResponse(ex.getCode() , ex.getMessage());
//    }
//    
//    private void exceptionCommon(Exception ex){
//        log.error(ex, ex);
//        //HibernateUtil.rollbackTransaction();
//    }
}
