package org.com.pory.cglib;

public class MainTest {
    public static void main(String[] args) {
        
        InterfaceAImpl aImpl=(InterfaceAImpl)new CGLIBProxy().newProxy(new InterfaceAImpl());
        aImpl.sayHello();
    }

}
