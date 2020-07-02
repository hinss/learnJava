package com.hins.designmode.buildermode;

import com.hins.designmode.buildermode.po.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AssembleCarMenu implements ICarMenu {

    private List<Component> componentList = new ArrayList<>();
    private BigDecimal totalPrice = BigDecimal.ZERO;

    @Override
    public ICarMenu appendBody(Component component) {
        componentList.add(component);

        totalPrice.add(component.price());

        return this;
    }

    @Override
    public ICarMenu appendWheel(Component component) {
        componentList.add(component);

        totalPrice.add(component.price());
        return this;
    }

    @Override
    public ICarMenu appendEngine(Component component) {
        componentList.add(component);
        totalPrice.add(component.price());
        return this;
    }

    @Override
    public String getDesc() {
        StringBuilder str = new StringBuilder();
        componentList.stream().forEach(c ->
                str.append(c.name()));

        return str.toString();
    }
}
