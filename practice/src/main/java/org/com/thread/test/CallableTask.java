package org.com.thread.test;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public class CallableTask implements Callable<Integer> {

    CountDownLatch countDownLatch;
    private byte[] lock;

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public byte[] getLock() {
        return lock;
    }

    public void setLock(byte[] lock) {
        this.lock = lock;
    }

    @Override
    public Integer call() {
        System.out.println(Thread.currentThread().getName() + "开始");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "结束");
        countDownLatch.countDown();
        return 1;
    }
}
