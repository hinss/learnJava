package com.hins.designmode.singleton;

/**
 * 内部类 实现单例 (保证了线程安全 也保证了懒加载 更确保了唯一性)
 * 原理:
 *  1)JVM虚拟机可以保证多线程并发访问的正确性，就是一个类的构造函数在多线程下可以被正确加载。
 *  2)私有静态的内部类的加载：加载一个类时，其内部类不会同时被加载。
 *  一个类被加载，当且仅当其某个静态成员（静态域、构造器、静态方法等）被调用时发生。
 *  3) 静态内部类的优点: 外部类加载时并不需要立刻加载内部类，内部类不去加载就不会去初始化instance，就不会占内存。
 */
public class Singleton_04 {

    private Singleton_04(){

    }

    private static class SingletonHolder{

        private static Singleton_04 instance = new Singleton_04();

        static {
            System.out.println("load static inner class...");
        }


    }

    public static Singleton_04 getInstance(){

        System.out.println("aaaa");
        return Singleton_04.SingletonHolder.instance;
    }


}
