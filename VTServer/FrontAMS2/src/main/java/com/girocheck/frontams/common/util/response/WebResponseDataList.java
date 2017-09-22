/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.girocheck.frontams.common.util.response;

import java.util.Collection;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Roberto Rodriguez   :: <roberto.rodriguez@smartbt.com>
 */
@XmlRootElement
public class WebResponseDataList<T> extends WebResponse {

    private Collection<T> data; 
    public WebResponseDataList() {
    }

    public WebResponseDataList(Collection<T> data) {
        super();
        this.data = data; 
    }

    public WebResponseDataList(Collection<T> data, String label) {
        super();
        this.data = data;  
    }
    public WebResponseDataList(Collection<T> data, Integer totalPages) {
        this(data); 
    }

    /**
     *
     * @return
     */
    public Collection<T> getData() {
        return data;
    }

    /**
     *
     * @param data
     */
    public void setData(Collection<T> data) {
        this.data = data;
    } 
}