package com.hins.lock.lock;

import java.util.concurrent.*;

/**
 * @author: hins
 * @created: 2021-01-26 14:53
 * @desc: CLH 公平锁并发修改数值测试
 **/
public class CLHLockTest {

    private static int num = 0;


    public static void main(String[] args) throws InterruptedException {

        CLHLock clhLock = new CLHLock();
        CountDownLatch countDownLatch = new CountDownLatch(2);
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for(int i = 0; i < 2; i++){

            executorService.submit(() -> {

//                clhLock.lock();
                num++ ;
//                clhLock.unlock();

                countDownLatch.countDown();
            });
        }


        countDownLatch.await();

        System.out.println(num);

        executorService.shutdown();
    }


}
