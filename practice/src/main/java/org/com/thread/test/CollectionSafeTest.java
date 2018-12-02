package org.com.thread.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

public class CollectionSafeTest {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMap<String, Object> list=new ConcurrentHashMap<>();
        list.put("1",123);
        Runable1 runnable1=new Runable1();
        runnable1.setList(list);
        Runable2 runnable2=new Runable2();
        runnable2.setList(list);
        Thread thread2=new Thread(runnable2);
        Thread thread=new Thread(runnable1);
        thread.start();
        thread2.start();
        Thread.sleep(500);
        System.out.println(list.toString());
    }
    
}
class Runable1 implements Runnable{
    ConcurrentHashMap<String, Object> list;
    
    public ConcurrentHashMap<String, Object> getList() {
        return list;
    }

    public void setList(ConcurrentHashMap<String, Object> list) {
        this.list = list;
    }

    @Override
    public void run() {
        for(int i=0;i<100;i++){
            list.put(i+"", 100);   
        }
    }
    
}
class Runable2 implements Runnable{
    ConcurrentHashMap<String, Object> list;
    
    public ConcurrentHashMap<String, Object> getList() {
        return list;
    }

    public void setList(ConcurrentHashMap<String, Object> list) {
        this.list = list;
    }

    @Override
    public void run() {
        for(int i=0;i<100;i++){
            list.remove(i);
        }
       
    }
    
}