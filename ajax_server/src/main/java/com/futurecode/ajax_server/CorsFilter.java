package com.futurecode.ajax_server;


import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author :lvweihao@outlook.com
 * @description:
 * @date :create in 23:50 18.5.21
 * @modify by :
 */
public class CorsFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 允许http://localhost:8070跨域
        // response.addHeader("Access-Control-Allow-Origin" , "http://localhost:8070");
        // 允许所有域跨域
        response.addHeader("Access-Control-Allow-Origin" , "*");
        // 允许get方法跨域
        // response.addHeader("Access-Control-Allow-Method" , "GET");
        // 允许所有方法跨域
        response.addHeader("Access-Control-Allow-Method" , "*");

        filterChain.doFilter(servletRequest , response);
    }

    @Override
    public void destroy() {

    }
}
