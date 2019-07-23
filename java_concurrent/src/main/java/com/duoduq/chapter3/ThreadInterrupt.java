package com.duoduq.chapter3;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: Jack
 * @Date: 2019-07-23 14:45
 * @Description:Interrupt
 */
public class ThreadInterrupt {

    public static void test01() throws InterruptedException {
        /**
         * 创建一个线程，并且企图休眠1分钟的时长，不过很可惜，大约在2毫秒之后就被主线程调用
         * Interrupt方法打断，程序的执行结果就是"Oh,i am be interrupted."
         */
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.MINUTES.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("Oh,i am be interrupted.");
            }
        });
        thread.start();
        TimeUnit.MICROSECONDS.sleep(2);
        thread.interrupt();
    }

    public static void test02(){
        /**
         * 如果一个线程设置了interrupt标识，那么接下来的可中断方法会立即中断。
         */
        //判断当前线程是否被中断
        System.out.println("Main thread is interrupted? "+Thread.interrupted());
        //中断当前线程
        Thread.currentThread().interrupt();
        //判断当前线程是否已经被中断
        System.out.println("Main thread is interrupted? "+Thread.currentThread().isInterrupted());
        try{
            //当前线程执行可中断方法
            TimeUnit.MINUTES.sleep(1);
        }catch (InterruptedException e){
            //捕获中断信号
            System.out.println("I will be interrupted still.");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        test02();
    }
}
