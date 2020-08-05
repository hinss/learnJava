package com.hins.designmode.compositemode.service.engine;

import com.hins.designmode.compositemode.service.logic.LogicFilter;
import com.hins.designmode.compositemode.service.logic.impl.UserAgeFilter;
import com.hins.designmode.compositemode.service.logic.impl.UserSexFilter;
import lombok.Data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: hins
 * @created: 2020-08-05 09:40
 * @desc:
 **/

public class EngineConfig {

    static Map<String, LogicFilter> logicFilterMap;

    static {

        logicFilterMap = new ConcurrentHashMap<>();
        logicFilterMap.put("userAge", new UserAgeFilter());
        logicFilterMap.put("userGender", new UserSexFilter());
    }

    public static Map<String, LogicFilter> getLogicFilterMap() {
        return logicFilterMap;
    }

    public static void setLogicFilterMap(Map<String, LogicFilter> logicFilterMap) {
        EngineConfig.logicFilterMap = logicFilterMap;
    }
}
