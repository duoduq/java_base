package com.duoduq.chapter8;

/**
 * @Auther: Jack
 * @Date: 2019-08-13 16:22
 * @Description:是RuntimeException的子类，主要用于通知任务提交者，任务队列已无法在接收新的任务
 */
public class RunnableDenyException extends RuntimeException {

    public RunnableDenyException(String message) {
        super(message);
    }
}
