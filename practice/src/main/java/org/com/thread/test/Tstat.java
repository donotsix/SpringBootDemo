package org.com.thread.test;

public class Tstat {
    public int a = 500;

    public static void main(String[] args) {
        Tstat tstat = new Tstat();
        Tstat tstat1 = new Tstat();
        new Thread(new Runnable() {

            @Override
            public void run() {
                while (tstat.a > 0) {
                    System.out.println("a:" + tstat.a--);
                }
            }
        }).start();
        new Thread(new Runnable() {

            @Override
            public void run() {
                while (tstat1.a > 0) {
                    System.out.println("b:" + tstat1.a--);
                }

            }
        }).start();
    }
}
