package com.smartbt.girocheck.servercommon.manager;

import com.smartbt.girocheck.servercommon.dao.AchCardDAO;
import com.smartbt.girocheck.servercommon.dao.CheckBlacklistRuleDAO;
import com.smartbt.girocheck.servercommon.dao.CreditCardDAO;
import com.smartbt.girocheck.servercommon.display.message.ResponseData;
import com.smartbt.girocheck.servercommon.display.message.ResponseDataList;
import com.smartbt.girocheck.servercommon.model.AchCard;
import com.smartbt.girocheck.servercommon.model.CheckBlacklistRule;
import com.smartbt.girocheck.servercommon.model.CreditCard;
import com.smartbt.girocheck.servercommon.model.Merchant;
import com.smartbt.girocheck.servercommon.utils.Utils;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

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
}
