package com.hins.designmode.buildermode.po;

import java.math.BigDecimal;

public class FerrariEngine implements Component {

    @Override
    public String brand() {
        return "Ferrari";
    }

    @Override
    public BigDecimal price() {
        return new BigDecimal(888888);
    }

    @Override
    public String name() {
        return "法拉利引擎";
    }
}
