package com.duoduq.chapter8;

/**
 * @Auther: Jack
 * @Date: 2019-08-13 15:59
 * @Description:主要用于存放提交的Runnable,该Runnable是一个BlockedQueue,并且具有limit的限制。
 */
public interface RunnableQueue {

    //当有新的任务进来是首先会offer到队列中
    void offer(Runnable runnable);

    //工作线程通过take方法获取Runnable
    Runnable take() throws InterruptedException;

    //获取任务队列中任务的数量
    int size();

}
