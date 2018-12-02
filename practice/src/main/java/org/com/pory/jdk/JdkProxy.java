package org.com.pory.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxy implements InvocationHandler {
    // 被代理的目标对象
    private Object proxyObj;

    public Object newProxy(Object proxyObj) {
        this.proxyObj = proxyObj;
        // 返回一个代理对象
        return Proxy.newProxyInstance(proxyObj.getClass().getClassLoader(), proxyObj.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        method.invoke(proxyObj, args);
        after();
        return null;
    }
    
    public void before(){
        System.out.println("Before proxy!");
    }
    
    public void after(){
        System.out.println("After proxy!");
    }
}
