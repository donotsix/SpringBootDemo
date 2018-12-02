package org.com.thread.test;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long beginTime=System.currentTimeMillis();
        Callable callable1=new Callable1();
        FutureTask<String> task = new FutureTask<>(callable1);
        Thread thread=new Thread(task,"A");
        Callable callable2=new Callable1();
        FutureTask<String> task1 = new FutureTask<>(callable2);
        Thread thread2=new Thread(task1,"B");
        thread.start();
        thread2.start();
        String reString=task.get();
        String reString1=task1.get();
        long endTime=System.currentTimeMillis();
        System.out.println("cast : " + (endTime - beginTime) / 1000 + " second!");
    }

}
class Callable1 implements Callable<String>{

    @Override
    public String call() throws Exception {
        System.out.println("Thread:"+Thread.currentThread().getName());
        Thread.sleep(2000);
        return "abc";
    }
    
}