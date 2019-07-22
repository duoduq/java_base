package com.duoduq.chapter2;

/**
 * @Auther: Jack
 * @Date: 2019-07-22 15:07
 * @Description:守护线程
 */
public class DaemonThread {

    public static void main(String[] args) throws InterruptedException {
        /**
         * 一个是由JVM启动的main线程，另外一个则是自己创建的线程thread，运行这段代码，
         * 发现JVM进程永远不会退出，即使main线程正常地结束自己的生命周期，原因就是因为
         * 在JVM进程中还存在一个非守护线程在运行。
         */
        Thread thread = new Thread(()->{
            while (true){
                try {
                    Thread.sleep(1);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
//        thread.setDaemon(true);//将Thread设置为守护线程
        thread.start();//启动thread线程
        Thread.sleep(2_000L);
        System.out.println("Main thread finishied lifecycle.");
    }
}
