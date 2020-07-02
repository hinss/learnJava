package com.hins.designmode.buildermode;

import com.hins.designmode.buildermode.po.BMWEngine;
import com.hins.designmode.buildermode.po.BenzBody;
import com.hins.designmode.buildermode.po.BenzWheel;
import com.hins.designmode.buildermode.po.FerrariEngine;

public class CarBuilder {


    public ICarMenu builderFirstTypeCar(){

        return new AssembleCarMenu()
                .appendBody(new BenzBody())
                .appendEngine(new BMWEngine())
                .appendWheel(new BenzWheel());
    }

    public ICarMenu builderSecondTypeCar(){

        return new AssembleCarMenu()
                .appendBody(new BenzBody())
                .appendEngine(new FerrariEngine())
                .appendWheel(new BenzWheel());
    }
}
