package com.hins.designmode.compositemode.service.engine;

import com.hins.designmode.compositemode.constants.TreeNodeTypeEnum;
import com.hins.designmode.compositemode.model.*;
import com.hins.designmode.compositemode.service.logic.LogicFilter;

import java.util.List;
import java.util.Map;

/**
 * @author: hins
 * @created: 2020-08-05 09:46
 * @desc:
 **/
public abstract class EngineBase implements IEngine {

    @Override
    public abstract EngineResult process(Long treeId, String userId, TreeRich treeRich, Map<String, String> descisionMap);

    public TreeNode engineDecisionMaker(TreeRich treeRich, Long treeId, String userId, Map<String, String> decisionMatter){

        // 具体根据用户的特征通过决策树找到决策节点的过程
        TreeRoot treeRoot = treeRich.getTreeRoot();

        // NodeId -> TreeNodes
        Map<Long, TreeNode> treeNodeMap = treeRich.getTreeNodeMap();

        Long treeNodeId = treeRoot.getTreeRootNodeId();

        while(treeNodeMap.get(treeNodeId).getNodeType() != TreeNodeTypeEnum.FRUIT){

            TreeNode resultNode = treeNodeMap.get(treeNodeId);
            //每次遍历得到下一个决策节点
            List<TreeNodeLink> treeNodeLinkList = resultNode.getTreeNodeLinkList();

            String ruleKey = resultNode.getRuleKey();
            LogicFilter userFilter = EngineConfig.getLogicFilterMap().get(ruleKey);
            String matterValue = userFilter.matterValue(treeId,userId,decisionMatter);

            Long nextNodeId = userFilter.filter(matterValue, treeNodeLinkList);

            treeNodeId = nextNodeId;

        }

        return treeNodeMap.get(treeNodeId);
    }


}
