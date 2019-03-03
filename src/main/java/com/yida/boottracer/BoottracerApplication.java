package com.yida.boottracer;

import java.util.UUID;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.cosium.spring.data.jpa.entity.graph.repository.support.EntityGraphJpaRepositoryFactoryBean;
import com.yida.boottracer.domain.SysRole;
import com.yida.boottracer.domain.SysUser;
import com.yida.boottracer.repo.SysRoleRepository;
import com.yida.boottracer.repo.SysUserRepository;

@SpringBootApplication
@MapperScan("com.yida.boottracer.repo.impl.mybatis.mapper")
// @EnableAutoConfiguration
// @EnableJpaRepositories(basePackages =
// {"com.yida.boottracer.repo","com.yida.boottracer.domain.test"})
// @EntityScan(basePackages = { "com.yida.boottracer.domain" })
// @ComponentScan(basePackages = { "com.yida.boottracer.service" })
// @EnableTransactionManagement

// 用来集成mybatis
// @MapperScan("")

// 开始审计,auditorProvider申请在自定义的BeanCofnig中
@EnableJpaAuditing(modifyOnCreate=true,auditorAwareRef="auditorProvider")
// 修改默认的JpaRepositoryFactoryBean，实现JPa默认方法增加指定的EntityGraph能力
@EnableJpaRepositories(repositoryFactoryBeanClass = EntityGraphJpaRepositoryFactoryBean.class, basePackages = {"com.yida.boottracer.repo", "com.yida.boottracer.domain.test" })
public class BoottracerApplication
{
	public static void main(String[] args)
	{
		//JpaBaseConfiguration  DD;
		SpringApplication.run(BoottracerApplication.class, args);
	}

	/*
	 * @Autowired private SysUserRepository sysUserRepository;
	 * 
	 * @Autowired private SysRoleRepository sysRoleRepository;
	 * 
	 * @Bean public CommandLineRunner demoData() { return (args)->{
	 * BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); SysUser admin;
	 * if (sysUserRepository.findByUserName("admin")==null) { admin=new SysUser();
	 * admin.setUserId(UUID.randomUUID().toString()); admin.setUserName("admin");
	 * admin.setUserNameCn("管理员"); admin.setPassword("{bcrypt}" +
	 * encoder.encode("1")); admin.setIsLockedOut(false);
	 * 
	 * sysUserRepository.save(admin); } }; }
	 */
}
