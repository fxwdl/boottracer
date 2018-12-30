package com.yida.boottracer.web.mgn;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.yida.boottracer.domain.DictSystemFunction;
import com.yida.boottracer.domain.SysUser;
import com.yida.boottracer.dto.SimpleResponse;
import com.yida.boottracer.enums.SystemFunctionType;
import com.yida.boottracer.service.UserService;
import com.yida.enums.FunctionEnum;

public abstract class BaseController
{
	@Autowired
	private UserService userService;

	// @ModelAttribute先于RequestMapping来构造Model
	@ModelAttribute
	public void initSystemMenu(Model model)
	{
		List<DictSystemFunction> all = userService.getSystemMenu(getUser().getUsername());
		List<DictSystemFunction> menu = new ArrayList<DictSystemFunction>();
		all.forEach(item ->
		{
			if (item.getFunType() == SystemFunctionType.Module.ordinal())
			{
				menu.add(item);
			}
		});
		
		model.addAttribute("menu", menu);
	}

	@ModelAttribute
	public void initUser(Model model)
	{
		SysUser u = this.getUser();
		if (!model.containsAttribute("user") && u != null)
		{
			model.addAttribute("user", this.getUser());
		}
	}

	protected Authentication getAuthentication()
	{
		return SecurityContextHolder.getContext().getAuthentication();
	}

	protected SysUser getUser()
	{
		SysUser result = null;
		Authentication au = getAuthentication();

		if (au != null)
		{
			if (au.getPrincipal() instanceof SysUser) // 不清楚为什么AbstractUserDetailsAuthenticationProvider的实现，并不是将userDetails放到Details上，而是放到了Principal上
			{
				result = (SysUser) au.getPrincipal();
			}
		}

		return result;
	}
	/*
	 * protected User getCurrentUser() { User u = (User)
	 * getHttpRequest().getSession(true).getAttribute("user"); return u; }
	 */

	protected HttpServletRequest getHttpRequest()
	{
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	// @RequestMapping("/mgn/error.html")
	// public String handleError(HttpServletRequest request) {
	// return "/mgn/error";
	// }

	// 默认的异常处理，跳转到错误页
	// @ExceptionHandler(RuntimeException.class)
	// protected String defaultErrorHandler(HttpServletRequest request, Exception
	// ex) throws Exception, IOException
	// {
	// request.setAttribute("msg",ex.getMessage());
	// return "redirect:/mgn/error.html";
	// }
	// 上面跳转的方式需要写2个方法，这个一个就可以的。但是有一个问题是@ModelAttribute这个会不生效
	// @ExceptionHandler(RuntimeException.class)
	// public ModelAndView defaultErrorHandler(HttpServletRequest
	// request,HttpServletResponse response,HttpSession session,Principal principal,
	// Exception ex) throws Exception, IOException
	// {
	// ModelAndView v=new ModelAndView();
	// v.addObject("msg", ex.getMessage());
	// v.addObject("exception",ex);
	// v.setViewName("mgn/error");
	// return v;
	// }

	@ExceptionHandler(AccessDeniedException.class)
	@ResponseBody
	public Object accessDeniedErrorHandler(HttpServletRequest request, HttpServletResponse response, Exception ex)
			throws Exception, IOException
	{
		return errorHandler(request, response, ex, "mgn/403");
	}

	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public Object defaultErrorHandler(HttpServletRequest request, HttpServletResponse response, Exception ex)
			throws Exception, IOException
	{
		return errorHandler(request, response, ex, "mgn/error");
	}

	protected Object errorHandler(HttpServletRequest request, HttpServletResponse response, Exception ex,
			String redirect)
	{
		String url = request.getRequestURI();
		if (StringUtils.endsWithIgnoreCase(url, "html"))
		{
			ModelAndView v = new ModelAndView();
			v.addObject("msg", ex.getMessage());
			v.addObject("exception", ex);
			v.setViewName(redirect);
			return v;
		}
		else
		{
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}
}
