package com.hins.designmode.abstractfactory.adapter;

import com.hins.designmode.abstractfactory.factory.StraitsFactory;

public class IStraitsAdapter implements IAdapter {

    private StraitsFactory straitsFactory = new StraitsFactory();

    @Override
    public void generateBreakdown() {

        straitsFactory.getStraits("SPREAD").StraitsBreakDown();
        straitsFactory.getStraits("TENOR").StraitsBreakDown();
    }
}
