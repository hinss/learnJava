package com.hins.mysql;

import com.hins.mysql.config.Resources;
import com.hins.mysql.config.SqlSessionFactory;
import com.hins.mysql.config.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;

//@SpringBootTest
class MysqlApplicationTests {


    @Test
    public void testClassLoaderReadResourceAsStream(){

        String resource = "myOrm.xml";

        try {
            InputStream inputStream = Resources.getResources(resource);

            System.out.println(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testReadXmlAsConfiguration(){

        String resource = "myOrm.xml";

        try {

            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(Resources.getReousrceAsReader(resource));

            System.out.println(sqlSessionFactory);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
