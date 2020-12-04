package com.hins.designmode.proxymode;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: hins
 * @created: 2020-12-04 11:39
 * @desc:
 **/
public class IUserDaoTest {


    @Test
    public void testProxy(){

        // 1.加载bean的定义
        // 2.注册bean到bean factory中
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("proxy-bean.xml");

        // 3.获取代理bean
        IUserDao userDao = beanFactory.getBean("userDao", IUserDao.class);

        // 4.对外提供的简单的调用
        String res = userDao.queryUserByUserId("userId-1001");

        System.out.println(res);

    }


}
