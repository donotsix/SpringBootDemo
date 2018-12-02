package org.com.thread.test;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MainThread {

    public static void main(String[] args) {

        ThreadB b = new ThreadB();
        b.setK(200);
        ThreadA a = new ThreadA();
        a.setK(100);
        ThreadC c = new ThreadC();
        c.setK(100);
        ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(2, 3, 1,TimeUnit.SECONDS,   
                new ArrayBlockingQueue<Runnable>(10),  
                new ThreadPoolExecutor.DiscardOldestPolicy());  
        threadPoolExecutor.execute(a);
        threadPoolExecutor.execute(b);
        threadPoolExecutor.execute(c);
        
        System.out.println(threadPoolExecutor.getActiveCount());
        
/*       Thread threadb = new Thread(b);
        Thread threada = new Thread(a);
        Thread threadc = new Thread(c);
        threadb.start();
        threada.start();
        threadc.start();*/
/*        try {
            threadb.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            threada.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
        System.out.println(a.getValue());
        System.out.println("主线程运行完毕");
    }

}
class ThreadA implements Runnable {
    int k;
    int value=0;

    public int getK() {
        return k;
    }
    public void setK(int k) {
        this.k = k;
    }
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }


    @Override
    public synchronized void run() {
        for (int i = 0; i < k; i++) {
            value=1+50;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread()+"threadA:"+i);
        }
    }

}
class ThreadB implements Runnable {
    int k;
    

    public int getK() {
        return k;
    }


    public void setK(int k) {
        this.k = k;
    }


    @Override
    public synchronized void run() {
        for (int i = 0; i < k; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread()+"ThreadB:"+i);
        }
    }

}
class ThreadC implements Runnable {
    int k;
    

    public int getK() {
        return k;
    }


    public void setK(int k) {
        this.k = k;
    }


    @Override
    public synchronized void run() {
        for (int i = 0; i < k; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread()+"ThreadC:"+i);
        }
    }

}