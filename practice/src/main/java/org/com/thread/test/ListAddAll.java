package org.com.thread.test;

import java.util.ArrayList;
import java.util.List;

public class ListAddAll {
    public static void main(String[] args) {
        List<String> list=new ArrayList<>();
        List<String> list1=new ArrayList<>();
        list1.add("1");
        List<String> list2=new ArrayList<>();
        list2.add("2");
        List<Integer> list3=new ArrayList<>();
        for(int i=0;i<5;i++){
            list3.add(i);
        }
        List<Integer> list4=new ArrayList<>();
        for(int i=3;i<8;i++){
            list4.add(i);
        }
        List<Integer> list5=new ArrayList<>();
        for(int i=0;i<5;i++){
            list5.add(i);
        }
        List<Integer> set1=new ArrayList<>();
        for(int i=3;i<5;i++){
            list4.add(i);
        }
        List<Integer> set2=new ArrayList<>();
        for(int i=0;i<5;i++){
            list5.add(i);
        }
        //list.addAll(null);
        //list6.addAll(list);
        //list1.addAll(list2);
        //System.out.println(list1.toString());
        //A可以为空
/*        list.addAll(list1);
        System.out.println(list.toString());*/
/*        list1.addAll(list);
        System.out.println(list1.toString());*/
        
        System.out.println("list3:"+list3.toString());
        System.out.println("list4:"+list4.toString());
/*        list3.addAll(list4);
        System.out.println("并集不去重:"+list3.toString());
        list5.removeAll(list4);
        System.out.println("左差集:"+list5.toString());
        list4.removeAll(list6);
        System.out.println("右差集:"+list4.toString());
        */
/*        list3.retainAll(list4);
        System.out.println("并集:"+list3.toString());*/
        list3.removeAll(list5);
        System.out.println("左差集:"+list3.toString());
        set1.removeAll(set2);
        System.out.println("左差集:"+set1.toString());
    }
}
