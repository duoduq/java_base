package com.duoduq.chapter5;

import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * @Auther: Jack
 * @Date: 2019-07-29 14:03
 * @Description:
 */
public interface Lock {
    /**
     * lock()方法永远阻塞，除非获取到了锁，这一点和synchronized非常类似，但是该方法是可以被中断的，
     * 中断时会抛出InterruptedException异常
     *
     * @throws InterruptedException
     */
    void lock() throws InterruptedException;

    /**
     * lock(long mills)方法除了可以被中断以外，还增加了对应的超时功能
     *
     * @param mills
     * @throws InterruptedException
     * @throws TimeoutException
     */
    void lock(long mills) throws InterruptedException, TimeoutException;

    /**
     * unlock()方法可用来进行锁的释放
     */
    void unlock();

    /**
     * getBlockedThreads()用于获取当前有哪些线程被阻塞
     *
     * @return
     */
    List<Thread> getBlockedThreads();
}
