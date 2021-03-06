package com.hins.designmode.compositemode.model;

import com.hins.designmode.compositemode.constants.TreeNodeTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: hins
 * @created: 2020-08-05 07:40
 * @desc: 决策树节点类
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TreeNode {

    private Long treeId;

    private Long nodeId;

    /**
     * 1-叶子节点   2-果实节点
     */
    private TreeNodeTypeEnum nodeType;

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
