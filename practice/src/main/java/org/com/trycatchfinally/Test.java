package org.com.trycatchfinally;

public class Test {
    public static void main(String[] args) {
        Test test=new Test();
        System.out.println(test.test());
        System.out.println(test.test2());
    }
    
    public String test(){
        String result="";
        try {
            result ="try";
            return result;
        } catch (Exception e) {
            int a=1/0;
            result="catch";
            return result;
        } finally {
            int a=1/0;
            result="finally";
        }
    }
    
    public int test2(){
        int result=0;
        try {
            result =1;
            return result;
        } catch (Exception e) {
            result=2;
            return result;
        } finally {
            result=3;
        }
    }
}
