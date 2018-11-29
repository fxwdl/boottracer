package com.yida.boottracer.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

//https://www.jianshu.com/p/18875c2995f1  可以配置相关类，用来读取properties中的内容，有点麻烦，暂时先不弄了
//@ConfigurationProperties(prefix = "my.security")

public class SecurityProperties
{
	@Value("${my.security.login.loginPage}")
	private String loginPage;

	@Value("${my.security.login.loginProcessing}")
	private String loginProcessing;

	@Value("${my.security.login.image.codelength}")
	private int imageCodeLength;

	@Value("${my.security.login.image.width}")
	private int imageWidth;

	@Value("${my.security.login.image.height}")
	private int imageHeight;

	@Value("${my.security.login.image.expireIn}")
	private int imageExpireIn;

	@Value("${my.security.login.image.url}")
	private String imageUrl;

	public int getImageHeight()
	{
		return this.imageHeight;
	}

	public int getImageExpireIn()
	{
		return this.imageExpireIn;
	}

	public String getLoginPage()
	{
		return this.loginPage;
	}

	public int getImageCodeLength()
	{
		return this.imageCodeLength;
	}

	public int getImageWidth()
	{
		return this.imageWidth;
	}

	public String getImageUrl()
	{
		return this.imageUrl;
	}

	public String getLoginProcessing()
	{
		return this.loginProcessing;
	}
}
