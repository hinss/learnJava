package com.hins.designmode.compositemode.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 决策结果
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EngineResult {

    /**
     * 执行结果
     */
    private boolean isSuccess;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 规则树ID
     */
    private Long treeId;
    /**
     * 果实节点ID
     */
    private Long nodeId;
    /**
     * 果实节点值
     */
    private Object nodeValue;

    public EngineResult(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

}
