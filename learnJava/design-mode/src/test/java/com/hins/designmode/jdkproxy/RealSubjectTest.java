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

        /**
         *  假设我们实现了RealSubject的基本功能后，我们需要对原有的方法进行增强，可以使用动态代理的方式。
         *  整理动态代理增加真实对象的所需步骤:
         *  1.被代理的真实对象
         *  2.一个包裹了真实对象的 代理对象(实现InvocationHandler) 自定义代理增强的的逻辑。
         *  3.通过反射通过代理对象返回真实对象的实例
         *  以此来实现原有类型的实例调用方法 从而得到增强的效果。
         */


        // 1.被代理的真实实现类
        Subject realSubject = new RealSubject();

        // 2.动态代理的 执行handler 包括真实对象
        InvocationHandler handler = new DynamicProxy(realSubject);

        // 3.获得代理类的一个实例:
//        Class<?> proxyClass = Proxy.getProxyClass(realSubject.getClass().getClassLoader(), realSubject.getClass().getInterfaces());
//
//        Constructor<?> constructor = proxyClass.getConstructor(new Class[]{InvocationHandler.class});
//
//        Subject proxyInstance = (Subject)constructor.newInstance(new Object[]{handler});

        Subject proxyInstance = (Subject) Proxy.newProxyInstance(realSubject.getClass().getClassLoader(), realSubject.getClass().getInterfaces(), new DynamicProxy(realSubject));

        // 代理类执行实际方法
        proxyInstance.rent();

        // 关于为什么使用代理对象会自动执行invoke方法？
        // 原因是JDK生成的最终真正的代理类,它继承自Proxy并实现了我们的接口，
        // 在实现接口的内部方法时，通过反射调用了InnovacationHandler.invoke方法。




    }


}
