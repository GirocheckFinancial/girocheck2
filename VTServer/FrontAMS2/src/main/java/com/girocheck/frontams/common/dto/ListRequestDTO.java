/*
 @Autor: Roberto Rodriguez
 Email: robertoSoftwareEngineer@gmail.com

 @Copyright 2016 
 */
package com.girocheck.frontams.common.dto;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.criterion.Criterion;

/**
 *
 * @author rrodriguez
 */
public class ListRequestDTO {

    private Integer page;
    private Integer start;
    private Integer limit;

    private List<Criterion> expressions = new ArrayList<>(); 

    public ListRequestDTO() {
    }

    public ListRequestDTO(Integer page, Integer start, Integer limit,List<Criterion> expressions) {
        this.page = page;
        this.start = start;
        this.limit = limit;
        this.expressions = expressions; 
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
     * @return the expressions
     */
    public List<Criterion> getExpressions() {
        return expressions;
    }

    /**
     * @param expressions the expressions to set
     */
    public void setExpressions(List<Criterion> expressions) {
        this.expressions = expressions;
    }

}
