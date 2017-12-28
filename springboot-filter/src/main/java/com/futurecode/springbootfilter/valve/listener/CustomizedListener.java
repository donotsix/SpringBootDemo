package com.futurecode.springbootfilter.valve.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class CustomizedListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("======init CustomizedListener======");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("======destory CustomizedListener======");
    }
}
