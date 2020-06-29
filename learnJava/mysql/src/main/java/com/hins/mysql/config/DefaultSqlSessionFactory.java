package com.hins.mysql.config;

public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration){
        this.configuration = configuration;
    }

    @Override
    public DefaultSqlSession openSession() {

        return new DefaultSqlSession(configuration.connection,configuration.mapperElement);
    }
}
