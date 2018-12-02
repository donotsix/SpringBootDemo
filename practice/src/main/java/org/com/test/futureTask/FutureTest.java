package org.com.test.futureTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FutureTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 20, 100, TimeUnit.SECONDS, new SynchronousQueue<>());
        List<Callable<Object>> list = new ArrayList<>();
        List<Future<Object>> invokeAll = new ArrayList<>();
        Long time = System.currentTimeMillis();
        for (int i = 0; i < 3; i++) {
            Callable<Object> task = new Callable<Object>() {
                @Override
                public String call() throws Exception {
                    System.out.println(Thread.currentThread().getName() + " task start-------------------------------");
                    Thread.sleep(5000);
                    System.out.println(Thread.currentThread().getName() + " task end---------------------------------");
                    return "task1" + Thread.currentThread().getName();
                }
            };
            invokeAll.add(executor.submit(task));
            // completionService.submit(task);
            // list.add(task);
        }
        Callable<Object> task2 = new Callable<Object>() {
            @Override
            public String call() throws Exception {
                System.out.println(Thread.currentThread().getName() + " task2 start----------------------------");
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " task2 end---------------------------");
                return "task2" + Thread.currentThread().getName();
            }
        };
        invokeAll.add(executor.submit(task2));
        // completionService.submit(task2);
        // list.add(task2);

        for (Future<Object> future : invokeAll) {
            Boolean boolean1 = false;
            while (true) {
                if (future.isDone() && !future.isCancelled()) {
                    System.out.println(future.get().toString());
                    boolean1 = true;
                    break;
                }
            }
            if (boolean1) {
                break;
            }
        }

        /*
         * for (int i = 0; i < 11; i++) { Future<Object> f =
         * completionService.take(); System.out.println(f.get()); }
         */
        Long time2 = System.currentTimeMillis();
        System.out.println(time - time2);
        executor.shutdown();
    }
}
