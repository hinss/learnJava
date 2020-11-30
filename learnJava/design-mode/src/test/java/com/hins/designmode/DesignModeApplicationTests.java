package com.hins.designmode;

import com.hins.designmode.abstractfactory.factory.AbstractFactory;
import com.hins.designmode.abstractfactory.factory.FactoryProvider;
import com.hins.designmode.abstractfactory.service.CommonBreakdownService;
import com.hins.designmode.abstractfactory.service.impl.CommonBreakdownServiceImpl;
import org.junit.Test;

class DesignModeApplicationTests {



    @Test
    public void testAbstractFactoryMode(){

        System.out.println("===模拟抽象工程模式切换产品线===");

        System.out.println("===ORIENT breakdown 产品====");
        AbstractFactory orientProductFactory = FactoryProvider.provideFactory("ORIENT");
        orientProductFactory.getOrient("CME").orientBreakDown();
        orientProductFactory.getOrient("ICE").orientBreakDown();

        System.out.println("===STRAITS breakdown 产品====");
        AbstractFactory stratistProductFactory = FactoryProvider.provideFactory("STRAITS");
        stratistProductFactory.getStraits("SPREAD").StraitsBreakDown();
        stratistProductFactory.getStraits("TENOR").StraitsBreakDown();

    }

    @Test
    public void testCommonBreakdownService(){

        System.out.println("===模拟配置了Orients公司,只生产Orients breakdown====");

        CommonBreakdownService commonBreakdownService = new CommonBreakdownServiceImpl();
        commonBreakdownService.generateBreakdown();

    }
}
