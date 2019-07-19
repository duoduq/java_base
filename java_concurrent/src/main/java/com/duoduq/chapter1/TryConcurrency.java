package com.duoduq.chapter1;


import java.util.concurrent.TimeUnit;

/**
 * @Auther: Jack
 * @Date: 2019-07-19 10:47
 * @Description:尝试并行运行
 */
public class TryConcurrency {

    public static void main(String[] args) throws InterruptedException {
        test05();
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
    public static void test04(){
        Thread thread = new Thread(){
            @Override
            public void run() {
                try{
                    TimeUnit.SECONDS.sleep(10);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        thread.start();//启动线程
        thread.start();//再次启动
    }

    public static void test05() throws InterruptedException {
        Thread thread = new Thread(){
            @Override
            public void run() {
                try{
                    TimeUnit.SECONDS.sleep(1);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        thread.start();//启动线程
        System.out.println(thread.getState());//RUNNABLE
        TimeUnit.SECONDS.sleep(2);//休眠主要是确保thread结束生命周期
        System.out.println(thread.getState());//TERMINATED
        thread.start();//企图重新激活
        /**
         * 该方法运行结果跟test04一样会抛出IllegalThreadStateException异常，但是这两个异常的抛出却有本质区别：
         * test04：重复启动
         * test05: 将线程的状态输出，可以看出线程的状态为TERMINATED，再次调用start方法是不允许的。
         */
    }
}
