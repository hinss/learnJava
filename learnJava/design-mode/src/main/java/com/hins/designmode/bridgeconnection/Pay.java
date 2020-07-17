package com.hins.designmode.bridgeconnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public abstract class Pay {

    protected Logger logger = LoggerFactory.getLogger(Pay.class);

    protected IPayMode iPayMode;

    /**
     * 抽象类中可以定义构造方法，但是不能直接使用
     * 子类在实例化的过程中可以调用父类的构造方法
     */
    public Pay(IPayMode iPayMode){
        this.iPayMode = iPayMode;
    }

    public abstract String transfer(String uId, String tradeId, BigDecimal amount);


}
