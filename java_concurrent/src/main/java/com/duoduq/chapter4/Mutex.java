package com.duoduq.chapter4;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @Auther: Jack
 * @Date: 2019-07-25 22:34
 * @Description:
 */
public class Mutex {

    private final static Object MUTEX = new Object();

    public void accessResource() {
        synchronized (MUTEX) {
            System.out.println(Thread.currentThread().getName() + " will be working.");
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " will be exit.");
        }
    }

    public static void main(String[] args) {
        final Mutex mutex = new Mutex();
        for (int i = 0; i < 5; i++) {
            new Thread(mutex::accessResource, "第" + i + "线程").start();
        }
    }
}
