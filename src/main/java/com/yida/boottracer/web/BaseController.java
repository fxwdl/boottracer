package com.yida.boottracer.web;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.weaver.ast.Var;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.yida.boottracer.domain.SysUser;


@ControllerAdvice()
public class BaseController
{
	@ModelAttribute
	public void initSystemMenu(Model model)
	{
//			List<SystemMenuModel> m = new ArrayList<SystemMenuModel>();
//			m = this.getAccountService().GetSystemMenu(u.getUsername());
//			model.addAttribute("menu", m);
		SysUser u=this.getUser();
		if (!model.containsAttribute("user") && u!=null)
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

		if (au!=null)
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
}
