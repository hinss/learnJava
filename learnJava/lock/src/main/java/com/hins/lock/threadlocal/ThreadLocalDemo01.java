package com.hins.lock.threadlocal;

/**
 * @author: hins
 * @created: 2020-12-30 15:18
 * @desc:
 **/
public class ThreadLocalDemo01 {

    ThreadLocal<Integer> totalScore = ThreadLocal.withInitial(() -> {
        return new Integer(60);
    });

    Integer score = 60;

    public ThreadLocalDemo01() {
    }


    public void changeSocre(){
        score++;
    }

    public void changeTotalSocre(){
        Integer integer = totalScore.get();
        integer++;
        totalScore.set(integer);
    }


    public static void main(String[] args) {

        ThreadLocalDemo01 threadLocalDemo01 = new ThreadLocalDemo01();
        for(int i = 0; i < 1000; i++){

            new Thread(() -> {
                // 多线程访问公共变量的方式, 会出现加不到1060的情况。
                threadLocalDemo01.changeSocre();
                // 使用ThreadLocal 多线程下会复制60到自己的线程中，每个线程都加到61.
                threadLocalDemo01.changeTotalSocre();
                System.out.println(threadLocalDemo01.totalScore.get());
                System.out.println(threadLocalDemo01.score);
            }).start();
        }




    }


}
