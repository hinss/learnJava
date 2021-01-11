package com.hins.lock.threadlocal;

/**
 * @author: hins
 * @created: 2021-01-04 11:20
 * @desc: $InheritableThreadLocal 可继承的ThreadLocal 实现父子线程数据传递的示例
 **/
public class ThreadLocalDemo03 {

    public static void main(String[] args) {

        // 主线程中赋值
        ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();
        stringThreadLocal.set("ThreadLocal string");

        InheritableThreadLocal<String> stringInheritableThreadLocal = new InheritableThreadLocal<>();
        stringInheritableThreadLocal.set("Inheritable ThreadLocal string");

        new Thread(() -> {
            // 打印结果
            // ThreadLocal value: null
            // InheritableThreadLocal value: Inheritable ThreadLocal string
            System.out.println("ThreadLocal value: " + stringThreadLocal.get());

            System.out.println("InheritableThreadLocal value: " + stringInheritableThreadLocal.get());
        }).start();

        // 那么为什么这个new 出来的子线程可以打印出这个"Inheritable ThreadLocal string"的字符串呢？

        // 1.明确父、子线程: 这个demo里, main线程相当于主线程，new Thread()相当于子线程。
        // 2.在new Thread的时候：
        // 2.1 Thread parent = currentThread() 也就是得到调用这个new Thread的线程。
        // 2.2 然后拿到父线程中可以继承的ThreadLocal(也就是InheritableThreadLocal)过来丢到自己的ThreadLocalMap中, 所以才实现了继承的作用。
    }


}
