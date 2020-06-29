package com.hins.designmode.abstractfactory.service;

/**
 * 模拟提供统一breakdown服务接口
 */
public interface CommonBreakdownService {

    /**
     * 本质上提供的就是生成margin breakdown的服务
     * Orient: 只要FO的 方法名不一样 orientBreakdown()
     * Staits: 只要FX的 straitsBreakdown()
     * 应该通过配置的形式,让服务只提供对应产品线的服务,但是暴露的方法时公共的
     */
    void generateBreakdown();
}
