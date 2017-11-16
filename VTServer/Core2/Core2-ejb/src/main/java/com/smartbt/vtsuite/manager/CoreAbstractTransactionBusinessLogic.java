/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartbt.vtsuite.manager;

 
import com.smartbt.girocheck.servercommon.manager.HostTxManager;
import com.smartbt.girocheck.servercommon.messageFormat.DirexTransactionRequest;
import com.smartbt.girocheck.servercommon.model.Transaction;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomHost;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Roberto Rodriguez :: <roberto.rodriguez@smartbt.com>
 */
public abstract class CoreAbstractTransactionBusinessLogic {

    protected static Map<NomHost, HostTxManager> hostManagers = new HashMap();

    static {
        hostManagers.put(NomHost.COMPLIANCE, ComplianceHostManager.get());
        hostManagers.put(NomHost.CERTEGY, CertegyHostManager.get());
        hostManagers.put(NomHost.FISS, FissHostManager.get());
        hostManagers.put(NomHost.IDEOLOGY, IdeologyHostManager.get());
        hostManagers.put(NomHost.ISTREAM2, IStream2HostManager.get());
        hostManagers.put(NomHost.TECNICARD, TecnicardHostManager.get());
        hostManagers.put(NomHost.WESTECH, WestechHostManager.get());
    }

    public abstract void process(DirexTransactionRequest direxTransactionRequest, Transaction transaction) throws Exception;
}
