package com.hins.designmode.decoratormode;

import org.junit.Test;

/**
 * @author: hins
 * @created: 2020-08-06 17:40
 * @desc:
 **/
public class LoginSsoDecoratorTest {


    @Test
    public void testLoginSsoDecorator(){

        HandlerInterceptor handlerInterceptor = new SsoInterceptor();

        LoginSsoDecorator loginSsoDecorator = new LoginSsoDecorator(handlerInterceptor);

        boolean success = loginSsoDecorator.preHandle("xsuccesshins", "response", null);
        if(success){
            System.out.println("cookie 校验通过, username校验通过!");
        }else{
            System.out.println("cookie 校验不通过 或者 username校验通过!");
        }


    }


}
