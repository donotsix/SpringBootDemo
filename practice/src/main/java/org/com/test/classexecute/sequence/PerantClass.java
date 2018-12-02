package org.com.test.classexecute.sequence;

public abstract class PerantClass {

    static int a = init();

    int b=initb();
    
    public static int init() {
        System.out.println("我是父类（静态变量）");
        return 1;
    }
    public static int initb() {
        System.out.println("我是父类（成员变量）");
        return 1;
    }

    static {
        System.out.println("我是父类（静态代码块）!");
    }

    public PerantClass() {
        System.out.println("我是父类（构造器）");
    }

    {
        System.out.println("我是父类（普通代码块）!");
    }

    public void sayHello() {
        System.out.println("我是父类（成员方法）");
    }

}
