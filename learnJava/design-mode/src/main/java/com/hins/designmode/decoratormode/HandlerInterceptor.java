package com.hins.designmode.decoratormode;


public interface HandlerInterceptor {

    /**
     * 模拟spring 中的通用拦截器接口
     * @param request
     * @param response
     * @param hanlder
     * @return
     */
    boolean preHandle(String request,String response, Object hanlder);
}
