package com.futurecode.springbootfilter.valve.filter;


import javax.servlet.*;
import java.io.IOException;

public class CustomizedFIlter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("======init CustomizedFIlter======");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("======do filter======");
    }

    @Override
    public void destroy() {
        System.out.println("======filter destory======");
    }
}
