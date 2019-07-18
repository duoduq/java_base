package com.duoduq.chapter10;

import com.duoduq.chapter10.MyClassLoader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Auther: Jack
 * @Date: 2019-07-18 15:42
 * @Description:测试MyClassLoader
 */
public class MyClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException,
            InstantiationException, NoSuchMethodException, InvocationTargetException {
        //声明一个MyClassLoader
        MyClassLoader classLoader = new MyClassLoader();
        //使用MyClassLoader加载HelloWorld
        Class<?> aClass = classLoader.loadClass("com.duoduq.chapter10.HelloWorld");
        System.out.println(aClass.getClassLoader());//1
        Object helloWorld = aClass.newInstance();
        System.out.println(helloWorld);
        Method welocomeMethod = aClass.getMethod("welcome");
        String result = (String) welocomeMethod.invoke(helloWorld);
        System.out.println("Result:"+result);
    }
}
