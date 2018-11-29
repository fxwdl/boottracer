package com.yida.boottracer.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.yida.boottracer.properties.SecurityProperties;
import com.yida.boottracer.service.auth.ImageCodeGenerator;
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
}
