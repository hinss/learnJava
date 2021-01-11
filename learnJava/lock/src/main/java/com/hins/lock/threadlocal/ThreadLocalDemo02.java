package com.hins.lock.threadlocal;

/**
 * @author: hins
 * @created: 2020-12-30 17:10
 * @desc:
 **/
public class ThreadLocalDemo02 {

    public static void main(String[] args) {

        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("abc");
        threadLocal.set("def");


        System.out.println(threadLocal.get());

    }


}
