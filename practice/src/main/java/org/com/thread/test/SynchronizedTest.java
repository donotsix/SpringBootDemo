package org.com.thread.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SynchronizedTest {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(10);
        byte[] lock = new byte[0];
        int count = 0;
        for (int i = 0; i < 10; i++) {
            CallableTask runnable = new CallableTask();
            runnable.setLock(lock);
            runnable.setCountDownLatch(countDownLatch);
            Future<Integer> future = executorService.submit(runnable);
            try {
                System.out.println(count += future.get());
            } catch (ExecutionException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        countDownLatch.await();
        System.out.println(count);
        executorService.shutdown();
    }
}
