package com.hins.designmode.abstractfactory.factory;

import com.hins.designmode.abstractfactory.adapter.IAdapter;
import com.hins.designmode.utils.ClassLoaderUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JDKInvocationHandler implements InvocationHandler {

    private IAdapter adapter;

    public JDKInvocationHandler(IAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(args == null){
            args = new Object[]{};
        }

        return IAdapter.class.getMethod(method.getName(), ClassLoaderUtils.getClazzByArgs(args)).invoke(adapter, args);
    }
}
