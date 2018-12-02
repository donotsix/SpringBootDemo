package org.com.thread.test;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestSync1 implements Runnable {
    private Lock lock = new ReentrantLock();
    private int i;

    public static void main(String[] args) {
        TestSync1 dt = new TestSync1();

        Thread t1 = new Thread(dt);
        Thread t2 = new Thread(dt);
        Thread t3 = new Thread(dt);
        Thread t4 = new Thread(dt);
        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }

    public void run() {
        long start = System.currentTimeMillis();
        for (i = 1; i < 100000000; i++) {
            lock.lock();
            try {

            } finally {
                lock.unlock();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("时间" + (end - start));
    }

}
