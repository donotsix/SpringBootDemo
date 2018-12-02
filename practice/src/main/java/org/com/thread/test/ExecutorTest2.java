package org.com.thread.test;

import java.util.concurrent.CountDownLatch;

public class ExecutorTest2 {

	public static void main(String args[]) throws InterruptedException {
		Long time=System.currentTimeMillis();
		CountDownLatch countDownLatch=new CountDownLatch(5);
		for (int i = 0; i < 5; i++) {
			Thread thread = new Thread(new Runnable() {
				public void run() {
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName());
					countDownLatch.countDown();
				}
			});
			thread.start();
			//System.out.println(Thread.currentThread().getName());
			thread.join();
		}
		//countDownLatch.await();
		System.out.println(System.currentTimeMillis()-time);

	}

}