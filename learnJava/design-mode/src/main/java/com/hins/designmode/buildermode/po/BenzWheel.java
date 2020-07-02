package com.hins.designmode.buildermode.po;

import java.math.BigDecimal;

public class BenzWheel implements Component {

    @Override
    public String brand() {
        return "Benz";
    }

    @Override
    public BigDecimal price() {
        return new BigDecimal(50);
    }

    @Override
    public String name() {
        return "奔驰轮子";
    }
}
