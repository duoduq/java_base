package com.duoduq.chapter10;

/**
 * @Auther: Jack
 * @Date: 2019-07-18 16:56
 * @Description:类加载器命名空间
 * 每一个类加载器示例都有各自的命名空间，命名空间是由该加载器及其所有父加载器所构成的，因此在每个类加载器中同一个class都是独一无二的。
 */
public class NameSpace {

    public static void main(String[] args) throws ClassNotFoundException {
        test03();
    }


    public static void test01() throws ClassNotFoundException {
        //获取系统类加载器
        ClassLoader classLoader = NameSpace.class.getClassLoader();
        Class<?> aclass = classLoader.loadClass("com.duoduq.chapter10.Test");
        Class<?> bclass = classLoader.loadClass("com.duoduq.chapter10.Test");
        System.out.println(aclass.hashCode());
        System.out.println(bclass.hashCode());
        System.out.println(aclass == bclass);
        /**
         * 运行示例：
         * 1627674070
         * 1627674070
         * true
         * 不论运行多少次Test，你都将会发现他们是同一份class对象。但是使用不同的类加载器，或者同一个类加载器的不同实例，
         * 去加载同一个Class，则会在堆内存和方法区产生多个class的对象
         */
    }

    //不同类加载器加载同一个class
    public static void test02() throws ClassNotFoundException {
        MyClassLoader classLoader1 = new MyClassLoader("/Users/apple/Documents/classloader1",null);
        BrokerDelegateClassLoader classLoader2 = new BrokerDelegateClassLoader("/Users/apple/Documents/classloader1",null);
        Class<?> aclass = classLoader1.loadClass("com.duoduq.chapter10.Test");
        Class<?> bclass = classLoader2.loadClass("com.duoduq.chapter10.Test");
        System.out.println(aclass.hashCode());
        System.out.println(bclass.hashCode());
        System.out.println(aclass == bclass);
    }

    //相同类加载器加载同一个class
    public static void test03() throws ClassNotFoundException {
        MyClassLoader classLoader1 = new MyClassLoader("/Users/apple/Documents/classloader1",null);
        MyClassLoader classLoader2 = new MyClassLoader("/Users/apple/Documents/classloader1",null);
        Class<?> aclass = classLoader1.loadClass("com.duoduq.chapter10.Test");
        Class<?> bclass = classLoader2.loadClass("com.duoduq.chapter10.Test");
        System.out.println(aclass.hashCode());
        System.out.println(bclass.hashCode());
        System.out.println(aclass == bclass);
        /**
         * 运行示例：
         * 644117698
         * 1872034366
         * false
         * JDK中关于ClassLoader的相关源码
         * protected Class<?> loadClass(String name, boolean resolve)
         *         throws ClassNotFoundException
         *     {
         *         synchronized (getClassLoadingLock(name)) {
         *             // First, check if the class has already been loaded
         *             Class<?> c = findLoadedClass(name);
         *             if (c == null) {
         *                 long t0 = System.nanoTime();
         *                 try {
         *                     if (parent != null) {
         *                         c = parent.loadClass(name, false);
         *                     } else {
         *                         c = findBootstrapClassOrNull(name);
         *                     }
         *                 } catch (ClassNotFoundException e) {
         *                     // ClassNotFoundException thrown if class not found
         *                     // from the non-null parent class loader
         *                 }
         *
         *                 if (c == null) {
         *                     // If still not found, then invoke findClass in order
         *                     // to find the class.
         *                     long t1 = System.nanoTime();
         *                     c = findClass(name);
         *
         *                     // this is the defining class loader; record the stats
         *                     sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
         *                     sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
         *                     sun.misc.PerfCounter.getFindClasses().increment();
         *                 }
         *             }
         *             if (resolve) {
         *                 resolveClass(c);
         *             }
         *             return c;
         *         }
         *     }
         * 在类加载器进行类加载的时候，首先会到加载记录表也就是缓存中，查看该类是否已经被加载过了，
         * 如果已经被加载过了，就不会重复加载，否则将会认为其是首次加载
         *
         * 经过对类加载器命名空间的了解之后，同一个class实例只能在jvm中存在一份这样的说法是不够严谨的，
         * 更准确的说法应该是同一个class实例在同一个类加载器命名空间之下是唯一的。
         */
    }
}
