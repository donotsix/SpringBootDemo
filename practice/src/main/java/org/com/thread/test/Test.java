package org.com.thread.test;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class Test {
    public final static int count1=0;
    public static void main(String[] args) {
        BeanFactory beanFactory=new XmlBeanFactory(new ClassPathResource("Beans.xml"));
    }
}
