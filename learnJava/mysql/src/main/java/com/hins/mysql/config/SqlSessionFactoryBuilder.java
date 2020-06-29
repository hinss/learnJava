package com.hins.mysql.config;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SqlSessionFactoryBuilder {


    public SqlSessionFactory build(Reader reader){

        SAXReader saxReader = new SAXReader();

        try {
            Document document = saxReader.read(new InputSource(reader));
            Element rootElement = document.getRootElement();
            Configuration configuration = parseConfiguration(rootElement);

            return new DefaultSqlSessionFactory(configuration);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Configuration parseConfiguration(Element root) {
        Configuration configuration = new Configuration();
        configuration.setDataSourceMap(dataSource(root.selectNodes("//dataSource")));
        configuration.setConnection(connection(configuration.dataSourceMap));
        configuration.setMapperElement(mapperElement(root.selectNodes("mappers")));
        return configuration;
    }

    private Map<String, XNode> mapperElement(List selectNodes) {

        Map<String, XNode> mapperElement = new HashMap<>();
        //<mappers>
        Element element = (Element) selectNodes.get(0);
        List content = element.content();
        for(Object o : content){

            Element e = (Element)o;
            String resource = e.attributeValue("resource");

            try {
                SAXReader saxReader = new SAXReader();
                Document mapperDocument = saxReader.read(Resources.getReousrceAsReader(resource));
                Element rootElement = mapperDocument.getRootElement();

                String namespace = rootElement.attributeValue("namespace");
                List<Element> elements = rootElement.selectNodes("select");
                for(Element node : elements){
                    String id = node.attributeValue("id");
                    String parameterType = node.attributeValue("parameterType");
                    String resultType = node.attributeValue("resultType");
                    String sql = node.getText();

                    // ? 匹配
                    Map<Integer, String> parameter = new HashMap<>();
                    Pattern pattern = Pattern.compile("(#\\{(.*?)})");
                    Matcher matcher = pattern.matcher(sql);
                    for (int i = 1; matcher.find(); i++) {
                        String g1 = matcher.group(1);
                        String g2 = matcher.group(2);
                        parameter.put(i, g2);
                        sql = sql.replace(g1, "?");
                    }

                    XNode xNode = new XNode();
                    xNode.setNameSpace(namespace);
                    xNode.setId(id);
                    xNode.setParameterType(parameterType);
                    xNode.setResultType(resultType);
                    xNode.setSql(sql);
                    xNode.setParameterMap(parameter);

                    mapperElement.put(namespace+"."+id,xNode);
                }


            } catch (IOException | DocumentException ex) {
                ex.printStackTrace();
            }


        }
        return mapperElement;

    }

    private Map<String,String> dataSource(List selectNodes) {

        Map<String,String> dataSourceMap = new HashMap<>(4);

        Element element = (Element)selectNodes.get(0);
        List content = element.content();
        for(Object o : content){
           Element e = (Element)o;
            String name = e.attributeValue("name");
            String value = e.attributeValue("value");
            dataSourceMap.put(name,value);
        }
        System.out.println(dataSourceMap);
        return dataSourceMap;

    }

    private Connection connection(Map<String,String> dataSourceMap){

        try {
            Class.forName(dataSourceMap.get("driver"));

            return DriverManager.getConnection(dataSourceMap.get("url"), dataSourceMap.get("username"), dataSourceMap.get("password"));
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


}
