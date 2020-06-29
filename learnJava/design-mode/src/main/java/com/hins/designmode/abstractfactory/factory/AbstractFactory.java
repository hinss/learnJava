package com.hins.designmode.abstractfactory.factory;


import com.hins.designmode.abstractfactory.biz.orientcompany.Orient;
import com.hins.designmode.abstractfactory.biz.straits.Straits;

public abstract class AbstractFactory {

    public abstract Orient getOrient(String orientBreakdownType);
    public abstract Straits getStraits(String straitsBreakdownType);

}
