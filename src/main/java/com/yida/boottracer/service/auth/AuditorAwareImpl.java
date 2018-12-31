package com.yida.boottracer.service.auth;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.yida.boottracer.domain.SysUser;

/**
 * 审计Aware类，负责返回当前的用户名 https://www.baeldung.com/database-auditing-jpa
 * 
 * @author fx__w
 *
 */
public class AuditorAwareImpl implements AuditorAware<String>
{

	@Override
	public Optional<String> getCurrentAuditor()
	{
		SecurityContext ctx = SecurityContextHolder.getContext();
		if (ctx == null)
		{
			return null;
		}
		if (ctx.getAuthentication() == null)
		{
			return null;
		}
		if (ctx.getAuthentication().getPrincipal() == null)
		{
			return null;
		}
		Object principal = ctx.getAuthentication().getPrincipal();
		if (principal instanceof SysUser)
		{
			String name = ((SysUser) principal).getUserName();
			return Optional.ofNullable(name).filter(s -> !s.isEmpty());
		}
		else
		{
			return null;
		}
	}

}
