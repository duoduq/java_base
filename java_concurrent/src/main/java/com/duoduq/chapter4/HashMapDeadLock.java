package com.duoduq.chapter4;

import java.util.Collections;
import java.util.HashMap;

/**
 * @Auther: Jack
 * @Date: 2019-07-26 10:48
 * @Description:
 */
public class HashMapDeadLock {

    private final HashMap<String, String> map = new HashMap();

    public void add(String key, String value) {
        this.map.put(key, value);
    }

    public static void main(String[] args) {
        /**
         * HashMap不具备线程安全的能力，如果想要使用线程安全的map结构
         * 请使用ConcurrentHashMap或者Collections.synchronizedMap()来代替
         */
        final HashMapDeadLock hmdl = new HashMapDeadLock();
        for (int x = 0; x < 2; x++) {
            new Thread(() -> {
                for (int i = 1; i < Integer.MAX_VALUE; i++) {
                    hmdl.add(String.valueOf(i), String.valueOf(i));
                }
            }).start();
        }
    }
}
