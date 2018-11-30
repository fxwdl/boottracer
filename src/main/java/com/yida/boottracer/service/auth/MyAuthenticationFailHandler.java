package com.yida.boottracer.service.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yida.boottracer.dto.SimpleResponse;

@Component
public class MyAuthenticationFailHandler extends SimpleUrlAuthenticationFailureHandler
{
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException e) throws IOException, ServletException
	{
		logger.info("登录失败");
		// String type = "html";
		// if (type.equals("json"))
		// {
		// response.setStatus(500);
		// response.setContentType("application/json;charset=UTF-8");
		// response.getWriter().write(objectMapper.writeValueAsString(e));
		// }
		// else
		// {
		// super.onAuthenticationFailure(request, response, e);
		// }
		//response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.setContentType("application/json;charset=utf-8");
		String msg = "";
		if (e instanceof BadCredentialsException)
		{
			msg = "用户名或密码错误";
		}
		else
		{
			msg = e.getMessage();
		}
		response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(false, msg)));
	}
}
