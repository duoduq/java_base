package com.duoduq.chapter2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther: Jack
 * @Date: 2019-07-22 14:25
 * @Description:Thread与虚拟机栈
 */
public class ThreadCounter extends Thread{

    final static AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) {
        /**
         * 不断地创建线程，知道JVM在也没有能力创建新的线程为止，通过设置栈内存的大小来分析栈内存大小对创建线程的影响，运行该程序
         * 容易出现系统死机的情况。
         * Java -Xmx256m -Xms64m
         */
        try{
            while (true){
                new ThreadCounter().start();
            }
        }catch (Throwable e){
            System.out.println("failed At=>"+counter.get());
        }
    }

    @Override
    public void run() {
        try{
            System.out.println("The "+counter.getAndIncrement()+" thread be created.");
            TimeUnit.MICROSECONDS.sleep(10);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
