package com.duoduq.chapter10;

/**
 * @Auther: Jack
 * @Date: 2019-07-18 14:25
 * @Description: 扩展类加载器
 *  扩展类加载器的父加载器是根加载器，它主要用于加载JAVA_HOME下的jre\lib\ext子目录里面的类库。扩展类加载器是由纯Java语言实现的，
 * 它是java.lang.URLClassLoader的子类，它的完整类名是sun.misc.Launcher$ExtClassLoader。扩展类加载器所加载的类库可以通过
 * 系统属性java.ext.dirs获得。
 */
public class ExtClassLoader {

    public static void main(String[] args) {
        System.out.println(System.getProperty("java.ext.dirs"));
    }

    /**
     * 运行示例：
     * /Users/apple/Library/Java/Extensions:/Library/Java/JavaVirtualMachines/jdk1.8.0_151.jdk/Contents/Home/jre/lib/ext:
     * /Library/Java/Extensions:/Network/Library/Java/Extensions:/System/Library/Java/Extensions:/usr/lib/java
     */
}
