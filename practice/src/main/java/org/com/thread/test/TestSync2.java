package org.com.thread.test;

public class TestSync2 implements Runnable {
    private int i;

    public static void main(String[] args) {
        TestSync2 dt1 = new TestSync2();

        Thread t1 = new Thread(dt1);
        Thread t2 = new Thread(dt1);
        Thread t3 = new Thread(dt1);
        Thread t4 = new Thread(dt1);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }

    public void run() {
        long start = System.currentTimeMillis();
        for (i = 1; i < 100000000; i++) {
            synchronized (this) {

            }
        }
        long end = System.currentTimeMillis();
        System.out.println("时间" + (end - start));
    }
}
