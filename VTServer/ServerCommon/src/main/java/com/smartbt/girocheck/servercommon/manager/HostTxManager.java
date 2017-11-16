  
package com.smartbt.girocheck.servercommon.manager;

import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionResponse;

/**
 *
 * @author rrodriguez
 */


public interface HostTxManager {
     public DirexTransactionResponse processTransaction(DirexTransactionRequest request) throws Exception;
}
