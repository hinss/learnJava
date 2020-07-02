package com.hins.designmode.buildermode;

import com.hins.designmode.buildermode.po.Component;

public interface ICarMenu {

    ICarMenu appendBody(Component component);

    ICarMenu appendWheel(Component component);

    ICarMenu appendEngine(Component component);

    String getDesc();

}
