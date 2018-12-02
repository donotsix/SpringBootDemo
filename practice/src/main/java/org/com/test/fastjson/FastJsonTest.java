package org.com.test.fastjson;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class FastJsonTest {
    public static void main(String[] args) {
        Map<String, String> hashMap=new HashMap<>();
        hashMap.put("A", "1_2");
        hashMap.put("B", "1_3");
        System.out.println(hashMap.size());
        System.out.println(JSONObject.toJSONString(hashMap));
    }
}
