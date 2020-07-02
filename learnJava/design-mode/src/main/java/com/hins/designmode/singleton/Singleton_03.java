package com.hins.designmode.singleton;

/**
 * 饿汉模式 线程安全
 */
public class Singleton_03 {

    private static Singleton_03 singleton03 = new Singleton_03();

    private Singleton_03(){

    }

    public static Singleton_03 getSingleton03() {
        return singleton03;
    }
}
