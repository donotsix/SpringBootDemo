package com.futurecode.gradle_demo_todo;

import java.util.Scanner;

/**
   * @author : lvweihao@outlook.com
   * @description : 
   * @date : create in 23:29 18.5.3
   * @modify by : 
   */
public class App {

    /**
       * @author : lvweihao@outlook.com
       * @description : 
       * @date : create in 23:30 18.5.3
        * @param null
       * @modify by : 
       */
    public static void main(String[] args) {
        int i = 0;
        Scanner scanner = new Scanner(System.in);
        while (++i > 0) {
            System.out.println(i + "please input todo item name");
            TodoItem todoItem = new TodoItem(scanner.nextLine(), false);
            System.out.println(todoItem);
        }
    }

    public void hello() {
        System.out.println("hello");
    }

}
