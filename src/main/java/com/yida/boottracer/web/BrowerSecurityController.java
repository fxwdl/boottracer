package com.yida.boottracer.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.yida.boottracer.dto.SimpleResponse;

/**
 * 响应状态码 UNAUTHORIZED(401, "Unauthorized")
 */

@Controller
public class BrowerSecurityController
{
	/** * 日志 */
	private Logger logger = LoggerFactory.getLogger(getClass());

	/** * 重定向 策略 */
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	/**
	 * 把当前的请求缓存到 session 里去
	 */
	private RequestCache requestCache = new HttpSessionRequestCache();

	
	@GetMapping("/login.html")
	public String login()
	{
		return "login";
	}
	
	//是不是只有对已登录但未权限不足的才会跳转到这里进行处理？
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	public SimpleResponse Handler(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		// 拿到请求对象
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		if (savedRequest != null)
		{
			// 获取 跳转url
			String targetUrl = savedRequest.getRedirectUrl();
			logger.info("引发跳转的请求是:" + targetUrl); 
			// 判断 targetUrl 是不是 .html 结尾, 如果是：跳转到登录页(返回view)
			if (StringUtils.endsWithIgnoreCase(targetUrl, ".html"))
			{
				String redirectUrl = "/login.html";
				redirectStrategy.sendRedirect(request, response, redirectUrl);
			}
		}

		return new SimpleResponse("问的服务需要身份认证，请引导用户到登录页");
	}
}
