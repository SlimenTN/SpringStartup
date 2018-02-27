package com.devopsbuddy.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	/** The application logger */
	private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(SecurityConfig.class);
	
	@Autowired
	private Environment env;
	
	private static final String[] PUBLIC_MATCHERS = {
		"/webjars/**",
		"/css/**",
		"/js/**",
		"/images/**",
		"/",
		"/about/**",
		"/contact/**",
		"/error/**/*",
		"/console/**",
	};
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		List<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
		if(activeProfiles.contains("dev")) {
			LOG.info("Secueity", "WE ARE UN DEV ENV");
			http.csrf().disable();
			http.headers().frameOptions().disable();
		}
		
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
