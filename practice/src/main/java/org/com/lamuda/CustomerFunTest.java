package org.com.lamuda;

import java.util.ArrayList;
import java.util.List;

import org.com.thread.test.Person;

public class CustomerFunTest {
    
    public static void main(String[] args) {
        CustomerFunTest customerFunTest=new CustomerFunTest();
        PredicateImpl impl=new PredicateImpl();
        customerFunTest.test(()->System.out.println("123"));
        System.out.println(impl.test("ac"));
        System.out.println(impl.test(123));
        Runnable runnable=()->{
            System.out.println("abc");
        };
        runnable.run();
        CustomerFun customerFun=()->{
        };
        Person person=new Person();
        person.setName("张三");
        person.setAge(14);
        List<Person> list=new ArrayList<>();
        list.add(person);
        list.stream().filter(p->p.getAge()>10).count();
    }
    
    public String test(CustomerFun customerFun){
        customerFun.fun();
        return "abc";
    }
    
    public class Phone{
        public Integer size;
        private String color;
        public Phone(Integer size,String color) {
            this.size=size;
            this.color=color;
        }
        public Integer getSize() {
            return size;
        }
        public String getColor() {
            return color;
        }
        
    }
}
