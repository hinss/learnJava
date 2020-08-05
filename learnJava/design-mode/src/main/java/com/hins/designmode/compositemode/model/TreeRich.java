package com.hins.designmode.compositemode.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author: hins
 * @created: 2020-08-05 07:56
 * @desc:
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TreeRich {

    /**
     * 决策树根节点
     */
    private TreeRoot treeRoot;

    /**
     * 决策树 节点ID -> 树节点对象
     */
    private Map<Long,TreeNode> treeNodeMap;



}
