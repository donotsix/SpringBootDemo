package org.com.test.blockQueue;

import java.util.LinkedList;

public class BlockQueue {

    int max = 5;

    LinkedList<String> linkedList = new LinkedList<>();

    public synchronized void get() {
        //linkedList有元素就取出
        while (linkedList.size() != 0) {
            System.out.println("消费"+linkedList.poll());
        }
        //linkedList没元素就让出CPU
        try {
            System.out.println("blocking!!!");
            wait(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void put(String str) {
        //linkedList没满就加入队列
        while (linkedList.size() <= max) {
            System.out.println("生产："+str);
            linkedList.offer(str);
            break;
        }
        //linkedList满了让出cpu
        try {
            wait(10);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
