package com.hins.lock.threadlocal;

/**
 * @author: hins
 * @created: 2021-01-06 12:30
 * @desc:
 **/
public class ThreadLocalDemo05 {

    public static void main(String[] args) {

        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("key1");

        ThreadLocal<String> threadLocal2 = new ThreadLocal<>();
        threadLocal2.set("key2");







    }


}
