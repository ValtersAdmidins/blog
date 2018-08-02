package com.valters.blog.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.valters.blog.controller.HomeController;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.
			authorizeRequests()
				.antMatchers("/", "/posts/**").permitAll()
				.antMatchers("/admin/**", "/h2-console/**").hasAuthority("ADMIN").anyRequest()
				.authenticated().and().csrf().disable().formLogin()
				.loginPage("/").failureUrl("/?error=true")
				.defaultSuccessUrl("/")
				.and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/").and().exceptionHandling()
				.accessDeniedPage("/access-denied");
		
		http.headers().frameOptions().disable();
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder builder)
	          throws Exception {
		
		builder.inMemoryAuthentication()
		             .withUser("aaa")
		.password("{noop}123")
		.authorities("ADMIN");
		
		builder.inMemoryAuthentication()
        .withUser("qqq")
        .password("{noop}123")
        .authorities("USER");
	}
	
}
