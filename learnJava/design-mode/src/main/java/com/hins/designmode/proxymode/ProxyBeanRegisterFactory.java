package com.hins.designmode.proxymode;

import org.apache.catalina.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * @author: hins
 * @created: 2020-12-04 11:45
 * @desc:
 **/
public class ProxyBeanRegisterFactory implements BeanDefinitionRegistryPostProcessor {


    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

        // 自定义BeanDefinition的方式 将Bean注册到spring 容器中
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        // 设置bean的Class 这里是实现代理生成bean实例的关键
//        beanDefinition.setBeanClass(UserDaoImpl.class);
        beanDefinition.setBeanClass(MapperFactoryBean.class);
        // 设置代理类用到的Class实例 在代理生成实例中用到
        // (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{mapperInterface}, handler)
        beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(IUserDao.class);

        // 设置生命周期
        beanDefinition.setScope("singleton");

        BeanDefinitionHolder beanDefinitionHolder = new BeanDefinitionHolder(beanDefinition, "userDao");

        BeanDefinitionReaderUtils.registerBeanDefinition(beanDefinitionHolder, registry);

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }
}
