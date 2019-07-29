package com.duoduq.chapter5;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.IntStream;

import static java.lang.Thread.currentThread;
import static java.util.concurrent.ThreadLocalRandom.current;

/**
 * @Auther: Jack
 * @Date: 2019-07-29 14:42
 * @Description:
 */
public class BooleanLockTest {

    //定义BooleanLock
    private final Lock lock = new BooleanLock();

    //使用try..finally语句块确保lock每次都能被正确释放
    public void syncMethod() {
        try {
            lock.lock();
            int randomInt = current().nextInt(10);
            System.out.println(currentThread() + " get the lock.");
            TimeUnit.SECONDS.sleep(randomInt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void syncMethodTimeoutable() {
        try {
            lock.lock(1000);
            System.out.println(currentThread() + " get the lock.");
            int randomInt = current().nextInt(10);
            TimeUnit.SECONDS.sleep(randomInt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void test01() {
        BooleanLockTest blt = new BooleanLockTest();
        //定义一个线程并且启动
        IntStream.range(0, 10).mapToObj(i -> new Thread(blt::syncMethod)).forEach(Thread::start);
    }

    public static void test02() throws InterruptedException {
        BooleanLockTest blt = new BooleanLockTest();
        new Thread(blt::syncMethod, "T1").start();
        TimeUnit.MILLISECONDS.sleep(2);
        Thread t2 = new Thread(blt::syncMethodTimeoutable, "T2");
        t2.start();
        TimeUnit.MILLISECONDS.sleep(10);
       // t2.interrupt();
    }

    public static void main(String[] args) {
        try {
            test02();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
