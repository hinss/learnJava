package com.hins.mysql.config;

public interface SqlSession {

    <T> T selectOne(String satement);
}
