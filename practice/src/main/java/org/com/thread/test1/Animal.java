package org.com.thread.test1;

public abstract class Animal {
    String name;
    String shape;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getShape() {
        return shape;
    }
    public void setShape(String shape) {
        this.shape = shape;
    }    
    public abstract void eat();
    public abstract void drink();
}
