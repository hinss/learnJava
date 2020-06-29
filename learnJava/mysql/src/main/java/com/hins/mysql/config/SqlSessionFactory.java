package com.hins.mysql.config;

public interface SqlSessionFactory {

    DefaultSqlSession openSession();
}
