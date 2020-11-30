package com.hins.designmode.springcodes;

import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

/**
 * @author: hins
 * @created: 2020-11-23 13:33
 * @desc:
 **/
public class SpringTest {


    @Test
    public void test01(){

        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        ClassPathResource resource = new ClassPathResource("test.xml");
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(resource);

        factory.getBean("foo1");

    }

    @Test
    public void test02(){

        try {
            Class<?> forName = Class.forName("java.lang.String");
            String str = forName.newInstance().toString();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }


    }


}
