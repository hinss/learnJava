package com.hins.mysql.config;

import java.sql.Connection;
import java.util.Map;

/**
 * 装载配置的实体类
 */
public class Configuration {

    protected Connection connection;
    protected Map<String,String> dataSourceMap;
    protected Map<String,XNode> mapperElement;


    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void setDataSourceMap(Map<String, String> dataSourceMap) {
        this.dataSourceMap = dataSourceMap;
    }

    public void setMapperElement(Map<String, XNode> mapperElement) {
        this.mapperElement = mapperElement;
    }

}
