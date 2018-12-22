package com.yida.boottracer.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.context.WebApplicationContext;

import com.cosium.spring.data.jpa.entity.graph.repository.support.EntityGraphJpaRepositoryFactoryBean;
import com.yida.boottracer.properties.SecurityProperties;
import com.yida.boottracer.service.auth.ImageCodeGenerator;
import com.yida.boottracer.service.auth.SysUserDetailsService;
import com.yida.boottracer.service.auth.ValidateCodeGenerator;

@Configuration

public class BeanConfig
{
	@Bean()
	
	public SecurityProperties createSecurityProperties()
	{
		return new SecurityProperties();
	}

	@Bean
	@ConditionalOnMissingBean(name = "imageCodeGenerator") 
	public ValidateCodeGenerator imageCodeGenerator()
	{
		ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
		return codeGenerator;
	}
	
	//加入userDetailsService bean
	@Bean
	//@Scope(scopeName=WebApplicationContext.SCOPE_REQUEST)
	public UserDetailsService userDetailsService()
	{
		return new SysUserDetailsService();
	}
	
	/**加入密码encoder bean
	参考文章 https://www.cnkirito.moe/spring-security-6/
	加密码的密码前面要包含加密方式的前缀
	{bcrypt}$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG 
	{noop}password 
	{pbkdf2}5d923b44a6d129f3ddf3e3c8d29412723dcbde72445e8ef6bf3b508fbf17fa4ed4d6b99ca763d8dc 
	{scrypt}$e0801$8bWJaSu2IKSn9Z9kM+TPXfOc/9bdYSrN1oD9qfVThWEwdRTnO7re7Ei+fUZRJ68k9lTyuTeUp4of4g24hHnazw==$OAOec05+bXxvuu/1qZ6NUR+xQYvYv7BeL1QxwRpY5Pc=  
	{sha256}97cde38028ad898ebc02e690819fa220e88c62e0699403e94fff291cfffaf8410849f27605abcbc0
	*/
	@Bean
	@Scope(scopeName=BeanDefinition.SCOPE_SINGLETON)
	public PasswordEncoder passwordEncoder(){
	    //return new BCryptPasswordEncoder();
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
}
