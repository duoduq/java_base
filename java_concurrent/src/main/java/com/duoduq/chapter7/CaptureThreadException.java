package com.duoduq.chapter7;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: Jack
 * @Date: 2019-07-29 16:21
 * @Description:UncaughtExceptionHandler实例
 */
public class CaptureThreadException {

    public static void main(String[] args) {
        //设置回调接口
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            System.out.println(t.getName() + " occur exception.");
            e.printStackTrace();
        });
        final Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {

            }
            //这里会出现unchecked异常
            //here will throw unchecked exception
            System.out.println(1 / 0);
        }, "Test-Thread");
        thread.start();
    }
}
