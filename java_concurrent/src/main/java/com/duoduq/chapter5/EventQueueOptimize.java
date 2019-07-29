package com.duoduq.chapter5;

import java.util.LinkedList;

import static java.lang.Thread.currentThread;

/**
 * @Auther: Jack
 * @Date: 2019-07-26 11:41
 * @Description:改进
 */
public class EventQueueOptimize {
    /**
     * 在EventQueue中定义了一个队列，offer方法会提交一个Event至队尾，如果此时队列已经满了，那么
     * 提交的线程将会被阻塞，这是调用了wait方法的结果，同样take方法会从对头获取数据，如果队列中没
     * 有可用数据，那么工作线程就会被阻塞，这也是调用wait方法的直接结果，如果队列中没有可用数据，那
     * 么工作线程就会被阻塞，这也是调用wait方法的直接结果。
     */

    private final int MAX;

    static class Event {

    }

    private final LinkedList<Event> eventQueue = new LinkedList<>();

    private final static int DEFAULT_MAX_EVENT = 10;

    public EventQueueOptimize() {
        this(DEFAULT_MAX_EVENT);
    }

    public EventQueueOptimize(int max) {
        MAX = max;
    }

    public void offer(Event event) {
        synchronized (eventQueue) {
            while (eventQueue.size() >= MAX) {
                console(" the queue is full.");
                try {
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            console(" the new event is submitted");
            eventQueue.addLast(event);
            eventQueue.notifyAll();
        }
    }

    public Event take() {
        synchronized (eventQueue) {
            while (eventQueue.isEmpty()) {
                console(" the queue is empty.");
                try {
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Event event = eventQueue.removeFirst();
            this.eventQueue.notifyAll();
            console(" the event " + event + " is handled.");
            return event;
        }
    }

    private void console(String message) {
        System.out.printf("%s:%s\n", currentThread().getName(), message);
    }
}
