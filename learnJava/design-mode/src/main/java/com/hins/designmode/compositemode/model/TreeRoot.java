package com.hins.designmode.compositemode.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author: hins
 * @created: 2020-08-05 07:53
 * @desc: 决策树根节点
 **/
@Data
@Builder
public class TreeRoot {

    private Long treeId;

    private Long treeRootNodeId;

    private String treeName;

}
