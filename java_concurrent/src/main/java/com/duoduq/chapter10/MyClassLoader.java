package com.duoduq.chapter10;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Auther: Jack
 * @Date: 2019-07-18 14:51
 * @Description:自定义类加载器
 * 所有的自定义类加载器都是ClassLoader的直接子类或者间接子类，java.lang.ClassLoader是一个抽象类，
 * 它里面并没有抽象方法，但是有findClass方法，务必实现该方法，否则将会抛出Class找不到的异常。
 * protected Class<?> findClass(String name)throws ClassNotfoundException{
 * throw new ClassNotFoundException(name);
 * }
 */
// 自定义类加载器必须是ClassLoader的直接或者间接子类
public class MyClassLoader extends ClassLoader {

    //定义默认的class存放路径
    private final static Path DEFAULT_CLASS_DIR = Paths.get("/Users/apple/Documents", "classloader1");

    private final Path classDir;

    //使用默认的class路径
    public MyClassLoader() {
        super();
        this.classDir = DEFAULT_CLASS_DIR;
    }

    //允许传入指定路径的class路径
    public MyClassLoader(String classDir) {
        super();
        this.classDir = Paths.get(classDir);
    }

    //指定class路径的同时，指定父类加载器
    public MyClassLoader(String classDir, ClassLoader parent) {
        super(parent);
        this.classDir = Paths.get(classDir);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //读取class的二进制数据
        byte[] classBytes = this.readClassBytes(name);
        //如果数据为null,或者没有读到任何信息，则抛出ClassNotFoundException异常
        if (null == classBytes || classBytes.length == 0) {
            throw new ClassNotFoundException("Can not load the class " + name);
        }
        //调用defineClass方法定义class
        /**
         * 第一个是要定义类的名字，一般与findClass方法中的类名保持一致即可
         * 第二个是class文件的二进制字节数组
         * 第三个是字节数组的偏移量
         * 第四个是从偏移量开始读取多长的byte数据
         */
        return this.defineClass(name, classBytes, 0, classBytes.length);
    }

    //将class文件读入内存
    private byte[] readClassBytes(String name) throws ClassNotFoundException {
        //将包名分隔转换为文件路径分隔符
        String classPath = name.replace(".", "/");
        Path classFullPath = classDir.resolve(Paths.get(classPath + ".class"));
        if (!classFullPath.toFile().exists())
            throw new ClassNotFoundException("The class " + name + " not found.");
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Files.copy(classFullPath, baos);
            return baos.toByteArray();
        } catch (IOException e) {
            throw new ClassNotFoundException("load the class " + name + " occur error.", e);
        }
    }

    @Override
    public String toString() {
        return "MyClassLoader{" +
                "classDir=" + classDir +
                '}';
    }
}
