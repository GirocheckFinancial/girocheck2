package com.smartbt.vtsuite.connector;

import com.smartbt.girocheck.servercommon.enums.ParameterName; 
import java.util.Map;

/**
 *
 * @author rrodriguez
 */
public interface Connector {
   
    public void callWS(Map<ParameterName, Object> params);
}
