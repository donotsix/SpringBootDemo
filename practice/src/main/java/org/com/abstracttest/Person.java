package org.com.abstracttest;

public abstract class Person {
    int a;
    
    public abstract void say();
    
    public void eat(){
        System.out.println(a);
        System.out.println("Person eat");
    };
}
