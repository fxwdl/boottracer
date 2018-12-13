package com.yida.boottracer.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer
{
	@Override
	public void addViewControllers(ViewControllerRegistry registry)
	{
//		ViewControllerRegistration login = registry.addViewController("/login");
//		login.setViewName("login");
	}
	
}
