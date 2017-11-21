package com.smartbt.vtsuite.connector;

import com.smartbt.girocheck.servercommon.enums.ParameterName; 
import com.smartbt.vtsuite.util.FissParam;
import java.util.Map;

/**
 *
 * @author rrodriguez
 */
public interface Connector {
   
    public Map<FissParam, Object> callWS(Map<ParameterName, Object> params);
}
