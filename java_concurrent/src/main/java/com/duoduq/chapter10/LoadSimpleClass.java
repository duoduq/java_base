package com.duoduq.chapter10;

/**
 * @Auther: Jack
 * @Date: 2019-07-19 09:50
 * @Description:初识类加载器
 */
public class LoadSimpleClass {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        BrokerDelegateClassLoader classLoader = new BrokerDelegateClassLoader("/Users/apple/Documents/classloader2");
        Class<?> aClass = classLoader.loadClass("com.duoduq.chapter10.SimpleClass");
        System.out.println(classLoader.getParent());
        aClass.newInstance();
    }
    /**
     * 运行实例：
     * sun.misc.Launcher$AppClassLoader@18b4aac2
     * 1
     * Simple
     * element
     *  每一个类在经过CLassLoader的加载之后，在虚拟机中都会有对应的Class实例，如果某个类C被类加载器CL加载，那么CL就被称为C的初识类加载器。
     *  Jvm为每一个类加载器维护了一个列表，该列表中记录了该类加载器作为初始化类加载器的所有class，在加载一个类时，Jvm使用这些列表来判断该类
     * 是否已经被加载过了，是否需要首次加载。
     *  根据Jvm规范的规定，在类的加载过程中，所有参与的类加载器，即使没有亲自加载过该类，也都会被标识为该类的初始类加载器，比如java.lang.String
     * 首先经过了BrokerDelegateClassLoader类加载器，依次又经过了系统类加载器、扩展类加载器、根类加载器，这些类加载器都是java.lang.String的初
     * 始类加载器，Jvm会在每一个类加载器维护的列表中添加该class类型。
     *
     *
     * 类的卸载：
     *  对象在堆内存中的Class对象以及Class在方法区中的数据结构何时被回收呢？
     *      Jvm规定了一个Class只有在满足下面三个条件的时候才会被GC回收，也就是类被卸载
     *      1.该类所有的实例都已经被GC,比如Simple.class的所有Simple实例都被回收掉。
     *      2.加载该类的ClassLoader实例被回收
     *      3.该类的class实例没有在其他地方被引用
     */
}

