package com.equality.springbootdemo2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@MapperScan({"com.equality.springbootdemo2.mapper.one"}) //mybatis包扫描,同时需要在属性文件中配置好对应的xml文件扫描路径
//@EnableScheduling//定时任务
public class SpringBootDemo2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemo2Application.class, args);
	}
}
