package com.hins.designmode.abstractfactory.adapter;

import com.hins.designmode.abstractfactory.factory.OrientFactory;

public class IOrientAdapter implements IAdapter {

    private OrientFactory orientFactory = new OrientFactory();

    @Override
    public void generateBreakdown() {

        orientFactory.getOrient("CME").orientBreakDown();
        orientFactory.getOrient("ICE").orientBreakDown();
    }



}
