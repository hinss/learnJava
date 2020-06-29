package com.hins.mysql.config;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * 读取资源文件 -> 字符流
 */
public class Resources {

    public static Reader getReousrceAsReader(String resource) throws IOException{

        return new InputStreamReader(getResources(resource));
    }

    public static InputStream getResources(String resource) throws IOException{

        ClassLoader[] classLoaders = getClassLoaders();
        for(ClassLoader classLoader : classLoaders){

            InputStream inputStream = classLoader.getResourceAsStream(resource);
            if(inputStream != null){
                return inputStream;
            }
        }

        throw new IOException("Could not find resource " + resource);
    }



    private static ClassLoader[] getClassLoaders() {
        return new ClassLoader[]{
                ClassLoader.getSystemClassLoader(),
                Thread.currentThread().getContextClassLoader()};
    }


}
