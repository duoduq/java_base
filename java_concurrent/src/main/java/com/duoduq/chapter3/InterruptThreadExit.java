package com.duoduq.chapter3;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: Jack
 * @Date: 2019-07-23 22:45
 * @Description:捕获中断信号关闭线程
 */
public class InterruptThreadExit {

    public static void test01() throws InterruptedException {
        Thread t = new Thread() {
            @Override
            public void run() {
                System.out.println("I will start work.");
                while (!isInterrupted()) {
                    //working
                }
                System.out.println("I will be exiting.");
            }
        };
        t.start();
        TimeUnit.MINUTES.sleep(1);
        System.out.println("System will be shutdown.");
        t.interrupt();
    }

    public static void test02() throws InterruptedException {
        Thread t = new Thread() {
            @Override
            public void run() {
                System.out.println("I will start work.");
                for (;;) {
                    //working
                    try {
                        TimeUnit.MILLISECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
                System.out.println("I will be exiting.");
            }
        };
        t.start();
        TimeUnit.MINUTES.sleep(1);
        System.out.println("System will be shutdown.");
        t.interrupt();
    }

    public static void main(String[] args) throws InterruptedException {
        test02();
    }

}
