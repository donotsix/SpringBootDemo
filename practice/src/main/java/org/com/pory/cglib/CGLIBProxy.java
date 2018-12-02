package org.com.pory.cglib;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class CGLIBProxy implements MethodInterceptor{
    
    private Object proxyObj ;//被代理的目标对象
    
    public Object newProxy(Object proxyObj) {
        this.proxyObj = proxyObj;
        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(proxyObj.getClass());// 设置代理目标

        enhancer.setCallback( this );// 设置回调

        return enhancer.create();
    }

    @Override
    public Object intercept(Object arg0, Method arg1, Object[] arg2, MethodProxy methodProxy) throws Throwable {
        System. out .println( "前置处理开始 ..." );
        methodProxy.invoke( proxyObj , arg2);//执行目标对象的方法
        System. out .println( "后置处理开始  ..." );
        return null;
    }

}
