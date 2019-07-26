package com.duoduq.chapter2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
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

    public static void test03() {
        Thread t1 = new Thread("t1");
        ThreadGroup group = new ThreadGroup("TestGroup");
        Thread t2 = new Thread(group, "t2");
        ThreadGroup mainThreadGroup = Thread.currentThread().getThreadGroup();
        System.out.println("Main thread belong group:" + mainThreadGroup.getName());
        System.out.println("t1 and main belong the same group:" + (mainThreadGroup == t1.getThreadGroup()));
        System.out.println("t2 thread group not belong main group:" + (mainThreadGroup == t2.getThreadGroup()));
        System.out.println("t2 thread group belong main group:" + (group == t2.getThreadGroup()));
    }

    private static Thread createThread(final int intName) {
        return new Thread(() -> System.out.println(Thread.currentThread().getName()), PREFIX + intName);
    }

    public static void main(String[] args) {
        List<Integer> integers = IntStream.range(0, 5).boxed().collect(Collectors.toList());
        /**
         * 在线程中，设定了一个简单的递归，就是不断调用自己，然后输出int值，
         * 为了使的效果更加明显，在运行上面的代码过程中请指定Jvm内存参数为
         * java -Xmx512m -Xms64m ThreadConstruction 1
         */
        if (integers.size() < 1) {
            System.out.println("Please enter the stack size");
            System.exit(1);
        }
        ThreadGroup group = new ThreadGroup("TestGroup");
        Runnable runnable = new Runnable() {
            final int MAX = Integer.MAX_VALUE;

            @Override
            public void run() {
                int i = 0;
                recurse(1);
            }

            private void recurse(int i) {
                System.out.println(i);
                if (i < MAX) {
                    recurse(i + 1);
                }
            }
        };
        Thread thread = new Thread(group, runnable, "Test", integers.get(0));
        thread.start();
    }

}
