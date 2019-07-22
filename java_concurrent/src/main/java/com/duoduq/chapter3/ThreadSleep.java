package com.duoduq.chapter3;


/**
 * @Auther: Jack
 * @Date: 2019-07-22 15:51
 * @Description:线程sleep
 */
public class ThreadSleep {

    public static void main(String[] args) {
        /**
         * 在自定义的线程和主线程中进行了休眠，每个线程的休眠互不影响，
         * Thread.sleep只会导致当前线程进入指定时间的休眠。
         */
        new Thread(()->{
            long startTime = System.currentTimeMillis();
            sleep(2_000L);
            long endTime = System.currentTimeMillis();
            System.out.println(String.format("Total spend %d ms",(endTime-startTime)));
        }).start();
        long startTime = System.currentTimeMillis();
        sleep(3_000L);
        long endTime = System.currentTimeMillis();
        System.out.println(String.format("Total spend %d ms",(endTime-startTime)));
    }

    private static void sleep(Long ms){
        try{
            Thread.sleep(ms);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
