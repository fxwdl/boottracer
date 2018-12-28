package com.yida.boottracer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

import com.yida.boottracer.service.auth.CustomPermissionEvaluator;

/**
 * 实现自定义方法级权限，目前还没有验证这是不是标准方法，似乎太简单了
 * https://www.jianshu.com/p/acad97bcaea6
 * @author fx__w
 *
 
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration
{
	private CustomPermissionEvaluator customPermissionEvaluator;

	@Autowired
	public void setCustomPermissionEvaluator(CustomPermissionEvaluator customPermissionEvaluator)
	{
		this.customPermissionEvaluator = customPermissionEvaluator;
	}

	@Override
	protected MethodSecurityExpressionHandler createExpressionHandler()
	{
		DefaultMethodSecurityExpressionHandler handler = new DefaultMethodSecurityExpressionHandler();
		handler.setPermissionEvaluator(customPermissionEvaluator);
		//return expressionHandler;
		//DefaultMethodSecurityExpressionHandler handler= (DefaultMethodSecurityExpressionHandler) super.createExpressionHandler();
		return handler;
	}
}
*/
