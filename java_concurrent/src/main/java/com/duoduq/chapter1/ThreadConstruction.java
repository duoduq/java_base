package com.duoduq.chapter1;

import java.util.stream.IntStream;

/**
 * @Auther: Jack
 * @Date: 2019-07-19 17:20
 * @Description:线程的默认命名
 */
public class ThreadConstruction {

    private final static String PREFIX = "ALEX-";

    public static void test01() {
        IntStream.range(0, 5).boxed()
                .map(integer -> new Thread(() -> System.out.println(Thread.currentThread().getName())))
                .forEach(Thread::start);
    }

    public static void test02() {
        IntStream.range(0, 5).mapToObj(ThreadConstruction::createThread).forEach(Thread::start);
    }

    private static Thread createThread(final int intName) {
        return new Thread(() -> System.out.println(Thread.currentThread().getName()), PREFIX + intName);
    }

    public static void main(String[] args) {
        test02();
    }
    /**
     * 修改线程的名字
     *  不论你使用的是默认的函数命名规则，还是指定了一个特殊的名字，在线程启动之前还有一个机会可以对其进行修改，
     *  一旦线程启动，名字将不再被修改，下面是Thread的setName源码：
     *      public final synchronized void setName(String name) {
     *             checkAccess();
     *             if (name == null) {
     *                throw new NullPointerException("name cannot be null");
     *             }
     *
     *             this.name = name;
     *             if (threadStatus != 0) {
     *                 setNativeName(name);
     *             }
     *     }
     */

}
