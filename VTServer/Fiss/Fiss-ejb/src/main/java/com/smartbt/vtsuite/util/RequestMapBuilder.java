package com.smartbt.vtsuite.util;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author rrodriguez
 */
public class RequestMapBuilder {

    
    public static Map<FissParam, String> staticFissParams;

    static {
        staticFissParams = new HashMap<>();
        staticFissParams.put(FissParam.FISS_SERVICE_VERSION, FissUtil.FISS_SERVICE_VERSION);
        staticFissParams.put(FissParam.SOURCE_ID, FissUtil.SOURCE_ID);
        staticFissParams.put(FissParam.ROUTING_ID, FissUtil.ROUTING_ID);
        staticFissParams.put(FissParam.USER, FissUtil.FISS_USERNAME);
    }

    private static RequestMapBuilder INSTANCE;

    public static RequestMapBuilder get() {
        if (INSTANCE == null) {
            INSTANCE = new RequestMapBuilder();
        }
        return INSTANCE;
    }

    public static Map<FissParam, String> buildParamsMap(Map<ParameterName, Object> params) {
        Map<FissParam, String> fissParams = new HashMap<>();
        fissParams.putAll(staticFissParams);
        fissParams.put(FissParam.TRANSACTION_TYPE, params.get(ParameterName.TRANSACTION_TYPE).toString());
        fissParams.put(FissParam.MSG_UUID, FissUtil.generateUUID());
        fissParams.put(FissParam.REQUEST_ID, FissUtil.generateUUID());
        fissParams.put(FissParam.PASSWORD, FissUtil.FISS_PASSWORD);
        return fissParams;
    }
  
}
