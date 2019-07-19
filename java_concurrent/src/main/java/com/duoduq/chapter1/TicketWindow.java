package com.duoduq.chapter1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Auther: Jack
 * @Date: 2019-07-19 15:45
 * @Description:Thread模拟营业大厅叫号机程序
 */
public class TicketWindow extends Thread {
    //柜台名称
    private static String name;

    //最多受理50笔业务
    private static final int MAX = 50;

    //private int index = 1;
    private static int index = 1;

    public TicketWindow(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (index <= MAX){
            System.out.println("柜台:"+name+"当前的号码是："+(index++));
        }
    }

    public static void main(String[] args) {
        TicketWindow ticketWindow1 = new TicketWindow("一号出号机");
        ticketWindow1.start();

        TicketWindow ticketWindow2 = new TicketWindow("二号出号机");
        ticketWindow2.start();

        TicketWindow ticketWindow3 = new TicketWindow("三号出号机");
        ticketWindow3.start();

        TicketWindow ticketWindow4 = new TicketWindow("四号出号机");
        ticketWindow4.start();
    }
}
