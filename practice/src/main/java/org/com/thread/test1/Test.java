package org.com.thread.test1;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class Test implements Filter{
    private Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        new Test().testv();

    }

    private void testv() {
        String string = "agc";
        lock.lock();
        System.out.println(string);
        lock.lock();
        System.out.println(string);
        lock.unlock();
        lock.unlock();
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
            throws IOException, ServletException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub
        
    }
}
