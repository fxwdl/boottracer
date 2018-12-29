package com.yida.boottracer.service.auth;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import com.yida.boottracer.service.UserService;

/**
 * 实现RBAC服务，支持URL级别的权限控制
 * 但是还得考虑实现方法级，因为页面上展现内容的控制有时候需要方法级，或者叫权限id一类的，应该需要使用PermissionEvaluator
 * https://www.jianshu.com/p/ca00d0ce75af
 * @author fx__w
 *
 */
@Component("rbacService")
public class RbacServiceImpl implements RbacService
{
	@Autowired
	private UserService userService;

	@Value("${server.servlet.context-path}")
	public static String contextPath;
	
	@Value("${my.debug:true}")
	private boolean debug;

	private AntPathMatcher antPathMatcher = new AntPathMatcher();

	@Override
	public boolean hasPermission(HttpServletRequest request, Authentication authentication)
	{	
		Object principal = authentication.getPrincipal();
		boolean hasPermission = false;
		if (principal instanceof UserDetails)
		{ 
			if (debug)
			{
				return true;
			}
			
			// 首先判断先当前用户是否是我们UserDetails对象。
			String userName = ((UserDetails) principal).getUsername();

			List<String> urls = userService.GetAllUrlFunction(userName);

			// 注意这里不能用equal来判断，因为有些URL是有参数的，所以要用AntPathMatcher来比较

			for (String url : urls)
			{
				String requestUrl = request.getRequestURI();
				url = contextPath + "/" + url;
				//将首页排除在外
				if (requestUrl.equals(contextPath) || requestUrl.equals(contextPath + "/#"))
				{
					hasPermission = true;
					break;
				}
				if (antPathMatcher.match(url, requestUrl))
				{
					hasPermission = true;
					break;
				}
			}
		}
		return hasPermission;
	}

}
