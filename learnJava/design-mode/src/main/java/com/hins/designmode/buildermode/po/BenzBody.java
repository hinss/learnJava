package com.hins.designmode.buildermode.po;

import java.math.BigDecimal;

public class BenzBody implements Component {

    @Override
    public String brand() {
        return "BenZ";
    }

    @Override
    public BigDecimal price() {
        return new BigDecimal(10000);
    }

    @Override
    public String name() {
        return "奔驰车身";
    }
}
