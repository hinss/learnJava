package com.hins.designmode.compositemode.model;

import com.hins.designmode.compositemode.constants.RuleLimitTypeEnum;
import lombok.Data;

/**
 * @author: hins
 * @created: 2020-08-05 07:39
 * @desc: 决策树节点关系链接
 **/
@Data
public class TreeNodeLink {

    private Long fromNodeId;

    private Long toNodeId;

    /**
     * 限定类型；1:=;2:>;3:<;4:>=;5<=;6:enum[枚举范围]
     */
    private RuleLimitTypeEnum ruleLimitTypeEnum;

    /**
     * 限定值
     */
    private String ruleLimitValue;

}
