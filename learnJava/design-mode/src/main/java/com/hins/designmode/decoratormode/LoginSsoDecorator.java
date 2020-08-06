package com.hins.designmode.decoratormode;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: hins
 * @created: 2020-08-06 17:35
 * @desc:
 **/
public class LoginSsoDecorator extends SsoDecorator {

    private static Map<String, String> authMap = new ConcurrentHashMap<String, String>();

    static {
        authMap.put("huahua", "queryUserInfo");
        authMap.put("doudou", "queryUserInfo");
        authMap.put("hins", "queryUserInfo");
    }

    public LoginSsoDecorator(HandlerInterceptor handlerInterceptor) {
        super(handlerInterceptor);
    }

    @Override
    public boolean preHandle(String request, String response, Object hanlder) {
        //先调用原本被装饰的单点登录类的实现
        boolean success = super.preHandle(request, response, hanlder);
        if(!success){
            return false;
        }

        //实现增强逻辑
        String userName = request.substring(8);

        if(authMap.get(userName) != null){
            return true;
        }else{
            return false;
        }
    }
}
