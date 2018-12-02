package org.com.thread.test;

public class ParamsTest {
    public static void main(String[] args) {
        int a = 0;
        {
            {
                System.out.println(a);
            }
        }

    }
}
