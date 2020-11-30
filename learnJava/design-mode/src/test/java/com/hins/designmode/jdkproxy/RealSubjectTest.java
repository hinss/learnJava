package com.hins.designmode.jdkproxy;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

/**
 * @author: hins
 * @created: 2020-11-30 11:36
 * @desc: 动态代理测试类
 **/
public class RealSubjectTest {

    @Test
    public void testDynamic() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        // 被代理的真实实现类
        Subject realSubject = new RealSubject();
        // 动态代理的 执行handler
        InvocationHandler handler = new DynamicProxy(realSubject);

        Class<?> proxyClass = Proxy.getProxyClass(realSubject.getClass().getClassLoader(), realSubject.getClass().getInterfaces());

        Constructor<?> constructor = proxyClass.getConstructor(new Class[]{InvocationHandler.class});

        Subject proxyInstance = (Subject)constructor.newInstance(new Object[]{handler});


//        Subject proxyInstance = (Subject) Proxy.newProxyInstance(realSubject.getClass().getClassLoader(), realSubject.getClass().getInterfaces(), new DynamicProxy(realSubject));

        // 代理类执行实际方法
        proxyInstance.rent();


    }


}
