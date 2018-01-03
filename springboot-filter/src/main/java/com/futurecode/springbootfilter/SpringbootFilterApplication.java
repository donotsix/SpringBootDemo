package com.futurecode.springbootfilter;

import com.futurecode.springbootfilter.valve.filter.CustomizedFIlter;
import com.futurecode.springbootfilter.valve.listener.CustomizedListener;
import com.futurecode.springbootfilter.valve.servlet.CustomizedServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.*;
import org.springframework.context.annotation.Bean;
import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.EnumSet;

@ServletComponentScan
@SpringBootApplication
public class SpringbootFilterApplication {
	/*implements ServletContextInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws
			ServletException {
		servletContext.addServlet("customizedServlet", new CustomizedServlet()).addMapping("/servlet");
		servletContext.addFilter("customizedFIlter", new CustomizedFIlter()).addMappingForServletNames(EnumSet.of(DispatcherType.REQUEST), true,"customizedServlet");
		servletContext.addListener(new CustomizedListener());
	}*/

	public static void main(String[] args) {
		SpringApplication.run(SpringbootFilterApplication.class, args);
	}

	/*@Bean
	public ServletRegistrationBean servletRegistrationBean() {
		return new ServletRegistrationBean(new CustomizedServlet(), "/servlet");
	}
	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		return new FilterRegistrationBean(new CustomizedFIlter());
	}
	@Bean
	public ServletListenerRegistrationBean<CustomizedListener> servletListenerRegistrationBean() {
		return new ServletListenerRegistrationBean<CustomizedListener>(new CustomizedListener());
	}*/

}
