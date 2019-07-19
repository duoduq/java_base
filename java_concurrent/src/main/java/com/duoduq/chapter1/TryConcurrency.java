package com.duoduq.chapter1;


import java.util.concurrent.TimeUnit;

/**
 * @Auther: Jack
 * @Date: 2019-07-19 10:47
 * @Description:尝试并行运行
 */
public class TryConcurrency {

    public static void main(String[] args) {
        test03();
    }

    private static void enjoyMusic() {
        for(;;){
            System.out.println("Uh-huh, the nice music.");
            sleep(1);

        }
    }

    private static void browseNews() {
        for(;;){
            System.out.println("Uh-huh, the good news.");
            sleep(1);

        }
    }

    private static void sleep(int second){
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void test01(){
        browseNews();
        enjoyMusic();
    }

    public static void test02(){
        new Thread(){
            @Override
            public void run() {
                browseNews();
            }
        }.start();
        enjoyMusic();
    }

    public static void test03(){
        new Thread(TryConcurrency::browseNews).start();
        enjoyMusic();
    }

    /**
     * 线程的生命周期：
     * new
     * runnable
     * running
     * blocked
     * terminated
     */
}
