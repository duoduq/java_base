package com.duoduq.chapter8;

/**
 * @Auther: Jack
 * @Date: 2019-08-13 16:06
 * @Description:提供了创建线程的接口，以便于个性化定制Thread，比如Thread应该被加到哪个Group中，优先级、线程名字以及是否为守护线程等
 */
//创建线程的工厂
@FunctionalInterface
public interface ThreadFactory {

    Thread createThread(Runnable runnable);
}
