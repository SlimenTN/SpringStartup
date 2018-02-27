package com.devopsbuddy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String[] PUBLIC_MATCHERS = {
		"/webjars/**",
		"/css/**",
		"/js/**",
		"/images/**",
		"/",
		"/about/**",
		"/contact/**",
		"/error/**/*",
	};
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()// authorize all http requests
			.antMatchers(PUBLIC_MATCHERS).permitAll()// don't require authentication for the declared URLs
			.anyRequest().authenticated()// Authenticate everything else
			.and()
			.formLogin().loginPage("/login")// use our form login for authentication
			.defaultSuccessUrl("/payload")// After successful authentication redirect user to the payload page
			.failureUrl("/login?error").permitAll()// for any login failure redirect to login URL with error GET param
			.and()
			.logout().permitAll()// allow anyone to logout with asking for authentication
			// by default Spring handle the logout request for us and redirect us to login page with "logout" as param
		;
	}
	
	/**
	 * We added the annotation {@link Autowired} here so that Spring inject an instance of the Bean {@link AuthenticationManagerBuilder} in our function
	 * @param auth
	 * @throws Exception
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		auth
			.inMemoryAuthentication()
			.withUser("user").password("password")
			.roles("USER")
		;
	}
}
