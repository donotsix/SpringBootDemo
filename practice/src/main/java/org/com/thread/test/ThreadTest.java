package org.com.thread.test;

import java.util.concurrent.CountDownLatch;

public class ThreadTest {

    public static void main(String[] args) throws InterruptedException {
        NumBean signslten=new NumBean();
        signslten.setNum(20);
        long time1=System.currentTimeMillis();
        CountDownLatch countDownLatch=new CountDownLatch(2);
        Runnable task = new Runnable() {
            public void run() {
                while (true) {
/*                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }*/
                    synchronized (NumBean.class) {
                        if (signslten.getNum() > 0) {
                            try {
                                // 通过睡眠线程来模拟出最后一张票的抢票场景
                                Thread.sleep(200);
                                int num = signslten.getNum();
                                System.out.println(Thread.currentThread().getName() + "卖票---->" + num--);
                                signslten.setNum(num);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }else{
                            countDownLatch.countDown();
                        }
                    }
                }
            }
        };
        Thread t1 = new Thread(task, "A");
        Thread t2 = new Thread(task, "B");
        t1.start();
        t2.start();
        countDownLatch.await();
        System.out.println(time1-System.currentTimeMillis());
        
        
        
/*        NumBean signslten2=new NumBean();
        signslten2.setNum(20);
        long time2=System.currentTimeMillis();
        while (true) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            if (signslten2.getNum() > 0) {
                try {
                    // 通过睡眠线程来模拟出最后一张票的抢票场景
                    Thread.sleep(200);
                    int num = signslten2.getNum();
                    System.out.println(Thread.currentThread().getName() + "卖票---->" + num--);
                    signslten2.setNum(num);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                break;
            }
        }
        System.out.println(time2-System.currentTimeMillis());*/
    }

}
