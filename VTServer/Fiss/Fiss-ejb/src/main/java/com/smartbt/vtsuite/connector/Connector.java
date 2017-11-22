package com.smartbt.vtsuite.connector;

import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.vtsuite.util.FissParam;
import com.smartbt.vtsuite.util.LogMessageHandler;
import java.util.List;
import java.util.Map;
import javax.xml.ws.Binding;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.Handler;

/**
 *
 * @author rrodriguez
 */
public abstract class Connector {

    public abstract Map<FissParam, Object> callWS(Map<ParameterName, Object> params);

    protected void addLogger(BindingProvider bindingProvider) {
        Binding binding = bindingProvider.getBinding();
        List<Handler> handlerChain = binding.getHandlerChain();
        handlerChain.add(new LogMessageHandler());
        binding.setHandlerChain(handlerChain);
    }
}
