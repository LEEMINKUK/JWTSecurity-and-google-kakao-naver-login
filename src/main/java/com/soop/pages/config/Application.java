package com.soop.pages.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = "com.soop")
@MapperScan(basePackages = "com.soop", annotationClass = Mapper.class)
@EnableScheduling // 모집완료 자동 변경을 위한 스케쥴링 활성화 어노테이션
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
