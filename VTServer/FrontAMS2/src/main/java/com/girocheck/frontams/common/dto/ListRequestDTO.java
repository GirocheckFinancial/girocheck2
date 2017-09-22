/*
 @Autor: Roberto Rodriguez
 Email: robertoSoftwareEngineer@gmail.com

 @Copyright 2016 
 */
package com.girocheck.frontams.common.dto;

import com.girocheck.frontams.common.util.GRUtil;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author rrodriguez
 */
public class ListRequestDTO {

    private Integer page;
    private Integer start;
    private Integer limit;

    private Map<String, Object> params = new HashMap();

    public ListRequestDTO() {
    }

    public ListRequestDTO(Integer page, Integer start, Integer limit, Map<String, Object> params) {
        this.page = page;
        this.start = start;
        this.limit = limit;
        this.params = params; 
    }

    /**
     * @return the page
     */
    public Integer getPage() {
        return page;
    }

    /**
     * @param page the page to set
     */
    public void setPage(Integer page) {
        this.page = page;
    }

    /**
     * @return the start
     */
    public Integer getStart() {
        return start;
    }

    /**
     * @param start the start to set
     */
    public void setStart(Integer start) {
        this.start = start;
    }

    /**
     * @return the limit
     */
    public Integer getLimit() {
        return limit;
    }

    /**
     * @param limit the limit to set
     */
    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    /**
     * @return the params
     */
    public Map<String, Object> getParams() {
        return params;
    }

    /**
     * @param params the params to set
     */
    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

}
