package com.hins.designmode.decoratormode;

/**
 * @author: hins
 * @created: 2020-08-06 17:30
 * @desc:  模拟通用的SSO 登录的校验
 **/
public class SsoInterceptor implements  HandlerInterceptor {

    @Override
    public boolean preHandle(String request, String response, Object hanlder) {

        String cookie = request.substring(1, 8);
        return "success".equals(cookie);
    }
}
