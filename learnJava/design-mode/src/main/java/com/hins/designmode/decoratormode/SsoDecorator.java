package com.hins.designmode.decoratormode;

/**
 * @author: hins
 * @created: 2020-08-06 17:33
 * @desc: 装饰者模式核心类  SsoInterceptor的装饰类
 **/
public abstract class SsoDecorator extends SsoInterceptor {

    private HandlerInterceptor handlerInterceptor;

    public SsoDecorator(HandlerInterceptor handlerInterceptor) {
        this.handlerInterceptor = handlerInterceptor;
    }

    @Override
    public boolean preHandle(String request, String response, Object hanlder) {
        return handlerInterceptor.preHandle(request, response, hanlder);
    }
}
