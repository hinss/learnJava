package com.hins.designmode.jdkproxy;

/**
 * @author: hins
 * @created: 2020-11-30 11:31
 * @desc: 被代理的真实 subject实现类
 **/
public class RealSubject implements Subject {

    @Override
    public void rent() {
        System.out.println("i want to rent my house");
    }

    @Override
    public void hello(String str) {
        System.out.println("hello: " + str);
    }
}
