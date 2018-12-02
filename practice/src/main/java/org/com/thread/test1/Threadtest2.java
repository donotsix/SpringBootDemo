package org.com.thread.test1;

public class Threadtest2 {
    private static ThreadLocal<Object> local = new ThreadLocal<Object>();
    static{
        local.set(1);
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+":"+local.get());
        local.set(200);
        System.out.println(Thread.currentThread().getName()+":"+local.get());
        new Thread(new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread().getName()+":"+local.get());
                for (int i = 0; i < 1000; i++) {
                }
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread().getName()+":"+local.get());
                for (int i = 0; i < 1000; i++) {
                }
            }
        }).start();
        Thread.sleep(1000);
    }
}
