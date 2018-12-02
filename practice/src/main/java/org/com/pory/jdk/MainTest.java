package org.com.pory.jdk;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class MainTest {
    public static void main(String[] args) {
        
        Callable<String> callable=new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("abc");
                return "abc";
            }
        };
        FutureTask<String> futureTask=new FutureTask<>(callable);
        InterfaceA interfaceA=new InterfaceAImpl();
        JdkProxy jdkProxy=new JdkProxy();
        InterfaceA interfaceA2= (InterfaceA) jdkProxy.newProxy(interfaceA);
        interfaceA2.sayHello();
    }
}
