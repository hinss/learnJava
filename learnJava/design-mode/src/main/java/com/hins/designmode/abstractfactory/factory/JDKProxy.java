package com.hins.designmode.abstractfactory.factory;

import com.hins.designmode.abstractfactory.adapter.IAdapter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class JDKProxy {

    public static <T> T getProxy(Class<T> interfaceClass, IAdapter adapter) throws Exception {
        InvocationHandler handler = new JDKInvocationHandler(adapter);
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Class<?>[] classes = interfaceClass.getInterfaces();
        return (T) Proxy.newProxyInstance(classLoader, new Class[]{classes[0]}, handler);
    }
}
