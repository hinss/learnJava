package com.hins.designmode.compositemode.service.engine.impl;

import com.hins.designmode.compositemode.constants.RuleLimitTypeEnum;
import com.hins.designmode.compositemode.constants.TreeNodeTypeEnum;
import com.hins.designmode.compositemode.model.*;
import com.hins.designmode.compositemode.service.engine.IEngine;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: hins
 * @created: 2020-08-05 10:48
 * @desc:
 **/
public class TreeEngineHandleTest {


    private Logger logger = LoggerFactory.getLogger(TreeEngineHandleTest.class);

    TreeRich treeRich;

    @Before
    public void init(){

        // 节点：1
        TreeNode treeNode_01 = new TreeNode();
        treeNode_01.setTreeId(10001L);
        treeNode_01.setNodeId(1L);
        treeNode_01.setNodeType(TreeNodeTypeEnum.LEAF);
        treeNode_01.setNodeValue(null);
        treeNode_01.setRuleKey("userGender");
        treeNode_01.setRuleDesc("用户性别[男/女]");

        // 链接：1->11
        TreeNodeLink treeNodeLink_11 = new TreeNodeLink();
        treeNodeLink_11.setFromNodeId(1L);
        treeNodeLink_11.setToNodeId(11L);
        treeNodeLink_11.setRuleLimitTypeEnum(RuleLimitTypeEnum.EQUAL);
        treeNodeLink_11.setRuleLimitValue("man");

        // 链接：1->12
        TreeNodeLink treeNodeLink_12 = new TreeNodeLink();
        treeNodeLink_12.setFromNodeId(1L);
        treeNodeLink_12.setToNodeId(12L);
        treeNodeLink_12.setRuleLimitTypeEnum(RuleLimitTypeEnum.EQUAL);
        treeNodeLink_12.setRuleLimitValue("woman");

        List<TreeNodeLink> treeNodeLinkList_1 = new ArrayList<>();
        treeNodeLinkList_1.add(treeNodeLink_11);
        treeNodeLinkList_1.add(treeNodeLink_12);

        treeNode_01.setTreeNodeLinkList(treeNodeLinkList_1);

        // 节点：11
        TreeNode treeNode_11 = new TreeNode();
        treeNode_11.setTreeId(10001L);
        treeNode_11.setNodeId(11L);
        treeNode_11.setNodeType(TreeNodeTypeEnum.LEAF);
        treeNode_11.setNodeValue(null);
        treeNode_11.setRuleKey("userAge");
        treeNode_11.setRuleDesc("用户年龄");

        // 链接：11->111
        TreeNodeLink treeNodeLink_111 = new TreeNodeLink();
        treeNodeLink_111.setFromNodeId(11L);
        treeNodeLink_111.setToNodeId(111L);
        treeNodeLink_111.setRuleLimitTypeEnum(RuleLimitTypeEnum.LESS);
        treeNodeLink_111.setRuleLimitValue("25");

        // 链接：11->112
        TreeNodeLink treeNodeLink_112 = new TreeNodeLink();
        treeNodeLink_112.setFromNodeId(11L);
        treeNodeLink_112.setToNodeId(112L);
        treeNodeLink_112.setRuleLimitTypeEnum(RuleLimitTypeEnum.LESS_EQUAL);
        treeNodeLink_112.setRuleLimitValue("25");

        List<TreeNodeLink> treeNodeLinkList_11 = new ArrayList<>();
        treeNodeLinkList_11.add(treeNodeLink_111);
        treeNodeLinkList_11.add(treeNodeLink_112);

        treeNode_11.setTreeNodeLinkList(treeNodeLinkList_11);

        // 节点：12
        TreeNode treeNode_12 = new TreeNode();
        treeNode_12.setTreeId(10001L);
        treeNode_12.setNodeId(12L);
        treeNode_12.setNodeType(TreeNodeTypeEnum.LEAF);
        treeNode_12.setNodeValue(null);
        treeNode_12.setRuleKey("userAge");
        treeNode_12.setRuleDesc("用户年龄");

        // 链接：12->121
        TreeNodeLink treeNodeLink_121 = new TreeNodeLink();
        treeNodeLink_121.setFromNodeId(12L);
        treeNodeLink_121.setToNodeId(121L);
        treeNodeLink_121.setRuleLimitTypeEnum(RuleLimitTypeEnum.LESS);
        treeNodeLink_121.setRuleLimitValue("25");

        // 链接：12->122
        TreeNodeLink treeNodeLink_122 = new TreeNodeLink();
        treeNodeLink_122.setFromNodeId(12L);
        treeNodeLink_122.setToNodeId(122L);
        treeNodeLink_122.setRuleLimitTypeEnum(RuleLimitTypeEnum.LESS_EQUAL);
        treeNodeLink_122.setRuleLimitValue("25");

        List<TreeNodeLink> treeNodeLinkList_12 = new ArrayList<>();
        treeNodeLinkList_12.add(treeNodeLink_121);
        treeNodeLinkList_12.add(treeNodeLink_122);

        treeNode_12.setTreeNodeLinkList(treeNodeLinkList_12);

        // 节点：111
        TreeNode treeNode_111 = new TreeNode();
        treeNode_111.setTreeId(10001L);
        treeNode_111.setNodeId(111L);
        treeNode_111.setNodeType(TreeNodeTypeEnum.FRUIT);
        treeNode_111.setNodeValue("果实A");

        // 节点：112
        TreeNode treeNode_112 = new TreeNode();
        treeNode_112.setTreeId(10001L);
        treeNode_112.setNodeId(112L);
        treeNode_112.setNodeType(TreeNodeTypeEnum.FRUIT);
        treeNode_112.setNodeValue("果实B");

        // 节点：121
        TreeNode treeNode_121 = new TreeNode();
        treeNode_121.setTreeId(10001L);
        treeNode_121.setNodeId(121L);
        treeNode_121.setNodeType(TreeNodeTypeEnum.FRUIT);
        treeNode_121.setNodeValue("果实C");

        // 节点：122
        TreeNode treeNode_122 = new TreeNode();
        treeNode_122.setTreeId(10001L);
        treeNode_122.setNodeId(122L);
        treeNode_122.setNodeType(TreeNodeTypeEnum.FRUIT);
        treeNode_122.setNodeValue("果实D");

        // 树根
        TreeRoot treeRoot = new TreeRoot();
        treeRoot.setTreeId(10001L);
        treeRoot.setTreeRootNodeId(1L);
        treeRoot.setTreeName("规则决策树");

        Map<Long, TreeNode> treeNodeMap = new HashMap<>();
        treeNodeMap.put(1L, treeNode_01);
        treeNodeMap.put(11L, treeNode_11);
        treeNodeMap.put(12L, treeNode_12);
        treeNodeMap.put(111L, treeNode_111);
        treeNodeMap.put(112L, treeNode_112);
        treeNodeMap.put(121L, treeNode_121);
        treeNodeMap.put(122L, treeNode_122);

        treeRich = new TreeRich(treeRoot, treeNodeMap);

    }


    @Test
    public void testCompositeMode(){


        IEngine treeEngineHandle = new TreeEngineHandle();

        Map<String, String> decisionMatter = new HashMap<>();
        decisionMatter.put("sex", "man");
        decisionMatter.put("age", "29");

        EngineResult result = treeEngineHandle.process(10001L, "Oli09pLkdjh", treeRich, decisionMatter);

        System.out.println(result);
    }


}
