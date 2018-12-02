package org.com.thread.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.alibaba.fastjson.JSON;

public class SerializableTest {
    public static void main(String[] args) throws Exception {  
        // 序列化User对象  
        SerializeUser();  
    }  
  
    /** 
     * 序列化User对象 
     */  
    private static void SerializeUser() throws Exception {  
        // 创建一个小明  
        Person user = new Person("小明",1);  
        String string=JSON.toJSONString(user);
        System.out.println(string);
        Person person=JSON.parseObject(string,Person.class);
        System.out.println(person);
    }  
  

}
