package com.hins.lock.threadlocal;

import java.util.concurrent.*;

/**
 * @author: hins
 * @created: 2021-01-04 11:20
 * @desc: $InheritableThreadLocal 可继承的ThreadLocal 在线程池下的测试
 * 可以解释alibaba的 transmitaable-thread-local的使用场景
 **/
public class ThreadLocalDemo04 {

    public static void main(String[] args) {


        InheritableThreadLocal<String> stringInheritableThreadLocal = new InheritableThreadLocal<>();
        stringInheritableThreadLocal.set("Inheritable ThreadLocal string first set");

        ExecutorService singleExecutorService = Executors.newSingleThreadExecutor();
        singleExecutorService.submit(() -> {

            System.out.println(" firt task print: InheritableThreadLocal value: " + stringInheritableThreadLocal.get());
        });

        stringInheritableThreadLocal.set("Inheritable ThreadLocal string second set");
        singleExecutorService.submit(() -> {

            System.out.println(" second task print: InheritableThreadLocal value: " + stringInheritableThreadLocal.get());
        });

        // 两次输出的结果相同。
        // 说明在单线程池中时:
        //  -> 第一次执行时,创建的新线程可以从父线程中继承到InheritableThreadLocal的值
        //  -> 第二次执行时,由于无须重复创建线程，会直接用上一次缓存的那条Thread中的ThreadLocal的值。
//        firt task print: InheritableThreadLocal value: Inheritable ThreadLocal string first set
//        second task print: InheritableThreadLocal value: Inheritable ThreadLocal string first set
    }
}
