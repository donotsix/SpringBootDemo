package com.futurecode.springbootmybatis1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.futurecode.springbootmybatis1.mapper")
public class SpringbootMybatis1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMybatis1Application.class, args);
	}
}
