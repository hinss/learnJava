package com.hins.designmode.singleton;

/**
 * 单例 懒汉模式 线程不安全
 */
public class Singleton_01{

    private static Singleton_01 singleton01;

    private Singleton_01(){

    }

    public static Singleton_01 getInstance(){
        if(singleton01 == null){
            singleton01 = new Singleton_01();
        }

        return singleton01;
    }
}
