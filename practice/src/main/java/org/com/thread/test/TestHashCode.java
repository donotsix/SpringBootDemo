package org.com.thread.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestHashCode {

	public static void main(String[] args) {
		String Stra="aaa";
		String Strb="bbb";
		String Strc="aaa";
		System.out.println(Stra==Strc);
		System.out.println(Stra==Strb);
		System.out.println(Stra.equals(Strc));
		System.out.println(Stra.equals(Strb));
		for(int i=0;i<100;i++){
			if(i==10){
				break;
			}
			System.out.println("2");
		}
		System.out.println("3");
		List<Map<String,Object>> list=new ArrayList<>();
		Map<String,Object> map2=new HashMap<>();
		map2.put("gmtModified", 456);
		map2.put("item", "bbb");
		Map<String,Object> map=new HashMap<>();
		map.put("gmtModified", 123);
		map.put("item", "aaa");
		list.add(map2);
		list.add(map);
		System.out.println(list.toString());
		List<Map<String,Object>> listc=list.stream().sorted(Comparator.comparing(TestHashCode::comparingBygmtModified)).collect(Collectors.toList());
		System.out.println(listc.toString());
		List<Map<String,Object>> listd=list.stream().sorted(Comparator.comparing(TestHashCode::comparingBygmtModified).reversed()).collect(Collectors.toList());
		System.out.println(listd.toString());
		List<Bean> listbean=new ArrayList<>();
		Bean bean=new Bean();
		bean.setGmtModified("123");
		bean.setItem("aaa");
		Bean bean1=new Bean();
		bean1.setGmtModified("456");
		bean1.setItem("bbb");
		listbean.add(bean1);
		listbean.add(bean);
		List<Bean> lista=listbean.stream().sorted(Comparator.comparing(Bean::getGmtModified)).collect(Collectors.toList());
		System.out.println(lista.toString());
		List<Bean> listb=listbean.stream().sorted(Comparator.comparing(Bean::getGmtModified).reversed()).collect(Collectors.toList());
		System.out.println(listb.toString());
		
		List<Long> list2=new ArrayList<>();
		list2.add(1L);
		list2.add(2L);
		Collections.sort(list2,Collections.reverseOrder());
		System.out.println(list2.toString());
		Collections.sort(list2);
		System.out.println(list2.toString());
	}
	
	
    private static int comparingBygmtModified(Map<String, Object> map){
        return (int) map.get("gmtModified");
    }

}
class Bean{
	public String gmtModified;
	public String item;
	public String getGmtModified() {
		return gmtModified;
	}
	public void setGmtModified(String gmtModified) {
		this.gmtModified = gmtModified;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	@Override
	public String toString() {
		return "Bean [gmtModified=" + gmtModified + ", item=" + item + "]";
	}
	
}
