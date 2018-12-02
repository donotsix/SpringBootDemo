package org.com.test.abstractTest;

public class MainTest {

    public static void main(String[] args) {
        Man manTemp = new Man();
        manTemp.setName("I'm a man!");
        Animal man = manTemp;
        man.eat();
        man.yep();
        Animal woman = new Woman();
        woman.eat();
        woman.yep();
        Animal dog = new Dog();
        dog.eat();
        dog.yep();
        if (man instanceof Man) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
        Man man2 = (Man) man;
        System.out.println(man2.getName());
    }

}
