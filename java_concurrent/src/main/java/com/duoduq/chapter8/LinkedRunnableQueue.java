package com.duoduq.chapter8;

import java.util.LinkedList;

/**
 * @Auther: Jack
 * @Date: 2019-08-13 16:35
 * @Description:
 */
public class LinkedRunnableQueue implements RunnableQueue {

    //任务队列的最大容量，在构造时传入
    private final int limit;

    //若任务队列中的任务已经满了，则需要执行拒绝策略
    private final DenyPolicy denyPolicy;

    //存放任务的队列
    private final LinkedList<Runnable> runnableList = new LinkedList<>();

    private final ThreadPool threadPool;

    public LinkedRunnableQueue(int limit, DenyPolicy denyPolicy, ThreadPool threadPool) {
        this.limit = limit;
        this.denyPolicy = denyPolicy;
        this.threadPool = threadPool;
    }

    /**
     * offer方法是一个同步方法，如果队列数量达到了上限，则会执行拒绝策略，否则会将runnable存放至队列中
     * 同时唤醒take任务的线程
     * @param runnable
     */
    @Override
    public void offer(Runnable runnable) {
        synchronized (runnableList) {
            if (runnableList.size() >= limit){
                //无法容纳新的任务时执行拒绝策略
                denyPolicy.reject(runnable,threadPool);
            }else {
                //将任务加入队尾，并且唤醒阻塞中的线程
                runnableList.add(runnable);
                runnableList.notifyAll();
            }
        }
    }

    /**
     * take方法也是同步方法，线程不断从队列中获取Runnable任务，当队列为空的时候工作线程就会陷入阻塞，有可能在
     * 阻塞的过程中被中断，为了传递中断信号需要在catch语句块中将异常抛出以通知上游
     * @return
     * @throws InterruptedException
     */
    @Override
    public Runnable take() throws InterruptedException{
        synchronized (runnableList){
            while (runnableList.isEmpty()){
                try {
                    /**
                     * 如果任务队列中没有可执行任务，则当前线程将会挂起，进入runnableList关联的monitor
                     * waitset中等待唤醒(新的任务加入)
                     */
                    runnableList.wait();
                } catch (InterruptedException e) {
                    //被中断时需要将该异常抛出
                    e.printStackTrace();
                    throw e;
                }
            }
        }
        return runnableList.removeFirst();
    }

    @Override
    public int size() {
        synchronized (runnableList){
            return runnableList.size();
        }
    }
}
