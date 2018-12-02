package org.com.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

public class CallableTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ThreadPoolExecutor poolExecutor=new ThreadPoolExecutor(3, 5, 1000, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName()+"执行");
                return "abc";
            }
        };
        long time=System.currentTimeMillis();
        System.out.println(time);
        List<Future<String>> list=new ArrayList<>();
        for(int i=0;i<10;i++){
            list.add(poolExecutor.submit(callable));
        }
        for(Future<String> future:list){
            while(true){
                if (future.isDone()&&!future.isCancelled()) {
                    future.get(); 
                    break;
                }else {
                    Thread.sleep(1);
                }
            }
        }
        long time1=System.currentTimeMillis();
        System.out.println(time1-time);
        List<Future<String>> list1=new ArrayList<>();
        for(int i=0;i<10;i++){
            FutureTask<String> futureTask = new FutureTask<>(callable);
            list1.add((Future<String>) poolExecutor.submit(futureTask));
        }
        for(Future<String> future:list1){
            while(true){
                if (future.isDone()&&!future.isCancelled()) {
                    future.get(); 
                    break;
                }else {
                    Thread.sleep(1);
                }
            }
        }
        long time2=System.currentTimeMillis();
        System.out.println(time2-time1);
        CompletableFuture<String> completableFuture=new CompletableFuture<>();
        Predicate<String> predicate=(s)->s.length()>0;
        List<String> list2 =new ArrayList<>();
        list2.add("1");
        list2.add("2");
        list2.add("3");
        poolExecutor.shutdown();
    }
}
