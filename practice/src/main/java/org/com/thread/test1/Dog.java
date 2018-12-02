package org.com.thread.test1;

public class Dog extends Animal{
    String voice;
    
    @Override
    public void eat() {
        System.out.println("dog eat");
        
    }

    @Override
    public void drink() {
        System.out.println("dog drink");
        
    }
    
    public void yep() {
        System.out.println("dog yep");
        
    }


}
