package com.yida.boottracer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
// @EnableAutoConfiguration
// @EnableJpaRepositories(basePackages = { "com.yida.boottracer.dao" })
// @EntityScan(basePackages = { "com.yida.boottracer.domain" })
// @ComponentScan(basePackages = { "com.yida.boottracer.service" })
// @EnableTransactionManagement

// 用来集成mybatis
// @MapperScan("")
@EnableJpaAuditing

public class BoottracerApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(BoottracerApplication.class, args);
	}
}
