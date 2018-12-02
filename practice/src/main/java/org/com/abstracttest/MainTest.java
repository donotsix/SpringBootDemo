package org.com.abstracttest;

public class MainTest {
    static int a = 1;

    public static void main(String[] args) {
        Person person = new Woman();
        person.say();
        person.eat();
        String aString = "abcdabcdabc";
        char[] charArray = aString.toCharArray();
        System.out.println(charArray.length);
        
    }
    public void test() {
        System.out.println(a);
    }
}
