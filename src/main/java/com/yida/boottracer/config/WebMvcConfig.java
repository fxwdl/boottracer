package com.yida.boottracer.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer
{
	@Override
	public void addViewControllers(ViewControllerRegistry registry)
	{
		// ViewControllerRegistration login = registry.addViewController("/login");
		// login.setViewName("login");
	}

	/**
	 * 配置消息转换，加上JSON的Hibernate5Module，可以实现对空的属性（包括Lazy loading的）不进行序列化
	 */
	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters)
	{
		for (HttpMessageConverter converter : converters)
		{
			if (converter instanceof org.springframework.http.converter.json.MappingJackson2HttpMessageConverter)
			{
				ObjectMapper mapper = ((MappingJackson2HttpMessageConverter) converter).getObjectMapper();
				mapper.registerModule(new Hibernate5Module());
				// replace Hibernate4Module() with the proper class for your hibernate version.
			}
		}
	}
}
