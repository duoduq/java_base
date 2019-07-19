package com.duoduq.chapter10;

/**
 * @Auther: Jack
 * @Date: 2019-07-19 10:27
 * @Description:
 */
public class LoadString {

    public static void main(String[] args) throws ClassNotFoundException {
        BrokerDelegateClassLoader classLoader = new BrokerDelegateClassLoader("/Users/apple/Documents/classloader3");
        Class<?> aClass = classLoader.loadClass("java.lang.String");
    }
}
