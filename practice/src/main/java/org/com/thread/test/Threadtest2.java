
package org.com.thread.test;

public class Threadtest2 {
    public static void main(String[] args) {
        Task task=new Task();
        Thread t1=new Thread(task,"A");
        t1.start();
    }
}

class Task implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }

}
