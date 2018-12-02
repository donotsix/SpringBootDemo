package org.com.test.abstractTest;

public class Man extends Person{

    @Override
    public void eat() {
        System.out.println("Man eat!");
    }

    @Override
    public void say() {
        System.out.println("Man say hello!");
    }

}
