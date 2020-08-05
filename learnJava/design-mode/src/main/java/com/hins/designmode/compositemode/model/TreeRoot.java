package com.hins.designmode.compositemode.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: hins
 * @created: 2020-08-05 07:53
 * @desc: 决策树根节点
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TreeRoot {

    private Long treeId;

    private Long treeRootNodeId;

    private String treeName;

}
