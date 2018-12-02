package com.yida.boottracer.config;

import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.memory.UserAttribute;
import org.springframework.security.core.userdetails.memory.UserAttributeEditor;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.util.Assert;

//参考org.springframework.security.provisioning.InMemoryUserDetailsManager
public class InMemoryUserDetailsManager implements UserDetailsManager, UserDetailsPasswordService
{
	protected final Log logger = LogFactory.getLog(getClass());

	private final Map<String, UserDetails> users = new HashMap<>();

	private AuthenticationManager authenticationManager;

	public InMemoryUserDetailsManager()
	{
	}

	public InMemoryUserDetailsManager(Collection<UserDetails> users)
	{
		for (UserDetails user : users)
		{
			createUser(user);
		}
	}

	public InMemoryUserDetailsManager(UserDetails... users)
	{
		for (UserDetails user : users)
		{
			createUser(user);
		}
	}

	public InMemoryUserDetailsManager(Properties users)
	{
		Enumeration<?> names = users.propertyNames();
		UserAttributeEditor editor = new UserAttributeEditor();

		while (names.hasMoreElements())
		{
			String name = (String) names.nextElement();
			editor.setAsText(users.getProperty(name));
			UserAttribute attr = (UserAttribute) editor.getValue();
			UserDetails user = new User(name, attr.getPassword(), attr.isEnabled(), true, true, true,
					attr.getAuthorities());
			createUser(user);
		}
	}

	public void createUser(UserDetails user)
	{
		Assert.isTrue(!userExists(user.getUsername()), "user should not exist");
	}

	public void deleteUser(String username)
	{
		users.remove(username.toLowerCase());
	}

	public void updateUser(UserDetails user)
	{
		Assert.isTrue(userExists(user.getUsername()), "user should exist");
	}

	public boolean userExists(String username)
	{
		return users.containsKey(username.toLowerCase());
	}

	public void changePassword(String oldPassword, String newPassword)
	{
		
	}

	@Override
	public UserDetails updatePassword(UserDetails user, String newPassword)
	{
		return user;
	}

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		UserDetails user = users.get(username.toLowerCase());

		if (user == null)
		{
			throw new UsernameNotFoundException(username);
		}

		return user;
	}

	public void setAuthenticationManager(AuthenticationManager authenticationManager)
	{
		this.authenticationManager = authenticationManager;
	}
}
