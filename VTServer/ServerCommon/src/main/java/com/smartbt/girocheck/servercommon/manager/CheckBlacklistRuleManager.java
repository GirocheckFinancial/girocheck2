package com.smartbt.girocheck.servercommon.manager;

import com.smartbt.girocheck.servercommon.dao.CheckBlacklistRuleDAO;
import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.girocheck.servercommon.model.CheckBlacklistRule;
import java.util.Date;

/**
 *
 * @author Roberto
 */
public class CheckBlacklistRuleManager {

    protected static CheckBlacklistRuleManager INSTANCE;

    public static CheckBlacklistRuleManager get() {
        if (INSTANCE == null) {
            INSTANCE = new CheckBlacklistRuleManager();
        }
        return INSTANCE;
    }

    private CheckBlacklistRuleDAO checkBlacklistRuleDAO = CheckBlacklistRuleDAO.get();

    public ResponseData saveOrUpdate(CheckBlacklistRule rule) throws Exception {
        rule.setLastUpdated(new Date());
        checkBlacklistRuleDAO.saveOrUpdate(rule);
        return ResponseData.OK();
    }

    public ResponseDataList searchRules(String searchFilter, int firstResult, int maxResult) {
        return checkBlacklistRuleDAO.searchRules(searchFilter, firstResult, maxResult);
    }

    public ResponseData deleteRule(int id) {
        checkBlacklistRuleDAO.deleteGivenID(id, "check_blacklist_rule");
        return ResponseData.OK();
    }
    
    public boolean validateCheck(String makerName, String routingNumber, String accountNumber){
        return checkBlacklistRuleDAO.validateCheck(  makerName,   routingNumber,   accountNumber);
    }
}
