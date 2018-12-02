package org.com.test.abstractTest;

public abstract class Person extends Animal implements IPerson {
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public abstract void eat();

    @Override
    public void say() {
        System.out.println("person hello");
    }

    @Override
    public void yep() {
        say();
    }
}
