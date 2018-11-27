package com.yida.boottracer.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController
{

	@GetMapping(value = { "/", "/index", "/index.html" })
	public String Index(HttpServletRequest request)
	{
		return "index";
	}

	@GetMapping("/login")
	public String loginc( HttpServletRequest request)
	{
		return "login";
	}
	@PostMapping("/logincheck")
	public void logincheck(@RequestParam(defaultValue = "") String username,
			@RequestParam(defaultValue = "") String password, HttpServletRequest request)
	{
		try
		{
			System.out.println(username);
			System.out.println(password);
//			Authentication authentication = myAuthenticationManager.authenticate(authRequest); // 调用loadUserByUsername
//			SecurityContextHolder.getContext().setAuthentication(authentication);
//			HttpSession session = request.getSession();
//			session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext()); // 这个非常重要，否则验证后将无法登陆
//			return new LoginInfo().success().msg(authentication.getName());
		}
		catch (AuthenticationException ex)
		{
			//return new LoginInfo().failed().msg("用户名或密码错误");
		}
	}
}
