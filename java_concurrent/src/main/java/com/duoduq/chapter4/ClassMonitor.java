package com.duoduq.chapter4;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.currentThread;

/**
 * @Auther: Jack
 * @Date: 2019-07-26 00:16
 * @Description:
 */
public class ClassMonitor {

    public static synchronized void method1() {
        System.out.println(currentThread().getName() + " enter method1.");
        try {
            TimeUnit.MINUTES.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void method2() {
        System.out.println(currentThread().getName() + " enter method2.");
        try {
            TimeUnit.MINUTES.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Thread(ClassMonitor::method1,"T1").start();
        new Thread(ClassMonitor::method2,"T2").start();
    }
}
