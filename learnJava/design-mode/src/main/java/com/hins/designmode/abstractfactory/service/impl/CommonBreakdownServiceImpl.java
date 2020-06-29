package com.hins.designmode.abstractfactory.service.impl;

import com.hins.designmode.abstractfactory.adapter.IAdapter;
import com.hins.designmode.abstractfactory.adapter.IOrientAdapter;
import com.hins.designmode.abstractfactory.adapter.IStraitsAdapter;
import com.hins.designmode.abstractfactory.factory.AbstractFactory;
import com.hins.designmode.abstractfactory.factory.OrientFactory;
import com.hins.designmode.abstractfactory.service.CommonBreakdownService;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class CommonBreakdownServiceImpl implements CommonBreakdownService {

    /**
     * 模拟配置的客户名称
     */
    private final String CUST_NAME = "ORIENT";
    private static Map<String, IAdapter> map = new HashMap<>();


    static {
        //可以使用代理的模式替换工厂,代理成CommonBreakdownServiceImpl的适配器类
        map.put("ORIENT",new IOrientAdapter());
        map.put("STRAITS",new IStraitsAdapter());
    }

    @Override
    public void generateBreakdown() {

        for(String key : map.keySet()){

            if(StringUtils.endsWithIgnoreCase(CUST_NAME,key)){
                map.get(key).generateBreakdown();
            }
        }


    }
}
