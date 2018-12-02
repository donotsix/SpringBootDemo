package org.com.thread.test1;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Threadtest{

    public static void main(String[] args) {
        Runnable runnable=new Runnable() {
            public void run() {
                try {
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        };
        //核心数初始化为3，线程池数量0，任务队列0
        ThreadPoolExecutor poolExecutor=new ThreadPoolExecutor(3, 5, 100, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(3));
        //小于线程核心数，创建新线程运行，核心线程：3 线程池数量：0，队列：0
        System.out.println("------------------------------------");
        System.out.println("核心线程数："+poolExecutor.getCorePoolSize());
        System.out.println("线程池数量："+poolExecutor.getPoolSize());
        System.out.println("队列任务数："+poolExecutor.getQueue().size());
        poolExecutor.execute(runnable);
        poolExecutor.execute(runnable);
        poolExecutor.execute(runnable);
        //大于线程核心数，创建新线程进入队列，核心线程：3 线程池数量：3，队列：0
        System.out.println("------------------------------------");
        System.out.println("核心线程数："+poolExecutor.getCorePoolSize());
        System.out.println("线程池数量："+poolExecutor.getPoolSize());
        System.out.println("队列任务数："+poolExecutor.getQueue().size());
        poolExecutor.execute(runnable);
        poolExecutor.execute(runnable);
        poolExecutor.execute(runnable);
        System.out.println("------------------------------------");
        System.out.println("核心线程数："+poolExecutor.getCorePoolSize());
        System.out.println("线程池数量："+poolExecutor.getPoolSize());
        System.out.println("队列任务数："+poolExecutor.getQueue().size());
        poolExecutor.execute(runnable);
        poolExecutor.execute(runnable);
        System.out.println("------------------------------------");
        System.out.println("核心线程数："+poolExecutor.getCorePoolSize());
        System.out.println("线程池数量："+poolExecutor.getPoolSize());
        System.out.println("队列任务数："+poolExecutor.getQueue().size());
    }
}
