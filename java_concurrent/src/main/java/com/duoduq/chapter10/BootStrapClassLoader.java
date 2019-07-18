package com.duoduq.chapter10;

/**
 * @Auther: jack
 * @Date: 2019-07-18 14:07
 * @Description:根类加载器
 *  根加载器又称为Bootstrap类加载器，该类加载器是最为顶层的类加载器，其没有任何父加载器，它是有C++编写的，主要负责虚拟机核心类库的加载，
 * 比如整个java.lang包都是由根加载器所加载的，可以通过-Xbootclasspath来指定根加载器的路径，也可以通过系统属性来得知当前jvm的跟加载器
 * 都加载那些资源。
 */
public class BootStrapClassLoader {

    public static void main(String[] args) {
        //String.class的类加载器是根加载器，根加载器是获取不到引用的，因此输出为null
        System.out.println("Bootstrap:"+String.class.getClassLoader());
        //根加载器所在的加载路径可以通过sun.boot.class.path这个属性来获得
        System.out.println(System.getProperty("sun.boot.class.path"));
    }

    /**
     * 运行示例：
     * Bootstrap:null
     * /Library/Java/JavaVirtualMachines/jdk1.8.0_151.jdk/Contents/Home/jre/lib/resources.jar:
     * /Library/Java/JavaVirtualMachines/jdk1.8.0_151.jdk/Contents/Home/jre/lib/rt.jar:
     * /Library/Java/JavaVirtualMachines/jdk1.8.0_151.jdk/Contents/Home/jre/lib/sunrsasign.jar:
     * /Library/Java/JavaVirtualMachines/jdk1.8.0_151.jdk/Contents/Home/jre/lib/jsse.jar:
     * /Library/Java/JavaVirtualMachines/jdk1.8.0_151.jdk/Contents/Home/jre/lib/jce.jar:
     * /Library/Java/JavaVirtualMachines/jdk1.8.0_151.jdk/Contents/Home/jre/lib/charsets.jar:
     * /Library/Java/JavaVirtualMachines/jdk1.8.0_151.jdk/Contents/Home/jre/lib/jfr.jar:
     * /Library/Java/JavaVirtualMachines/jdk1.8.0_151.jdk/Contents/Home/jre/classes
     */
}
