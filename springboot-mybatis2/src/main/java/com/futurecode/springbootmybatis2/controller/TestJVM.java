package com.futurecode.springbootmybatis2.controller;

/**
 * @author :lvweihao@outlook.com
 * @description: 测试jvm
 * @date :create in 22:37 2018/11/24
 * @modify by :
 */
public class TestJVM {

    public static void main(String[] args) {

        /*String str = System.getProperty("str");
        if (null == str || "".equals(str)) {
            System.out.println("Test");
        } else {
            System.out.println(str);
        }*/

        int i = Integer.MAX_VALUE + 1;
        System.out.println(i);
        int j = (int)123124142141241L;

    }

}
