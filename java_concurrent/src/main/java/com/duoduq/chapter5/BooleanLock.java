package com.duoduq.chapter5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeoutException;

import static java.lang.System.currentTimeMillis;
import static java.lang.Thread.currentThread;

/**
 * @Auther: Jack
 * @Date: 2019-07-29 14:11
 * @Description:
 */
public class BooleanLock implements Lock {

    private Thread currentThread;

    private boolean locked = false;

    private final List<Thread> blockedList = new ArrayList<>();

    @Override
    public void lock() throws InterruptedException {

        /*
         //如果某个线程被打断，那么它将与可能还存在与blockedList中
         synchronized (this) {
            while (locked) {
                blockedList.add(currentThread());
                this.wait();
            }
            blockedList.remove(currentThread());
            this.locked = true;
            this.currentThread = currentThread();
        }*/

        synchronized (this) {
            while (locked) {
                //暂存当前线程
                final Thread tempThread = currentThread();
                try {
                    if (!blockedList.contains(tempThread))
                        blockedList.add(tempThread);
                    this.wait();
                } catch (InterruptedException e) {
                    //如果当前线程在wait时被中断，则从blockedList中将其删除，避免内存泄漏
                    blockedList.remove(tempThread);
                    //继续抛出异常
                    throw e;
                }
            }
            blockedList.remove(currentThread());
            this.locked = true;
            this.currentThread = currentThread();
        }

    }

    @Override
    public void lock(long mills) throws InterruptedException, TimeoutException {
        synchronized (this) {
            if (mills <= 0) {
                this.lock();
            } else {
                long remainingMills = mills;
                long endMills = currentTimeMillis() + remainingMills;
                while (locked) {
                    if (remainingMills <= 0) {
                        throw new TimeoutException("can not get the lock during " + mills);
                    }
                    if (!blockedList.contains(currentThread())) {
                        blockedList.add(currentThread());
                    }
                    this.wait(remainingMills);
                    remainingMills = endMills - currentTimeMillis();
                }
                blockedList.remove(currentThread());
                this.locked = true;
                this.currentThread = currentThread();
            }
        }
    }

    @Override
    public void unlock() {
        synchronized (this) {
            if (currentThread == currentThread()) {
                this.locked = false;
                this.notifyAll();
            }
        }
    }

    @Override
    public List<Thread> getBlockedThreads() {
        return Collections.unmodifiableList(blockedList);
    }
}
