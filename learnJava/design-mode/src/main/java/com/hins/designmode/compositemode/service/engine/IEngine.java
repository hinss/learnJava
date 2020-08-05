package com.hins.designmode.compositemode.service.engine;

import com.hins.designmode.compositemode.model.EngineResult;
import com.hins.designmode.compositemode.model.TreeRich;

import java.util.Map;

public interface IEngine {

    EngineResult process(final Long treeId, final String userId, TreeRich treeRich, final Map<String,String> descisionMap);
}
