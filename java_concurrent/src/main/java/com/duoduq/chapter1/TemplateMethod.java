package com.duoduq.chapter1;

/**
 * @Auther: Jack
 * @Date: 2019-07-19 15:32
 * @Description:模板设计模式在Thread中的应用
 */
public class TemplateMethod {

    public final void print(String message) {
        System.out.println("###############");
        wrapPrint(message);
        System.out.println("###############");
    }

    protected void wrapPrint(String message) {
    }

    public static void main(String[] args) {
        TemplateMethod t1 = new TemplateMethod() {
            @Override
            protected void wrapPrint(String message) {
                System.out.println("*" + message + "*");
            }
        };
        t1.print("Hello Thread");

        TemplateMethod t2 = new TemplateMethod() {
            @Override
            protected void wrapPrint(String message) {
                System.out.println("+" + message + "+");
            }
        };
        t2.print("Hello Thread");
        /**
         * print方法类似于Thread的start方法，而wrapPrint则类似于run方法，这样做的好处是，程序结构有父类控制，
         * 并且是final修饰的，不允许被重写，子类只需要实现想要的逻辑任务即可
         */
    }
}
