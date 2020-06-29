package com.hins.designmode.abstractfactory.factory;

import org.springframework.util.StringUtils;

public class FactoryProvider {


    public static AbstractFactory provideFactory(String productType){

        if(StringUtils.endsWithIgnoreCase("ORIENT", productType)){

//            return new OrientFactory();
            return null;
        }else if(StringUtils.endsWithIgnoreCase("STRAITS", productType)){

            return new StraitsFactory();
        }

        return null;

    }
}
