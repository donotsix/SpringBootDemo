package org.com.test.classexecute.sequence;

public class ChildClass extends PerantClass {

    static {
        System.out.println("我是子类（静态代码块）!");
    }

    static int a = init();
    
    
    
    public static int initc() {
        System.out.println("我是子类（成员变量）");
        return 1;
    }
    
    {
        System.out.println("我是子类（普通代码块）!");
    }
    
    int b=initc();
    
    public ChildClass() {
        System.out.println("我是子类（构造器）");
        System.out.println("--------------------");
    }

    public void sayHello() {
        System.out.println("我是子类（成员方法）");
    }

    public static void main(String[] args) {
        ChildClass class1 = new ChildClass();
        class1.sayHello();
    }

    public static int init() {
        System.out.println("我是子类（静态变量）");
        System.out.println("--------------------");
        return 1;
    }

}
