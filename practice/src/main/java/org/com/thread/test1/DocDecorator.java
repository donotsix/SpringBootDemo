package org.com.thread.test1;

public abstract class DocDecorator extends Coffee{
    
    Coffee coffee;

    public DocDecorator(Coffee coffee) {
        this.coffee = coffee;
    }


}
