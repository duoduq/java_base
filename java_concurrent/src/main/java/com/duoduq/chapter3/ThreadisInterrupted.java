package com.duoduq.chapter3;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: Jack
 * @Date: 2019-07-23 15:11
 * @Description:isInterrupted
 */
public class ThreadisInterrupted {

    public static void test01() throws InterruptedException {
        /**
         * 定义一个线程，并且在线程的执行单元中(run方法)写了一个空的死循环，为什么不写sleep呢？
         * 因为sleep是可中断方法，会捕获到中断信号，从而干扰我们程序的结果。
         */
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    //do nothing,just empty loop.
                }
            }
        };
        thread.start();
        TimeUnit.MICROSECONDS.sleep(2);
        System.out.printf("Thread is interrupted ? %s\n", thread.isInterrupted());
        thread.interrupt();
        System.out.printf("Thread is interrupted ? %s\n", thread.isInterrupted());
    }

    public static void test02() throws InterruptedException {
        /**
         * 由于在run方法中使用了sleep这个可中断方法，它会捕获到中断信号，并且会擦除
         * interrupt标识，因此程序的执行结果都会是false。
         */
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    TimeUnit.MINUTES.sleep(1);
                } catch (InterruptedException e) {
                    //ignore the exception
                    //here the interrupt flag will be clear.
                    System.out.printf("Thread is interrupted ? %s\n", isInterrupted());
                }
            }
        };
        thread.setDaemon(true);
        thread.start();
        TimeUnit.MICROSECONDS.sleep(2);
        System.out.printf("Thread is interrupted ? %s\n", thread.isInterrupted());
        thread.interrupt();
        TimeUnit.MICROSECONDS.sleep(2);
        System.out.printf("Thread is interrupted ? %s\n", thread.isInterrupted());
    }

    public static void main(String[] args) throws InterruptedException {
        test02();
    }
}
