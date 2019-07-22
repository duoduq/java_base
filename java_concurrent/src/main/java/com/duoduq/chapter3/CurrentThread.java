package com.duoduq.chapter3;

/**
 * @Auther: Jack
 * @Date: 2019-07-22 23:20
 * @Description:获取当前线程
 */
public class CurrentThread {

    public static void main(String[] args) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                //always true
                System.out.println(Thread.currentThread() == this);
            }
        };
        thread.start();
        String name = thread.currentThread().getName();
        System.out.println("main".equalsIgnoreCase(name));
    }
}
