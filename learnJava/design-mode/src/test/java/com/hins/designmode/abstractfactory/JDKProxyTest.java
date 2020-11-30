package com.hins.designmode.abstractfactory;

import com.hins.designmode.abstractfactory.adapter.IOrientAdapter;
import com.hins.designmode.abstractfactory.factory.JDKProxy;
import com.hins.designmode.abstractfactory.service.CommonBreakdownService;
import com.hins.designmode.abstractfactory.service.impl.CommonBreakdownServiceImpl;
import org.junit.Test;

public class JDKProxyTest {

    @Test
    public void testJDKProxy(){

        try {
            CommonBreakdownService orientBreakdownProxy = JDKProxy.getProxy(CommonBreakdownServiceImpl.class, new IOrientAdapter());
            orientBreakdownProxy.generateBreakdown();


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
