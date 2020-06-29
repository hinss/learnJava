package com.hins.mysql.config;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class DefaultSqlSession implements SqlSession{

    private Connection connection;
    private Map<String,XNode> xNodeMap;

    public DefaultSqlSession(Connection connection, Map<String,XNode> xNodeMap){
        this.connection = connection;
        this.xNodeMap = xNodeMap;
    }

    @Override
    public <T> T selectOne(String statement){

        try {
            //statement 就是外部调用的dao唯一id
            //com.hins.mysql.po.School.ISchoolDao.querySchoolInfoById
            XNode xNode = xNodeMap.get(statement);
            PreparedStatement preparedStatement = connection.prepareStatement(xNode.getSql());
            ResultSet resultSet = preparedStatement.executeQuery();
            List<T> list = resultSet2Obj(resultSet,Class.forName(xNode.getResultType()));

            return list.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private <T> List<T> resultSet2Obj(ResultSet resultSet, Class<?> clazz) {

        List<T> list = new ArrayList<>();

        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            while(resultSet.next()){

                T obj = (T)clazz.newInstance();

                //通过反射的方式设置属性
                for(int i=1; i <= columnCount; i++ ){

                    //每一列的数据
                    Object value = resultSet.getObject(i);
                    String columnName = metaData.getColumnName(i);
                    String setMethod = "set" + columnName.substring(0, 1).toUpperCase() + columnName.substring(1);
                    Method method;

                    //判断每一列的数据对象是什么类型的
                    if (value instanceof Timestamp) {
                        method = clazz.getMethod(setMethod, Date.class);
                    } else {
                        method = clazz.getMethod(setMethod, value.getClass());
                    }
                    //反射调用set方法设置属性
                    method.invoke(obj, value);
                }

                list.add(obj);
            }
        } catch (SQLException | IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        return list;
    }


}
