package org.com.test.blockQueue;

public class BlockTest {
    public static void main(String[] args) {
        BlockQueue blockQueue = new BlockQueue();
        //消费者
        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 30; i++) {
                    blockQueue.get();
                }
            }
        }).start();
        //生产者1号
        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 20; i++) {
                    blockQueue.put("生产者A：" + i+"号产品");
                }
            }
        }).start();
        //生产者2号
        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 20; i++) {
                    blockQueue.put("生产者B：" + i+"号产品");
                }
            }
        }).start();
    }

}
