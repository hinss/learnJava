package com.hins.designmode.singleton;

/**
 * 单例 懒汉模式 线程安全
 */
public class Singleton_02 {

    private static Singleton_02 singleton_02;

    private Singleton_02(){

    }

    public static synchronized Singleton_02 getInstance(){
        if(singleton_02 == null){
            singleton_02 = new Singleton_02();
        }
        return singleton_02;
    }
}
