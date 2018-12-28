package com.yida.boottracer.service.auth;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * 自定义hasPermission表达式
 * 还可以实现自定义己的表达式（不限于hasPermission），或者屏蔽掉自带的表达式
 * https://www.baeldung.com/spring-security-create-new-custom-security-expression
 * https://stackoverflow.com/questions/36696393/how-to-implement-custom-spring-security-acl
 *
 * @author fx__w
 *
 */
@Component("permissionEvaluator")
public class CustomPermissionEvaluator implements PermissionEvaluator
{
	private static final Logger log = LoggerFactory.getLogger(CustomPermissionEvaluator.class);

	// 普通的targetDomainObject判断
	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission)
	{
	
		log.error("-------------------------------------");
		return false;
	}

	// 用于ACL的访问控制
	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
			Object permission)
	{
		log.error("-------------------------------------");
		return false;
	}
}