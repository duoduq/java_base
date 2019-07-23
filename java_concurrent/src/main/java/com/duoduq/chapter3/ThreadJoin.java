package com.duoduq.chapter3;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Auther: Jack
 * @Date: 2019-07-23 16:58
 * @Description:join
 */
public class ThreadJoin {

    private static Thread create(int seq) {
        return new Thread(() -> {
            IntStream.range(0, 10).boxed().forEach(i -> {
                System.out.println(Thread.currentThread().getName() + "#" + i);
                shortSleep();
            });
        }, String.valueOf(seq));
    }

    private static void shortSleep() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        /**
         * 创建亮哥线程，分别启动，并且调用了每个线程的join方法(注意：join方法是被主线程调用的，
         * 因此在第一个线程还没有结束生命周期的时候，第二个线程的join不会得到执行，但是此时，第
         * 二个线程也已经启动了)，运行上面的程序，发现线程一和线程二会交替地输出直到他们结束生命
         * 周期，main线程的循环才会运行。
         */
        //定义两个线程，并保存在threads中
        List<Thread> threads = IntStream.range(1, 3).mapToObj(ThreadJoin::create).collect(Collectors.toList());
        //启动这两个线程
        threads.forEach(Thread::start);

        //执行这两个线程的join方法
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        //main线程循环输出
        IntStream.range(0, 10).boxed().forEach(i -> {
            System.out.println(Thread.currentThread().getName() + "#" + i);
            shortSleep();
        });

    }
}
