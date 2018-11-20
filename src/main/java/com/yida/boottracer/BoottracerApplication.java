package com.yida.boottracer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @EnableAutoConfiguration
// @EnableJpaRepositories(basePackages = { "com.yida.boottracer.domain" })
// @EntityScan(basePackages = { "com.yida.boottracer.domain" })
// @ComponentScan(basePackages = { "com.yida.boottracer.service" })
// @EnableTransactionManagement

// 用来集成mybatis
// @MapperScan("")
public class BoottracerApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(BoottracerApplication.class, args);
	}
}
