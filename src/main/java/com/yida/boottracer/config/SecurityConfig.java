package com.yida.boottracer.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;

import com.yida.boottracer.service.auth.SysUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
	DataSource dataSource;
	
	@Bean
	SysUserDetailsService customUserService()
	{
		return new SysUserDetailsService();
	}

	/**
	 * 忽略掉不需要进行安全认证的资源,忽略认证的资源是无法得到当前用户登录的信息的，所以只应该对静态资源进行忽略即可
	 */
    @Override
    public void configure(WebSecurity web) throws Exception
    {
        web.ignoring().antMatchers("/bower_components/**", "/js/**", "/images/**");
    }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(customUserService());
//		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
//		auth.jdbcAuthentication().dataSource(dataSource).withUser("user").password("password").roles("USER");
		super.configure(auth);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{

		Authentication a;
		ProviderManager p;
		//permitAll是允许授权与非授权用户访问，anonymous是只授权匿名账户访问
		
		http
			//管理端需认证			
			.authorizeRequests().antMatchers("/mgn/**").authenticated().and()
			//非管理端所有资源都授权所有用户访问
			.authorizeRequests().anyRequest().permitAll().and()
			//配置login相关
			.formLogin().loginPage("/login").usernameParameter("userName").passwordParameter("password").loginProcessingUrl("/logincheck").failureUrl("/login?error").defaultSuccessUrl("/mgn/index",true).and()
			//配置登出相关
			.logout().permitAll().logoutUrl("/mgn/logout").logoutSuccessUrl("/index").deleteCookies("JSESSIONID").and()			
			//配置csrf
			
			//配置授权失败时相关处理
			.exceptionHandling().accessDeniedPage("/403.html");
		http.csrf().disable();
		http.formLogin();
	}
}
