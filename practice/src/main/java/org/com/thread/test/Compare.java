package org.com.thread.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Compare extends Object implements Comparable<Compare>{
    private int age;
    
    public Compare(int age){
        this.age=age;
    }

    public int getAge() {
        return age;
    }


    public void setAge(int age) {
        this.age = age;
    }


    @Override
    public int compareTo(Compare o) {
        // TODO Auto-generated method stub
        return this.age-o.getAge();
    }
    

    @Override
    public String toString() {
        return "Compare [age=" + age + "]";
    }

    public static void main(String[] args) {
        Compare compare2=new Compare(2);
        Compare compare1=new Compare(1);
        int result=compare1.compareTo(compare2);
        System.out.println(result);
        List<Compare> compares=new ArrayList<>();
        compares.add(compare1);
        compares.add(compare2);
        Collections.sort(compares);
        System.out.println(compares);
    }
}

