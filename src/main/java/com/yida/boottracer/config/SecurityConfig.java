package com.yida.boottracer.config;

import javax.sql.DataSource;
import javax.swing.text.html.HTML;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.context.WebApplicationContext;

import com.yida.boottracer.service.auth.MyAuthenticationFailHandler;
import com.yida.boottracer.service.auth.MyAuthenticationSuccessHandler;
import com.yida.boottracer.service.auth.SysUserDetailsService;
import com.yida.boottracer.service.auth.ValidateCodeFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,proxyTargetClass = true)  
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
	DataSource dataSource;
	
	@Autowired
    private MyAuthenticationSuccessHandler mySuccessHandler;
	
	@Autowired
    private MyAuthenticationFailHandler myFailHandler;
	
    @Autowired
    private ValidateCodeFilter validateCodeFilter;
    

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
		//如果未声明UserDetailsService、PasswordEncoder bean的话，也可以这样加入，但二者不可以同时，会产生2次验证!?
		//auth.userDetailsService(createUserDetailsService()).passwordEncoder(new BCryptPasswordEncoder());
		
		//增加自定义的AuthenticationProvider
		//auth.authenticationProvider(authenticationProvider);
//		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
//		auth.jdbcAuthentication().dataSource(dataSource).withUser("user").password("password").roles("USER");
		super.configure(auth);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{

		//permitAll是允许授权与非授权用户访问，anonymous是只授权匿名账户访问
		http.addFilterBefore(this.validateCodeFilter, UsernamePasswordAuthenticationFilter.class);
		http
			//管理端需认证			
			.authorizeRequests().antMatchers("/mgn/**").authenticated().and()			
			//非管理端所有资源都授权所有用户访问
			.authorizeRequests().anyRequest().permitAll().and()
			//配置login相关
			.formLogin().loginPage("/login.html").usernameParameter("username")
				.passwordParameter("password").loginProcessingUrl("/logincheck").failureUrl("/login?error")
				.defaultSuccessUrl("/mgn/index",true).successHandler(mySuccessHandler).failureHandler(myFailHandler)
				.and()
			//配置登出相关
			.logout().logoutUrl("/mgn/logout").permitAll().logoutSuccessUrl("/login.html").deleteCookies("JSESSIONID").and()			
			//配置csrf
			
			//配置授权失败时相关处理
			.exceptionHandling().accessDeniedPage("/mgn/403.html");
		http.csrf().disable();
		http.formLogin();
		
	}
}
