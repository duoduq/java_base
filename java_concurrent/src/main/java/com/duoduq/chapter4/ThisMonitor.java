package com.duoduq.chapter4;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.currentThread;

/**
 * @Auther: Jack
 * @Date: 2019-07-25 23:54
 * @Description:
 */
public class ThisMonitor {

    public synchronized void method1() {
        System.out.println(currentThread().getName() + "enter to method1");
        try {
            TimeUnit.MINUTES.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void method2() {
        /*
        System.out.println(currentThread().getName() + "enter to method2");
        try {
            TimeUnit.MINUTES.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */
        synchronized (this){
            System.out.println(currentThread().getName() + "enter to method2");
            try {
                TimeUnit.MINUTES.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        /**
         * synchronized关键字同步类的不同实例方法，争抢的是同一个monitor的lock，
         * 而与之关联的因公则是ThisMonitor的实例引用
         */
        ThisMonitor thisMonitor = new ThisMonitor();
        new Thread(thisMonitor::method1,"T1").start();
        new Thread(thisMonitor::method2,"T2").start();
    }
}
