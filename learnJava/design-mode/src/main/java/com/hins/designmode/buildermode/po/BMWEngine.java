package com.hins.designmode.buildermode.po;

import java.math.BigDecimal;

public class BMWEngine implements Component {
    @Override
    public String brand() {
        return "BMW";
    }

    @Override
    public BigDecimal price() {
        return new BigDecimal(6666);
    }

    @Override
    public String name() {
        return "宝马引擎";
    }
}
