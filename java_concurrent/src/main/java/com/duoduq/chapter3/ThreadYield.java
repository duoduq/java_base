package com.duoduq.chapter3;

import java.util.stream.IntStream;

/**
 * @Auther: Jack
 * @Date: 2019-07-22 17:21
 * @Description:yield方法介绍
 */
public class ThreadYield {

    private static Thread create(int index) {
        return new Thread(() -> {
//            if(index==0)
//                Thread.yield();
            System.out.println(index);
        });
    }

    public static void main(String[] args) {
        /**
         * 多次运行程序，会发现输出的结果不一致，但是当你打开代码的注释部分，顺序始终是1、0
         * 因为第一个线程如果最先获得CPU资源，它会比较谦虚，主动告诉CPU调度器释放了原本属于
         * 自己的资源，但是yield只是一个提示，CPU调度器并不会担保每次都能满足yield提示。
         */
        IntStream.range(0, 2).mapToObj(ThreadYield::create).forEach(Thread::start);

    }
}
