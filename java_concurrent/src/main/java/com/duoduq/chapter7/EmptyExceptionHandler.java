package com.duoduq.chapter7;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: Jack
 * @Date: 2019-07-29 16:52
 * @Description:
 */
public class EmptyExceptionHandler {

    /**
     * 代码中没有设置默认的Handler,也没有对thread指定Handler，因此当thread出现异常时，会向上寻找group的uncaughtException方法
     * @param args
     */
    public static void main(String[] args) {
        //get current thread's thread group
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        System.out.println(mainGroup.getName());
        System.out.println(mainGroup.getParent());
        System.out.println(mainGroup.getParent().getParent());

        final Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {

            }
            //have will throw unchecked exception.
            System.out.println(1 / 0);
        },"Test-Thread");
        thread.start();
    }
}
