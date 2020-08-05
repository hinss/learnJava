package com.hins.designmode.compositemode.model;

public enum RuleLimitTypeEnum {
    EQUAL(1,"="),
    GREATER(2,">"),
    LESS(3,"<"),
    GREATER_EQUAL(4,">="),
    LESS_EQUAL(5,"<=");

    private Integer value;

    private String desc;

    RuleLimitTypeEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
