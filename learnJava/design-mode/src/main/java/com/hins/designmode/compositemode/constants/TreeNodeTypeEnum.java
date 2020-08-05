package com.hins.designmode.compositemode.constants;

public enum TreeNodeTypeEnum {
    LEAF(1),
    FRUIT(2);

    private Integer code;

    TreeNodeTypeEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
