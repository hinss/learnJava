package com.hins.lock.volatiles;

/**
 * @author: hins
 * @created: 2021-01-14 17:20
 * @desc:
 *  volatile的作用:
 *  1.对于多线程访问的内存变量,volatile可以保证内存的可见性:
 *      线程每次读取该变量的值时都会强制从主内存中去取，并且修改后会强制刷到主内存中
 *  2.volatile 可以防止指令重排。
 **/
public class VolatileDemo01 {

    private static volatile boolean flag = false;

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {

            System.out.println("Thread one Start to run....");
            System.out.println("Thread one read Flag status: " + flag);
            while(!flag){

            }
            // 这里有 volatile 和 没volatile将会影响输出结果
            System.out.println("Thread one read Flag status: " + flag);
            System.out.println("Thread one End.....");
        }).start();

        // 保证第一个线程先执行
        Thread.sleep(2000L);

        new Thread(() -> {

            System.out.println("Thread two read Flag status: " + flag);
            System.out.println("Thread two change flag now....");

            flag = true;

        }).start();


    }





}
