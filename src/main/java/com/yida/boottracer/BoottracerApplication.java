package com.yida.boottracer;

import java.util.UUID;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.yida.boottracer.domain.SysRole;
import com.yida.boottracer.domain.SysUser;
import com.yida.boottracer.repo.SysRoleRepository;
import com.yida.boottracer.repo.SysUserRepository;

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
	
	/*
	@Autowired
	private SysUserRepository sysUserRepository;
	@Autowired
	private SysRoleRepository sysRoleRepository;
	
	@Bean
	public CommandLineRunner demoData() {
		return (args)->{
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			SysUser admin;
			if (sysUserRepository.findByUserName("admin")==null)
			{
				admin=new SysUser();
				admin.setUserId(UUID.randomUUID().toString());
				admin.setUserName("admin");
				admin.setUserNameCn("管理员");
				admin.setPassword("{bcrypt}" + encoder.encode("1"));
				admin.setIsLockedOut(false);			
				
				sysUserRepository.save(admin);
			}
		};
	}
	*/
}
