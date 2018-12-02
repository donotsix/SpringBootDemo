package org.com.test.treemapTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class TreeMapTest {
    public static void main(String[] args) {
        TreeMap<String, Integer> map1 = new TreeMap<>();
        map1.put("zzz", 2);
        map1.put("ddd", 1);
        map1.put("aaa", 3);
        System.out.println(map1.toString());
        TreeMap<String, Integer> map = new TreeMap<>(new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        map.put("zzz", 2);
        map.put("ddd", 1);
        map.put("aaa", 3);
        System.out.println(map.toString());
        TreeMap<String, Integer> map2 = new TreeMap<>(new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        map2.put("zzz", 2);
        map2.put("ddd", 1);
        map2.put("aaa", 3);
        System.out.println(map2.toString());
        Set<Entry<String, Integer>> entrySet = map2.entrySet();
        List<Entry<String, Integer>> list = new ArrayList<>(entrySet);

        Comparator comparator = new Comparator<Entry<String, Integer>>() {

            @Override
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        };
        Collections.sort(list, comparator);
        System.out.println(list);
    }
}
