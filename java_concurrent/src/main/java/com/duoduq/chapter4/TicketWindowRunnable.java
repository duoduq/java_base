package com.duoduq.chapter4;

/**
 * @Auther: Jack
 * @Date: 2019-07-25 17:17
 * @Description:synchronized
 */
public class TicketWindowRunnable implements Runnable {

    private final static int MAX = 500;

    private int index = 1;

    private final static Object MUTER = new Object();

    @Override
    public void run() {
        synchronized (MUTER) {
            while (index <= MAX) {
                System.out.println(Thread.currentThread() + "的号码是：" + (index++));
            }
        }
    }

    public static void main(String[] args) {
        /**
         * 多次运行程序，每次都会不一样的发现，但是总结起来主要有三个问题
         * 1.某个号码被略过没有出现
         * 2.某个号码被多次显示
         * 3.号码超过了最大值500
         */
        TicketWindowRunnable task = new TicketWindowRunnable();
        Thread t1 = new Thread(task, "一号窗口");
        Thread t2 = new Thread(task, "二号窗口");
        Thread t3 = new Thread(task, "三号窗口");
        Thread t4 = new Thread(task, "四号窗口");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
