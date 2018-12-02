package org.com.test.futureTask;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class ListFutureTest {

    volatile static String result;
    
    static Function<String, String> function = (x) -> {
        return "abc";
    };
    
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 20, 100, TimeUnit.SECONDS, new SynchronousQueue<>());
        CompletionService<Object> completionService = new ExecutorCompletionService<Object>(executor);
        Long time = System.currentTimeMillis();
        for (int i = 0; i < 3; i++) {
            int a = i;
            Callable<Object> task = new Callable<Object>() {
                @Override
                public String call() throws Exception {
                    System.out.println(
                            Thread.currentThread().getName() + " task" + a + " start-------------------------------");
                    Thread.sleep(5000);
                    System.out.println(
                            Thread.currentThread().getName() + " task" + a + " end---------------------------------");
                    return " task" + a + Thread.currentThread().getName();
                }
            };
            completionService.submit(task);
        }
        Callable<Object> task = new Callable<Object>() {
            @Override
            public String call() throws Exception {
                System.out.println(Thread.currentThread().getName() + " task start----------------------------");
                Thread.sleep(1000);
                result = "tag";
                System.out.println(Thread.currentThread().getName() + " task end---------------------------");
                return "task" + Thread.currentThread().getName();
            }
        };
        completionService.submit(task);
        Callable<Object> callable = new Callable<Object>() {

            @Override
            public Object call() throws Exception {
                System.out.println(Thread.currentThread().getName()
                        + " start task_children waiting task end----------------------------");
                while (true) {
                    if (result != null) {
                        System.out.println(
                                Thread.currentThread().getName() + " task_children doing---------------------------");
                        break;
                    }
                }
                System.out.println(Thread.currentThread().getName() + " task_children end---------------------------");
                return null;
            }
        };
        completionService.submit(callable);
        while (true) {
            try {
                Future<Object> f = completionService.take();
                System.out.println(f.get());
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                break;
            }
        }
        Long time2 = System.currentTimeMillis();
        System.out.println("总时间:" + (time - time2));
        Callable<String> callable2 = () -> {
            return "avc";
        };
        Future<String> submit = executor.submit(callable2);
        System.out.println(submit.get());
        executor.shutdown();

        System.out.println(test());
        LaInterface interface1=()->{System.out.println("tst");};
        interface1.testLa();
        
    }
    
    static String test(){
        return function.apply("a");
    }
    
}
