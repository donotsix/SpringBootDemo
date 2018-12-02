package org.com.thread.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public class TestCompare {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("b");
        list.add("c");
        list.add("a");
        System.out.println(list.toString());
        String[] strArr={"1","4","3"};
        String s1="ss";
        List<String> asList =new ArrayList<>(Arrays.asList(s1));
        asList.add("bb");
        System.out.println(asList);
        List<String> list2=new ArrayList<>(list);
        System.out.println(list2.toString());
        LinkedList<String> list3=new LinkedList<>(list);
        Collection<String> collection = new ArrayList<>();
        collection.add("1");
        collection.add("2");
        Predicate<String> predicate = (s) -> s.equals("1");
        collection.removeIf(predicate);
        System.out.println(collection.toString());
    }
}
