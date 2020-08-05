package com.hins.designmode.compositemode.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author: hins
 * @created: 2020-08-05 07:40
 * @desc: 决策树节点类
 **/
@Data
@Builder
public class TreeNode {

    private Long treeId;

    private Long nodeId;

    /**
     * 1-叶子节点   2-果实节点
     */
    private Integer nodeType;

    /**
     * 具体果实节点的东西,可以是任何的对象
     */
    private Object nodeValue;

    private String ruleKey;

    private String ruleDesc;

    /**
     * 与该节点关联的节点之间的关系
     */
    private List<TreeNodeLink> treeNodeLinkList;


}
