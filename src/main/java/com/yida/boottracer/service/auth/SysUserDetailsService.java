package com.yida.boottracer.service.auth;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.yida.boottracer.domain.SysRole;
import com.yida.boottracer.domain.SysUser;
import com.yida.boottracer.repo.SysUserRepository;

public class SysUserDetailsService implements UserDetailsService
{
	@Autowired
	SysUserRepository sysUserRepository;
	private String rolePrefix = "";

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		SysUser user = sysUserRepository.findByUserName(username);
		Set<GrantedAuthority> dbAuthsSet = new HashSet<GrantedAuthority>();
		if (user != null)
		{

			for (SysRole r : user.getRoles())
			{
				String roleName = this.rolePrefix + r.getName();
				dbAuthsSet.add(new SimpleGrantedAuthority(roleName));
			}
			user.setAuthorities(dbAuthsSet);
		}
		else
		{
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String salt=BCrypt.gensalt();
			user = new SysUser();
			user.setUserName("user");
			user.setPassword("{bcrypt}" + encoder.encode("pass"));
			user.setIsLockedOut(false);

			dbAuthsSet.add(new SimpleGrantedAuthority("admin"));
			user.setAuthorities(dbAuthsSet);
		}
		return user;
	}

	public void setRolePrefix(String rolePrefix)
	{
		this.rolePrefix = rolePrefix;
	}

	protected String getRolePrefix()
	{
		return this.rolePrefix;
	}
}
