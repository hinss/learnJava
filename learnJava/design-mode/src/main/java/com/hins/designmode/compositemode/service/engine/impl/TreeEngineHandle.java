package com.hins.designmode.compositemode.service.engine.impl;

import com.hins.designmode.compositemode.model.EngineResult;
import com.hins.designmode.compositemode.model.TreeNode;
import com.hins.designmode.compositemode.model.TreeRich;
import com.hins.designmode.compositemode.service.engine.EngineBase;

import java.util.Map;

/**
 * @author: hins
 * @created: 2020-08-05 08:05
 * @desc:
 **/
public class TreeEngineHandle extends EngineBase {


    @Override
    public EngineResult process(Long treeId, String userId, TreeRich treeRich, Map<String, String> descisionMap) {

        TreeNode treeNode = engineDecisionMaker(treeRich,treeId,userId,descisionMap);

        return EngineResult.builder()
                .isSuccess(true)
                .nodeId(treeNode.getNodeId())
                .nodeValue(treeNode.getNodeValue())
                .treeId(treeId)
                .userId(userId)
                .build();
    }
}
