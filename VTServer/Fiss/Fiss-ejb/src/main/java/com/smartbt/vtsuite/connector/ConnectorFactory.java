package com.smartbt.vtsuite.connector;

import com.smartbt.vtsuite.connector.mock.MockFissBalanceInquiryConnector;
import com.smartbt.vtsuite.connector.mock.MockFissCardActivationConnector;
import com.smartbt.vtsuite.connector.mock.MockFissCardCashingConnector;
import com.smartbt.vtsuite.connector.mock.MockFissCardLoadCashConnector;
import com.smartbt.vtsuite.connector.mock.MockFissCardLoadConnector;
import com.smartbt.vtsuite.connector.mock.MockFissCardPersonalizationConnector;
import com.smartbt.vtsuite.connector.mock.MockFissChangePasswordConnector;
import com.smartbt.vtsuite.connector.mock.MockFissSetProductIdConnector;
import com.smartbt.vtsuite.connector.mock.MockFissTransactionHistoryDetailsConnector;
import com.smartbt.vtsuite.connector.mock.MockFissTransactionHistoryGeneralConnector;
import com.smartbt.vtsuite.connector.mock.MockFissTransactionHistoryHoldConnector;
import com.smartbt.vtsuite.connector.mock.MockFissTransactionHistoryPendingConnector;
import com.smartbt.vtsuite.connector.mock.MockSetPinConnector;
import com.smartbt.vtsuite.connector.prod.FissBalanceInquiryConnector;
import com.smartbt.vtsuite.connector.prod.FissCardActivationConnector;
import com.smartbt.vtsuite.connector.prod.FissCardCashingConnector;
import com.smartbt.vtsuite.connector.prod.FissCardLoadCashConnector;
import com.smartbt.vtsuite.connector.prod.FissCardLoadConnector;
import com.smartbt.vtsuite.connector.prod.FissCardPersonalizationConnector;
import com.smartbt.vtsuite.connector.prod.FissChangePasswordConnector;
import com.smartbt.vtsuite.connector.prod.FissSetProductIdConnector;
import com.smartbt.vtsuite.connector.prod.FissTransactionHistoryDetailsConnector;
import com.smartbt.vtsuite.connector.prod.FissTransactionHistoryGeneralConnector;
import com.smartbt.vtsuite.connector.prod.FissTransactionHistoryHoldConnector;
import com.smartbt.vtsuite.connector.prod.FissTransactionHistoryPendingConnector;
import com.smartbt.vtsuite.connector.prod.SetPinConnector;

/**
 *
 * @author rrodriguez
 */
public class ConnectorFactory {

    public static boolean PROD = true;

 

    public static Connector getBalanceInquiryConnector() {
        if (PROD) {
            return FissBalanceInquiryConnector.get();
        } else {
            return MockFissBalanceInquiryConnector.get();
        }
    }

    public static Connector getCardPersonalizationConnector() {
        if (PROD) {
            return FissCardPersonalizationConnector.get();
        } else {
            return MockFissCardPersonalizationConnector.get();
        }
    }

    public static Connector getCardActivationConnector() {
        if (PROD) {
            return FissCardActivationConnector.get();
        } else {
            return MockFissCardActivationConnector.get();
        }
    }

    public static Connector getSetProductIdConnector() {
        if (PROD) {
            return FissSetProductIdConnector.get();
        } else {
            return MockFissSetProductIdConnector.get();
        }
    }

    public static Connector getCardLoadConnector() {
        if (PROD) {
            return FissCardLoadConnector.get();
        } else {
            return MockFissCardLoadConnector.get();
        }
    }

    public static Connector getCardLoadCashConnector() {
        if (PROD) {
            return FissCardLoadCashConnector.get();
        } else {
            return MockFissCardLoadCashConnector.get();
        }
    }

    public static Connector getChangePasswordConnector() {
        if (PROD) {
            return FissChangePasswordConnector.get();
        } else {
            return MockFissChangePasswordConnector.get();
        }
    }

    public static Connector getSetPinConnector() {
        if (PROD) {
            return SetPinConnector.get();
        } else {
            return MockSetPinConnector.get();
        }
    }
    
   public static Connector getTransactionHistoryHoldConnector() {
        if (PROD) {
            return FissTransactionHistoryHoldConnector.get();
        } else {
            return MockFissTransactionHistoryHoldConnector.get();
        }
    }

    public static Connector getTransactionHistoryPendingConnector() {
        if (PROD) {
            return FissTransactionHistoryPendingConnector.get();
        } else {
            return MockFissTransactionHistoryPendingConnector.get();
        }
    }

    public static Connector getTransactionHistoryGeneralConnector() {
        if (PROD) {
            return FissTransactionHistoryGeneralConnector.get();
        } else {
            return MockFissTransactionHistoryGeneralConnector.get();
        }
    }

    public static Connector getTransactionHistoryDetailsConnector() {
        if (PROD) {
            return FissTransactionHistoryDetailsConnector.get();
        } else {
            return MockFissTransactionHistoryDetailsConnector.get();
        }
    }

    public static Connector getCardCashingConnector() {
        if (PROD) {
            return FissCardCashingConnector.get();
        } else {
            return MockFissCardCashingConnector.get();
        }
    }

}
